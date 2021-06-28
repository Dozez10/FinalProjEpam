package com.example.FinalProjectI.services;


import com.example.FinalProjectI.db.TimeSlotDao;
import com.example.FinalProjectI.db.entity.TimeSlot;
import com.example.FinalProjectI.db.exception.CustomApplicationException;
import com.example.FinalProjectI.db.exception.CustomDBException;
import hthurow.tomcatjndi.TomcatJNDI;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class SalonServiceTimeSlotTest {
    private TimeSlotDao timeSlotDao;
    private SalonServiceTimeSlot salonServiceTimeSlot;
    private static TomcatJNDI tomcatJNDI;
    @BeforeAll
    static void initJNDI(){
        tomcatJNDI = new TomcatJNDI();
        tomcatJNDI.processContextXml(new File("src/main/webapp/META-INF/context.xml"));
        tomcatJNDI.start();
    }
    @BeforeEach
    void initMocks(){
        timeSlotDao = mock(TimeSlotDao.class);
        salonServiceTimeSlot = new SalonServiceTimeSlot(timeSlotDao);
    }
    @AfterAll
    static void clearEnv(){
        tomcatJNDI.tearDown();
    }

    @Test
    void insertTimeSlot() throws CustomDBException, CustomApplicationException {

        when(timeSlotDao.insertTimeSlot(any(TimeSlot.class),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceTimeSlot.insertTimeSlot(new TimeSlot()));

    }
    @Test
    void insertTimeSlotException() throws CustomDBException, CustomApplicationException {

        when(timeSlotDao.insertTimeSlot(any(TimeSlot.class),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceTimeSlot.insertTimeSlot(new TimeSlot());});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());

    }

    @Test
    void deleteTimeSlot() throws CustomDBException, CustomApplicationException {

        when(timeSlotDao.deleteTimeSlot(anyInt(),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceTimeSlot.deleteTimeSlot(1));

    }

    @Test
    void deleteTimeSlotException() throws CustomDBException, CustomApplicationException {

        when(timeSlotDao.deleteTimeSlot(anyInt(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceTimeSlot.deleteTimeSlot(1);});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());

    }

    @Test
    void findTimeSlot() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findTimeSlot(anyInt(),any(Connection.class))).thenReturn(new TimeSlot());
        assertNotNull(salonServiceTimeSlot.findTimeSlot(1));

    }
    @Test
    void findTimeSlotException() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findTimeSlot(anyInt(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceTimeSlot.findTimeSlot(3);});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());


    }

    @Test
    void findAllFreeTimeSlotByMaster() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllFreeTimeSlotByMaster(anyInt(),any(LocalDate.class),any(Connection.class))).thenReturn(new ArrayList<>());
        assertNotNull(salonServiceTimeSlot.findAllFreeTimeSlotByMaster(1, LocalDate.now()));
    }
    @Test
    void findAllFreeTimeSlotByMasterException() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllFreeTimeSlotByMaster(anyInt(),any(LocalDate.class),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceTimeSlot.findAllFreeTimeSlotByMaster(3,LocalDate.now());});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());
    }

    @Test
    void findAllFreeTimeSlotByMasterLimitOffset() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllFreeTimeSlotByMasterLimitOffset(anyInt(),any(LocalDate.class),anyInt(),anyInt(),any(Connection.class))).thenReturn(new ArrayList<>());
        assertNotNull(salonServiceTimeSlot.findAllFreeTimeSlotByMasterLimitOffset(1, LocalDate.now(),1,2));

    }
    @Test
    void findAllFreeTimeSlotByMasterLimitOffsetException() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllFreeTimeSlotByMasterLimitOffset(anyInt(),any(LocalDate.class),anyInt(),anyInt(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceTimeSlot.findAllFreeTimeSlotByMasterLimitOffset(3,LocalDate.now(),1,2);});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());

    }

    @Test
    void findAllFreeTimeSlotByMasterDayLong() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllFreeTimeSlotByMasterDayLong(anyInt(),any(LocalDate.class),any(Connection.class))).thenReturn(new ArrayList<>());
        assertNotNull(salonServiceTimeSlot.findAllFreeTimeSlotByMasterDayLong(1, LocalDate.now()));
    }
    @Test
    void findAllFreeTimeSlotByMasterDayLongException() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllFreeTimeSlotByMasterDayLong(anyInt(),any(LocalDate.class),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceTimeSlot.findAllFreeTimeSlotByMasterDayLong(3,LocalDate.now());});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());
    }

    @Test
    void findCountFreeSlotsByMastersDistinct() throws CustomDBException, CustomApplicationException {

        when(timeSlotDao.findCountFreeSlotsByMastersDistinct(anyInt(),any(LocalDate.class),any(Connection.class))).thenReturn(10);
        assertEquals(10, salonServiceTimeSlot.findCountFreeSlotsByMastersDistinct(1, LocalDate.now()));
    }
    @Test
    void findCountFreeSlotsByMastersDistinctException() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findCountFreeSlotsByMastersDistinct(anyInt(),any(LocalDate.class),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceTimeSlot.findCountFreeSlotsByMastersDistinct(3,LocalDate.now());});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());
    }

    @Test
    void findAllFreeTimeSlotByMasterCount() throws CustomDBException, CustomApplicationException {

        when(timeSlotDao.findAllFreeTimeSlotByMasterCount(anyInt(),any(LocalDate.class),any(Connection.class))).thenReturn(10);
        assertEquals(10,salonServiceTimeSlot.findAllFreeTimeSlotByMasterCount(1, LocalDate.now()));

    }
    @Test
    void findAllFreeTimeSlotByMasterCountException() throws CustomDBException, CustomApplicationException {

        when(timeSlotDao.findAllFreeTimeSlotByMasterCount(anyInt(),any(LocalDate.class),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceTimeSlot.findAllFreeTimeSlotByMasterCount(3,LocalDate.now());});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());

    }

    @Test
    void findAllNotFreeTimeSlotByMasterCount() throws CustomDBException, CustomApplicationException {
       when(timeSlotDao.findAllNotFreeTimeSlotByMasterCount(anyInt(),any(LocalDate.class),any(Connection.class))).thenReturn(10);
    assertEquals(10,salonServiceTimeSlot.findAllNotFreeTimeSlotByMasterCount(1, LocalDate.now()));
    }
    @Test
    void findAllNotFreeTimeSlotByMasterCountException() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllNotFreeTimeSlotByMasterCount(anyInt(),any(LocalDate.class),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceTimeSlot.findAllNotFreeTimeSlotByMasterCount(3,LocalDate.now());});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());
    }

    @Test
    void findAllNotFreeTimeSlotByMaster() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllNotFreeTimeSlotByMaster(anyInt(),any(LocalDate.class),any(Connection.class))).thenReturn(new ArrayList<>());
        assertNotNull(salonServiceTimeSlot.findAllNotFreeTimeSlotByMaster(1, LocalDate.now()));

    }
    @Test
    void findAllNotFreeTimeSlotByMasterException() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllNotFreeTimeSlotByMaster(anyInt(),any(LocalDate.class),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceTimeSlot.findAllNotFreeTimeSlotByMaster(3,LocalDate.now());});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());

    }

    @Test
    void findAllNotFreeTimeSlotByMasterLimitOffset() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllNotFreeTimeSlotByMasterLimitOffset(anyInt(),any(LocalDate.class),anyInt(),anyInt(),any(Connection.class))).thenReturn(new ArrayList<>());
        assertNotNull(salonServiceTimeSlot.findAllNotFreeTimeSlotByMasterLimitOffset(1, LocalDate.now(),1,2));
    }
    @Test
    void findAllNotFreeTimeSlotByMasterLimitOffsetException() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllNotFreeTimeSlotByMasterLimitOffset(anyInt(),any(LocalDate.class),anyInt(),anyInt(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceTimeSlot.findAllNotFreeTimeSlotByMasterLimitOffset(3,LocalDate.now(),2,1);});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());
    }

    @Test
    void findAllNotFreeTimeSlotByMasterDayLong() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllNotFreeTimeSlotByMasterDayLong(anyInt(),any(LocalDate.class),any(Connection.class))).thenReturn(new ArrayList<>());
        assertNotNull(salonServiceTimeSlot.findAllNotFreeTimeSlotByMasterDayLong(1, LocalDate.now()));
    }
    @Test
    void findAllNotFreeTimeSlotByMasterDayLongException() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllNotFreeTimeSlotByMasterDayLong(anyInt(),any(LocalDate.class),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceTimeSlot.findAllNotFreeTimeSlotByMasterDayLong(3,LocalDate.now());});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());
    }

    @Test
    void findAllNotFreeTimeSlotByMasterFromDay() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllNotFreeTimeSlotByMasterFromDay(anyInt(),any(LocalDate.class),any(Connection.class))).thenReturn(new ArrayList<>());
        assertNotNull(salonServiceTimeSlot.findAllNotFreeTimeSlotByMasterFromDay(1, LocalDate.now()));
    }
    @Test
    void findAllNotFreeTimeSlotByMasterFromDayException() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllNotFreeTimeSlotByMasterFromDay(anyInt(),any(LocalDate.class),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceTimeSlot.findAllNotFreeTimeSlotByMasterFromDay(3,LocalDate.now());});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());
    }

    @Test
    void findAllTimeSlotByMaster() throws CustomDBException, CustomApplicationException {

        when(timeSlotDao.findAllTimeSlotByMaster(anyInt(),any(LocalDate.class),any(Connection.class))).thenReturn(new ArrayList<>());
        assertNotNull(salonServiceTimeSlot.findAllTimeSlotByMaster(1, LocalDate.now()));

    }
    @Test
    void findAllTimeSlotByMasterException() throws CustomDBException, CustomApplicationException {

        when(timeSlotDao.findAllTimeSlotByMaster(anyInt(),any(LocalDate.class),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceTimeSlot.findAllTimeSlotByMaster(3,LocalDate.now());});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());

    }

    @Test
    void findAllTimeSlots() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllTimeSlots(any(Connection.class))).thenReturn(new ArrayList<>());
        assertNotNull(salonServiceTimeSlot.findAllTimeSlots());
    }

    @Test
    void findAllTimeSlotsException() throws CustomDBException, CustomApplicationException {
        when(timeSlotDao.findAllTimeSlots(any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceTimeSlot.findAllTimeSlots();});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Failed to commit in Service section",thrown.getMessage());
    }

}