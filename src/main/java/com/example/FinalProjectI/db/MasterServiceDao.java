package com.example.FinalProjectI.db;

import com.example.FinalProjectI.db.entity.MasterService;
import com.example.FinalProjectI.db.exception.CustomDBException;

import java.sql.Connection;
import java.util.List;

public interface MasterServiceDao {
    boolean insertMasterService(MasterService masterService, Connection connection) throws CustomDBException;
    boolean deleteMasterService(int masterId,int serviceId, Connection connection) throws CustomDBException;
    List<MasterService> findAllServicesByMaster(int masterId,Connection connection) throws CustomDBException;
    List<MasterService> findAllMastersByService(int serviceId, Connection connection) throws CustomDBException;
    List<MasterService> findAllMasterServices(Connection connection) throws CustomDBException;
}
