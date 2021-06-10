package com.example.FinalProjectI.services;

import com.example.FinalProjectI.db.OrderDao;
import com.example.FinalProjectI.db.TimeSlotDao;
import com.example.FinalProjectI.db.entity.Order;
import com.example.FinalProjectI.db.entity.TimeSlot;
import com.example.FinalProjectI.db.exception.CustomApplicationException;
import com.example.FinalProjectI.db.exception.CustomDBException;
import com.example.FinalProjectI.db.handler.CustomDBExceptionHandler;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.utils.ConnectionNeedUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalonServiceTimeSlot {
    private static final Logger LOGGER = LogManager.getLogger(SalonServiceTimeSlot.class);
    private TimeSlotDao timeSlotDao;
    public SalonServiceTimeSlot(TimeSlotDao timeSlotDao){this.timeSlotDao = timeSlotDao;}

  public boolean insertTimeSlot(TimeSlot timeSlot) throws CustomApplicationException {
        boolean result = false;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            result = timeSlotDao.insertTimeSlot(timeSlot,connection);
            ConnectionNeedUtil.commit(connection);

        }catch (CustomDBException customDBException)
        {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Failed to commit in Service section",customDBException.getMessage(),customDBException);
        }
        finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }
        return result;
    }


   public   boolean deleteTimeSlot(int timeSlotId) throws CustomApplicationException {
        boolean result = false;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            result = timeSlotDao.deleteTimeSlot(timeSlotId,connection);
            ConnectionNeedUtil.commit(connection);

        }catch (CustomDBException customDBException)
        {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Failed to commit in Service section",customDBException.getMessage(),customDBException);
        }
        finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }
        return result;
    }

    public  TimeSlot findTimeSlot(int timeSlotId) throws CustomApplicationException {
        TimeSlot timeSlot = null;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            timeSlot = timeSlotDao.findTimeSlot(timeSlotId,connection);
            ConnectionNeedUtil.commit(connection);

        }catch (CustomDBException customDBException)
        {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Failed to commit in Service section",customDBException.getMessage(),customDBException);
        }
        finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }
        return timeSlot;
    }


    public List<TimeSlot> findAllFreeTimeSlotByMaster(int masterId,LocalDate fromWhichDay) throws CustomApplicationException {
        List<TimeSlot> timeSlots = new ArrayList<>();
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            timeSlots = timeSlotDao.findAllFreeTimeSlotByMaster(masterId,fromWhichDay,connection);
            ConnectionNeedUtil.commit(connection);

        }catch (CustomDBException customDBException)
        {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Failed to commit in Service section",customDBException.getMessage(),customDBException);
        }
        finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }
        return timeSlots;
    }


    public List<TimeSlot> findAllNotFreeTimeSlotByMaster(int masterId,LocalDate fromWhichDay) throws CustomApplicationException {
        List<TimeSlot> timeSlots = new ArrayList<>();
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            timeSlots = timeSlotDao.findAllNotFreeTimeSlotByMaster(masterId,fromWhichDay,connection);
            ConnectionNeedUtil.commit(connection);

        }catch (CustomDBException customDBException)
        {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Failed to commit in Service section",customDBException.getMessage(),customDBException);
        }
        finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }
        return timeSlots;
    }



    public List<TimeSlot> findAllTimeSlots() throws CustomApplicationException {
        List<TimeSlot> timeSlots = new ArrayList<>();
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            timeSlots = timeSlotDao.findAllTimeSlots(connection);
            ConnectionNeedUtil.commit(connection);

        }catch (CustomDBException customDBException)
        {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Failed to commit in Service section",customDBException.getMessage(),customDBException);
        }
        finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }
        return timeSlots;
    }

}
