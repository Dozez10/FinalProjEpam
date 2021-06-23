package com.example.FinalProjectI.web.command;

import com.example.FinalProjectI.db.DAOFactory;
import com.example.FinalProjectI.db.MasterDao;
import com.example.FinalProjectI.db.TimeSlotDao;
import com.example.FinalProjectI.db.entity.Master;
import com.example.FinalProjectI.db.entity.TimeSlot;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.services.SalonServiceMaster;
import com.example.FinalProjectI.services.SalonServiceTimeSlot;
import com.example.FinalProjectI.web.view.View;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * ChangeOrder
 * @author Ivan Manuilenko
 */
public class ChangeOrderAction implements Action  {
    @Override
    public void execute(View view) throws Exception {
        HttpServletRequest req = view.getRequest();
        DAOFactory daoFactory = (MySQLDAOFactory) req.getServletContext().getAttribute("factoryDao");
        double count = 0;
        int ordersPerPage = 5;
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
        MasterDao masterDao = daoFactory.getMasterDao();
        SalonServiceMaster salonServiceMaster = new SalonServiceMaster(masterDao);
        Master master = salonServiceMaster.findMasterById(Integer.parseInt(req.getParameter("masterId")));
        TimeSlotDao timeSlotDao = daoFactory.getTimeSlotDao();
        SalonServiceTimeSlot salonServiceTimeSlot = new SalonServiceTimeSlot(timeSlotDao);
        LocalDate cur = LocalDate.now().plusDays(offset);
        List<LocalDate> datesOf = new ArrayList<>();
        List<List<TimeSlot>> timeSlotListList = new ArrayList<>();
        List<List<TimeSlot>> timeSlotsAvailable = new ArrayList<>();
        for(int i = 0;i<5;i++) {
            List<TimeSlot> timeSlots = salonServiceTimeSlot.findAllNotFreeTimeSlotByMasterDayLong(master.getMasterId(),cur.plusDays(i));
            timeSlotListList.add(timeSlots);

            timeSlots = salonServiceTimeSlot.findAllFreeTimeSlotByMasterDayLong(master.getMasterId(),cur.plusDays(i));
            if(timeSlots.size()>0)
            {   datesOf.add(cur.plusDays(i));
                timeSlotsAvailable.add(timeSlots);
            }
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        List<String> timeSlotMinutesStart = new ArrayList<>();
        List<String> timeSlotMinutesEnd = new ArrayList<>();

        for(int i = 0 ;i<timeSlotsAvailable.size();i++) {
            sb.append("[");
            sb.append(master.getStartTime().toSecondOfDay()/60);
            sb1.append("[");
            sb1.append(master.getEndTime().toSecondOfDay()/60);
            for(int j =0;j<timeSlotListList.get(i).size();j++)
            {
                TimeSlot timeSlot = timeSlotListList.get(i).get(j);
                sb.append(",");
                sb.append(timeSlot.getStartTime().toLocalTime().toSecondOfDay()/60);
                sb1.append(",");
                sb1.append(timeSlot.getEndTime().toLocalTime().toSecondOfDay()/60);

            }
            sb.append("]");
            sb1.append("]");
            timeSlotMinutesStart.add(sb.toString());
            timeSlotMinutesEnd.add(sb1.toString());
            sb.setLength(0);
            sb1.setLength(0);
        }
        count = salonServiceTimeSlot.findCountFreeSlotsByMastersDistinct(master.getMasterId(),LocalDate.now());
        count = count/ordersPerPage;
        if(count>(int)count)count++;
        req.setAttribute("timeSlotsAvailableList",timeSlotsAvailable);
        req.setAttribute("dateOfTimeLine",datesOf);
        req.setAttribute("startTimeSlots",timeSlotMinutesStart);
        req.setAttribute("endTimeSlots",timeSlotMinutesEnd);
        req.setAttribute("pageNumber",page);
        req.setAttribute("countPages",(int)count);
      view.setView(req.getPathInfo());
    }
}
