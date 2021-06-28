package com.example.FinalProjectI.db.mysql;

import com.example.FinalProjectI.db.ServiceDao;
import com.example.FinalProjectI.db.TimeSlotDao;
import com.example.FinalProjectI.db.entity.TimeSlot;
import com.example.FinalProjectI.db.exception.CustomDBException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
class MySQLTimeSlotDaoTest {
    private TimeSlotDao timeSlotDao;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeAll
    static void someMethodBeforeAll() throws SQLException {

    }
    @BeforeEach
    void initMocks() throws SQLException {
        timeSlotDao = new MySQLTimeSlotDao();
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
    void insertTimeSlot() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.getInt(any())).thenReturn(3213312);
        when(resultSet.next()).thenReturn(true);
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartTime(LocalDateTime.now());
        timeSlot.setEndTime(LocalDateTime.now());
         timeSlot.setFree(false);
         timeSlot.setMasterId(3211);
        assertTrue(timeSlotDao.insertTimeSlot(timeSlot,connection));
    }

    @Test
    void deleteTimeSlot() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        assertTrue(timeSlotDao.deleteTimeSlot(1,connection));
    }

    @Test
    void findTimeSlot() throws CustomDBException, SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.next()).thenReturn(true);
        assertNotNull(timeSlotDao.findTimeSlot(1,connection));
    }

    @Test
    void findAllFreeTimeSlotByMaster() throws CustomDBException, SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        assertNotNull(timeSlotDao.findAllFreeTimeSlotByMaster(11,LocalDate.now(),connection));
    }

    @Test
    void findAllFreeTimeSlotByMasterLimitOffset() throws CustomDBException, SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        assertNotNull(timeSlotDao.findAllFreeTimeSlotByMasterLimitOffset(11,LocalDate.now(),1,2,connection));
    }

    @Test
    void findAllFreeTimeSlotByMasterDayLong() throws CustomDBException, SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        assertNotNull(timeSlotDao.findAllFreeTimeSlotByMasterDayLong(11,LocalDate.now(),connection));
    }

    @Test
    void findCountFreeSlotsByMastersDistinct() throws CustomDBException, SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.next()).thenReturn(true);
        assertEquals(11,timeSlotDao.findCountFreeSlotsByMastersDistinct(11,LocalDate.now(),connection));
    }

    @Test
    void findAllFreeTimeSlotByMasterCount() throws CustomDBException, SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.next()).thenReturn(true);
        assertEquals(11,timeSlotDao.findAllFreeTimeSlotByMasterCount(11,LocalDate.now(),connection));
    }

    @Test
    void findAllNotFreeTimeSlotByMasterCount() throws CustomDBException, SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.next()).thenReturn(true);
        assertEquals(11,timeSlotDao.findAllNotFreeTimeSlotByMasterCount(11,LocalDate.now(),connection));
    }

    @Test
    void findAllTimeSlotByMaster() throws CustomDBException, SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        assertNotNull(timeSlotDao.findAllTimeSlotByMaster(11,LocalDate.now(),connection));
    }

    @Test
    void findAllNotFreeTimeSlotByMaster() throws CustomDBException, SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        assertNotNull(timeSlotDao.findAllNotFreeTimeSlotByMaster(11,LocalDate.now(),connection));
    }

    @Test
    void findAllNotFreeTimeSlotByMasterLimitOffset() throws CustomDBException, SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        assertNotNull(timeSlotDao.findAllNotFreeTimeSlotByMasterLimitOffset(11,LocalDate.now(),1,2,connection));
    }

    @Test
    void findAllNotFreeTimeSlotByMasterDayLong() throws CustomDBException, SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        assertNotNull(timeSlotDao.findAllNotFreeTimeSlotByMasterDayLong(11,LocalDate.now(),connection));

    }

    @Test
    void findAllNotFreeTimeSlotByMasterFromDay() throws CustomDBException, SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        assertNotNull(timeSlotDao.findAllNotFreeTimeSlotByMasterFromDay(11,LocalDate.now(),connection));
    }

    @Test
    void findAllTimeSlots() throws CustomDBException, SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getTimestamp(anyString())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.getTimestamp(anyInt())).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        assertNotNull(timeSlotDao.findAllTimeSlots(connection));
    }
}