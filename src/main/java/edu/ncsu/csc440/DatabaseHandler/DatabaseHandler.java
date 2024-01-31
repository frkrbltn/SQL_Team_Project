package edu.ncsu.csc440.DatabaseHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import edu.ncsu.csc440.utils.ConfigurationLoader;

public abstract class DatabaseHandler {
    static final String       configFile      = "config/credentials.txt";
    static final String       DB_URL_TEMPLATE = "jdbc:%s://%s:%s/%s";
    private static Connection connection;

    public static Connection getInstance () throws SQLException {
        if ( connection == null ) {
        	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            final Properties properties = ConfigurationLoader.loadConfiguration( configFile );
            final String DB_USER = properties.getProperty( "DB_USER" );
            final String DB_PASS = properties.getProperty( "DB_PASS" );
            final String DB_TYPE = properties.getProperty( "DB_TYPE" );
            final String DB_HOST = properties.getProperty( "DB_HOST" );
            final String DB_PORT = properties.getProperty( "DB_PORT" );
            final String DB_NAME = properties.getProperty( "DB_NAME" );
            final String DB_URL = String.format( DB_URL_TEMPLATE, DB_TYPE, DB_HOST, DB_PORT, DB_NAME );
            connection = DriverManager.getConnection( DB_URL, DB_USER, DB_PASS );
        }
        return connection;
    }

    public static void closeDatabaseConnection () throws SQLException {
        connection.close();
    }
}
