package com.example.FinalProjectI.db.mysql;

import com.example.FinalProjectI.db.TimeSlotDao;
import com.example.FinalProjectI.db.UserDao;
import com.example.FinalProjectI.db.entity.User;
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
class MySQLUserDaoTest {
    private UserDao userDao;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeAll
    static void someMethodBeforeAll() throws SQLException {

    }
    @BeforeEach
    void initMocks() throws SQLException {
        userDao = new MySQLUserDao();
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
    void insertUser() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.getInt(any())).thenReturn(3213312);
        when(resultSet.next()).thenReturn(true);
        User user = new User();
        user.setUserLogin("asda");
        user.setUserType("asdsad");
        user.setUserPassword("asdad");
        user.setUserEmail("sadasd");
        assertTrue(userDao.insertUser(user,connection));
    }

    @Test
    void deleteUser() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        assertTrue(userDao.deleteUser("sasd",connection));
    }

    @Test
    void validateUser() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        assertTrue(userDao.validateUser("sasd","asdasd",connection));
    }

    @Test
    void findUser() throws CustomDBException, SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getString(anyString())).thenReturn("asdsadsa");
        when(resultSet.getString(anyInt())).thenReturn("asdsadads");
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.next()).thenReturn(true);
    assertNotNull(userDao.findUser(1,connection));
    }

    @Test
    void testFindUser() throws CustomDBException, SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getString(anyString())).thenReturn("asdsadsa");
        when(resultSet.getString(anyInt())).thenReturn("asdsadads");
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.next()).thenReturn(true);
        assertNotNull(userDao.findUser("asdasd",connection));
    }

    @Test
    void updateUser() throws CustomDBException, SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(10);
        assertTrue(userDao.updateUser("asdasddsa","asdasd",connection));
    }

    @Test
    void findAllUser() throws CustomDBException, SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getString(anyString())).thenReturn("asdsadsa");
        when(resultSet.getString(anyInt())).thenReturn("asdsadads");
        when(resultSet.getInt(anyString())).thenReturn(11);
        when(resultSet.getInt(anyInt())).thenReturn(11);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        assertNotNull(userDao.findAllUser(connection));
    }
}