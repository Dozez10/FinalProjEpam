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
import org.omg.CORBA.PUBLIC_MEMBER;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalonServiceTimeSlot {
    private static final Logger LOGGER = LogManager.getLogger(SalonServiceTimeSlot.class);
    private TimeSlotDao timeSlotDao;
    /**
     * Sets dao
     * @param timeSlotDao object which will be used
     */
    public SalonServiceTimeSlot(TimeSlotDao timeSlotDao){this.timeSlotDao = timeSlotDao;}


    /**
     * Inserts TimeSlot entity into database table
     * @param timeSlot entity to be inserted
     * @return true if insert operation went without exception and false otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

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

    /**
     * Deletes TimeSlot entity from database table
     * @param timeSlotId id by which timeSlot to be deleted
     * @return true if delete operation went without exception and false otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */
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
    /**
     * Find TimeSlot entity by type
     * @param timeSlotId id by which TimeSlot to be find
     * @return TimeSlot entity if find operation went without exception and null otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

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
    /**
     * Find free TimeSlot entities by filtering Time with offset and limit
     * @param masterId to filter by master id
     @param fromWhichDay day from start find
      * @return List of TimeSlots entities if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

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


    /**
     * Find free TimeSlot entities by filtering Time with offset and limit
     * @param masterId to filter by master id
     * @param limit to limit result set
     * @param offset to offset result set
     @param fromWhichDay day from start find
      * @return List of TimeSlots entities if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */
   public   List<TimeSlot> findAllFreeTimeSlotByMasterLimitOffset(int masterId,LocalDate fromWhichDay,int limit,int offset) throws CustomApplicationException {
        List<TimeSlot> timeSlots = new ArrayList<>();
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            timeSlots = timeSlotDao.findAllFreeTimeSlotByMasterLimitOffset(masterId,fromWhichDay,limit,offset,connection);
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

    /**
     * Find free TimeSlot entities by filtering Time
     * @param masterId to filter by master id
     @param fromWhichDay day to  start find
      * @return List of TimeSlots entities if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */
    public List<TimeSlot> findAllFreeTimeSlotByMasterDayLong(int masterId,LocalDate fromWhichDay) throws CustomApplicationException {
        List<TimeSlot> timeSlots = new ArrayList<>();
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            timeSlots = timeSlotDao.findAllFreeTimeSlotByMasterDayLong(masterId,fromWhichDay,connection);
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
    /**
     * Find free TimeSlot count by filtering Time
     * @param masterId to filter by master id
     @param fromWhichDay day from   start find
      * @return Count of TimeSlots entities if find operation went without exception and 0 otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */
   public   int findCountFreeSlotsByMastersDistinct(int masterId,LocalDate fromWhichDay) throws CustomApplicationException {
       int result = 0;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            result = timeSlotDao.findCountFreeSlotsByMastersDistinct(masterId,fromWhichDay,connection);
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


    /**
     * Find free TimeSlot count by filtering Time
     * @param masterId to filter by master id
     @param fromWhichDay day from   start find
      * @return Count of TimeSlots entities if find operation went without exception and 0 otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

    public   int findAllFreeTimeSlotByMasterCount(int masterId,LocalDate fromWhichDay) throws CustomApplicationException {
        int result = 0;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            result = timeSlotDao.findAllFreeTimeSlotByMasterCount(masterId,fromWhichDay,connection);
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
    /**
     * Find not free TimeSlot count by filtering Time
     * @param masterId to filter by master id
     @param fromWhichDay day from   start find
      * @return Count of TimeSlots entities if find operation went without exception and 0 otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */
    public   int findAllNotFreeTimeSlotByMasterCount(int masterId,LocalDate fromWhichDay) throws CustomApplicationException {
        int result = 0;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            result = timeSlotDao.findAllNotFreeTimeSlotByMasterCount(masterId,fromWhichDay,connection);
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



    /**
     * Find not free TimeSlot count by filtering Time
     * @param masterId to filter by master id
     @param fromWhichDay day from   start find
      * @return List of Time Slot entities if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

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
    /**
     * Find not free TimeSlot count by filtering Time with offset and limit
     * @param masterId to filter by master id
     @param fromWhichDay day from   start find

      * @return List of Time Slot entities if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */
   public List<TimeSlot> findAllNotFreeTimeSlotByMasterLimitOffset(int masterId,LocalDate fromWhichDay,int limit,int offset) throws CustomApplicationException {
        List<TimeSlot> timeSlots = new ArrayList<>();
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            timeSlots = timeSlotDao.findAllNotFreeTimeSlotByMasterLimitOffset(masterId,fromWhichDay,limit,offset,connection);
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


    /**
     * Find not free TimeSlot count by filtering Time
     * @param masterId to filter by master id
     @param fromWhichDay day from   start find

      * @return List of Time Slot entities if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */


    public List<TimeSlot> findAllNotFreeTimeSlotByMasterDayLong(int masterId,LocalDate fromWhichDay) throws CustomApplicationException {
        List<TimeSlot> timeSlots = new ArrayList<>();
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            timeSlots = timeSlotDao.findAllNotFreeTimeSlotByMasterDayLong(masterId,fromWhichDay,connection);
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


    /**
     * Find not free TimeSlot count by filtering Time
     * @param masterId to filter by master id
     @param fromWhichDay day from   start find

      * @return List of Time Slot entities if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

  public   List<TimeSlot> findAllNotFreeTimeSlotByMasterFromDay(int masterId,LocalDate fromWhichDay) throws CustomApplicationException {
        List<TimeSlot> timeSlots = new ArrayList<>();
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            timeSlots = timeSlotDao.findAllNotFreeTimeSlotByMasterFromDay(masterId,fromWhichDay,connection);
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


    /**
     * Find TimeSlot count by filtering Time
     * @param masterId to filter by master id
     @param fromWhichDay day from   start find
      * @return List of Time Slot entities if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

    public List<TimeSlot> findAllTimeSlotByMaster(int masterId, LocalDate fromWhichDay) throws CustomApplicationException {
        List<TimeSlot> timeSlots = new ArrayList<>();
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            timeSlots = timeSlotDao.findAllTimeSlotByMaster(masterId,fromWhichDay,connection);
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



    /**
     * Find all Time slot entities
     * @return  List of  Service entities  if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

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
