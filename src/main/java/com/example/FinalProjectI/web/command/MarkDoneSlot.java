package com.example.FinalProjectI.web.command;

import com.example.FinalProjectI.db.DAOFactory;
import com.example.FinalProjectI.db.OrderDao;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.services.SalonServiceOrder;
import com.example.FinalProjectI.web.view.View;

import javax.servlet.http.HttpServletRequest;

public class MarkDoneSlot implements Action {
    @Override
    public void execute(View view) throws Exception {
        HttpServletRequest req = view.getRequest();
        DAOFactory daoFactory = (MySQLDAOFactory) req.getServletContext().getAttribute("factoryDao");
        OrderDao orderDao = daoFactory.getOrderDao();
        SalonServiceOrder salonServiceOrder = new SalonServiceOrder(orderDao);
        salonServiceOrder.updateOrderDoneStatus(salonServiceOrder.findOrderByTimeSlot(Integer.parseInt(req.getParameter("timeSlotId"))).getOrderId(),true);
        view.setView(req.getContextPath()+"/pages/master/scheduleSlots?pageNumber1=1&pageNumber2=1");
    }
}
