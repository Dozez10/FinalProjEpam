package com.example.FinalProjectI.db;

import com.example.FinalProjectI.db.entity.Master;
import com.example.FinalProjectI.db.exception.CustomDBException;


import java.sql.Connection;
import java.time.LocalTime;
import java.util.List;

public interface MasterDao {
    boolean insertMaster(Master master, Connection connection) throws CustomDBException;
    int selectCountOfMasters(Connection connection) throws CustomDBException;
    boolean deleteMaster(String login, Connection connection) throws CustomDBException;
    Master findMaster(String login, Connection connection) throws CustomDBException;
    Master findMasterById(int masterId, Connection connection) throws CustomDBException;
    boolean updateMaster(String login, LocalTime startTime, LocalTime endTime, Connection connection) throws CustomDBException;
    List<Master> findMastersFilterByName(String orderByColumn,String orderingType,String masterName,int limit,int offset,Connection connection) throws CustomDBException;
    List<Master> findMastersFilterByService(String orderByColumn,String orderingType,String serviceName,int limit,int offset,Connection connection) throws CustomDBException;
    List<Master> findMastersFilterByServiceByName(String orderByColumn,String orderingType,String masterName ,String serviceName,int limit,int offset,Connection connection) throws CustomDBException;
    List<Master> findMastersOrdered(String orderByColumn,String orderingType,int limit,int offset,Connection connection) throws CustomDBException;
    int findMastersFilterByNameCount(String orderByColumn,String orderingType,String masterName,Connection connection) throws CustomDBException;
    int findMastersFilterByServiceCount(String orderByColumn,String orderingType,String serviceName,Connection connection) throws CustomDBException;
    int findMastersFilterByServiceByNameCount(String orderByColumn,String orderingType,String masterName ,String serviceName,Connection connection) throws CustomDBException;
    int findMastersOrderedCount(String orderByColumn,String orderingType,Connection connection) throws CustomDBException;
    List<Master> findAllMaster(Connection connection) throws CustomDBException;
}