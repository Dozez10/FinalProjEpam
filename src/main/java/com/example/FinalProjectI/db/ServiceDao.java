package com.example.FinalProjectI.db;

import com.example.FinalProjectI.db.entity.Service;
import com.example.FinalProjectI.db.exception.CustomDBException;

import java.sql.Connection;
import java.util.List;

public interface ServiceDao {
    boolean insertService(Service service, Connection connection) throws CustomDBException;
    boolean deleteService(String serviceType, Connection connection) throws CustomDBException;
    boolean updateService(String serviceType, double newPrice, Connection connection) throws CustomDBException;
    Service findService(String type, Connection connection) throws CustomDBException;
    Service findServiceById(int serviceId, Connection connection) throws CustomDBException;
    List<Service> findAllService(Connection connection) throws CustomDBException;
}
