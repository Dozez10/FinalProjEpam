package com.example.FinalProjectI.db.mysql;

import com.example.FinalProjectI.db.MasterServiceDao;
import com.example.FinalProjectI.db.entity.MasterService;
import com.example.FinalProjectI.db.exception.CustomDBException;
import com.example.FinalProjectI.utils.ConnectionNeedUtil;
import com.example.FinalProjectI.utils.PropUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLMasterServiceDao implements MasterServiceDao {
    private static final Logger LOGGER = LogManager.getLogger(MySQLMasterServiceDao.class);
    @Override
    public boolean insertMasterService(MasterService masterService, Connection con) throws CustomDBException {
        String sqlQuery = null;
        try {
            sqlQuery = PropUtil.getQuery("insertMasterService");
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());
        }

        boolean result = false;
        try(PreparedStatement   statement =  con.prepareStatement(sqlQuery) )
        {
            statement.setInt(1,masterService.getMasterId());
            statement.setInt(2,masterService.getServiceId());
            if(statement.executeUpdate()>0) result = true;

        }
        catch (SQLException sqlException)
        {
            LOGGER.error(sqlException);
            throw new CustomDBException("bad execution",sqlException.getMessage(),sqlException,sqlException.getErrorCode());
        }

       return result;
    }

    @Override
    public boolean deleteMasterService(int masterId, int serviceId , Connection con ) throws CustomDBException {
        String sqlQuery = null;
        try {
            sqlQuery = PropUtil.getQuery("deleteMasterService");
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());
        }
        boolean result = false;
        try( PreparedStatement statement =  con.prepareStatement(sqlQuery) )
        {
            statement.setInt(1,masterId);
            statement.setInt(2,serviceId);
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
    public List<MasterService> findAllServicesByMaster(int masterId,Connection con) throws CustomDBException {
        List<MasterService> masterServiceList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try{
            String sqlQuery = PropUtil.getQuery("findAllServicesByMaster");
            statement = con.prepareStatement(sqlQuery);
            statement.setInt(1,masterId);
            rs = statement.executeQuery();
            while(rs.next()){
                MasterService masterService = new MasterService();
                masterService.setMasterId(rs.getInt("masterId"));
                masterService.setServiceId(rs.getInt("serviceId"));
                masterServiceList.add(masterService);
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
        return masterServiceList;
    }

    @Override
   public List<MasterService> findAllMastersByService(int serviceId, Connection con) throws CustomDBException {
        List<MasterService> masterServiceList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try{
            String sqlQuery = PropUtil.getQuery("findAllMastersByService");
            statement = con.prepareStatement(sqlQuery);
            statement.setInt(1,serviceId);
            rs = statement.executeQuery();
            while(rs.next()){
                MasterService masterService = new MasterService();
                masterService.setMasterId(rs.getInt("masterId"));
                masterService.setServiceId(rs.getInt("serviceId"));
                masterServiceList.add(masterService);
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
        return masterServiceList;
    }

    @Override
    public List<MasterService> findAllMasterServices(Connection con) throws CustomDBException {
        List<MasterService> masterServiceList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try{
            String sqlQuery = PropUtil.getQuery("findAllMasterServices");
            statement = con.prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            while(rs.next()){
                MasterService masterService = new MasterService();
                masterService.setMasterId(rs.getInt("masterId"));
                masterService.setServiceId(rs.getInt("serviceId"));
                masterServiceList.add(masterService);
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
       return masterServiceList;
    }
}
