package com.example.FinalProjectI.db.mysql;

import com.example.FinalProjectI.db.OrderDao;
import com.example.FinalProjectI.db.ServiceDao;
import com.example.FinalProjectI.db.entity.Service;
import com.example.FinalProjectI.db.exception.CustomDBException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MySQLServiceDaoTest {
    private ServiceDao serviceDao;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeAll
    static void someMethodBeforeAll() throws SQLException {

    }
    @BeforeEach
    void initMocks() throws SQLException {
        serviceDao = new MySQLServiceDao();
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
    void insertService() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.getInt(any())).thenReturn(3213312);
        when(resultSet.next()).thenReturn(true);
        Service service = new Service();
        service.setServiceId(321);
        service.setServicePrice(321d);
        service.setServiceType("asdasd");
        assertTrue(serviceDao.insertService(service,connection));
    }

    @Test
    void deleteService() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        assertTrue(serviceDao.deleteService("asdsad",connection));
    }

    @Test
    void updateService() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        assertTrue(serviceDao.updateService("asd",123123d,connection));
    }

    @Test
    void findService() throws CustomDBException, SQLException {
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getDouble(anyString())).thenReturn(11d);
        when(resultSet.getDouble(anyInt())).thenReturn(11d);
        when(resultSet.getString(anyString())).thenReturn("asd");
        when(resultSet.getString(anyInt())).thenReturn("adsf");
        when(resultSet.next()).thenReturn(true);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        assertNotNull(serviceDao.findService("adssads",connection));
    }

    @Test
    void findServiceById() throws CustomDBException, SQLException {
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getDouble(anyString())).thenReturn(11d);
        when(resultSet.getDouble(anyInt())).thenReturn(11d);
        when(resultSet.getString(anyString())).thenReturn("asd");
        when(resultSet.getString(anyInt())).thenReturn("adsf");
        when(resultSet.next()).thenReturn(true);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        assertNotNull(serviceDao.findServiceById(123,connection));
    }

    @Test
    void findAllService() throws CustomDBException, SQLException {
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getDouble(anyString())).thenReturn(11d);
        when(resultSet.getDouble(anyInt())).thenReturn(11d);
        when(resultSet.getString(anyString())).thenReturn("asd");
        when(resultSet.getString(anyInt())).thenReturn("adsf");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        assertNotNull(serviceDao.findAllService(connection));
    }
}