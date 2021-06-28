package com.example.FinalProjectI.db.mysql;

import com.example.FinalProjectI.db.MasterDao;
import com.example.FinalProjectI.db.MasterServiceDao;
import com.example.FinalProjectI.db.entity.MasterService;
import com.example.FinalProjectI.db.exception.CustomDBException;
import com.example.FinalProjectI.services.SalonServiceMaster;
import com.example.FinalProjectI.services.SalonServiceMasterService;
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
class MySQLMasterServiceDaoTest {


    private MasterServiceDao masterServiceDao;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeAll
    static void someMethodBeforeAll() throws SQLException {

    }
    @BeforeEach
    void initMocks() throws SQLException {
        masterServiceDao = new MySQLMasterServiceDao();
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
    void insertMasterService() throws CustomDBException, SQLException {

        when(preparedStatement.executeUpdate()).thenReturn(10);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.getInt(any())).thenReturn(3213312);
        when(resultSet.next()).thenReturn(true);
        assertTrue(masterServiceDao.insertMasterService(new MasterService(),connection));
    }

    @Test
    void deleteMasterService() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        assertTrue(masterServiceDao.deleteMasterService(1,2,connection));
    }

    @Test
    void findAllServicesByMaster() throws CustomDBException, SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(anyInt())).thenReturn(1);
        when(resultSet.getInt(anyString())).thenReturn(1);
        when(resultSet.getString(anyInt())).thenReturn("s");
        when(resultSet.getString(anyString())).thenReturn("s");
        assertNotNull(masterServiceDao.findAllServicesByMaster(1,connection));
    }

    @Test
    void findAllMastersByService() throws CustomDBException, SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(anyInt())).thenReturn(1);
        when(resultSet.getInt(anyString())).thenReturn(1);
        when(resultSet.getString(anyInt())).thenReturn("s");
        when(resultSet.getString(anyString())).thenReturn("s");
        assertNotNull(masterServiceDao.findAllMastersByService(1,connection));
    }

    @Test
    void findAllMasterServices() throws CustomDBException, SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(anyInt())).thenReturn(1);
        when(resultSet.getInt(anyString())).thenReturn(1);
        when(resultSet.getString(anyInt())).thenReturn("s");
        when(resultSet.getString(anyString())).thenReturn("s");
        assertNotNull(masterServiceDao.findAllMasterServices(connection));
    }
}