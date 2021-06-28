package com.example.FinalProjectI.db.entity;

import com.example.FinalProjectI.db.MasterDao;
import com.example.FinalProjectI.services.SalonServiceMaster;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
class MasterTest {
 private Master master;


    @BeforeAll
    static void someMethodBeforeAll() throws SQLException {

    }
    @BeforeEach
    void initMocks(){
      master = new Master();
    }
    @AfterAll
    static void someMethodAfterAll(){

    }

    @Test
    void getMasterId() {
        master.setMasterId(11);
     assertEquals(11,master.getMasterId());
    }

    @Test
    void setMasterId() {
        master.setMasterId(11);
        assertEquals(11,master.getMasterId());
    }

    @Test
    void getUserId() {
        master.setUserId(121);
        assertEquals(121,master.getUserId());
    }

    @Test
    void setUserId() {
       master.setUserId(121);
       assertEquals(121,master.getUserId());
    }

    @Test
    void getUserType() {
       master.setUserType("asdsaddsa");
       assertEquals("asdsaddsa",master.getUserType());
    }

    @Test
    void setUserType() {
       master.setUserType("asdsaddsa");
       assertEquals("asdsaddsa",master.getUserType());
    }

    @Test
    void getStartTime() {
       master.setStartTime(LocalTime.now());
       assertEquals(LocalTime.now().truncatedTo(ChronoUnit.MINUTES),master.getStartTime().truncatedTo(ChronoUnit.MINUTES));
    }

    @Test
    void setStartTime() {
       master.setStartTime(LocalTime.now());
       assertEquals(LocalTime.now().truncatedTo(ChronoUnit.MINUTES),master.getStartTime().truncatedTo(ChronoUnit.MINUTES));
    }

    @Test
    void getEndTime() {
       master.setEndTime(LocalTime.now());
       assertEquals(LocalTime.now().truncatedTo(ChronoUnit.MINUTES),master.getEndTime().truncatedTo(ChronoUnit.MINUTES));
    }

    @Test
    void setEndTime() {
       master.setEndTime(LocalTime.now());
       assertEquals(LocalTime.now().truncatedTo(ChronoUnit.MINUTES),master.getEndTime().truncatedTo(ChronoUnit.MINUTES));
    }

    @Test
    void getMasterName() {
       master.setMasterName("asdasd");
       assertEquals("asdasd",master.getMasterName());
    }

    @Test
    void setMasterName() {
       master.setMasterName("asdasd");
       assertEquals("asdasd",master.getMasterName());
    }

    @Test
    void getRating() {
       master.setRating(123d);
       assertEquals(123d,master.getRating());
    }

    @Test
    void setRating() {
       master.setRating(123d);
       assertEquals(123d,master.getRating());
    }

    @Test
    void testNotEquals() {
       Master master = new Master();
       master.setMasterId(11);
       Master master2 = new Master();
       master2.setMasterId(12);
       assertNotEquals(master2,master);
    }
   @Test
   void testEquals() {
      Master master = new Master();
      master.setMasterId(11);
      Master master2 = new Master();
      master2.setUserId(11);
      assertEquals(master2,master2);
   }
    @Test
    void testHashCode() {
       Master master = new Master();
       master.setMasterId(11);
       Master master2 = new Master();
       master2.setMasterId(11);
       assertEquals(master2.hashCode(),master.hashCode());
    }

    @Test
    void testToString() {
       User user = new User();
       user.setUserId(21212);
       User user2 = new User();
       user2.setUserId(21212);
       assertNotEquals(user2.toString(),user.toString());
    }
}