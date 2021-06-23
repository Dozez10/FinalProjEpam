package com.example.FinalProjectI.db.mysql;

import com.example.FinalProjectI.db.OrderDao;
import com.example.FinalProjectI.db.entity.Order;
import com.example.FinalProjectI.db.exception.CustomDBException;
import com.example.FinalProjectI.utils.ConnectionNeedUtil;
import com.example.FinalProjectI.utils.PropUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
/**
 * Data access object for MasterService related entities
 */
public class MySQLOrderDao implements OrderDao {
    private static final Logger LOGGER = LogManager.getLogger(MySQLOrderDao.class);
    /**
     * Inserts Order entity into database table
     * @param con object with database
     * @param order entity to be inserted
     * @return true if insert operation went without exception and false otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
    public boolean insertOrder(Order order, Connection con) throws CustomDBException {

        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean result = false;
        try
        {
            String sqlQuery = PropUtil.getQuery("insertOrder");
            statement = con.prepareStatement(sqlQuery,Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,order.getUserId());
            statement.setInt(2,order.getMasterId());
            statement.setInt(3,order.getServiceId());

            statement.setTimestamp(4, Timestamp.valueOf(order.getStartTime()));


            statement.setTimestamp(5, Timestamp.valueOf(order.getEndTime()));


            statement.setBoolean(6,order.isApplied());

            statement.setBoolean(7,order.isDone());



            if(statement.executeUpdate()>0)
            {
                rs = statement.getGeneratedKeys();
                rs.next();
                order.setOrderId(rs.getInt(1));
                result = true;
            }

        }

        catch (SQLException sqlException)

        {

            LOGGER.error(sqlException);
            throw new CustomDBException("bad execution",sqlException.getMessage(),sqlException,sqlException.getErrorCode());

        }
         finally
        {
            ConnectionNeedUtil.close(rs,statement,null);
        }
       return result;

    }
    /**
     * Deletes Order entity from database table
     * @param con object with database
     * @param orderId id by which masterService to be deleted
     * @return true if delete operation went without exception and false otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
    public boolean deleteOrder(int orderId, Connection con) throws CustomDBException {
        String sqlQuery = null;
        try {
            sqlQuery = PropUtil.getQuery("deleteOrder");
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());
        }
        boolean result = false;
        try(PreparedStatement statement = con.prepareStatement(sqlQuery))
        {
            statement.setInt(1,orderId);
            if(statement.executeUpdate()>0)result = true;

        }

        catch (SQLException sqlException)
        {
            LOGGER.error(sqlException);
            throw new CustomDBException("bad execution",sqlException.getMessage(),sqlException,sqlException.getErrorCode());

        }

        return result;

    }
    /**
     * Find Order entity by login
     * @param con object with database
     * @param orderId id by which order to be find
     * @return Order entity if find operation went without exception and null otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
    public Order findOrder(int orderId, Connection con) throws CustomDBException {

        PreparedStatement statement = null;
        ResultSet rs = null;
        Order order = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findOrder");
            statement = con.prepareStatement(sqlQuery);
            statement.setInt(1,orderId);
            rs = statement.executeQuery();
            if(rs.next())
            {
                order = new Order();
                order.setOrderId(orderId);
                order.setUserId(rs.getInt("userId"));
                order.setMasterId(rs.getInt("masterId"));
                order.setServiceId(rs.getInt("serviceId"));
                order.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                order.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                order.setApplied(rs.getBoolean("isApplied"));
                order.setDone(rs.getBoolean("isDone"));
                order.setTimeSlotId(rs.getInt("timeSlotId"));

            }


        }

        catch (SQLException sqlException)

        {
            LOGGER.error(sqlException);
            throw new CustomDBException("bad execution",sqlException.getMessage(),sqlException,sqlException.getErrorCode());
        }
        finally
        {

            ConnectionNeedUtil.close(rs,statement,null);

        }

       return order;
    }

    /**
     * Find Order entity by id
     * @param con object with database
     * @param timeSlotId id by which order to be find
     * @return Order entity if find operation went without exception and null otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */

