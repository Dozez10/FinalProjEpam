package com.example.FinalProjectI.db.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
class OrderTest {
    private Order order;
    @BeforeAll
    static void someMethodBeforeAll() throws SQLException {

    }
    @BeforeEach
    void initMocks(){
        order = new Order();
    }
    @AfterAll
    static void someMethodAfterAll(){

    }

    @Test
    void getOrderId() {
        order.setOrderId(32133112);
        assertEquals(32133112,order.getOrderId());
    }

    @Test
    void setOrderId() {
        order.setOrderId(32133112);
        assertEquals(32133112,order.getOrderId());
    }

    @Test
    void getUserId() {
        order.setUserId(32133112);
        assertEquals(32133112,order.getUserId());
    }

    @Test
    void setUserId() {
        order.setUserId(32133112);
        assertEquals(32133112,order.getUserId());
    }

    @Test
    void getMasterId() {
        order.setMasterId(32133112);
        assertEquals(32133112,order.getMasterId());
    }

    @Test
    void setMasterId() {
        order.setMasterId(32133112);
        assertEquals(32133112,order.getMasterId());
    }

    @Test
    void getServiceId() {
        order.setServiceId(32133112);
        assertEquals(32133112,order.getServiceId());
    }

    @Test
    void setServiceId() {
        order.setServiceId(32133112);
        assertEquals(32133112,order.getServiceId());
    }

    @Test
    void getStartTime() {
        order.setStartTime(LocalDateTime.now());
        assertEquals(LocalDateTime.now().getDayOfWeek(),order.getStartTime().getDayOfWeek());
    }

    @Test
    void setStartTime() {
        order.setStartTime(LocalDateTime.now());
        assertEquals(LocalDateTime.now().getDayOfWeek(),order.getStartTime().getDayOfWeek());
    }

    @Test
    void getEndTime() {
        order.setEndTime(LocalDateTime.now());
        assertEquals(LocalDateTime.now().getDayOfWeek(),order.getEndTime().getDayOfWeek());
    }

    @Test
    void setEndTime() {
        order.setEndTime(LocalDateTime.now());
        assertEquals(LocalDateTime.now().getDayOfWeek(),order.getEndTime().getDayOfWeek());
    }

    @Test
    void getTimeSlotId() {
        order.setTimeSlotId(32133112);
        assertEquals(32133112,order.getTimeSlotId());
    }

    @Test
    void setTimeSlotId() {
        order.setTimeSlotId(32133112);
        assertEquals(32133112,order.getTimeSlotId());
    }

    @Test
    void isApplied() {
        order.setApplied(true);
       assertTrue(order.isApplied());
    }

    @Test
    void setApplied() {
        order.setApplied(true);
        assertTrue(order.isApplied());
    }

    @Test
    void isDone() {
        order.setDone(true);
        assertTrue(order.isDone());
    }

    @Test
    void setDone() {
        order.setDone(true);
        assertTrue(order.isDone());
    }

    @Test
    void testEquals() {
        Order order = new Order();
        order.setOrderId(3213213);
        Order order1 = new Order();
        order1.setOrderId(3213213);
        assertEquals(order1,order);
    }

    @Test
    void testHashCode() {
        Order order = new Order();
        order.setOrderId(3213213);
        Order order1 = new Order();
        order1.setOrderId(3213213);
        assertEquals(order1.hashCode(),order.hashCode());
    }

    @Test
    void testToString() {
        Order order = new Order();
        order.setOrderId(3213213);
        Order order1 = new Order();
        order1.setOrderId(3213213);
        assertNotEquals(order1.toString(),order.toString());
    }
}