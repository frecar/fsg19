package server;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class API {

	public Object getPersons() {
		String query = "SELECT * FROM Person";
		return getObjects(query, "models.Person");
	}
	
	public Object getMeetings() {
		String query = "SELECT * FROM Meeting";
		return getObjects(query, "models.Meeting");
	}
	
	public Object getRooms() {
		String query = "SELECT * FROM Room";
		return getObjects(query, "models.Room");
	}
	
	public Object getObjects(String query, String classname) {	
		ByteArrayOutputStream xml = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(xml);
        
        ResultSet result = requestDatabase(query);
        
		try {
			Class<?> cls = Class.forName(classname);
            Class<?> partypes[] = new Class[1];
            partypes[0] = ResultSet.class;
            Constructor<?> ct = cls.getConstructor(partypes);
            Object arglist[] = new Object[1];

            while(result.next()) {								
            	arglist[0] = result;
                Object retobj = ct.newInstance(arglist);
                encoder.writeObject(retobj);
            }
		}	
		catch (Throwable e) {
            System.err.println(e);
         }
		
		encoder.close();	
		return xml;
	}
	
	/*
	 * Specify format like this:
	 * handle(method,arg1,arg2,arg3)
	 * 
	 */
	public ResultSet requestDatabase(String query) {

		MySQLAccess dao = new MySQLAccess();
		
		Connection conn = dao.createConnection();
		Statement stat;
		ResultSet result = null;
		
		try {
			stat = conn.createStatement();
			result = stat.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Object handle(String request) {
		
		API api = new API();

		String[] requestSplitted = request.split(",");
		String type 	= requestSplitted[0].trim();
		String method 	= requestSplitted[1].trim();
		
	    Class[] parameters = new Class[requestSplitted.length-2];
		String[] arguments = new String[requestSplitted.length-2];
		
		for (int i = 0; i < requestSplitted.length-2; i++) {
		    parameters[i] = String.class;
		    arguments[i] = requestSplitted[i+2].trim();
	    }
		
		try {
			Method m = api.getClass().getMethod(method, parameters);
			Object ret = (Object) m.invoke(api, arguments);
			return (Object) ret;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return "empty";
	}
	
	
}
