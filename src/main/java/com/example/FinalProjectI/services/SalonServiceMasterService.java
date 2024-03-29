package com.example.FinalProjectI.services;

import com.example.FinalProjectI.db.MasterServiceDao;
import com.example.FinalProjectI.db.entity.MasterService;
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

public class SalonServiceMasterService {
    private static final Logger LOGGER = LogManager.getLogger(SalonServiceMasterService.class);
    private MasterServiceDao masterServiceDao ;
    /**
     * Sets dao
     * @param masterServiceDao object which will be used
     */
    public SalonServiceMasterService(MasterServiceDao masterServiceDao){this.masterServiceDao = masterServiceDao;}


    /**
     * Inserts MasterService entity into database table
     * @param masterService entity to be inserted
     * @return true if insert operation went without exception and false otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

    public  boolean insertMasterService(MasterService masterService) throws CustomApplicationException {

        boolean result = false;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterServiceDao.insertMasterService(masterService,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return result;
    }


    /**
     * Deletes MasterService entity from database table
     * @param masterId id by which masterService to be deleted
     * @param serviceId id by which masterService to be deleted
     * @return true if delete operation went without exception and false otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

    public  boolean deleteMasterService(int masterId,int serviceId) throws CustomApplicationException {

        boolean result = false;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterServiceDao.deleteMasterService(masterId,serviceId,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return result;
    }

    /**
     * Find MasterService entities by filtering service name
     @param masterId id to filter by
      * @return List of MasterService entities if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */


    public List<MasterService> findAllServicesByMaster(int masterId) throws CustomApplicationException {

        List<MasterService> masterServiceList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            masterServiceList = masterServiceDao.findAllServicesByMaster(masterId,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return masterServiceList;
    }


    /**
     * Find MasterService entities by filtering service name
     @param serviceId id to filter by
      * @return List of MasterService entities if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */


    public List<MasterService> findAllMastersByService(int serviceId) throws CustomApplicationException {

        List<MasterService> masterServiceList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            masterServiceList = masterServiceDao.findAllMastersByService(serviceId,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return masterServiceList;
    }

    /**
     * Find all MasterService entities
     * @return  List of  MasterService entities  if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

    public List<MasterService> findAllMasterServices() throws CustomApplicationException {

       List<MasterService> masterServiceList = new ArrayList<>();
       Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            masterServiceList = masterServiceDao.findAllMasterServices(connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return masterServiceList;
    }
}
