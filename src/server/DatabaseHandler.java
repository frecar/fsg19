package server;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler {

	// Singleton pattern
	private static final DatabaseHandler INSTANCE = new DatabaseHandler();
	public static Connection connection;
	
	// Error message for database failure
	private static String errorMsg;


	public static DatabaseHandler getInstance() {
		return INSTANCE;
	}

	/** 
     * Creates a database connection using credentials provided in Config.java
     * And simply returns the Connection object ready to be used
     */
    public static Connection createConnection() {
    	
    	
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

    /**
     * Prints out all tables in current database
     */
    public String getTables() {
        Connection conn = createConnection();
        String test = "";
        
        
        try {
        	DatabaseMetaData dbmd = conn.getMetaData();
        	
        	// Specify the type of object; in this case we want tables
            String[] types = {"TABLE"};
            ResultSet resultSet = dbmd.getTables(null, null, "%", types);

            // Get the table names
            while (resultSet.next()) {
                // Get the table name
                String tableName = resultSet.getString(3);
                System.out.println(tableName);
                // Get the table's catalog and schema names (if any)
                String tableCatalog = resultSet.getString(1);
                String tableSchema = resultSet.getString(2);
                
                conn.close();
            }
        } catch (SQLException e) {
        }

        

        return test;
}


    /** Testing purposes */
    public void lol() {
    	System.out.println("test");
    	this.createConnection();
    }

}
