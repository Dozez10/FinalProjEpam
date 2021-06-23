package com.example.FinalProjectI.web.command;

import com.example.FinalProjectI.db.DAOFactory;
import com.example.FinalProjectI.db.OrderDao;
import com.example.FinalProjectI.db.entity.Order;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.services.SalonServiceOrder;
import com.example.FinalProjectI.web.view.View;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MakeRecordClientAction implements Action {
    @Override
    public void execute(View view) throws Exception {
        HttpServletRequest req = view.getRequest();
        int serviceId = Integer.parseInt(req.getParameter("serviceId"));
        int masterId = Integer.parseInt(req.getParameter("masterId"));
        int userId = Integer.parseInt(req.getParameter("userId"));
      LocalTime startTime = Time.valueOf(req.getParameter("startTime").concat(":00")).toLocalTime();
      LocalTime endTime = Time.valueOf(req.getParameter("endTime").concat(":00")).toLocalTime();
      LocalDate day = Date.valueOf(req.getParameter("dayDate")).toLocalDate();
        boolean isApplied = Boolean.parseBoolean(req.getParameter("isApplied"));
        boolean isDone = Boolean.parseBoolean(req.getParameter("isDone"));
        LocalDateTime startDateTime = LocalDateTime.of(day,startTime);
        LocalDateTime endDateTime = LocalDateTime.of(day,endTime);
        DAOFactory daoFactory =  (MySQLDAOFactory)req.getServletContext().getAttribute("factoryDao");
        OrderDao orderDao = daoFactory.getOrderDao();
        SalonServiceOrder salonServiceOrder = new SalonServiceOrder(orderDao);
        Order order = new Order();
        order.setMasterId(masterId);
        order.setUserId(userId);
        order.setStartTime(startDateTime);
        order.setEndTime(endDateTime);
        order.setDone(isDone);
        order.setServiceId(serviceId);
        order.setApplied(isApplied);
        String message;
         if(salonServiceOrder.insertOrder(order)) message = "insertedAnOrder";
         else
         {
             message = "Something went wrong";
         }
         view.setView(req.getContextPath()+req.getServletPath()+"/client/makeRecord?lang="+req.getParameter("lang")+"&masterId="+req.getParameter("masterId")+"&userId="+req.getParameter("userId")+"&errorMessageTo="+message);
    }
}
