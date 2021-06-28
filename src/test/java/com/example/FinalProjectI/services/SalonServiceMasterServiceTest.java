package com.example.FinalProjectI.services;

import com.example.FinalProjectI.db.MasterDao;
import com.example.FinalProjectI.db.MasterServiceDao;
import com.example.FinalProjectI.db.entity.Master;
import com.example.FinalProjectI.db.entity.MasterService;
import com.example.FinalProjectI.db.exception.CustomApplicationException;
import com.example.FinalProjectI.db.exception.CustomDBException;
import hthurow.tomcatjndi.TomcatJNDI;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SalonServiceMasterServiceTest {

    private MasterServiceDao masterServiceDao;
    private SalonServiceMasterService salonServiceMasterService;
    private static TomcatJNDI tomcatJNDI;
    @BeforeAll
    static void initJNDI(){
        tomcatJNDI = new TomcatJNDI();
        tomcatJNDI.processContextXml(new File("src/main/webapp/META-INF/context.xml"));
        tomcatJNDI.start();
    }
    @BeforeEach
    void initMocks(){
        masterServiceDao = mock(MasterServiceDao.class);
        salonServiceMasterService = new SalonServiceMasterService(masterServiceDao);
    }
    @AfterAll
    static void clearEnv(){
        tomcatJNDI.tearDown();
    }


    @Test
    void insertMasterService() throws CustomDBException, CustomApplicationException {
        MasterService masterService = new MasterService();
        when(masterServiceDao.insertMasterService(any(MasterService.class),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceMasterService.insertMasterService(masterService));


    }
    @Test
    void insertMasterServiceException() throws CustomDBException {
        MasterService masterService = new MasterService();
        when(masterServiceDao.insertMasterService(any(MasterService.class),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMasterService.insertMasterService(masterService));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());
    }

    @Test
    void deleteMasterService() throws CustomDBException, CustomApplicationException {
        when(masterServiceDao.deleteMasterService(anyInt(),anyInt(),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceMasterService.deleteMasterService(1,1));


    }
    @Test
    void deleteMasterServiceException() throws CustomDBException, CustomApplicationException {
        when(masterServiceDao.deleteMasterService(anyInt(),anyInt(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMasterService.deleteMasterService(1,1));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());

    }
    @Test
    void findAllServicesByMaster() throws CustomDBException, CustomApplicationException {
        when(masterServiceDao.findAllServicesByMaster(anyInt(),any(Connection.class))).thenReturn(new ArrayList<MasterService>());
        assertNotNull(salonServiceMasterService.findAllServicesByMaster(1));
    }
    @Test
    void findAllServicesByMasterException() throws CustomDBException, CustomApplicationException {
        when(masterServiceDao.findAllServicesByMaster(anyInt(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMasterService.findAllServicesByMaster(1));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());
    }

    @Test
    void findAllMastersByService() throws CustomDBException, CustomApplicationException {

        when(masterServiceDao.findAllMastersByService(anyInt(),any(Connection.class))).thenReturn(new ArrayList<MasterService>());
        assertNotNull(salonServiceMasterService.findAllMastersByService(1));

    }
    @Test
    void findAllMastersByServiceException() throws CustomDBException, CustomApplicationException {

        when(masterServiceDao.findAllMastersByService(anyInt(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMasterService.findAllMastersByService(1));
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());

    }
    @Test
    void findAllMasterServices() throws CustomDBException, CustomApplicationException {

        when(masterServiceDao.findAllMasterServices(any(Connection.class))).thenReturn(new ArrayList<MasterService>());
        assertNotNull(salonServiceMasterService.findAllMasterServices());

    }

    @Test
    void findAllMasterServicesException() throws CustomDBException, CustomApplicationException {

        when(masterServiceDao.findAllMasterServices(any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()-> salonServiceMasterService.findAllMasterServices());
        assertEquals("DB exception\n" +
                "\t\tOwn message is Filed to commit in Service Section",thrown.getMessage());

    }




}