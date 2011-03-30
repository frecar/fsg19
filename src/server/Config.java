package server;

public class Config {
	
	public static final String SQL_DRIVER	= "com.mysql.jdbc.Driver";
    public static final String SQL_HOST		= "jdbc:mysql://srv1.fncit.no/";
    public static final String SQL_DB		= "fellesprosjekt";
    public static final String SQL_USERNAME = "fellesprosjekt";
    public static final String SQL_PASSWORD = "fsg19";
    
    /**
     * Timeout in seconds before server closes session,	
     * if client has not pinged
     */
    public static final int timeout = 60;   
}