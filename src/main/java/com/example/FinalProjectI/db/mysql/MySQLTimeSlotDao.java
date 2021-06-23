package com.example.FinalProjectI.db.mysql;

import com.example.FinalProjectI.db.TimeSlotDao;
import com.example.FinalProjectI.db.entity.Order;
import com.example.FinalProjectI.db.entity.TimeSlot;
import com.example.FinalProjectI.db.exception.CustomDBException;
import com.example.FinalProjectI.utils.ConnectionNeedUtil;
import com.example.FinalProjectI.utils.PropUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySQLTimeSlotDao implements TimeSlotDao {

    private static final Logger LOGGER = LogManager.getLogger(MySQLTimeSlotDao.class);
    @Override
    public boolean insertTimeSlot(TimeSlot timeSlot, Connection con) throws CustomDBException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean result = false;
        try
        {
            String sqlQuery = PropUtil.getQuery("insertTimeSlot");
            statement = con.prepareStatement(sqlQuery,Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,timeSlot.getMasterId());
            statement.setTimestamp(2, Timestamp.valueOf(timeSlot.getStartTime()));
            statement.setTimestamp(3,Timestamp.valueOf(timeSlot.getEndTime()));
            statement.setBoolean(4,timeSlot.isFree());
            if(statement.executeUpdate()>0)
            {
                rs = statement.getGeneratedKeys();
                rs.next();
                timeSlot.setTimeSlotId(rs.getInt(1));
                result = true;
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
    public boolean deleteTimeSlot(int timeSlotId, Connection con) throws CustomDBException {
        String sqlQuery = null;
        try {
            sqlQuery = PropUtil.getQuery("deleteTimeSlot");
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());
        }
        boolean result = false;
        try(PreparedStatement statement = con.prepareStatement(sqlQuery))
        {
            statement.setInt(1,timeSlotId);
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
    public TimeSlot findTimeSlot(int timeSlotId, Connection con) throws CustomDBException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        TimeSlot timeSlot = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findTimeSlot");
            statement = con.prepareStatement(sqlQuery);
            statement.setInt(1,timeSlotId);
            rs = statement.executeQuery();
            if(rs.next())
            {
                timeSlot = new TimeSlot();
                timeSlot.setMasterId(rs.getInt("masterId"));
                timeSlot.setTimeSlotId(timeSlotId);
                timeSlot.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                timeSlot.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                timeSlot.setFree(rs.getBoolean("isFree"));
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

        return timeSlot;
    }

    @Override
    public  List<TimeSlot> findAllFreeTimeSlotByMaster(int masterId, LocalDate fromWhichDay, Connection con) throws CustomDBException {
        List<TimeSlot> timeSlots = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findAllFreeTimeSlotByMaster");
            statement = con.prepareStatement(sqlQuery);
            statement.setInt(1,masterId);
            statement.setDate(2,Date.valueOf(fromWhichDay));
            rs = statement.executeQuery();
            while(rs.next())
            {
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setTimeSlotId(rs.getInt("timeSlotId"));
                timeSlot.setMasterId(masterId);
                timeSlot.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                timeSlot.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                timeSlot.setFree(rs.getBoolean("isFree"));
                timeSlots.add(timeSlot);
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
        return timeSlots;
    }

    @Override
    public  List<TimeSlot> findAllFreeTimeSlotByMasterLimitOffset(int masterId, LocalDate fromWhichDay, int limit,int offset, Connection con) throws CustomDBException {
        List<TimeSlot> timeSlots = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findAllFreeTimeSlotByMasterLimitOffset");
            statement = con.prepareStatement(sqlQuery);
            statement.setInt(1,masterId);
            statement.setDate(2,Date.valueOf(fromWhichDay));
            statement.setInt(3,limit);
            statement.setInt(4,offset);
            rs = statement.executeQuery();
            while(rs.next())
            {
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setTimeSlotId(rs.getInt("timeSlotId"));
                timeSlot.setMasterId(masterId);
                timeSlot.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                timeSlot.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                timeSlot.setFree(rs.getBoolean("isFree"));
                timeSlots.add(timeSlot);
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
        return timeSlots;
    }


    @Override
    public  List<TimeSlot> findAllFreeTimeSlotByMasterDayLong(int masterId, LocalDate fromWhichDay, Connection con) throws CustomDBException {
        List<TimeSlot> timeSlots = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findAllFreeTimeSlotByMasterDayLong");
            statement = con.prepareStatement(sqlQuery);
            statement.setInt(1,masterId);
            statement.setDate(2,Date.valueOf(fromWhichDay));
            rs = statement.executeQuery();
            while(rs.next())
            {
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setTimeSlotId(rs.getInt("timeSlotId"));
                timeSlot.setMasterId(masterId);
                timeSlot.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                timeSlot.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                timeSlot.setFree(rs.getBoolean("isFree"));
                timeSlots.add(timeSlot);
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
        return timeSlots;
    }


    @Override
    public  int findCountFreeSlotsByMastersDistinct(int masterId, LocalDate fromWhichDay, Connection con) throws CustomDBException {
        int result = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findCountFreeSlotsByMastersDistinct");
            statement = con.prepareStatement(sqlQuery);
            statement.setInt(1,masterId);
            statement.setDate(2,Date.valueOf(fromWhichDay));
            rs = statement.executeQuery();
            if(rs.next())
            {
                result = rs.getInt(1);
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
    public  int findAllFreeTimeSlotByMasterCount(int masterId, LocalDate fromWhichDay, Connection con) throws CustomDBException {
        int result = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findAllFreeTimeSlotByMasterCount");
            statement = con.prepareStatement(sqlQuery);
            statement.setInt(1,masterId);
            statement.setDate(2,Date.valueOf(fromWhichDay));
            rs = statement.executeQuery();
            if(rs.next())
            {
                result = rs.getInt(1);
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
    public  int findAllNotFreeTimeSlotByMasterCount(int masterId, LocalDate fromWhichDay, Connection con) throws CustomDBException {
        int result = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findAllNotFreeTimeSlotByMasterCount");
            statement = con.prepareStatement(sqlQuery);
            statement.setDate(1,Date.valueOf(fromWhichDay));
            statement.setInt(2,masterId);
            rs = statement.executeQuery();
            if(rs.next())
            {
                result = rs.getInt(1);
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
    public  List<TimeSlot> findAllTimeSlotByMaster(int masterId, LocalDate fromWhichDay, Connection con) throws CustomDBException {
        List<TimeSlot> timeSlots = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findAllTimeSlotByMaster");
            statement = con.prepareStatement(sqlQuery);
            statement.setInt(1,masterId);
            statement.setDate(2,Date.valueOf(fromWhichDay));
            rs = statement.executeQuery();
            while(rs.next())
            {
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setTimeSlotId(rs.getInt("timeSlotId"));
                timeSlot.setMasterId(masterId);
                timeSlot.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                timeSlot.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                timeSlot.setFree(rs.getBoolean("isFree"));
                timeSlots.add(timeSlot);
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

        return timeSlots;
    }



    @Override
    public  List<TimeSlot> findAllNotFreeTimeSlotByMaster(int masterId,LocalDate fromWhichDay,Connection con) throws CustomDBException {

        List<TimeSlot> timeSlots = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findAllNotFreeTimeSlotByMaster");
            statement = con.prepareStatement(sqlQuery);
            statement.setDate(1,Date.valueOf(fromWhichDay));
            statement.setInt(2,masterId);
            rs = statement.executeQuery();
            while(rs.next())
            {
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setTimeSlotId(rs.getInt("timeSlotId"));
                timeSlot.setMasterId(masterId);
                timeSlot.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                timeSlot.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                timeSlot.setFree(rs.getBoolean("isFree"));
                timeSlots.add(timeSlot);
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
        return timeSlots;



    }



    @Override
    public  List<TimeSlot> findAllNotFreeTimeSlotByMasterLimitOffset(int masterId,LocalDate fromWhichDay,int limit,int offset,Connection con) throws CustomDBException {

        List<TimeSlot> timeSlots = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findAllNotFreeTimeSlotByMasterLimitOffset");
            statement = con.prepareStatement(sqlQuery);
            statement.setDate(1,Date.valueOf(fromWhichDay));
            statement.setInt(2,masterId);
            statement.setInt(3,limit);
            statement.setInt(4,offset);
            rs = statement.executeQuery();
            while(rs.next())
            {
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setTimeSlotId(rs.getInt("timeSlotId"));
                timeSlot.setMasterId(masterId);
                timeSlot.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                timeSlot.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                timeSlot.setFree(rs.getBoolean("isFree"));
                timeSlots.add(timeSlot);
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
        return timeSlots;



    }


    @Override
    public  List<TimeSlot> findAllNotFreeTimeSlotByMasterDayLong(int masterId,LocalDate fromWhichDay,Connection con) throws CustomDBException {

        List<TimeSlot> timeSlots = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findAllNotFreeTimeSlotByMasterDayLong");
            statement = con.prepareStatement(sqlQuery);
            statement.setDate(1,Date.valueOf(fromWhichDay));
            statement.setInt(2,masterId);
            rs = statement.executeQuery();
            while(rs.next())
            {
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setTimeSlotId(rs.getInt("timeSlotId"));
                timeSlot.setMasterId(masterId);
                timeSlot.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                timeSlot.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                timeSlot.setFree(rs.getBoolean("isFree"));
                timeSlots.add(timeSlot);
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
        return timeSlots;



    }
    @Override
 public    List<TimeSlot> findAllNotFreeTimeSlotByMasterFromDay(int masterId,LocalDate fromWhichDay,Connection con)throws CustomDBException {

        List<TimeSlot> timeSlots = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findAllNotFreeTimeSlotByMasterFromDay");
            statement = con.prepareStatement(sqlQuery);
            statement.setDate(1,Date.valueOf(fromWhichDay));
            statement.setInt(2,masterId);
            rs = statement.executeQuery();
            while(rs.next())
            {
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setTimeSlotId(rs.getInt("timeSlotId"));
                timeSlot.setMasterId(masterId);
                timeSlot.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                timeSlot.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                timeSlot.setFree(rs.getBoolean("isFree"));
                timeSlots.add(timeSlot);
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
        return timeSlots;



    }

    @Override
    public List<TimeSlot> findAllTimeSlots(Connection con) throws CustomDBException {
        List<TimeSlot> timeSlots = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findAllTimeSlots");
            statement = con.prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            while(rs.next())
            {
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setTimeSlotId(rs.getInt("timeSlotId"));
                timeSlot.setMasterId(rs.getInt("masterId"));
                timeSlot.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                timeSlot.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                timeSlot.setFree(rs.getBoolean("isFree"));
                timeSlots.add(timeSlot);
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
        return timeSlots;

    }
}
