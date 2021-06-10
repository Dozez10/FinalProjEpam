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
    public SalonServiceOrder(OrderDao orderDao){this.orderDao = orderDao;}

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

    public List<Order> findAllOrders() throws CustomApplicationException {
      List<Order> orderList = new ArrayList<>();
      Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            orderList = orderDao.findAllOrders(connection);
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
    public List<Order> findPagesOrder(int startRow,int rowsPerPage) throws CustomApplicationException {
        List<Order> orderList = new ArrayList<>();
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            orderList = orderDao.findPagesOrder(connection,startRow,rowsPerPage);
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
