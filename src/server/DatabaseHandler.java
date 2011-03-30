package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {

	// Singleton pattern
	private static final DatabaseHandler INSTANCE = new DatabaseHandler();

	// Error message for database failure
	private static String errorMsg;

	/** 
	 * Private constructor prevents instantiation from other classes
	 */
	private DatabaseHandler() {
	}
 
	public static DatabaseHandler getInstance() {
		return INSTANCE;
	}

	/** 
     * Creates a database connection using credentials provided in Config.java
     * And simply returns the Connection object ready to be used
     */
    private static Connection createConnection() {

        Connection conn = null;
        try {
        	Class.forName(Config.SQL_DRIVER).newInstance();
            conn = DriverManager.getConnection(
            		Config.SQL_HOST+Config.SQL_DB,
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

    public void lol() {
    	System.out.println("test");
    }

}
