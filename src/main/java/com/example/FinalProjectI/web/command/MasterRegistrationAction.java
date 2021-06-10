package com.example.FinalProjectI.web.command;

import com.example.FinalProjectI.db.DAOFactory;
import com.example.FinalProjectI.db.ServiceDao;
import com.example.FinalProjectI.db.UserDao;
import com.example.FinalProjectI.db.entity.Service;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.services.SalonServiceService;
import com.example.FinalProjectI.services.SalonServiceUser;
import com.example.FinalProjectI.web.view.View;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class MasterRegistrationAction implements Action{
    @Override
    public void execute(View view) throws Exception {
        HttpServletRequest request = view.getRequest();
        ObjectMapper mapper = new ObjectMapper();
        DAOFactory factory =(MySQLDAOFactory)request.getServletContext().getAttribute("factoryDao");
        UserDao userDao =  factory.getUserDao();
        ServiceDao serviceDao = factory.getServiceDao();
        SalonServiceUser serviceUser = new SalonServiceUser(userDao);
        SalonServiceService serviceService = new SalonServiceService(serviceDao);
         List<String> services = serviceService.findAllService().stream().map(Service::getServiceType).collect(Collectors.toList());
         request.setAttribute("services",services);
         request.setAttribute("serviceTypes",mapper.writeValueAsString(services));
         view.setView(request.getPathInfo());
    }
}
