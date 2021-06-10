package com.example.FinalProjectI.utils;

import com.example.FinalProjectI.db.exception.CustomDBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionNeedUtil {

    public static void close(ResultSet rs, Statement statement,Connection connection) throws CustomDBException {

       try
       {
           if (rs != null &&!rs.isClosed())
               rs.close();
           if (statement != null&&!statement.isClosed())
               statement.close();
           if (connection != null&&!connection.isClosed())
               connection.close();
       }

       catch (SQLException sqlException)
       {
           throw new CustomDBException("Failed to close",sqlException.getMessage(),sqlException);
       }


    }


    public static void commit(Connection connection) throws CustomDBException {
        if(connection!=null)
        {
            try {
                connection.commit();
            }
            catch (SQLException sqlException)
            {
              throw new CustomDBException("Failed to commit transaction",sqlException.getMessage(),sqlException);
            }
        }

    }//end of the method

    public static void rollBack(Connection connection) throws CustomDBException {
        if(connection!=null)
        {
            try {
                connection.rollback();
            }
            catch (SQLException sqlException ) {
                throw new CustomDBException("Failed to rollback",sqlException.getMessage(),sqlException);
            }
        }

    }//end of the method


}
