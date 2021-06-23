package com.example.FinalProjectI.web.command;


import com.example.FinalProjectI.db.*;
import com.example.FinalProjectI.db.entity.Master;
import com.example.FinalProjectI.db.entity.MasterService;
import com.example.FinalProjectI.db.entity.Service;
import com.example.FinalProjectI.db.entity.TimeSlot;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.services.SalonServiceMaster;
import com.example.FinalProjectI.services.SalonServiceMasterService;
import com.example.FinalProjectI.services.SalonServiceService;
import com.example.FinalProjectI.services.SalonServiceTimeSlot;
import com.example.FinalProjectI.web.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GetRecordClientAction implements Action{
    @Override
    public void execute(View view) throws Exception {

        HttpServletRequest request = view.getRequest();
        HttpSession session = request.getSession();
        String masterId = request.getParameter("masterId");
        String userId = request.getParameter("userId");
        DAOFactory daoFactory = (MySQLDAOFactory) request.getServletContext().getAttribute("factoryDao");

        MasterDao masterDao = daoFactory.getMasterDao();
        MasterServiceDao masterService = daoFactory.getMasterServiceDao();
        ServiceDao serviceDao = daoFactory.getServiceDao();

        SalonServiceService salonServiceService = new SalonServiceService(serviceDao);
        SalonServiceMasterService masterService1 = new SalonServiceMasterService(masterService);
        SalonServiceMaster salonServiceMaster = new SalonServiceMaster(masterDao);
        TimeSlotDao dao = daoFactory.getTimeSlotDao();

        SalonServiceTimeSlot salonServiceTimeSlot = new SalonServiceTimeSlot(dao);
        Master master = new Master();

        List<Service> serviceList = new ArrayList<>();
            if (masterId != null) {
                master = salonServiceMaster.findMasterById(Integer.parseInt(masterId));
                for (MasterService masterService2 : masterService1.findAllServicesByMaster(master.getMasterId())) {
                    serviceList.add(salonServiceService.findServiceById(masterService2.getServiceId()));
                }

            }

            LocalDate cur = LocalDate.now();
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
       request.setAttribute("timeSlotsAvailableList",timeSlotsAvailable);
       request.setAttribute("dateOfTimeLine",datesOf);
       request.setAttribute("startTimeSlots",timeSlotMinutesStart);
       request.setAttribute("endTimeSlots",timeSlotMinutesEnd);
       request.setAttribute("service", serviceList);
       request.setAttribute("master", master);

        view.setView(request.getPathInfo());
        }


}
