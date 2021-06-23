package com.example.FinalProjectI.db;

import com.example.FinalProjectI.db.entity.Order;
import com.example.FinalProjectI.db.exception.CustomDBException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public interface OrderDao {
    boolean insertOrder(Order order, Connection connection) throws CustomDBException;
    boolean deleteOrder(int orderId, Connection connection) throws CustomDBException;
    Order findOrder(int orderId, Connection connection) throws CustomDBException;
    Order findOrderByTimeSlot(int timeSlotId, Connection connection) throws CustomDBException;
    boolean updateOrderAppliedStatus(int orderId,boolean isApplied, Connection connection) throws CustomDBException;
    boolean updateOrderDoneStatus(int orderId,boolean isDone, Connection connection) throws CustomDBException;
    boolean updateOrderTimeSlot(int orderId,int timeSlotId, Connection connection) throws CustomDBException;
    List<Order> findAllOrdersFromTime(LocalDate fromWhichDay ,boolean isApplied, Connection connection) throws CustomDBException;
    List<Order> findAllOrdersFromTimeOffsetLimit(LocalDate fromWhichDay ,boolean isApplied,int limit,int offset, Connection connection) throws CustomDBException;
    List<Order> findAllOrders(Connection connection) throws CustomDBException;


}