    @Override
    public Order findOrderByTimeSlot(int timeSlotId, Connection con) throws CustomDBException {

        PreparedStatement statement = null;
        ResultSet rs = null;
        Order order = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findOrderByTimeSlot");
            statement = con.prepareStatement(sqlQuery);
            statement.setInt(1,timeSlotId);
            rs = statement.executeQuery();
            if(rs.next())
            {
                order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserId(rs.getInt("userId"));
                order.setMasterId(rs.getInt("masterId"));
                order.setServiceId(rs.getInt("serviceId"));
                order.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                order.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                order.setApplied(rs.getBoolean("isApplied"));
                order.setDone(rs.getBoolean("isDone"));
                order.setTimeSlotId(rs.getInt("timeSlotId"));

            }


        }

        catch (SQLException sqlException)

        {
            LOGGER.error(sqlException);
            throw new CustomDBException("bad execution",sqlException.getMessage(),sqlException,sqlException.getErrorCode());
        }
        finally
        {

            ConnectionNeedUtil.close(rs,statement,null);

        }

        return order;
    }
    /**
     * Updates Order entity applied status  by orderId
     * @param con object with database
     * @param orderId id by which order to be find
     * @param isApplied new applied status to update
     * @return true if update operation went without exception and false otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
    public boolean updateOrderAppliedStatus(int orderId, boolean isApplied, Connection con) throws CustomDBException {

        String sqlQuery = null;
        try {
            sqlQuery = PropUtil.getQuery("updateOrderAppliedStatus");
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());
        }
        boolean result = false;
        try(  PreparedStatement statement = con.prepareStatement(sqlQuery))
        {
            statement.setBoolean(1,isApplied);
            statement.setInt(2,orderId);
            if(statement.executeUpdate()>0)result = true;
        }
        catch (SQLException sqlException)
        {
            LOGGER.error(sqlException);
            throw new CustomDBException("bad execution",sqlException.getMessage(),sqlException,sqlException.getErrorCode());
        }
       return result;
    }
    /**
     * Updates Order entity Done status  by orderId
     * @param con object with database
     * @param orderId id by which order to be find
     * @param isDone new Done status to update
     * @return true if update operation went without exception and false otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
    public boolean updateOrderDoneStatus(int orderId, boolean isDone, Connection con) throws CustomDBException {
        String sqlQuery = null;
        try {
            sqlQuery = PropUtil.getQuery("updateOrderDoneStatus");
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());
        }
        boolean result = false;
        try(PreparedStatement statement = con.prepareStatement(sqlQuery) )
        {
            statement.setBoolean(1,isDone);
            statement.setInt(2,orderId);
            if(statement.executeUpdate()>0)result = true;
        }
        catch (SQLException sqlException)
        {
            LOGGER.error(sqlException);
            throw new CustomDBException("bad execution",sqlException.getMessage(),sqlException,sqlException.getErrorCode());
        }
       return result;
    }

    /**
     * Updates Order entity timeSlotId  by orderId
     * @param con object with database
     * @param orderId id by which order to be find
     * @param timeSlotId new timeSlotId  to update
     * @return true if update operation went without exception and false otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */

    @Override
   public boolean updateOrderTimeSlot(int orderId,int timeSlotId, Connection con) throws CustomDBException
    {

        String sqlQuery = null;
        try {
            sqlQuery = PropUtil.getQuery("updateOrderTimeSlot");
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());
        }
        boolean result = false;
        try(PreparedStatement statement = con.prepareStatement(sqlQuery) )
        {
            statement.setInt(1,timeSlotId);
            statement.setInt(2,orderId);
            if(statement.executeUpdate()>0)result = true;
        }
        catch (SQLException sqlException)
        {
            LOGGER.error(sqlException);
            throw new CustomDBException("bad execution",sqlException.getMessage(),sqlException,sqlException.getErrorCode());
        }
        return result;
    }
    /**
     * Find Orders entities by filtering Time
     * @param con object with database
        @param fromWhichDay day from start find
        @param isApplied status to find order
      * @return List of Orders entities if find operation went without exception and empty list otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
   public   List<Order> findAllOrdersFromTime(LocalDate fromWhichDay ,boolean isApplied, Connection con) throws CustomDBException
    {

        List<Order> orders = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findAllOrdersFromTime");
            statement = con.prepareStatement(sqlQuery);
            statement.setDate(1,Date.valueOf(fromWhichDay));
            statement.setBoolean(2,isApplied);
            rs = statement.executeQuery();
            while(rs.next())
            {
                Order order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserId(rs.getInt("userId"));
                order.setMasterId(rs.getInt("masterId"));
                order.setServiceId(rs.getInt("serviceId"));
                order.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                order.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                order.setApplied(rs.getBoolean("isApplied"));
                order.setDone(rs.getBoolean("isDone"));
                order.setTimeSlotId(rs.getInt("timeSlotId"));
                orders.add(order);
            }
        }
        catch (SQLException sqlException)
        {
            LOGGER.error(sqlException);
            throw new CustomDBException("bad execution",sqlException.getMessage(),sqlException,sqlException.getErrorCode());
        }
        finally
        {

            ConnectionNeedUtil.close(rs,statement,null);
        }
        return orders;


    }

    /**
     * Find Orders entities by filtering Time with offset and limit
     * @param con object with database
     @param fromWhichDay day from start find
     @param isApplied status to find order
     @param limit to limit result set
     @param offset to offset from position
      * @return List of Orders entities if find operation went without exception and empty list otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
  public   List<Order> findAllOrdersFromTimeOffsetLimit(LocalDate fromWhichDay ,boolean isApplied,int limit,int offset, Connection con) throws CustomDBException
    {

        List<Order> orders = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findAllOrdersFromTimeOffsetLimit");
            statement = con.prepareStatement(sqlQuery);
            statement.setDate(1,Date.valueOf(fromWhichDay));
            statement.setBoolean(2,isApplied);
            statement.setInt(3,limit);
            statement.setInt(4,offset);
            rs = statement.executeQuery();
            while(rs.next())
            {
                Order order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserId(rs.getInt("userId"));
                order.setMasterId(rs.getInt("masterId"));
                order.setServiceId(rs.getInt("serviceId"));
                order.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                order.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                order.setApplied(rs.getBoolean("isApplied"));
                order.setDone(rs.getBoolean("isDone"));
                order.setTimeSlotId(rs.getInt("timeSlotId"));
                orders.add(order);
            }
        }
        catch (SQLException sqlException)
        {
            LOGGER.error(sqlException);
            throw new CustomDBException("bad execution",sqlException.getMessage(),sqlException,sqlException.getErrorCode());
        }
        finally
        {

            ConnectionNeedUtil.close(rs,statement,null);
        }
        return orders;


    }
    /**
     * Find all Order entities
     * @param con object with database
     * @return  List of  Order entities  if find operation went without exception and empty list otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
 @Override
    public List<Order> findAllOrders(Connection con) throws CustomDBException {
        List<Order> orders = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findAllOrders");
            statement = con.prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            while(rs.next())
            {
                Order order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserId(rs.getInt("userId"));
                order.setMasterId(rs.getInt("masterId"));
                order.setServiceId(rs.getInt("serviceId"));
                order.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                order.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                order.setApplied(rs.getBoolean("isApplied"));
                order.setDone(rs.getBoolean("isDone"));
                order.setTimeSlotId(rs.getInt("timeSlotId"));
                orders.add(order);
            }
        }
        catch (SQLException sqlException)
        {
            LOGGER.error(sqlException);
            throw new CustomDBException("bad execution",sqlException.getMessage(),sqlException,sqlException.getErrorCode());
        }
        finally
        {

          ConnectionNeedUtil.close(rs,statement,null);
        }
       return orders;
    }

}
