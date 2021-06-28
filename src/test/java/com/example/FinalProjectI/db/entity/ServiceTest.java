package com.example.FinalProjectI.db.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
class ServiceTest {
    private Service service;
    @BeforeAll
    static void someMethodBeforeAll() throws SQLException {

    }
    @BeforeEach
    void initMocks(){
        service = new Service();
    }
    @AfterAll
    static void someMethodAfterAll(){

    }

    @Test
    void getServiceId() {
        service.setServiceId(3213123);
        assertEquals(3213123,service.getServiceId());
    }

    @Test
    void setServiceId() {
        service.setServiceId(3213123);
        assertEquals(3213123,service.getServiceId());
    }

    @Test
    void getServiceType() {
        service.setServiceType("asdsad");
        assertEquals("asdsad",service.getServiceType());
    }

    @Test
    void setServiceType() {
        service.setServiceType("asdsad");
        assertEquals("asdsad",service.getServiceType());
    }

    @Test
    void getServicePrice() {
        service.setServicePrice(3232d);
        assertEquals(3232d,service.getServicePrice());
    }

    @Test
    void setServicePrice() {
        service.setServicePrice(3232d);
        assertEquals(3232d,service.getServicePrice());
    }

    @Test
    void testEquals() {
        Service service = new Service();
        service.setServiceId(321313);
        Service service2 = new Service();
        service2.setServiceId(321313);
        assertEquals(service2,service);
    }

    @Test
    void testHashCode() {
        Service service = new Service();
        service.setServiceId(321313);
        Service service2 = new Service();
        service2.setServiceId(321313);
        assertEquals(service2.hashCode(),service.hashCode());
    }

    @Test
    void testToString() {
        Service service = new Service();
        service.setServiceId(321313);
        Service service2 = new Service();
        service2.setServiceId(321313);
        assertNotEquals(service2.toString(),service.toString());
    }
}