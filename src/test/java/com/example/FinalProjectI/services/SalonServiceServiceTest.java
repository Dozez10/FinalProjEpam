package com.example.FinalProjectI.services;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.example.FinalProjectI.db.ServiceDao;
import com.example.FinalProjectI.db.entity.Service;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SalonServiceServiceTest {

    private ServiceDao serviceDao;
    private SalonServiceService salonServiceService;
    private static TomcatJNDI tomcatJNDI;
    @BeforeAll
    static void initJNDI(){
        tomcatJNDI = new TomcatJNDI();
        tomcatJNDI.processContextXml(new File("src/main/webapp/META-INF/context.xml"));
        tomcatJNDI.start();
    }
    @BeforeEach
    void initMocks(){
        serviceDao = mock(ServiceDao.class);
        salonServiceService = new SalonServiceService(serviceDao);
    }
    @AfterAll
    static void clearEnv(){
        tomcatJNDI.tearDown();
    }

    @Test
    void insertService() throws CustomDBException, CustomApplicationException {

        when(serviceDao.insertService(any(Service.class),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceService.insertService(new Service()));

    }

    @Test
    void insertServiceException() throws CustomDBException, CustomApplicationException {

        when(serviceDao.insertService(any(Service.class),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
      Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceService.insertService(new Service());});
        assertEquals("Filed to commit in Service section",thrown.getMessage());

    }

    @Test
    void deleteService() throws CustomDBException, CustomApplicationException {
        when(serviceDao.deleteService(anyString(),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceService.deleteService("test"));
    }
    @Test
    void deleteServiceException() throws CustomDBException, CustomApplicationException {
        when(serviceDao.deleteService(anyString(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceService.deleteService("test");});
        assertEquals("Filed to commit in Service section",thrown.getMessage());
    }

    @Test
    void updateService() throws CustomDBException, CustomApplicationException {
        when(serviceDao.updateService(anyString(),anyDouble(),any(Connection.class))).thenReturn(true);
        assertTrue(salonServiceService.updateService("test",205d));
    }
    @Test
    void updateServiceException() throws CustomDBException, CustomApplicationException {
        when(serviceDao.updateService(anyString(),anyDouble(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceService.updateService("test",21d);});
        assertEquals("Filed to commit in Service section",thrown.getMessage());
    }

    @Test
    void findService() throws CustomDBException, CustomApplicationException {
        when(serviceDao.findService(anyString(),any(Connection.class))).thenReturn(new Service());
        assertNotNull(salonServiceService.findService("test"));
    }
    @Test
    void findServiceException() throws CustomDBException, CustomApplicationException {
        when(serviceDao.findService(anyString(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceService.findService("test");});
        assertEquals("Filed to commit in Service section",thrown.getMessage());
    }

    @Test
    void findServiceById() throws CustomDBException, CustomApplicationException {
        when(serviceDao.findServiceById(anyInt(),any(Connection.class))).thenReturn(new Service());
        assertNotNull(salonServiceService.findServiceById(23));
    }
    @Test
    void findServiceByIdException() throws CustomDBException, CustomApplicationException {
        when(serviceDao.findServiceById(anyInt(),any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceService.findServiceById(1);});
        assertEquals("Filed to commit in Service section",thrown.getMessage());
    }

    @Test
    void findAllService() throws CustomDBException, CustomApplicationException {
        when(serviceDao.findAllService(any(Connection.class))).thenReturn(new ArrayList<>());
        assertNotNull(salonServiceService.findAllService());
    }
    @Test
    void findAllServiceException() throws CustomDBException, CustomApplicationException {
        when(serviceDao.findAllService(any(Connection.class))).thenThrow(new CustomDBException("DB exception"));
        Throwable thrown = assertThrows(CustomApplicationException.class,()->{ salonServiceService.findAllService();});
        assertEquals("Filed to commit in Service section",thrown.getMessage());
    }

}