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
/**
 * Data access object for Master related entities
 */
public class MySQLMasterDao implements MasterDao {
    private static final Logger LOGGER = LogManager.getLogger(MySQLMasterDao.class);
    /**
     * Inserts Master entity into database table
     * @param con object with database
     * @param master entity to be inserted
     * @return true if insert operation went without exception and false otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
    public boolean insertMaster(Master master, Connection con) throws CustomDBException,SQLException {

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


    /**
     * Deletes Master entity from database table
     * @param con object with database
     * @param login login by which master to be deleted
     * @return true if delete operation went without exception and false otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
    public boolean deleteMaster(String login, Connection con) throws CustomDBException, SQLException{

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
    /**
     * Find Master entity by login
     * @param con object with database
     * @param login login by which master to be find
     * @return Master entity if find operation went without exception and null otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
    public Master findMaster(String login, Connection con) throws CustomDBException,SQLException {

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
    /**
     * Find Master entity by id
     * @param con object with database
     * @param masterId id by which master to be find
     * @return Master entity if find operation went without exception and null otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
    public Master findMasterById(int masterId, Connection con) throws CustomDBException,SQLException {

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
    /**
     * Updates Master entity start time and end time by login
     * @param con object with database
     * @param login login by which master to be updated
     * @param startTime to be updated
     * @param endTime to be updated
     * @return Master entity if delete operation went without exception and null otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
    public boolean updateMaster(String login, LocalTime startTime, LocalTime endTime , Connection con ) throws CustomDBException,SQLException {
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
    /**
     * Find Master entities by filtering name
     * @param con object with database
     * @param orderByColumn column to order by
     * @param orderingType type of ordering
     * @param masterName master name to find
     * @return List of Master entities if find operation went without exception and empty list otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
@Override
public List<Master> findMastersFilterByName(String orderByColumn,String orderingType,String masterName,int limit,int offset,Connection con) throws CustomDBException,SQLException{
    List<Master> masterList = new ArrayList<>();
    PreparedStatement statement = null;
    ResultSet rs = null;
    try
    {
        //SELECT * FROM masters where masters.masterName = ? ORDER BY ? ? LIMIT ? OFFSET ?
        StringBuilder sqlQuery = new StringBuilder(PropUtil.getQuery("findMastersFilterByName"));
        int ind1 = sqlQuery.indexOf("?",sqlQuery.indexOf("?")+1);
        sqlQuery.insert(ind1+1,orderByColumn);
        sqlQuery.deleteCharAt(ind1);
        ind1 = sqlQuery.indexOf("?",sqlQuery.indexOf("?")+1);
        sqlQuery.insert(ind1+1,orderingType);
        sqlQuery.deleteCharAt(ind1);
        statement = con.prepareStatement(sqlQuery.toString());
        statement.setString(1,masterName);
        statement.setInt(2,limit);
        statement.setInt(3,offset);
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
    /**
     * Find Master entities by filtering service name
     * @param con object with database
     * @param orderByColumn column to order by
     * @param orderingType type of ordering
     * @param serviceName service name to find
     * @return List of Master entities if find operation went without exception and empty list otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
    public List<Master> findMastersFilterByService(String orderByColumn,String orderingType,String serviceName,int limit,int offset,Connection con) throws CustomDBException,SQLException{
        List<Master> masterList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            StringBuilder sqlQuery = new StringBuilder(PropUtil.getQuery("findMastersFilterByService"));
            int ind1 = sqlQuery.indexOf("?",sqlQuery.indexOf("?")+1);
            sqlQuery.insert(ind1+1,orderByColumn);
            sqlQuery.deleteCharAt(ind1);
            ind1 = sqlQuery.indexOf("?",sqlQuery.indexOf("?")+1);
            sqlQuery.insert(ind1+1,orderingType);
            sqlQuery.deleteCharAt(ind1);
            statement = con.prepareStatement(sqlQuery.toString());
            statement.setString(1,serviceName);
            statement.setInt(2,limit);
            statement.setInt(3,offset);
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

    /**
     * Find Master entities by filtering service name  and masterName
     * @param con object with database
     * @param orderByColumn column to order by
     * @param orderingType type of ordering
     * @param serviceName service name to find
     * @param masterName masterName to find
     * @return List of Master entities if find operation went without exception and empty list otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
   public  List<Master> findMastersFilterByServiceByName(String orderByColumn,String orderingType,String masterName ,String serviceName,int limit,int offset,Connection con) throws CustomDBException,SQLException{
        List<Master> masterList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            StringBuilder sqlQuery = new StringBuilder(PropUtil.getQuery("findMastersFilterByServiceByName"));
            int ind1 = sqlQuery.indexOf("?",sqlQuery.indexOf("?")+1);
            ind1 = sqlQuery.indexOf("?",ind1+1);
            sqlQuery.insert(ind1+1,orderByColumn);
            sqlQuery.deleteCharAt(ind1);
            ind1 = sqlQuery.indexOf("?",ind1+1);
            sqlQuery.insert(ind1+1,orderingType);
            sqlQuery.deleteCharAt(ind1);
            statement = con.prepareStatement(sqlQuery.toString());
            statement.setString(1,serviceName);
            statement.setString(2,masterName);
            statement.setInt(3,limit);
            statement.setInt(4,offset);
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


    /**
     * Find Master entities ordered
     * @param con object with database
     * @param orderByColumn column to order by
     * @param orderingType type of ordering
     * @return List of Master entities if find operation went without exception and empty list otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
   public List<Master> findMastersOrdered(String orderByColumn,String orderingType,int limit,int offset,Connection con) throws CustomDBException,SQLException{
        List<Master> masterList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery =PropUtil.getQuery("findMastersOrdered")  ;
            sqlQuery = sqlQuery.replaceFirst("[?]",orderByColumn);
            sqlQuery = sqlQuery.replaceFirst("[?]",orderingType);
            statement = con.prepareStatement(sqlQuery);
            statement.setInt(1,limit);
            statement.setInt(2,offset);
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
    /**
     * Find Master entities count filtering by name
     * @param con object with database
     * @param orderByColumn column to order by
     * @param orderingType type of ordering
     * @param masterName master name filter
     * @return Count of master entities entities if find operation went without exception and 0  otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
@Override
   public int findMastersFilterByNameCount(String orderByColumn,String orderingType,String masterName,Connection con) throws CustomDBException, SQLException
    {
        PreparedStatement statement = null;
        ResultSet rs = null;
        int result = 0;
        try
        {
            StringBuilder sqlQuery =  new StringBuilder( PropUtil.getQuery("findMastersFilterByNameCount"));
            int ind1 = sqlQuery.indexOf("?",sqlQuery.indexOf("?")+1);
            sqlQuery.insert(ind1+1,orderByColumn);
            sqlQuery.deleteCharAt(ind1);
            ind1 = sqlQuery.indexOf("?",sqlQuery.indexOf("?")+1);
            sqlQuery.insert(ind1+1,orderingType);
            sqlQuery.deleteCharAt(ind1);
            statement = con.prepareStatement(sqlQuery.toString());
            statement.setString(1,masterName);
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
    /**
     * Find Master entities count filtering by service
     * @param con object with database
     * @param orderByColumn column to order by
     * @param orderingType type of ordering
     * @param serviceName filter
     * @return Count of master entities entities if find operation went without exception and 0  otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
  public int findMastersFilterByServiceCount(String orderByColumn,String orderingType,String serviceName,Connection con) throws CustomDBException, SQLException
    {
        PreparedStatement statement = null;
        ResultSet rs = null;
        int result = 0;
        try
        {
            StringBuilder sqlQuery =  new StringBuilder( PropUtil.getQuery("findMastersFilterByServiceCount"));
            int ind1 = sqlQuery.indexOf("?",sqlQuery.indexOf("?")+1);
            sqlQuery.insert(ind1+1,orderByColumn);
            sqlQuery.deleteCharAt(ind1);
            ind1 = sqlQuery.indexOf("?",sqlQuery.indexOf("?")+1);
            sqlQuery.insert(ind1+1,orderingType);
            sqlQuery.deleteCharAt(ind1);
            statement = con.prepareStatement(sqlQuery.toString());
            statement.setString(1,serviceName);
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
    /**
     * Find Master entities count filtering by service and by master name
     * @param con object with database
     * @param orderByColumn column to order by
     * @param orderingType type of ordering
     * @param serviceName filter
     * @param masterName filter
     * @return Count of master entities entities if find operation went without exception and 0  otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
 @Override
   public int findMastersFilterByServiceByNameCount(String orderByColumn,String orderingType,String masterName ,String serviceName,Connection con) throws CustomDBException, SQLException
    {
        PreparedStatement statement = null;
        ResultSet rs = null;
        int result = 0;
        try
        {
            StringBuilder sqlQuery =  new StringBuilder( PropUtil.getQuery("findMastersFilterByServiceByNameCount"));
            int ind1 = sqlQuery.indexOf("?",sqlQuery.indexOf("?")+1);
            ind1 = sqlQuery.indexOf("?",ind1+1);
            sqlQuery.insert(ind1+1,orderByColumn);
            sqlQuery.deleteCharAt(ind1);
            ind1 = sqlQuery.indexOf("?",ind1+1);
            sqlQuery.insert(ind1+1,orderingType);
            sqlQuery.deleteCharAt(ind1);
            statement = con.prepareStatement(sqlQuery.toString());
            statement.setString(1,serviceName);
            statement.setString(2,masterName);
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
    /**
     * Find Master entities count filtering
     * @param con object with database
     * @param orderByColumn column to order by
     * @param orderingType type of ordering
     * @return Count of master entities entities if find operation went without exception and 0  otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
  public   int findMastersOrderedCount(String orderByColumn,String orderingType,Connection con) throws CustomDBException, SQLException
    {

        PreparedStatement statement = null;
        ResultSet rs = null;
        int result = 0;
        try
        {

            String sqlQuery =   PropUtil.getQuery("findMastersOrderedCount");
            sqlQuery = sqlQuery.replaceFirst("[?]",orderByColumn);
            sqlQuery = sqlQuery.replaceFirst("[?]",orderingType);
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

}
