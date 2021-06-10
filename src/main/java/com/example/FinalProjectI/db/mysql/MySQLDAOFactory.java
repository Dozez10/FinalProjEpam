package com.example.FinalProjectI.db.mysql;

import com.example.FinalProjectI.db.*;
import com.example.FinalProjectI.db.exception.CustomDBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOFactory {
    private static final Logger LOGGER = LogManager.getLogger(MySQLDAOFactory.class);
    private static DataSource connPool;

    public static void  initDataSource()
    {
        try {

            Context context = new InitialContext();
            Context envContext = (Context) context.lookup("java:comp/env");
            connPool = (DataSource) envContext.lookup("jdbc/BeautySalonDB");
           }
        catch (NamingException e)
           {
               LOGGER.error(e);

           }

    }

    public static Connection getConnection() throws CustomDBException {
        Connection con = null;
        if(connPool==null) initDataSource();

        try {
               con =  connPool.getConnection();
            }
        catch (SQLException sqlException)
            {
                LOGGER.error(sqlException);
                throw new CustomDBException("Connection is not valid",sqlException.getMessage(),sqlException,sqlException.getErrorCode());
            }

       return  con;
    }

    @Override
    public MasterDao getMasterDao() {
        return new MySQLMasterDao();
    }

    @Override
    public UserDao getUserDao() {
        return new MySQLUserDao();
    }

    @Override
    public OrderDao getOrderDao() {
        return new MySQLOrderDao();
    }

    @Override
    public ServiceDao getServiceDao() {
        return new MySQLServiceDao();
    }

    @Override
    public MasterServiceDao getMasterServiceDao() {
        return new MySQLMasterServiceDao();
    }
    public  TimeSlotDao getTimeSlotDao()
    {
        return new MySQLTimeSlotDao();
    }
}
