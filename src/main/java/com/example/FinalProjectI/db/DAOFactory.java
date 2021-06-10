package com.example.FinalProjectI.db;

import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;

public abstract class DAOFactory {
    private static final int MYSQL = 1;
    public abstract MasterDao getMasterDao();
    public abstract UserDao getUserDao();
    public abstract OrderDao getOrderDao();
    public abstract ServiceDao getServiceDao();
    public abstract MasterServiceDao getMasterServiceDao();
    public abstract TimeSlotDao getTimeSlotDao();

    public static DAOFactory getDAOFactory(
            int whichFactory) {
        switch (whichFactory) {
            case MYSQL:
                return new MySQLDAOFactory();
            default :
                return null;
        }
    }

}