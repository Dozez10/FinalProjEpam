package com.example.FinalProjectI.services;

import com.example.FinalProjectI.db.ServiceDao;
import com.example.FinalProjectI.db.entity.Service;
import com.example.FinalProjectI.db.exception.CustomApplicationException;
import com.example.FinalProjectI.db.exception.CustomDBException;
import com.example.FinalProjectI.db.handler.CustomDBExceptionHandler;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.utils.ConnectionNeedUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SalonServiceService {
    private static final Logger LOGGER = LogManager.getLogger(SalonServiceService.class);
    private ServiceDao serviceDao ;
    /**
     * Sets dao
     * @param serviceDao object which will be used
     */
    public SalonServiceService(ServiceDao serviceDao){this.serviceDao = serviceDao;}


    /**
     * Inserts Servce entity into database table
     * @param service entity to be inserted
     * @return true if insert operation went without exception and false otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

    public boolean insertService(Service service) throws CustomApplicationException {
        boolean result = false;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            result = serviceDao.insertService(service,connection);
            ConnectionNeedUtil.commit(connection);


        }
        catch (CustomDBException customDBException)
        {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service section");
        }
        finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }
        return  result;
    }

    /**
     * Deletes Service entity from database table
     * @param serviceType type by which service to be deleted
     * @return true if delete operation went without exception and false otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

    public  boolean deleteService(String serviceType) throws CustomApplicationException {
        boolean result = false;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            result = serviceDao.deleteService(serviceType,connection);
            ConnectionNeedUtil.commit(connection);


        }
        catch (CustomDBException customDBException)
        {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service section");
        }
        finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }
        return  result;
    }

    /**
     * Updates Service entity price   by service type
     * @param serviceType type by which service to be find
     * @param newPrice new price  to update
     * @return true if update operation went without exception and false otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

    public  boolean updateService(String serviceType, double newPrice) throws CustomApplicationException {
        boolean result = false;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            result = serviceDao.updateService(serviceType,newPrice,connection);
            ConnectionNeedUtil.commit(connection);


        }
        catch (CustomDBException customDBException)
        {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service section");
        }
        finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }
        return  result;
    }
    /**
     * Find Service entity by type
     * @param type type by which service to be find
     * @return Service entity if find operation went without exception and null otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */
    public   Service findService(String type) throws CustomApplicationException {
    Service service = null;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            service = serviceDao.findService(type,connection);
            ConnectionNeedUtil.commit(connection);


        }
        catch (CustomDBException customDBException)
        {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service section");
        }
        finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }
        return  service;
    }

    /**
     * Find Service entity by id
     * @param serviceId id by which order to be find
     * @return Service entity if find operation went without exception and null otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */

    public Service findServiceById(int serviceId) throws CustomApplicationException {
        Service service = null;
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            service = serviceDao.findServiceById(serviceId,connection);
            ConnectionNeedUtil.commit(connection);


        }
        catch (CustomDBException customDBException)
        {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service section");
        }
        finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }
        return  service;
    }

    /**
     * Find all Service entities
     * @return  List of  Service entities  if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

    public List<Service> findAllService() throws CustomApplicationException {
        List<Service> serviceList = new ArrayList<>();
        Connection connection = null;
        try{
            connection = MySQLDAOFactory.getConnection();
            serviceList = serviceDao.findAllService(connection);
            ConnectionNeedUtil.commit(connection);


        }
        catch (CustomDBException customDBException)
        {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service section");
        }
        finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }
        return  serviceList;
    }



}
