package com.example.FinalProjectI.db.mysql;

import com.example.FinalProjectI.db.MasterDao;
import com.example.FinalProjectI.db.entity.Master;
import com.example.FinalProjectI.db.exception.CustomDBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
class MySQLMasterDaoTest {
    private MasterDao masterDao;
   private Connection connection;
   private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    @BeforeEach
    void initMocks() throws SQLException {
        masterDao = new MySQLMasterDao();
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString(),anyInt())).thenReturn(preparedStatement);
    }
    
    @Test
    void insertMaster() throws CustomDBException, SQLException {
          Master master  = new Master();
          master.setUserId(32131);
          master.setMasterName("asdsad");
          master.setUserType("asdasd");
          master.setRating(321321d);
          master.setStartTime(LocalTime.now());
          master.setEndTime(LocalTime.now());
          master.setMasterId(32132131);
        when(preparedStatement.executeUpdate()).thenReturn(10);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(anyInt())).thenReturn(323121);
        when(resultSet.getInt(anyString())).thenReturn(3213213);
        assertTrue(masterDao.insertMaster(master,connection));



    }

    @Test
    void deleteMaster() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        assertTrue(masterDao.deleteMaster("login",connection));
    }

    @Test
    void findMaster() throws CustomDBException, SQLException {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getDouble(anyInt())).thenReturn(11d);
        when(resultSet.getString(anyInt())).thenReturn("s");
        when(resultSet.getTime(anyInt())).thenReturn(Time.valueOf(LocalTime.now()));
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        assertNotNull(masterDao.findMaster("login",connection));
    }

    @Test
    void findMasterById() throws CustomDBException, SQLException {
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getDouble(anyInt())).thenReturn(11d);
        when(resultSet.getString(anyInt())).thenReturn("s");
        when(resultSet.getTime(anyInt())).thenReturn(Time.valueOf(LocalTime.now()));
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        assertNotNull(masterDao.findMasterById(1,connection));
    }

    @Test
    void updateMaster() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        assertTrue(masterDao.updateMaster("Asadsad",LocalTime.now(),LocalTime.now(),connection));
    }

    @Test
    void findMastersFilterByName() throws CustomDBException, SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getDouble(anyInt())).thenReturn(11d);
        when(resultSet.getDouble(anyString())).thenReturn(11d);
        when(resultSet.getString(anyInt())).thenReturn("s");
        when(resultSet.getString(anyString())).thenReturn("s");
        when(resultSet.getTime(anyInt())).thenReturn(Time.valueOf(LocalTime.now()));
        when(resultSet.getTime(anyString())).thenReturn(Time.valueOf(LocalTime.now()));
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        assertNotNull(masterDao.findMastersFilterByName("login","asd","asd",1,2,connection));

    }

    @Test
    void findMastersFilterByService() throws CustomDBException, SQLException {
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getDouble(anyInt())).thenReturn(11d);
        when(resultSet.getDouble(anyString())).thenReturn(11d);
        when(resultSet.getString(anyInt())).thenReturn("s");
        when(resultSet.getString(anyString())).thenReturn("s");
        when(resultSet.getTime(anyInt())).thenReturn(Time.valueOf(LocalTime.now()));
        when(resultSet.getTime(anyString())).thenReturn(Time.valueOf(LocalTime.now()));
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        assertNotNull(masterDao.findMastersFilterByService("login","asd","ASDSAD",1,2,connection));
    }

    @Test
    void findMastersFilterByServiceByName() throws CustomDBException, SQLException {
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getDouble(anyInt())).thenReturn(11d);
        when(resultSet.getDouble(anyString())).thenReturn(11d);
        when(resultSet.getString(anyInt())).thenReturn("s");
        when(resultSet.getString(anyString())).thenReturn("s");
        when(resultSet.getTime(anyInt())).thenReturn(Time.valueOf(LocalTime.now()));
        when(resultSet.getTime(anyString())).thenReturn(Time.valueOf(LocalTime.now()));
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        assertNotNull(masterDao.findMastersFilterByServiceByName("asdsa","login","asd","ASDSAD",1,2,connection));
    }

    @Test
    void findMastersOrdered() throws CustomDBException, SQLException {
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getDouble(anyInt())).thenReturn(11d);
        when(resultSet.getDouble(anyString())).thenReturn(11d);
        when(resultSet.getString(anyInt())).thenReturn("s");
        when(resultSet.getString(anyString())).thenReturn("s");
        when(resultSet.getTime(anyInt())).thenReturn(Time.valueOf(LocalTime.now()));
        when(resultSet.getTime(anyString())).thenReturn(Time.valueOf(LocalTime.now()));
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        assertNotNull(masterDao.findMastersOrdered("asdsa","login",1,2,connection));
    }

    @Test
    void findMastersFilterByNameCount() throws CustomDBException, SQLException {
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getDouble(anyInt())).thenReturn(11d);
        when(resultSet.getDouble(anyString())).thenReturn(11d);
        when(resultSet.getString(anyInt())).thenReturn("s");
        when(resultSet.getString(anyString())).thenReturn("s");
        when(resultSet.getTime(anyInt())).thenReturn(Time.valueOf(LocalTime.now()));
        when(resultSet.getTime(anyString())).thenReturn(Time.valueOf(LocalTime.now()));
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        assertEquals(11,masterDao.findMastersFilterByNameCount("asda","asdsa","login",connection));
    }

    @Test
    void findMastersFilterByServiceCount() throws CustomDBException, SQLException {
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getDouble(anyInt())).thenReturn(11d);
        when(resultSet.getDouble(anyString())).thenReturn(11d);
        when(resultSet.getString(anyInt())).thenReturn("s");
        when(resultSet.getString(anyString())).thenReturn("s");
        when(resultSet.getTime(anyInt())).thenReturn(Time.valueOf(LocalTime.now()));
        when(resultSet.getTime(anyString())).thenReturn(Time.valueOf(LocalTime.now()));
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        assertEquals(11,masterDao.findMastersFilterByServiceCount("asda","asdsa","login",connection));
    }

    @Test
    void findMastersFilterByServiceByNameCount() throws CustomDBException, SQLException {
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getDouble(anyInt())).thenReturn(11d);
        when(resultSet.getDouble(anyString())).thenReturn(11d);
        when(resultSet.getString(anyInt())).thenReturn("s");
        when(resultSet.getString(anyString())).thenReturn("s");
        when(resultSet.getTime(anyInt())).thenReturn(Time.valueOf(LocalTime.now()));
        when(resultSet.getTime(anyString())).thenReturn(Time.valueOf(LocalTime.now()));
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        assertEquals(11,masterDao.findMastersFilterByServiceByNameCount("asdsad","asda","asdsa","login",connection));
    }

    @Test
    void findMastersOrderedCount() throws CustomDBException, SQLException {
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getDouble(anyInt())).thenReturn(11d);
        when(resultSet.getDouble(anyString())).thenReturn(11d);
        when(resultSet.getString(anyInt())).thenReturn("s");
        when(resultSet.getString(anyString())).thenReturn("s");
        when(resultSet.getTime(anyInt())).thenReturn(Time.valueOf(LocalTime.now()));
        when(resultSet.getTime(anyString())).thenReturn(Time.valueOf(LocalTime.now()));
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        assertEquals(11,masterDao.findMastersOrderedCount("asdsad","asda",connection));
    }
}