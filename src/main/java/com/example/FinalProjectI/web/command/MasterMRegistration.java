package com.example.FinalProjectI.web.command;

import com.example.FinalProjectI.db.*;
import com.example.FinalProjectI.db.entity.Master;
import com.example.FinalProjectI.db.entity.MasterService;
import com.example.FinalProjectI.db.entity.User;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.services.SalonServiceMaster;
import com.example.FinalProjectI.services.SalonServiceMasterService;
import com.example.FinalProjectI.services.SalonServiceService;
import com.example.FinalProjectI.services.SalonServiceUser;
import com.example.FinalProjectI.web.view.View;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Arrays;

public class MasterMRegistration implements Action{
    @Override
    public void execute(View view) throws Exception {
        HttpServletRequest request = view.getRequest();
        String login = request.getParameter("login");
        String email = request.getParameter("Email");
        String psw = request.getParameter("psw");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String[]service = request.getParameterValues("serviceType");
        String masterName = request.getParameter("mName");
        DAOFactory daoFactory = (MySQLDAOFactory)request.getServletContext().getAttribute("factoryDao");
        UserDao userDao = daoFactory.getUserDao();
        SalonServiceUser uService = new SalonServiceUser(userDao);
        MasterDao masterDao = daoFactory.getMasterDao();
        SalonServiceMaster mService = new SalonServiceMaster(masterDao);
        MasterServiceDao masterServiceDao = daoFactory.getMasterServiceDao();
        SalonServiceMasterService msService = new SalonServiceMasterService(masterServiceDao);
        ServiceDao serviceDao = daoFactory.getServiceDao();
        SalonServiceService sService = new SalonServiceService(serviceDao);
        User user = new User();
        user.setUserType("master");
        user.setUserPassword(psw);
        user.setUserLogin(login);
        user.setUserEmail(email);
        uService.insertUser(user);
        Master master = new Master();
        master.setUserId(user.getUserId());
        master.setRating(30);
        master.setMasterName(masterName);
        master.setStartTime(Time.valueOf(startTime.concat(":00")).toLocalTime());
         master.setEndTime(Time.valueOf(endTime.concat(":00")).toLocalTime());
         master.setUserType("master");
         mService.insertMaster(master);
        for (String type:service) {
            MasterService ms = new MasterService();
            ms.setMasterId(master.getMasterId());
            ms.setServiceId(sService.findService(type).getServiceId());
            msService.insertMasterService(ms);
        }
     view.setView(request.getContextPath()+"/pages/index");
    }
}
