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
  public SalonServiceUser(UserDao userDao)
  {
      this.userDao = userDao;
  }
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
