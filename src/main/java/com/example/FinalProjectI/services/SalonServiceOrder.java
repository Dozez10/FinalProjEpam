package com.example.FinalProjectI.services;

import com.example.FinalProjectI.db.OrderDao;
import com.example.FinalProjectI.db.entity.Order;
import com.example.FinalProjectI.db.exception.CustomApplicationException;
import com.example.FinalProjectI.db.exception.CustomDBException;
import com.example.FinalProjectI.db.handler.CustomDBExceptionHandler;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.utils.ConnectionNeedUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SalonServiceOrder {
    private static final Logger LOGGER = LogManager.getLogger(SalonServiceOrder.class);
    private OrderDao orderDao;
    /**
     * Sets dao
     * @param orderDao object which will be used
     */
    public SalonServiceOrder(OrderDao orderDao){this.orderDao = orderDao;}
    /**
     * Inserts Order entity into database table
     * @param order entity to be inserted
     * @return true if insert operation went without exception and false otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */
    public boolean insertOrder(Order order) throws CustomApplicationException {
        boolean result = false;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            result = orderDao.insertOrder(order,connection);
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
     * Deletes Order entity from database table
     * @param orderId id by which masterService to be deleted
     * @return true if delete operation went without exception and false otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */


    public boolean deleteOrder(int orderId) throws CustomApplicationException {
        boolean result = false;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            result = orderDao.deleteOrder(orderId,connection);
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
     * Find Order entity by login
     * @param orderId id by which order to be find
     * @return Order entity if find operation went without exception and null otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */


    public Order findOrder(int orderId) throws CustomApplicationException {
       Order order = null;
       Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            order = orderDao.findOrder(orderId,connection);
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
        return order;
    }
    /**
     * Find Order entity by id
     * @param timeSlotId id by which order to be find
     * @return Order entity if find operation went without exception and null otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */
   public Order findOrderByTimeSlot(int timeSlotId) throws CustomApplicationException {
        Order order = null;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            order = orderDao.findOrderByTimeSlot(timeSlotId,connection);
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
        return order;
    }


    /**
     * Updates Order entity applied status  by orderId
     * @param orderId id by which order to be find
     * @param isApplied new applied status to update
     * @return true if update operation went without exception and false otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */


    public  boolean updateOrderAppliedStatus(int orderId,boolean isApplied) throws CustomApplicationException {
        boolean result = false;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            result = orderDao.updateOrderAppliedStatus( orderId, isApplied,connection);
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
     * Updates Order entity Done status  by orderId
     * @param orderId id by which order to be find
     * @param isDone new Done status to update
     * @return true if update operation went without exception and false otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */


    public    boolean updateOrderDoneStatus(int orderId,boolean isDone) throws CustomApplicationException {
        boolean result = false;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            result = orderDao.updateOrderDoneStatus( orderId, isDone,connection);
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
     * Updates Order entity timeSlotId  by orderId
     * @param orderId id by which order to be find
     * @param timeSlotId new timeSlotId  to update
     * @return true if update operation went without exception and false otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */


    public  boolean updateOrderTimeSlot(int orderId,int timeSlotId) throws CustomApplicationException {
        boolean result = false;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            result = orderDao.updateOrderTimeSlot( orderId, timeSlotId,connection);
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
     * Find Orders entities by filtering Time
     @param fromWhichDay day from start find
     @param isApplied status to find order
      * @return List of Orders entities if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */


    public   List<Order> findAllOrdersFromTime(LocalDate fromWhichDay , boolean isApplied) throws CustomApplicationException {
        List<Order> orderList = new ArrayList<>();
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            orderList = orderDao.findAllOrdersFromTime(fromWhichDay,isApplied,connection);
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
        return orderList;
    }

    /**
     * Find Orders entities by filtering Time with offset and limit
     @param fromWhichDay day from start find
     @param isApplied status to find order
     @param limit to limit result set
     @param offset to offset from position
      * @return List of Orders entities if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */
 public    List<Order> findAllOrdersFromTimeOffsetLimit(LocalDate fromWhichDay ,boolean isApplied,int limit,int offset) throws CustomApplicationException {
        List<Order> orderList = new ArrayList<>();
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            orderList = orderDao.findAllOrdersFromTimeOffsetLimit(fromWhichDay,isApplied,limit,offset,connection);
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
        return orderList;
    }


}
