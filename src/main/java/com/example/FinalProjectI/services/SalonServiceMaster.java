package com.example.FinalProjectI.services;

import com.example.FinalProjectI.db.MasterDao;
import com.example.FinalProjectI.db.entity.Master;
import com.example.FinalProjectI.db.exception.CustomApplicationException;
import com.example.FinalProjectI.db.exception.CustomDBException;
import com.example.FinalProjectI.db.handler.CustomDBExceptionHandler;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.utils.ConnectionNeedUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
/**
 * Master layer for Car entity
 */
public class SalonServiceMaster {
    private static final Logger LOGGER = LogManager.getLogger(SalonServiceMaster.class);
    private MasterDao masterDao;


    /**
     * Sets dao
     * @param masterDao object which will be used
     */

    public SalonServiceMaster(MasterDao masterDao){this.masterDao = masterDao;}

    /**
     * Inserts Master entity into database table
     * @param master entity to be inserted
     * @return true if insert operation went without exception and false otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

    public boolean insertMaster(Master master) throws CustomApplicationException {

        boolean result = false;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterDao.insertMaster(master,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException | SQLException customDBException) {
            System.out.println(customDBException.getMessage());
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return result;
    }

    /**
     * Deletes Master entity from database table
     * @param login login by which master to be deleted
     * @return true if delete operation went without exception and false otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

    public boolean deleteMaster(String login) throws CustomApplicationException {

        boolean result = false;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterDao.deleteMaster(login,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException | SQLException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return result;
    }

    /**
     * Find Master entity by login
     * @param login login by which master to be find
     * @return Master entity if find operation went without exception and null otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */


    public Master findMaster(String login) throws CustomApplicationException {

        Master master = null;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            master = masterDao.findMaster(login,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException | SQLException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return master;
    }
    /**
     * Find Master entity by id
     * @param masterId id by which master to be find
     * @return Master entity if find operation went without exception and null otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */
    public  Master findMasterById(int masterId) throws CustomApplicationException,SQLException {

        Master master = null;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            master = masterDao.findMasterById(masterId,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException | SQLException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return master;
    }


    /**
     * Updates Master entity start time and end time by login
     * @param login login by which master to be updated
     * @param startTime to be updated
     * @param endTime to be updated
     * @return Master entity if delete operation went without exception and null otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

    public boolean updateMaster(String login, LocalTime startTime, LocalTime endTime) throws CustomApplicationException {

        boolean result = false;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterDao.updateMaster( login,  startTime,  endTime,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException | SQLException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return result;
    }


    /**
     * Find Master entities by filtering name
     * @param orderByColumn column to order by
     * @param orderingType type of ordering
     * @param masterName master name to find
     * @return List of Master entities if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

    public  List<Master> findMastersFilterByName(String orderByColumn,String orderingType,String masterName,int limit,int offset) throws CustomApplicationException {

        List<Master> masterList = null;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            masterList = masterDao.findMastersFilterByName(orderByColumn,orderingType,masterName,limit,offset,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException | SQLException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return masterList;
    }
    /**
     * Find Master entities by filtering service name
     * @param orderByColumn column to order by
     * @param orderingType type of ordering
     * @param serviceName service name to find
     * @return List of Master entities if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */
   public List<Master> findMastersFilterByService(String orderByColumn,String orderingType,String serviceName,int limit,int offset) throws CustomApplicationException {

        List<Master> masterList = null;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            masterList = masterDao.findMastersFilterByService(orderByColumn,orderingType,serviceName,limit,offset,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException | SQLException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return masterList;
    }
    /**
     * Find Master entities by filtering service name  and masterName
     * @param orderByColumn column to order by
     * @param orderingType type of ordering
     * @param serviceName service name to find
     * @param masterName masterName to find
     * @return List of Master entities if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */
  public List<Master> findMastersFilterByServiceByName(String orderByColumn,String orderingType,String masterName ,String serviceName,int limit,int offset) throws CustomApplicationException {

        List<Master> masterList = null;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            masterList = masterDao.findMastersFilterByServiceByName(orderByColumn,orderingType,masterName,serviceName,limit,offset,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException | SQLException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return masterList;
    }
    /**
     * Find Master entities ordered
     * @param orderByColumn column to order by
     * @param orderingType type of ordering
     * @return List of Master entities if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */
  public   List<Master> findMastersOrdered(String orderByColumn,String orderingType,int limit,int offset) throws CustomApplicationException {

        List<Master> masterList = null;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            masterList = masterDao.findMastersOrdered(orderByColumn,orderingType,limit,offset,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException | SQLException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return masterList;
    }

    /**
     * Find Master entities count filtering by name
     * @param orderByColumn column to order by
     * @param orderingType type of ordering
     * @param masterName master name filter
     * @return Count of master entities entities if find operation went without exception and 0  otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

   public int findMastersFilterByNameCount(String orderByColumn,String orderingType,String masterName) throws CustomApplicationException {
        int result ;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterDao.findMastersFilterByNameCount(orderByColumn,orderingType,masterName,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException | SQLException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return result;
    }


    /**
     * Find Master entities count filtering by service
     * @param orderByColumn column to order by
     * @param orderingType type of ordering
     * @param serviceName filter
     * @return Count of master entities entities if find operation went without exception and 0  otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

  public   int findMastersFilterByServiceCount(String orderByColumn,String orderingType,String serviceName) throws CustomApplicationException {
        int result ;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterDao.findMastersFilterByServiceCount(orderByColumn,orderingType,serviceName,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException | SQLException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return result;
    }


    /**
     * Find Master entities count filtering by service and by master name
     * @param orderByColumn column to order by
     * @param orderingType type of ordering
     * @param serviceName filter
     * @param masterName filter
     * @return Count of master entities entities if find operation went without exception and 0  otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

   public int findMastersFilterByServiceByNameCount(String orderByColumn,String orderingType,String masterName ,String serviceName) throws CustomApplicationException {
        int result ;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterDao.findMastersFilterByServiceByNameCount(orderByColumn,orderingType,masterName,serviceName,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException | SQLException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return result;
    }


    /**
     * Find Master entities count filtering
     * @param orderByColumn column to order by
     * @param orderingType type of ordering
     * @return Count of master entities entities if find operation went without exception and 0  otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

   public int findMastersOrderedCount(String orderByColumn,String orderingType) throws CustomApplicationException {
        int result ;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterDao.findMastersOrderedCount(orderByColumn,orderingType,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException | SQLException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return result;
    }


}
