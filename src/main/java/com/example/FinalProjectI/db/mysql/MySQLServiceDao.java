package com.example.FinalProjectI.db.mysql;

import com.example.FinalProjectI.db.ServiceDao;
import com.example.FinalProjectI.db.entity.Service;
import com.example.FinalProjectI.db.exception.CustomDBException;
import com.example.FinalProjectI.utils.ConnectionNeedUtil;
import com.example.FinalProjectI.utils.PropUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLServiceDao implements ServiceDao {
    private static final Logger LOGGER = LogManager.getLogger(MySQLServiceDao.class);
    /**
     * Inserts Servce entity into database table
     * @param con object with database
     * @param service entity to be inserted
     * @return true if insert operation went without exception and false otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
    public boolean insertService(Service service, Connection con) throws CustomDBException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean result = false;
        try
        {
            String sqlQuery = PropUtil.getQuery("insertService");
            statement = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,service.getServiceType());
            statement.setDouble(2,service.getServicePrice());
            if(statement.executeUpdate()>0)
            {
                rs = statement.getGeneratedKeys();
                rs.next();
                service.setServiceId(rs.getInt(1));
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
    /**
     * Deletes Service entity from database table
     * @param con object with database
     * @param serviceType type by which service to be deleted
     * @return true if delete operation went without exception and false otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
    public boolean deleteService(String serviceType, Connection con) throws CustomDBException {
        String sqlQuery = null;
        try {
            sqlQuery = PropUtil.getQuery("deleteService");
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());
        }
        boolean result = false;
        try(  PreparedStatement statement = con.prepareStatement(sqlQuery))
        {
            statement.setString(1,serviceType);
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
     * Updates Service entity price   by service type
     * @param con object with database
     * @param serviceType type by which service to be find
     * @param newPrice new price  to update
     * @return true if update operation went without exception and false otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
    public boolean updateService(String serviceType, double newPrice, Connection con) throws CustomDBException {
        String sqlQuery = null;
        try {
            sqlQuery = PropUtil.getQuery("updateService");
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
            throw new CustomDBException("Properties not loaded properly",sqlException.getMessage(),sqlException, sqlException.getErrorCode());
        }
        boolean result = false;
        try( PreparedStatement statement = con.prepareStatement(sqlQuery); )
        {
            statement.setDouble(1,newPrice);
            statement.setString(2,serviceType);
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
     * Find Service entity by type
     * @param con object with database
     * @param type type by which service to be find
     * @return Service entity if find operation went without exception and null otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */

    @Override
    public Service findService(String type, Connection con) throws CustomDBException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Service service = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findService");
            statement = con.prepareStatement(sqlQuery);
            statement.setString(1,type);
            rs = statement.executeQuery();
            if(rs.next())
            {
                service = new Service();
                service.setServiceId(rs.getInt("serviceId"));
                service.setServiceType(type);
                service.setServicePrice(rs.getDouble("servicePrice"));

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

       return service;
    }

    /**
     * Find Service entity by id
     * @param con object with database
     * @param serviceId id by which order to be find
     * @return Service entity if find operation went without exception and null otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
    public Service findServiceById(int serviceId, Connection con) throws CustomDBException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Service service = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findServiceById");
            statement = con.prepareStatement(sqlQuery);
            statement.setInt(1,serviceId);
            rs = statement.executeQuery();
            if(rs.next())
            {
                service = new Service();
                service.setServiceId(rs.getInt("serviceId"));
                service.setServiceType(rs.getString("serviceType"));
                service.setServicePrice(rs.getDouble("servicePrice"));

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

        return service;
    }
    /**
     * Find all Service entities
     * @param con object with database
     * @return  List of  Service entities  if find operation went without exception and empty list otherwise
     * @throws CustomDBException if SQLException at execution query arises
     */
    @Override
    public List<Service> findAllService(Connection con) throws CustomDBException {
        List<Service> serviceList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String sqlQuery = PropUtil.getQuery("findAllService");
            statement = con.prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            while(rs.next())
            {

                Service service = new Service();
                service.setServicePrice(rs.getDouble("servicePrice"));
                service.setServiceId(rs.getInt("serviceId"));
                service.setServiceType(rs.getString("serviceType"));
                serviceList.add(service);
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

       return serviceList;
    }

}
