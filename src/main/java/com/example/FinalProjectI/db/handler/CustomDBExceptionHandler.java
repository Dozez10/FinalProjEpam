package com.example.FinalProjectI.db.handler;

import com.example.FinalProjectI.db.exception.CustomApplicationException;
import com.example.FinalProjectI.db.exception.CustomDBException;
import com.example.FinalProjectI.utils.ConnectionNeedUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomDBExceptionHandler{
    public static void close(ResultSet rs, Statement statement, Connection connection) throws CustomApplicationException {

        try{
            ConnectionNeedUtil.close(rs,statement,connection);
        }
        catch (CustomDBException customDBException)
        {
            throw new CustomApplicationException(" Failed to close in Service section",customDBException.getMessage(),customDBException, customDBException.getErrorCode());
        }
    }
    public static void commit(Connection connection) throws CustomApplicationException {

        try{
            ConnectionNeedUtil.commit(connection);
        }
        catch (CustomDBException customDBException)
        {
            throw new CustomApplicationException(" Failed to commit in Service section",customDBException.getMessage(),customDBException, customDBException.getErrorCode());
        }
    }
    public static void rollBack(Connection connection) throws CustomApplicationException {

        try{
            ConnectionNeedUtil.rollBack(connection);
        }
        catch (CustomDBException customDBException)
        {
            throw new CustomApplicationException(" Failed to rollback in Service section",customDBException.getMessage(),customDBException, customDBException.getErrorCode());
        }
    }

}
