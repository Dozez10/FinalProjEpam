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
    List<TimeSlot> findAllTimeSlotByMaster(int masterId,LocalDate fromWhichDay, Connection connection) throws CustomDBException;
    List<TimeSlot> findAllFreeTimeSlotByMaster(int masterId,LocalDate fromWhichDay, Connection connection) throws CustomDBException;
    List<TimeSlot> findAllFreeTimeSlotByMasterLimitOffset(int masterId,LocalDate fromWhichDay,int limit,int offset, Connection connection) throws CustomDBException;
    List<TimeSlot> findAllFreeTimeSlotByMasterDayLong(int masterId,LocalDate fromWhichDay, Connection connection) throws CustomDBException;
    int findCountFreeSlotsByMastersDistinct(int masterId,LocalDate fromWhichDay, Connection connection) throws CustomDBException;
    int findAllFreeTimeSlotByMasterCount(int masterId,LocalDate fromWhichDay, Connection connection) throws CustomDBException;
    int findAllNotFreeTimeSlotByMasterCount(int masterId,LocalDate fromWhichDay, Connection connection) throws CustomDBException;
    List<TimeSlot> findAllNotFreeTimeSlotByMaster(int masterId,LocalDate fromWhichDay,Connection connection) throws CustomDBException;
    List<TimeSlot> findAllNotFreeTimeSlotByMasterLimitOffset(int masterId,LocalDate fromWhichDay,int limit,int offset,Connection connection) throws CustomDBException;
    List<TimeSlot> findAllNotFreeTimeSlotByMasterDayLong(int masterId,LocalDate fromWhichDay,Connection connection) throws CustomDBException;
    List<TimeSlot> findAllNotFreeTimeSlotByMasterFromDay(int masterId,LocalDate fromWhichDay,Connection connection) throws CustomDBException;
    List<TimeSlot> findAllTimeSlots(Connection connection) throws CustomDBException;
}
