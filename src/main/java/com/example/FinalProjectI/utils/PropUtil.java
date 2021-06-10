package com.example.FinalProjectI.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class PropUtil {

    private static final String propFileName = "queNeed.properties";
    private static Properties props;
    private PropUtil(){}
        public static void loadProp() throws SQLException {
            //singleton
            if (props == null) {
                InputStream is = PropUtil.class.getResourceAsStream("/" + propFileName);
                props = new Properties();
                try {
                    props.load(is);
                } catch (IOException e) {
                    throw new SQLException("Unable to load property file: " + propFileName + System.lineSeparator() + e.getMessage());
                }
            }

        }

        public static String getQuery(String query) throws SQLException {
             if(props == null)PropUtil.loadProp();
             return props.getProperty(query);
        }


}
