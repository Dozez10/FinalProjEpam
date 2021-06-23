package com.example.FinalProjectI.services;

import com.example.FinalProjectI.db.MasterDao;
import com.example.FinalProjectI.db.entity.Master;
import com.example.FinalProjectI.db.exception.CustomApplicationException;
import com.example.FinalProjectI.db.exception.CustomDBException;
import hthurow.tomcatjndi.TomcatJNDI;
import org.junit.jupiter.api.*;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SalonServiceMasterTest {
    private MasterDao masterDao;
    private SalonServiceMaster salonServiceMaster;
    private static TomcatJNDI tomcatJNDI;
    @BeforeAll
    static void initJNDI(){
        tomcatJNDI = new TomcatJNDI();
        tomcatJNDI.processContextXml(new File("src/main/webapp/META-INF/context.xml"));
        tomcatJNDI.start();
    }
    @BeforeEach
    void initMocks(){
        masterDao = mock(MasterDao.class);
        salonServiceMaster = new SalonServiceMaster(masterDao);
    }
    @AfterAll
    static void clearEnv(){
        tomcatJNDI.tearDown();
    }
    @Test
    void insertMaster() throws CustomDBException, CustomApplicationException, SQLException {
        Master master = new Master();
        when(masterDao.insertMaster(any(Master.class),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceMaster.insertMaster(master));
    }

    @Test
    void insertMasterException() throws CustomDBException, SQLException {
        Master master = new Master();
        when(masterDao.insertMaster(any(Master.class),any(Connection.class))).thenThrow(new SQLException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMaster.insertMaster(master));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());
    }
    @Test
    void deleteMaster() throws CustomDBException, CustomApplicationException, SQLException {
        String login = "asd";
        when(masterDao.deleteMaster(anyString(),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceMaster.deleteMaster(login));
    }
    @Test
    void deleteMasterException() throws CustomDBException, SQLException {
        String login = "asd";
        when(masterDao.deleteMaster(anyString(),any(Connection.class))).thenThrow(new SQLException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMaster.deleteMaster(login));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());
    }
    @Test
    void findMaster() throws CustomDBException, CustomApplicationException, SQLException {
        String login = "asd";
        when(masterDao.findMaster(anyString(),any(Connection.class))).thenReturn(new Master());
        assertNotNull(salonServiceMaster.findMaster(login));
    }
    @Test
    void findMasterException() throws CustomDBException, SQLException {
        String login = "asd";
        when(masterDao.findMaster(anyString(),any(Connection.class))).thenThrow(new SQLException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMaster.findMaster(login));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());
    }
    @Test
    void findMasterById() throws CustomDBException, CustomApplicationException, SQLException {
        int id=1;
        when(masterDao.findMasterById(anyInt(),any(Connection.class))).thenReturn(new Master());
        assertNotNull(salonServiceMaster.findMasterById(id));
    }
    @Test
    void findMasterByIdException() throws CustomDBException, SQLException {
        int id=1;
        when(masterDao.findMasterById(anyInt(),any(Connection.class))).thenThrow(new SQLException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMaster.findMasterById(id));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());
    }

    @Test
    void updateMaster() throws CustomDBException, CustomApplicationException, SQLException {
        String login="login";
        LocalTime localTime1 = LocalTime.now();
        LocalTime localTime2 = LocalTime.now();
        when(masterDao.updateMaster(anyString(),any(LocalTime.class),any(LocalTime.class),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceMaster.updateMaster(login,localTime1,localTime2));
    }
    @Test
    void updateMasterException() throws CustomDBException, CustomApplicationException, SQLException {
        String login="login";
        LocalTime localTime1 = LocalTime.now();
        LocalTime localTime2 = LocalTime.now();
        when(masterDao.updateMaster(anyString(),any(LocalTime.class),any(LocalTime.class),any(Connection.class))).thenThrow(new SQLException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMaster.updateMaster(login,localTime1,localTime2));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());
    }
    @Test
    void findMastersFilterByName() throws CustomDBException, CustomApplicationException, SQLException {
        String orderColumn="column";
        String orderingType="type";
        String masterName="name";
        int limit=5;
        int offset=5;
        when(masterDao.findMastersFilterByName(anyString(),anyString(),anyString(),anyInt(),anyInt(),any(Connection.class))).thenReturn(new ArrayList<>());
        assertNotNull(salonServiceMaster.findMastersFilterByName(orderColumn,orderingType,masterName,limit,offset));
    }
    @Test
    void findMastersFilterByNameException() throws CustomDBException, CustomApplicationException, SQLException {
        String orderColumn="column";
        String orderingType="type";
        String masterName="name";
        int limit=5;
        int offset=5;
        when(masterDao.findMastersFilterByName(anyString(),anyString(),anyString(),anyInt(),anyInt(),any(Connection.class))).thenThrow(new SQLException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMaster.findMastersFilterByName(orderColumn,orderingType,masterName,limit,offset));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());
    }
    @Test
    void findMastersFilterByService() throws CustomDBException, CustomApplicationException, SQLException {
        String orderColumn="column";
        String orderingType="type";
        String serviceName="name";
        int limit=5;
        int offset=5;
        when(masterDao.findMastersFilterByService(anyString(),anyString(),anyString(),anyInt(),anyInt(),any(Connection.class))).thenReturn(new ArrayList<>());
        assertNotNull(salonServiceMaster.findMastersFilterByService(orderColumn,orderingType,serviceName,limit,offset));
    }
    @Test
    void findMastersFilterByServiceException() throws CustomDBException, SQLException {
        String orderColumn="column";
        String orderingType="type";
        String serviceName="name";
        int limit=5;
        int offset=5;
        when(masterDao.findMastersFilterByService(anyString(),anyString(),anyString(),anyInt(),anyInt(),any(Connection.class))).thenThrow(new SQLException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMaster.findMastersFilterByService(orderColumn,orderingType,serviceName,limit,offset));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());
    }
    @Test
    void findMastersFilterByServiceByName() throws CustomDBException, CustomApplicationException, SQLException {
        String orderColumn="column";
        String orderingType="type";
        String serviceName="name";
        String masterName="name";
        int limit=5;
        int offset=5;
        when(masterDao.findMastersFilterByServiceByName(anyString(),anyString(),anyString(),anyString(),anyInt(),anyInt(),any(Connection.class))).thenReturn(new ArrayList<>());
        assertNotNull(salonServiceMaster.findMastersFilterByServiceByName(orderColumn,orderingType,masterName,serviceName,limit,offset));
    }
    @Test
    void findMastersFilterByServiceByNameException() throws CustomDBException, SQLException {
        String orderColumn="column";
        String orderingType="type";
        String serviceName="name";
        String masterName="name";
        int limit=5;
        int offset=5;
        when(masterDao.findMastersFilterByServiceByName(anyString(),anyString(),anyString(),anyString(),anyInt(),anyInt(),any(Connection.class))).thenThrow(new SQLException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMaster.findMastersFilterByServiceByName(orderColumn,orderingType,masterName,serviceName,limit,offset));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());
    }
    @Test
    void findMastersOrdered() throws CustomDBException, CustomApplicationException, SQLException {
        String orderColumn="column";
        String orderingType="type";
        int limit=5;
        int offset=5;
        when(masterDao.findMastersOrdered(anyString(),anyString(),anyInt(),anyInt(),any(Connection.class))).thenReturn(new ArrayList<>());
        assertNotNull(salonServiceMaster.findMastersOrdered(orderColumn,orderingType,limit,offset));
    }
    @Test
    void findMastersOrderedException() throws CustomDBException, SQLException {
        String orderColumn="column";
        String orderingType="type";
        int limit=5;
        int offset=5;
        when(masterDao.findMastersOrdered(anyString(),anyString(),anyInt(),anyInt(),any(Connection.class))).thenThrow(new SQLException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMaster.findMastersOrdered(orderColumn,orderingType,limit,offset));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());
    }
    @Test
    void findMastersFilterByNameCount() throws CustomDBException, CustomApplicationException, SQLException {
        String orderColumn="column";
        String orderingType="type";
         String masterName = "login";
        when(masterDao.findMastersFilterByNameCount(anyString(),anyString(),anyString(),any(Connection.class))).thenReturn(1);
        assertEquals(1,salonServiceMaster.findMastersFilterByNameCount(orderColumn,orderingType,masterName));
    }
    @Test
    void findMastersFilterByNameCountException() throws CustomDBException, SQLException {
        String orderColumn="column";
        String orderingType="type";
        String masterName = "login";
        when(masterDao.findMastersFilterByNameCount(anyString(),anyString(),anyString(),any(Connection.class))).thenThrow(new SQLException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMaster.findMastersFilterByNameCount(orderColumn,orderingType,masterName));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());
    }
    @Test
    void findMastersFilterByServiceCount() throws CustomDBException, CustomApplicationException, SQLException {
        String orderColumn="column";
        String orderingType="type";
        String serviceName = "login";
        when(masterDao.findMastersFilterByServiceCount(anyString(),anyString(),anyString(),any(Connection.class))).thenReturn(1);
        assertEquals(1,salonServiceMaster.findMastersFilterByServiceCount(orderColumn,orderingType,serviceName));
    }
    @Test
    void findMastersFilterByServiceCountException() throws CustomDBException, SQLException {
        String orderColumn="column";
        String orderingType="type";
        String serviceName = "login";
        when(masterDao.findMastersFilterByServiceCount(anyString(),anyString(),anyString(),any(Connection.class))).thenThrow(new SQLException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMaster.findMastersFilterByServiceCount(orderColumn,orderingType,serviceName));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());
    }
    @Test
    void findMastersFilterByServiceByNameCount() throws CustomDBException, CustomApplicationException, SQLException {
        String orderColumn="column";
        String orderingType="type";
        String serviceName = "login";
        String masterName = "login";
        when(masterDao.findMastersFilterByServiceByNameCount(anyString(),anyString(),anyString(),anyString(),any(Connection.class))).thenReturn(1);
        assertEquals(1,salonServiceMaster.findMastersFilterByServiceByNameCount(orderColumn,orderingType,serviceName,masterName));
    }
    @Test
    void findMastersFilterByServiceByNameCountException() throws CustomDBException, SQLException {
        String orderColumn="column";
        String orderingType="type";
        String serviceName = "login";
        String masterName = "login";
        when(masterDao.findMastersFilterByServiceByNameCount(anyString(),anyString(),anyString(),anyString(),any(Connection.class))).thenThrow(new SQLException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMaster.findMastersFilterByServiceByNameCount(orderColumn,orderingType,serviceName,masterName));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());
    }
    @Test
    void findMastersOrderedCount() throws CustomDBException, CustomApplicationException, SQLException {
        String orderColumn="column";
        String orderingType="type";
        when(masterDao.findMastersOrderedCount(anyString(),anyString(),any(Connection.class))).thenReturn(1);
        assertEquals(1,salonServiceMaster.findMastersOrderedCount(orderColumn,orderingType));

    }


    @Test
    void findMastersOrderedCountException() throws CustomDBException, SQLException {
        String orderColumn="column";
        String orderingType="type";
        when(masterDao.findMastersOrderedCount(anyString(),anyString(),any(Connection.class))).thenThrow(new SQLException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMaster.findMastersOrderedCount(orderColumn,orderingType));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());

    }
}