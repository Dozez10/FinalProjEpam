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
class MasterServiceTest {
    private MasterService masterService;
    @BeforeAll
    static void someMethodBeforeAll() throws SQLException {

    }
    @BeforeEach
    void initMocks(){
        masterService = new MasterService();
    }
    @AfterAll
    static void someMethodAfterAll(){

    }

    @Test
    void getMasterId() {
        masterService.setMasterId(112);
        assertEquals(112,masterService.getMasterId());
    }

    @Test
    void setMasterId() {
        masterService.setMasterId(112);
        assertEquals(112,masterService.getMasterId());

    }

    @Test
    void getServiceId() {
        masterService.setServiceId(111213123);
        assertEquals(111213123,masterService.getServiceId());
    }

    @Test
    void setServiceId() {
        masterService.setServiceId(111213123);
        assertEquals(111213123,masterService.getServiceId());
    }

    @Test
    void testNotEquals() {
        MasterService masterService = new MasterService();
        masterService.setServiceId(111123123);
        masterService.setMasterId(1231231231);
        MasterService masterService2 = new MasterService();
        masterService.setServiceId(123123);
        masterService.setMasterId(2132131);
        assertNotEquals(masterService,masterService2);
    }
    @Test
    void testEquals() {
        MasterService masterService = new MasterService();
        masterService.setServiceId(111123123);
        masterService.setMasterId(1231231231);
        MasterService masterService2 = new MasterService();
        masterService2.setServiceId(111123123);
        masterService2.setMasterId(1231231231);
        assertEquals(masterService,masterService2);
    }

    @Test
    void testHashCode() {
        MasterService masterService = new MasterService();
        masterService.setServiceId(111123123);
        masterService.setMasterId(1231231231);
        MasterService masterService2 = new MasterService();
        masterService2.setServiceId(111123123);
        masterService2.setMasterId(1231231231);
        assertEquals(masterService.hashCode(),masterService2.hashCode());
    }

    @Test
    void testToString() {
        MasterService masterService = new MasterService();
        masterService.setServiceId(111123123);
        masterService.setMasterId(1231231231);
        MasterService masterService2 = new MasterService();
        masterService2.setServiceId(111123123);
        masterService2.setMasterId(1231231231);
        assertNotEquals(masterService.toString(),masterService2.toString());
    }
}