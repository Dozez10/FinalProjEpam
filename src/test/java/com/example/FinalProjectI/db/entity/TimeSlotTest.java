package com.example.FinalProjectI.db.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
class TimeSlotTest {
    private TimeSlot timeSlot;
    @BeforeAll
    static void someMethodBeforeAll() throws SQLException {

    }
    @BeforeEach
    void initMocks(){
        timeSlot = new TimeSlot();
    }
    @AfterAll
    static void someMethodAfterAll(){

    }

    @Test
    void getTimeSlotId() {
        timeSlot.setTimeSlotId(321321321);
        assertEquals(321321321,timeSlot.getTimeSlotId());
    }

    @Test
    void setTimeSlotId() {
        timeSlot.setTimeSlotId(321321321);
        assertEquals(321321321,timeSlot.getTimeSlotId());
    }

    @Test
    void getMasterId() {
        timeSlot.setMasterId(321321321);
        assertEquals(321321321,timeSlot.getMasterId());
    }

    @Test
    void setMasterId() {
        timeSlot.setMasterId(321321321);
        assertEquals(321321321,timeSlot.getMasterId());
    }

    @Test
    void getStartTime() {
        timeSlot.setStartTime(LocalDateTime.now());
        assertEquals(LocalDateTime.now().getDayOfWeek(),timeSlot.getStartTime().getDayOfWeek());
    }

    @Test
    void setStartTime() {
        timeSlot.setStartTime(LocalDateTime.now());
        assertEquals(LocalDateTime.now().getDayOfWeek(),timeSlot.getStartTime().getDayOfWeek());
    }

    @Test
    void getEndTime() {
        timeSlot.setEndTime(LocalDateTime.now());
        assertEquals(LocalDateTime.now().getDayOfWeek(),timeSlot.getEndTime().getDayOfWeek());
    }

    @Test
    void setEndTime() {
        timeSlot.setEndTime(LocalDateTime.now());
        assertEquals(LocalDateTime.now().getDayOfWeek(),timeSlot.getEndTime().getDayOfWeek());
    }

    @Test
    void isFree() {
        timeSlot.setFree(true);
        assertTrue(timeSlot.isFree());
    }

    @Test
    void setFree() {
        timeSlot.setFree(true);
        assertTrue(timeSlot.isFree());
    }

    @Test
    void testEquals() {
    TimeSlot timeSlot = new TimeSlot();
    timeSlot.setTimeSlotId(3123131);
        TimeSlot timeSlot2 = new TimeSlot();
        timeSlot2.setTimeSlotId(3123131);
        assertEquals(timeSlot2,timeSlot);
    }

    @Test
    void testHashCode() {
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setTimeSlotId(3123131);
        TimeSlot timeSlot2 = new TimeSlot();
        timeSlot2.setTimeSlotId(3123131);
        assertEquals(timeSlot2.hashCode(),timeSlot.hashCode());
    }

    @Test
    void testToString() {
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setTimeSlotId(3123131);
        TimeSlot timeSlot2 = new TimeSlot();
        timeSlot2.setTimeSlotId(3123131);
        assertNotEquals(timeSlot2.toString(),timeSlot.toString());
    }
}