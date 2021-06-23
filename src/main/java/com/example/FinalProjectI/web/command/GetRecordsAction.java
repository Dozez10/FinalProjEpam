package com.example.FinalProjectI.web.command;

import com.example.FinalProjectI.db.*;
import com.example.FinalProjectI.db.entity.Master;
import com.example.FinalProjectI.db.entity.Order;
import com.example.FinalProjectI.db.entity.Service;
import com.example.FinalProjectI.db.entity.User;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.services.SalonServiceMaster;
import com.example.FinalProjectI.services.SalonServiceOrder;
import com.example.FinalProjectI.services.SalonServiceService;
import com.example.FinalProjectI.services.SalonServiceUser;
import com.example.FinalProjectI.web.view.View;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class GetRecordsAction implements Action{
    @Override
    public void execute(View view) throws Exception {

        HttpServletRequest req = view.getRequest();
        int limit = 3;
        double count = 0;
        int tempSize;
        int ordersPerPage = 3;
        int offset;
        int page;
        try{
            page  = Integer.parseInt(req.getParameter("pageNumber"));
        }catch (NumberFormatException nef)
        {
            page=1;
        }
       if(page<=0)page=1;
       offset = (page-1)*ordersPerPage;

        DAOFactory daoFactory = (MySQLDAOFactory) req.getServletContext().getAttribute("factoryDao");
        OrderDao orderDao = daoFactory.getOrderDao();
        SalonServiceOrder salonServiceOrder = new SalonServiceOrder(orderDao);
        UserDao userDao = daoFactory.getUserDao();
        SalonServiceUser salonServiceUser = new SalonServiceUser(userDao);
        MasterDao masterDao = daoFactory.getMasterDao();
        SalonServiceMaster salonServiceMaster = new SalonServiceMaster(masterDao);
        ServiceDao serviceDao = daoFactory.getServiceDao();
        SalonServiceService salonServiceService = new SalonServiceService(serviceDao);
        List<Master> masterList =  new ArrayList<>();
        List<Service> serviceList = new ArrayList<>();
        List<User> users = new ArrayList<>();
        List<Order> orderList = salonServiceOrder.findAllOrdersFromTimeOffsetLimit(LocalDate.now(),false,limit,offset);
        for(Order order: orderList)
        {
            users.add(salonServiceUser.findUser(order.getUserId()));
            masterList.add(salonServiceMaster.findMasterById(order.getMasterId()));
            serviceList.add(salonServiceService.findServiceById(order.getServiceId()));
        }
        tempSize = salonServiceOrder.findAllOrdersFromTime(LocalDate.now(),false).size();
        if(tempSize>0){
            count = tempSize/(double)ordersPerPage;
        }
        if(count>(int)count)count++;
         req.setAttribute("countPages",(int)count);
         req.setAttribute("pageNumberss",page);
         req.setAttribute("listOfOrders",orderList);
         req.setAttribute("userListT",users);
         req.setAttribute("mastersT",masterList);
         req.setAttribute("servicesListTo",serviceList);
         view.setView(req.getPathInfo());





    }
}
