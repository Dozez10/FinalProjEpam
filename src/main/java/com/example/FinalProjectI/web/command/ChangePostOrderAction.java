package com.example.FinalProjectI.web.command;

import com.example.FinalProjectI.db.DAOFactory;
import com.example.FinalProjectI.db.OrderDao;
import com.example.FinalProjectI.db.TimeSlotDao;
import com.example.FinalProjectI.db.entity.TimeSlot;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.services.SalonServiceOrder;
import com.example.FinalProjectI.services.SalonServiceTimeSlot;
import com.example.FinalProjectI.web.view.View;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ChangePostOrderAction implements Action  {
    @Override
    public void execute(View view) throws Exception {
        HttpServletRequest req = view.getRequest();
        DAOFactory daoFactory = (MySQLDAOFactory) req.getServletContext().getAttribute("factoryDao");
        int masterId = Integer.parseInt(req.getParameter("masterId"));
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        LocalTime startTime = Time.valueOf(req.getParameter("startTime").concat(":00")).toLocalTime();
        LocalTime endTime = Time.valueOf(req.getParameter("endTime").concat(":00")).toLocalTime();
        LocalDate day = Date.valueOf(req.getParameter("dayDate")).toLocalDate();
        LocalDateTime startDateTime = LocalDateTime.of(day,startTime);
        LocalDateTime endDateTime = LocalDateTime.of(day,endTime);
        TimeSlotDao timeSlotDao = daoFactory.getTimeSlotDao();
        SalonServiceTimeSlot salonServiceTimeSlot = new SalonServiceTimeSlot(timeSlotDao);
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartTime(startDateTime);
        timeSlot.setEndTime(endDateTime);
        timeSlot.setFree(false);
        timeSlot.setMasterId(masterId);
        salonServiceTimeSlot.insertTimeSlot(timeSlot);
        OrderDao orderDao = daoFactory.getOrderDao();
        SalonServiceOrder salonServiceOrder = new SalonServiceOrder(orderDao);
        salonServiceOrder.updateOrderTimeSlot(orderId,timeSlot.getTimeSlotId());
        view.setView(req.getContextPath()+"/pages/admin/changeOrder?orderId="+orderId+"&masterId="+masterId+"&pageNumber=1");
    }
}
