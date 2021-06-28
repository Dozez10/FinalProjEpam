package com.example.FinalProjectI.db.mysql;

import com.example.FinalProjectI.db.MasterServiceDao;
import com.example.FinalProjectI.db.OrderDao;
import com.example.FinalProjectI.db.entity.Order;
import com.example.FinalProjectI.db.exception.CustomDBException;
import org.apache.logging.log4j.message.TimestampMessage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
class MySQLOrderDaoTest {
    private OrderDao orderDao;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeAll
    static void someMethodBeforeAll() throws SQLException {

    }
    @BeforeEach
    void initMocks() throws SQLException {
        orderDao = new MySQLOrderDao();
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString(),anyInt())).thenReturn(preparedStatement);
    }
    @AfterAll
    static void someMethodAfterAll(){

    }


    @Test
    void insertOrder() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.getInt(any())).thenReturn(3213312);
        when(resultSet.next()).thenReturn(true);
        Order order = new Order();
        order.setDone(false);
        order.setApplied(false);
        order.setStartTime(LocalDateTime.now());
        order.setEndTime(LocalDateTime.now());
        order.setMasterId(321321223);
        order.setUserId(312321313);
        order.setServiceId(321321311);
        order.setTimeSlotId(321321112);
       assertTrue(orderDao.insertOrder(order,connection));

    }

    @Test
    void deleteOrder() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        assertTrue(orderDao.deleteOrder(11,connection));
    }

    @Test
    void findOrder() throws CustomDBException, SQLException {
        when(resultSet.getString(anyInt())).thenReturn("asdsadsa");
        when(resultSet.getString(anyString())).thenReturn("asdsadsa");
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getInt(anyInt())).thenReturn(1233);
        when(resultSet.getInt(anyString())).thenReturn(1233);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
    assertNotNull(orderDao.findOrder(11,connection));
    }

    @Test
    void findOrderByTimeSlot() throws CustomDBException, SQLException {
        when(resultSet.getString(anyInt())).thenReturn("asdsadsa");
        when(resultSet.getString(anyString())).thenReturn("asdsadsa");
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getInt(anyInt())).thenReturn(1233);
        when(resultSet.getInt(anyString())).thenReturn(1233);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        assertNotNull(orderDao.findOrderByTimeSlot(11,connection));
    }

    @Test
    void updateOrderAppliedStatus() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        assertTrue(orderDao.updateOrderAppliedStatus(11,true,connection));
    }

    @Test
    void updateOrderDoneStatus() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        assertTrue(orderDao.updateOrderDoneStatus(11,true,connection));
    }

    @Test
    void updateOrderTimeSlot() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        assertTrue(orderDao.updateOrderTimeSlot(11,11,connection));
    }

    @Test
    void findAllOrdersFromTime() throws CustomDBException, SQLException {
        when(resultSet.getString(anyInt())).thenReturn("asdsadsa");
        when(resultSet.getString(anyString())).thenReturn("asdsadsa");
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getInt(anyInt())).thenReturn(1233);
        when(resultSet.getInt(anyString())).thenReturn(1233);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        assertNotNull(orderDao.findAllOrdersFromTime(LocalDate.now(),true,connection));
    }

    @Test
    void findAllOrdersFromTimeOffsetLimit() throws CustomDBException, SQLException {
        when(resultSet.getString(anyInt())).thenReturn("asdsadsa");
        when(resultSet.getString(anyString())).thenReturn("asdsadsa");
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getInt(anyInt())).thenReturn(1233);
        when(resultSet.getInt(anyString())).thenReturn(1233);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        assertNotNull(orderDao.findAllOrdersFromTimeOffsetLimit(LocalDate.now(),true,2,1,connection));
    }

    @Test
    void findAllOrders() throws CustomDBException, SQLException {
        when(resultSet.getString(anyInt())).thenReturn("asdsadsa");
        when(resultSet.getString(anyString())).thenReturn("asdsadsa");
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getInt(anyInt())).thenReturn(1233);
        when(resultSet.getInt(anyString())).thenReturn(1233);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        assertNotNull(orderDao.findAllOrders(connection));
    }
}