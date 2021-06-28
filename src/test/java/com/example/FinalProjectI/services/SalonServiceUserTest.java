package com.example.FinalProjectI.services;

import com.example.FinalProjectI.db.TimeSlotDao;
import com.example.FinalProjectI.db.UserDao;
import com.example.FinalProjectI.db.entity.MasterService;
import com.example.FinalProjectI.db.entity.TimeSlot;
import com.example.FinalProjectI.db.entity.User;
import com.example.FinalProjectI.db.exception.CustomApplicationException;
import com.example.FinalProjectI.db.exception.CustomDBException;
import hthurow.tomcatjndi.TomcatJNDI;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
class SalonServiceUserTest {
    private UserDao userDao;
    private SalonServiceUser salonServiceUser;
    private static TomcatJNDI tomcatJNDI;
    @BeforeAll
    static void initJNDI(){
        tomcatJNDI = new TomcatJNDI();
        tomcatJNDI.processContextXml(new File("src/main/webapp/META-INF/context.xml"));
        tomcatJNDI.start();
    }
    @BeforeEach
    void initMocks(){
        userDao = mock(UserDao.class);
        salonServiceUser = new SalonServiceUser(userDao);
    }
    @AfterAll
    static void clearEnv(){
        tomcatJNDI.tearDown();
    }

    @Test
    void insertUser() throws CustomDBException, CustomApplicationException {
      when(userDao.insertUser(any(User.class),any(Connection.class))).thenReturn(true);
      assertTrue(salonServiceUser.insertUser(new User()));

    }
    @Test
    void insertUserException() throws CustomDBException, CustomApplicationException {
        when(userDao.insertUser(any(User.class),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceUser.insertUser(new User());});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service section",thrown.getMessage());

    }

    @Test
    void deleteUser() throws CustomDBException, CustomApplicationException {
        when(userDao.deleteUser(anyString(),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceUser.deleteUser("test"));

    }
    @Test
    void deleteUserException() throws CustomDBException, CustomApplicationException {
        when(userDao.deleteUser(anyString(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceUser.deleteUser("test");});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service section",thrown.getMessage());


    }

    @Test
    void validateUser() throws CustomDBException, CustomApplicationException {

        when(userDao.validateUser(anyString(),anyString(),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceUser.validateUser("test","asdasd"));


    }
    @Test
    void validateUserException() throws CustomDBException, CustomApplicationException {

        when(userDao.validateUser(anyString(),anyString(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceUser.validateUser("test","asdasd");});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service section",thrown.getMessage());


    }
    @Test
    void findUser() throws CustomDBException, CustomApplicationException {
        when(userDao.findUser(anyString(),any(Connection.class))).thenReturn(new User());
        assertNotNull(salonServiceUser.findUser("test"));
    }
    @Test
    void findUserException() throws CustomDBException, CustomApplicationException {
        when(userDao.findUser(anyString(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceUser.findUser("test");});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service section",thrown.getMessage());
    }

    @Test
    void FindUserById() throws CustomDBException, CustomApplicationException {
        when(userDao.findUser(anyInt(),any(Connection.class))).thenReturn(new User());
        assertNotNull(salonServiceUser.findUser(1));
    }
    @Test
    void FindUserByIdException() throws CustomDBException, CustomApplicationException {
        when(userDao.findUser(anyInt(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceUser.findUser(1);});
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service section",thrown.getMessage());
    }

    @Test
    void updateUser() throws CustomDBException, CustomApplicationException {
        when(userDao.updateUser(anyString(),anyString(),any(Connection.class))).thenReturn(true);
        assertNotNull(salonServiceUser.updateUser("test","asdasd"));
    }
    @Test
    void updateUserException() throws CustomDBException, CustomApplicationException {
        when(userDao.updateUser(anyString(),anyString(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{salonServiceUser.updateUser("test","asdasd");});
        assertEquals("Filed to commit in Service section",thrown.getMessage());;
    }

    @Test
    void findAllUser() throws CustomDBException, CustomApplicationException {
        when(userDao.findAllUser(any(Connection.class))).thenReturn(new ArrayList<>());
        assertNotNull(salonServiceUser.findAllUser());
    }
    @Test
    void findAllUserException() throws CustomDBException, CustomApplicationException {
        when(userDao.findAllUser(any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{salonServiceUser.findAllUser();});
        assertEquals("Filed to commit in Service section",thrown.getMessage());;

    }
}