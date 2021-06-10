package com.example.FinalProjectI.web.command;

import com.example.FinalProjectI.db.DAOFactory;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.web.view.View;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

public class HomeAction implements Action{
    @Override
    public void execute(View view) throws Exception {

        HttpServletRequest request  = view.getRequest();
        DAOFactory factory = (MySQLDAOFactory)request.getServletContext().getAttribute("factoryDao");
        view.setView(request.getPathInfo());

    }
}
