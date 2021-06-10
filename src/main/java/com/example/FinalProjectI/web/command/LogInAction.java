package com.example.FinalProjectI.web.command;

import com.example.FinalProjectI.db.DAOFactory;
import com.example.FinalProjectI.db.UserDao;
import com.example.FinalProjectI.db.entity.User;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.services.SalonServiceUser;
import com.example.FinalProjectI.web.view.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogInAction implements Action{
    @Override
    public void execute(View view) throws Exception {
        HttpServletRequest request = view.getRequest();
        DAOFactory dao =(MySQLDAOFactory)request.getServletContext().getAttribute("factoryDao");
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String psw = request.getParameter("psw");
        UserDao userDao = dao.getUserDao();
        SalonServiceUser serviceUser = new SalonServiceUser(userDao);
        User user = serviceUser.findUser(login);
        if(user!=null&&serviceUser.validateUser(login,psw)){
            session.setAttribute("Login",login);
            session.setAttribute("userType",user.getUserType());
            view.setView(request.getContextPath()+"/pages/index");
        } else{
            String error= "Your login or password is wrong";
            view.setView(request.getContextPath()+"/pages/index?errorMessage="+error);
        }
    }
}
