package com.example.FinalProjectI.services;

import com.example.FinalProjectI.db.UserDao;
import com.example.FinalProjectI.db.entity.User;
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

public class SalonServiceUser {
    private static final Logger LOGGER = LogManager.getLogger(SalonServiceUser.class);
  private final UserDao userDao;
    /**
     * Sets dao
     * @param userDao object which will be used
     */
  public SalonServiceUser(UserDao userDao)
  {
      this.userDao = userDao;
  }


    /**
     * Inserts User entity into database table
     * @param user entity to be inserted
     * @return true if insert operation went without exception and false otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */

  public boolean insertUser(User user) throws CustomApplicationException {

      boolean result = false;
      Connection connection = null;
                try {
                    connection = MySQLDAOFactory.getConnection();
                    result = userDao.insertUser(user,connection);
                    ConnectionNeedUtil.commit(connection);
              } catch (CustomDBException customDBException) {
                    LOGGER.error(customDBException);
                    CustomDBExceptionHandler.rollBack(connection);
                    throw new CustomApplicationException("Filed to commit in Service section",customDBException.getMessage(),customDBException,customDBException.getErrorCode());
              } finally {
                     CustomDBExceptionHandler.close(null,null,connection);
                  }

      return result;
  }


    /**
     * Deletes user entity from database table
     * @param login login by which user to be deleted
     * @return true if delete operation went without exception and false otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */

    public boolean deleteUser(String login) throws CustomApplicationException {

        boolean result = false;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = userDao.deleteUser(login,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return result;
    }

    /**
     * Validate user entity in database table
     * @param login login by which user to be validated
     * @param  password password by which user to be validated
     * @return true if delete operation went without exception and false otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */


    public boolean validateUser(String login,String password) throws CustomApplicationException {

        boolean result = false;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            result = userDao.validateUser(login,password,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service section",customDBException.getMessage(),customDBException);
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return result;
    }

    /**
     * Find User entity by type
     * @param login id by which TimeSlot to be find
     * @return User entity if find operation went without exception and null otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

    public User findUser(String login) throws CustomApplicationException {

        User user = null;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            user = userDao.findUser(login,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service section",customDBException.getMessage(),customDBException,customDBException.getErrorCode());
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return user;
    }


    /**
     * Find User entity by id
     * @param userId id by which user to be find
     * @return User entity if find operation went without exception and null otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */


    public User findUser(int userId) throws CustomApplicationException {

        User user = null;
        Connection connection = null;
        try {
            connection = MySQLDAOFactory.getConnection();
            user = userDao.findUser(userId,connection);
            ConnectionNeedUtil.commit(connection);
        } catch (CustomDBException customDBException) {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service section",customDBException.getMessage(),customDBException,customDBException.getErrorCode());
        } finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }

        return user;
    }




    /**
     * Updates User entity applied status  by orderId
     * @param login login by which user to be find
     * @param newPassword new password  to update
     * @return true if update operation went without exception and false otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

    public boolean updateUser(String login,String newPassword) throws CustomDBException, CustomApplicationException {
        boolean result = false;
        Connection connection = null;
        try
        {
            connection = MySQLDAOFactory.getConnection();
            result = userDao.updateUser(login,newPassword,connection);
            ConnectionNeedUtil.commit(connection);
        }catch (CustomDBException customDBException)
        {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service section");

        }
        finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }
        return result;
    }

    /**
     * Find all User slot entities
     * @return  List of  User entities  if find operation went without exception and empty list otherwise
     * @throws CustomApplicationException if SQLException at execution query arises
     */

    public List<User> findAllUser() throws CustomDBException, CustomApplicationException {
       List<User> userList = new ArrayList<>();
       Connection connection = null;
        try
        {
            connection = MySQLDAOFactory.getConnection();
            userList = userDao.findAllUser(connection);
            ConnectionNeedUtil.commit(connection);
        }catch (CustomDBException customDBException)
        {
            LOGGER.error(customDBException);
            CustomDBExceptionHandler.rollBack(connection);
            throw new CustomApplicationException("Filed to commit in Service section");

        }
        finally {
            CustomDBExceptionHandler.close(null,null,connection);
        }
        return userList;
    }


}
