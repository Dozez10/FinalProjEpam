package com.example.FinalProjectI.db.mysql;

import com.example.FinalProjectI.db.UserDao;
import com.example.FinalProjectI.db.entity.User;
import com.example.FinalProjectI.db.exception.CustomDBException;
import com.example.FinalProjectI.utils.ConnectionNeedUtil;
import com.example.FinalProjectI.utils.PropUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserDao implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(MySQLUserDao.class);

    @Override
    public boolean insertUser(User user, Connection con) throws CustomDBException {
        PreparedStatement statement = null;
        ResultSet rs = null;
       boolean result = false;
        try{
            String sqlQuery =PropUtil.getQuery("insertUser");
            statement = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,user.getUserType());
            statement.setString(2,user.getUserPassword());
            statement.setString(3,user.getUserEmail());
            statement.setString(4,user.getUserLogin());
            if(statement.executeUpdate()>0){
                rs = statement.getGeneratedKeys();
                rs.next();
                user.setUserId(rs.getInt(1));
                result =  true;
            }

        }

        catch (SQLException sqlException)
        {
            LOGGER.error(sqlException);
            throw new CustomDBException("bad execution",sqlException.getMessage(),sqlException,sqlException.getErrorCode());


        }
        finally
        {

          ConnectionNeedUtil.close(rs,statement,null);

        }
       return result;

    }

    @Override
    public boolean deleteUser(String login,Connection con) throws CustomDBException {

        String sqlQuery = null;
        try {
            sqlQuery = PropUtil.getQuery("deleteUser");
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());
        }
        boolean result = false;
        try(PreparedStatement statement = con.prepareStatement(sqlQuery) )
        {
            statement.setString(1,login);
            if(statement.executeUpdate()>0)result = true;
        }

        catch (SQLException sqlException)
        {

            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());
        }

       return result;
    }

    @Override
    public boolean validateUser(String login, String password,Connection con) throws CustomDBException {

        String sqlQuery = null;
        try {
            sqlQuery = PropUtil.getQuery("validateUser");
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());
        }
        boolean result = false;
        try( PreparedStatement statement = con.prepareStatement(sqlQuery); )
        {
            statement.setString(1,login);
            statement.setString(2,password);
            result =  statement.executeQuery().next();

        }
        catch (SQLException sqlException)
        {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());
        }

       return result;
    }

    @Override
    public User findUser(String login, Connection con) throws CustomDBException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        User user = null;
        try
        {
            String sqlQuery =PropUtil.getQuery("findUser");
            statement = con.prepareStatement(sqlQuery);
            statement.setString(1,login);
            rs = statement.executeQuery();
            if(rs.next())
            {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setUserType(rs.getString(2));
                user.setUserPassword(rs.getString(3));
                user.setUserLogin(login);
                user.setUserEmail(rs.getString(4));

            }

        }

        catch (SQLException sqlException)
        {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());
        }
         finally
        {

        ConnectionNeedUtil.close(rs,statement,null);
        }
       return user;
    }

    @Override
    public boolean updateUser(String login, String newPassword, Connection con) throws CustomDBException {
        String sqlQuery = null;
        try {
            sqlQuery = PropUtil.getQuery("updateUser");
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());
        }
       boolean result = false;
        try(PreparedStatement statement = con.prepareStatement(sqlQuery); ) {
            statement.setString(1,newPassword);
            statement.setString(2,login);
            if(statement.executeUpdate()>0)result =  true;


        }

        catch (SQLException sqlException)
        {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());

        }

       return result;
    }

    @Override
    public List<User> findAllUser(Connection con) throws CustomDBException {
        List<User> userList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery =PropUtil.getQuery("findAllUser");
            statement = con.prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            while(rs.next())
            {
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setUserType(rs.getString("userType"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setUserEmail(rs.getString("userEmail"));
                user.setUserLogin(rs.getString("userLogin"));
                userList.add( user );

            }


        }
        catch (SQLException sqlException)
        {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());
        }
        finally
        {


            ConnectionNeedUtil.close(rs,statement,null);
        }
       return userList;

    }

}
