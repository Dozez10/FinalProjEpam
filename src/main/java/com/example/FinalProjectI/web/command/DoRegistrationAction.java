package com.example.FinalProjectI.web.command;

import com.example.FinalProjectI.db.DAOFactory;
import com.example.FinalProjectI.db.UserDao;
import com.example.FinalProjectI.db.entity.User;
import com.example.FinalProjectI.db.exception.CustomApplicationException;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.services.SalonServiceUser;
import com.example.FinalProjectI.web.view.View;

import javax.servlet.http.HttpServletRequest;

public class DoRegistrationAction implements Action{
    @Override
    public void execute(View view) throws Exception {
        HttpServletRequest request = view.getRequest();
        DAOFactory factory =(MySQLDAOFactory)request.getServletContext().getAttribute("factoryDao");
        UserDao userDao =  factory.getUserDao();
        String login = request.getParameter("login");
        String email = request.getParameter("Email");
        String psw = request.getParameter("psw");
        User user = new User();
        user.setUserLogin(login);
        user.setUserEmail(email);
        user.setUserPassword(psw);
        user.setUserType("client");
        SalonServiceUser serviceUser = new SalonServiceUser(userDao);
        view.setView(request.getContextPath()+"/pages/index");
        try{
            serviceUser.insertUser(user);
        }catch (CustomApplicationException e){
            String errorMessage = e.getMessage();
            System.out.println(e.getErrorCode());
            if(e.getErrorCode()==1062){
                 errorMessage = String.format("Your login %s or email %s already exists",login,email);
            }
            view.setView(request.getContextPath() + "/pages/guest/registration?registrationMessage=" + errorMessage);
        }
    }
}
