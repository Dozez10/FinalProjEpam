package com.example.FinalProjectI.web.command;

import com.example.FinalProjectI.db.DAOFactory;
import com.example.FinalProjectI.db.MasterDao;
import com.example.FinalProjectI.db.OrderDao;
import com.example.FinalProjectI.db.TimeSlotDao;
import com.example.FinalProjectI.db.entity.Master;
import com.example.FinalProjectI.db.entity.TimeSlot;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.services.SalonServiceMaster;
import com.example.FinalProjectI.services.SalonServiceOrder;
import com.example.FinalProjectI.services.SalonServiceTimeSlot;
import com.example.FinalProjectI.web.view.View;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public class ShowScheduleAction implements Action {
    @Override
    public void execute(View view) throws Exception {
        HttpServletRequest req = view.getRequest();
        DAOFactory daoFactory = (MySQLDAOFactory) req.getServletContext().getAttribute("factoryDao");
        String userLogin = (String)req.getSession().getAttribute("Login");
        MasterDao masterDao = daoFactory.getMasterDao();
        SalonServiceMaster salonServiceMaster = new SalonServiceMaster(masterDao);
        Master master = salonServiceMaster.findMaster(userLogin);

        double count1 = 0;
        int timeSlotsPerPage = 15;
        int offset1;
        int page1;
        double count2 = 0;
        int offset2;
        int page2;
        try{
            page1  = Integer.parseInt(req.getParameter("pageNumber1"));
        }catch (NumberFormatException nef)
        {
            page1=1;
        }
        if(page1<=0)page1=1;
        offset1 = (page1-1)*timeSlotsPerPage;
        try{
            page2  = Integer.parseInt(req.getParameter("pageNumber2"));
        }catch (NumberFormatException nef)
        {
            page2=1;
        }
        if(page2<=0)page2=1;
        offset2 = (page2-1)*timeSlotsPerPage;
        TimeSlotDao timeSlotDao = daoFactory.getTimeSlotDao();
        SalonServiceTimeSlot salonServiceTimeSlot = new SalonServiceTimeSlot(timeSlotDao);
        List<TimeSlot> timeSlotsFree = salonServiceTimeSlot.findAllFreeTimeSlotByMasterLimitOffset(master.getMasterId(), LocalDate.now(),timeSlotsPerPage,offset1);
        List<TimeSlot> timeSlotsNotFree = salonServiceTimeSlot.findAllNotFreeTimeSlotByMasterLimitOffset(master.getMasterId(),LocalDate.now(),timeSlotsPerPage,offset2);
        count1 = salonServiceTimeSlot.findAllFreeTimeSlotByMasterCount(master.getMasterId(),LocalDate.now());
        count2 = salonServiceTimeSlot.findAllNotFreeTimeSlotByMasterCount(master.getMasterId(),LocalDate.now());
        count1 = count1/timeSlotsPerPage;
        if(count1>(int)count1)count1++;
        count2 = count2/timeSlotsPerPage;
        if(count2>(int)count2)count2++;
        req.setAttribute("timeSlotsFree",timeSlotsFree);
        req.setAttribute("timeSlotsNotFree",timeSlotsNotFree);
        req.setAttribute("count1",(int)count1);
        req.setAttribute("count2",(int)count2);
        req.setAttribute("page1",page1);
        req.setAttribute("page2",page2);
        view.setView(req.getPathInfo());

    }
}
