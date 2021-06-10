package com.example.FinalProjectI.db;

import com.example.FinalProjectI.db.entity.Order;
import com.example.FinalProjectI.db.entity.TimeSlot;
import com.example.FinalProjectI.db.exception.CustomDBException;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public interface TimeSlotDao {
    boolean insertTimeSlot(TimeSlot timeSlot, Connection connection) throws CustomDBException;
    boolean deleteTimeSlot(int timeSlotId, Connection connection) throws CustomDBException;
    TimeSlot findTimeSlot(int timeSlotId , Connection connection) throws CustomDBException;
    List<TimeSlot> findAllFreeTimeSlotByMaster(int masterId,LocalDate fromWhichDay, Connection connection) throws CustomDBException;
    List<TimeSlot> findAllNotFreeTimeSlotByMaster(int masterId,LocalDate fromWhichDay,Connection connection) throws CustomDBException;
    List<TimeSlot> findAllTimeSlots(Connection connection) throws CustomDBException;
}
