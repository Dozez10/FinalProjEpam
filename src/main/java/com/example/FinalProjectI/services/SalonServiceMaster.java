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
import java.time.LocalTime;
import java.util.List;

public class SalonServiceMaster {
    private static final Logger LOGGER = LogManager.getLogger(SalonServiceMaster.class);
    private MasterDao masterDao;
    public SalonServiceMaster(MasterDao masterDao){this.masterDao = masterDao;}
    public boolean insertMaster(Master master) throws CustomApplicationException {

        boolean result = false;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterDao.insertMaster(master,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            System.out.println(customDBException.getMessage());
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return result;
    }
    public boolean deleteMaster(String login) throws CustomApplicationException {

        boolean result = false;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterDao.deleteMaster(login,connection);
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
    public Master findMaster(String login) throws CustomApplicationException {

        Master master = null;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            master = masterDao.findMaster(login,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return master;
    }
    public  Master findMasterById(int masterId) throws CustomApplicationException {

        Master master = null;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            master = masterDao.findMasterById(masterId,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return master;
    }
    public boolean updateMaster(String login, LocalTime startTime, LocalTime endTime) throws CustomApplicationException {

        boolean result = false;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterDao.updateMaster( login,  startTime,  endTime,connection);
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

    public  List<Master> findMastersFilterByName(String orderByColumn,String orderingType,String masterName,int limit,int offset) throws CustomApplicationException {

        List<Master> masterList = null;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            masterList = masterDao.findMastersFilterByName(orderByColumn,orderingType,masterName,limit,offset,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return masterList;
    }
   public List<Master> findMastersFilterByService(String orderByColumn,String orderingType,String serviceName,int limit,int offset) throws CustomApplicationException {

        List<Master> masterList = null;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            masterList = masterDao.findMastersFilterByService(orderByColumn,orderingType,serviceName,limit,offset,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return masterList;
    }

  public List<Master> findMastersFilterByServiceByName(String orderByColumn,String orderingType,String masterName ,String serviceName,int limit,int offset) throws CustomApplicationException {

        List<Master> masterList = null;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            masterList = masterDao.findMastersFilterByServiceByName(orderByColumn,orderingType,masterName,serviceName,limit,offset,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return masterList;
    }

  public   List<Master> findMastersOrdered(String orderByColumn,String orderingType,int limit,int offset) throws CustomApplicationException {

        List<Master> masterList = null;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            masterList = masterDao.findMastersOrdered(orderByColumn,orderingType,limit,offset,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return masterList;
    }
   public int findMastersFilterByNameCount(String orderByColumn,String orderingType,String masterName) throws CustomApplicationException {
        int result ;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterDao.findMastersFilterByNameCount(orderByColumn,orderingType,masterName,connection);
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

  public   int findMastersFilterByServiceCount(String orderByColumn,String orderingType,String serviceName) throws CustomApplicationException {
        int result ;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterDao.findMastersFilterByServiceCount(orderByColumn,orderingType,serviceName,connection);
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

   public int findMastersFilterByServiceByNameCount(String orderByColumn,String orderingType,String masterName ,String serviceName) throws CustomApplicationException {
        int result ;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterDao.findMastersFilterByServiceByNameCount(orderByColumn,orderingType,masterName,serviceName,connection);
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

   public int findMastersOrderedCount(String orderByColumn,String orderingType) throws CustomApplicationException {
        int result ;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterDao.findMastersOrderedCount(orderByColumn,orderingType,connection);
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



    public List<Master> findAllMaster() throws CustomApplicationException {

       List<Master> masterList = null;
       Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            masterList = masterDao.findAllMaster(connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service Section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return masterList;
    }

    public int selectCountOfMasters() throws CustomApplicationException {

        int result = 0;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = masterDao.selectCountOfMasters(connection);
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
}
