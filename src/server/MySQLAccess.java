package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public static Connection createConnection() {
    	String errorMsg;
    	
        Connection conn = null;
        try {
        	Class.forName(Config.SQL_DRIVER).newInstance();
            conn = DriverManager.getConnection(Config.SQL_HOST+Config.SQL_DB,
                            Config.SQL_USERNAME, Config.SQL_PASSWORD);
        }
        catch(SQLException se) {
        	errorMsg = se.getMessage();
        	System.out.println(errorMsg);     
        }
        catch (Exception e) {
            errorMsg = e.getMessage();
            System.out.println(errorMsg);
        }
        return conn;
    }

}
