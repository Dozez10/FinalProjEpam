package com.example.FinalProjectI.web.listener;

import com.example.FinalProjectI.db.DAOFactory;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.utils.PropUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.SQLException;
import java.util.Locale;

@WebListener
public class ApplicationListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(1);
        sce.getServletContext().setAttribute("factoryDao",daoFactory);
        MySQLDAOFactory.initDataSource();
        try {
            PropUtil.loadProp();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        Locale.setDefault(new Locale("ua"));
    }


}
