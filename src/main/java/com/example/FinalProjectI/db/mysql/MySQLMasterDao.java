package com.example.FinalProjectI.db.mysql;

import com.example.FinalProjectI.db.MasterDao;
import com.example.FinalProjectI.db.entity.Master;
import com.example.FinalProjectI.db.exception.CustomDBException;
import com.example.FinalProjectI.utils.ConnectionNeedUtil;
import com.example.FinalProjectI.utils.PropUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MySQLMasterDao implements MasterDao {
    private static final Logger LOGGER = LogManager.getLogger(MySQLMasterDao.class);
    @Override
    public boolean insertMaster(Master master, Connection con) throws CustomDBException {

        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean result = false;
        try
        {
            String sqlQuery = PropUtil.getQuery("insertMaster");
            statement = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,master.getUserId());
            statement.setString(2,master.getUserType());
            statement.setTime(3, Time.valueOf(master.getStartTime()));
            statement.setTime(4,Time.valueOf(master.getEndTime()));
            statement.setString(5,master.getMasterName());
            statement.setDouble(6,master.getRating());
            if(statement.executeUpdate()>0) {
              rs =   statement.getGeneratedKeys();
              rs.next();
              master.setMasterId(rs.getInt(1));
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
    public int selectCountOfMasters(Connection con) throws CustomDBException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        int result = 0;
        try
        {
            String sqlQuery = PropUtil.getQuery("countMasters");
            statement = con.prepareStatement(sqlQuery);

           rs = statement.executeQuery();
         if(rs.next())result = rs.getInt(1);

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
    public boolean deleteMaster(String login, Connection con) throws CustomDBException {

        String sqlQuery = null;
        try {
            sqlQuery = PropUtil.getQuery("deleteMaster");
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException);
        }
        boolean result = false;
        try( PreparedStatement statement =  con.prepareStatement(sqlQuery) )
        {
            statement.setString(1,login);
            if(statement.executeUpdate()>0)result =  true;
        }

        catch (SQLException sqlException)
        {

            LOGGER.error(sqlException);
            throw new CustomDBException("bad execution",sqlException.getMessage(),sqlException,sqlException.getErrorCode());
        }

    return result;
 }

    @Override
    public Master findMaster(String login, Connection con) throws CustomDBException {

        PreparedStatement statement = null;
        ResultSet rs = null;
        Master master = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findMaster");
            statement = con.prepareStatement(sqlQuery);
            statement.setString(1,login);
            rs = statement.executeQuery();
            if(rs.next())
            {
                master = new Master();
                master.setMasterId(rs.getInt(1));
                master.setUserId(rs.getInt(2));
                master.setUserType(rs.getString(3));
                master.setStartTime(rs.getTime(4).toLocalTime());
                master.setEndTime(rs.getTime(5).toLocalTime());
                master.setMasterName(rs.getString(6));
                master.setRating(rs.getDouble(7));

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

     return master;
 }
    @Override
    public Master findMasterById(int masterId, Connection con) throws CustomDBException {

        PreparedStatement statement = null;
        ResultSet rs = null;
        Master master = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findMasterById");
            statement = con.prepareStatement(sqlQuery);
            statement.setInt(1,masterId);
            rs = statement.executeQuery();
            if(rs.next())
            {
                master = new Master();
                master.setMasterId(rs.getInt(1));
                master.setUserId(rs.getInt(2));
                master.setUserType(rs.getString(3));
                master.setStartTime(rs.getTime(4).toLocalTime());
                master.setEndTime(rs.getTime(5).toLocalTime());
                master.setMasterName(rs.getString(6));
                master.setRating(rs.getDouble(7));
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

        return master;
    }

    @Override
    public boolean updateMaster(String login, LocalTime startTime, LocalTime endTime , Connection con ) throws CustomDBException {
        String sqlQuery = null;
        try {
            sqlQuery = PropUtil.getQuery("updateMaster");
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException);
        }
        boolean result = false;
        try( PreparedStatement statement = con.prepareStatement(sqlQuery) ){
            statement.setTime(1,Time.valueOf(startTime));
            statement.setTime(2,Time.valueOf(endTime));
            statement.setString(3,login);
            if(statement.executeUpdate()>0)result = true;
        }

        catch (SQLException sqlException)
        {
            LOGGER.error(sqlException);
            throw new CustomDBException("bad execution",sqlException.getMessage(),sqlException,sqlException.getErrorCode());
        }


       return result;
 }

    @Override
    public List<Master> findAllMaster(Connection con) throws CustomDBException {
        List<Master> masterList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery =PropUtil.getQuery("findAllMaster")  ;
            statement = con.prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            while(rs.next())
            {
                Master master = new Master();
                master.setMasterId(rs.getInt("masterId"));
                master.setUserId(rs.getInt("userId"));
                master.setUserType(rs.getString("userType"));
                master.setStartTime(rs.getTime("startTime").toLocalTime());
                master.setEndTime(rs.getTime("endTime").toLocalTime());
                master.setMasterName(rs.getString("masterName"));
                master.setRating(rs.getDouble("rating"));
                masterList.add(master);
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

       return masterList;
   }
}