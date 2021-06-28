package com.example.FinalProjectI.services;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.example.FinalProjectI.db.OrderDao;
import com.example.FinalProjectI.db.entity.Order;
import com.example.FinalProjectI.db.exception.CustomApplicationException;
import com.example.FinalProjectI.db.exception.CustomDBException;
import hthurow.tomcatjndi.TomcatJNDI;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SalonServiceOrderTest {


    private OrderDao orderDao;
    private SalonServiceOrder salonServiceOrderDao;
    private static TomcatJNDI tomcatJNDI;
    @BeforeAll
    static void initJNDI(){
        tomcatJNDI = new TomcatJNDI();
        tomcatJNDI.processContextXml(new File("src/main/webapp/META-INF/context.xml"));
        tomcatJNDI.start();
    }
    @BeforeEach
    void initMocks(){
        orderDao = mock(OrderDao.class);
        salonServiceOrderDao = new SalonServiceOrder(orderDao);
    }
    @AfterAll
    static void clearEnv(){
        tomcatJNDI.tearDown();
    }

    @Test
    void insertOrder() throws CustomDBException, CustomApplicationException {
        when(orderDao.insertOrder(any(Order.class),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceOrderDao.insertOrder(new Order()));
    }
    @Test
    void insertOrderException() throws CustomDBException {
        when(orderDao.insertOrder(any(Order.class),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceOrderDao.insertOrder(new Order()));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());
    }

    @Test
    void deleteOrder() throws CustomDBException, CustomApplicationException {
        when(orderDao.deleteOrder(anyInt(),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceOrderDao.deleteOrder(1));
    }
    @Test
    void deleteOrderException() throws CustomDBException, CustomApplicationException {
        when(orderDao.deleteOrder(anyInt(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceOrderDao.deleteOrder(1));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());
    }

    @Test
    void findOrder() throws CustomDBException, CustomApplicationException {
        when(orderDao.findOrder(anyInt(),any(Connection.class))).thenReturn(new Order());
        assertNotNull(salonServiceOrderDao.findOrder(1));
    }
    @Test
    void findOrderException() throws CustomDBException, CustomApplicationException {
        when(orderDao.findOrder(anyInt(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceOrderDao.findOrder(1));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());
    }

    @Test
    void findOrderByTimeSlot() throws CustomDBException, CustomApplicationException {
        when(orderDao.findOrderByTimeSlot(anyInt(),any(Connection.class))).thenReturn(new Order());
        assertNotNull(salonServiceOrderDao.findOrderByTimeSlot(1));
    }
    @Test
    void findOrderByTimeSlotException() throws CustomDBException, CustomApplicationException {
        when(orderDao.findOrderByTimeSlot(anyInt(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceOrderDao.findOrderByTimeSlot(1));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());

    }

    @Test
    void updateOrderAppliedStatus() throws CustomDBException, CustomApplicationException {
        when(orderDao.updateOrderAppliedStatus(anyInt(),anyBoolean(),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceOrderDao.updateOrderAppliedStatus(1,true));
    }
    @Test
    void updateOrderAppliedStatusException() throws CustomDBException, CustomApplicationException {
        when(orderDao.updateOrderAppliedStatus(anyInt(),anyBoolean(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceOrderDao.updateOrderAppliedStatus(1,false));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());
    }

    @Test
    void updateOrderDoneStatus() throws CustomDBException, CustomApplicationException {
        when(orderDao.updateOrderDoneStatus(anyInt(),anyBoolean(),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceOrderDao.updateOrderDoneStatus(1,true));
    }
    @Test
    void updateOrderDoneStatusException() throws CustomDBException, CustomApplicationException {
        when(orderDao.updateOrderDoneStatus(anyInt(),anyBoolean(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceOrderDao.updateOrderDoneStatus(1,false));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());
    }

    @Test
    void updateOrderTimeSlot() throws CustomDBException, CustomApplicationException {
        when(orderDao.updateOrderTimeSlot(anyInt(),anyInt(),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceOrderDao.updateOrderTimeSlot(1,2));

    }
    @Test
    void updateOrderTimeSlotException() throws CustomDBException, CustomApplicationException {
        when(orderDao.updateOrderTimeSlot(anyInt(),anyInt(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceOrderDao.updateOrderTimeSlot(1,2));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());

    }

    @Test
    void findAllOrdersFromTime() throws CustomDBException, CustomApplicationException {

        when(orderDao.findAllOrdersFromTime(any(LocalDate.class),anyBoolean(),any(Connection.class))).thenReturn(new ArrayList<Order>());
        assertNotNull(salonServiceOrderDao.findAllOrdersFromTime(LocalDate.now(),true));


    }
    @Test
    void findAllOrdersFromTimeException() throws CustomDBException, CustomApplicationException {

        when(orderDao.findAllOrdersFromTime(any(LocalDate.class),anyBoolean(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceOrderDao.findAllOrdersFromTime(LocalDate.now(),true));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());


    }
    @Test
    void findAllOrdersFromTimeOffsetLimit() throws CustomDBException, CustomApplicationException {


        when(orderDao.findAllOrdersFromTimeOffsetLimit(any(LocalDate.class),anyBoolean(),anyInt(),anyInt(),any(Connection.class))).thenReturn(new ArrayList<Order>());
        assertNotNull(salonServiceOrderDao.findAllOrdersFromTimeOffsetLimit(LocalDate.now(),true,1,2));

    }
    @Test
    void findAllOrdersFromTimeOffsetLimitException() throws CustomDBException, CustomApplicationException {


        when(orderDao.findAllOrdersFromTimeOffsetLimit(any(LocalDate.class),anyBoolean(),anyInt(),anyInt(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceOrderDao.findAllOrdersFromTimeOffsetLimit(LocalDate.now(),true,1,2));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());

    }
}