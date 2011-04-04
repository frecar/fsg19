package server;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import models.Person;

public class API {

	public Object getPersons() {
		
		MySQLAccess dao = new MySQLAccess();
		try {
			Connection conn = dao.createConnection();
			
			Statement stat = conn.createStatement();
			String query = "SELECT * FROM Person";
			ResultSet result = stat.executeQuery(query);
						
			while(result.next()) {
				System.out.println(result.getString("name"));
			}
			
			conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Person("ok");
	}


	public Object getPersonById(String test, String tap) {
		
		MySQLAccess dao = new MySQLAccess();
		try {
			Connection conn = dao.createConnection();
			
			Statement stat = conn.createStatement();
			String query = "SELECT * FROM persons";
			ResultSet result = stat.executeQuery("SELECT * FROM Person");
			
			System.out.println("Result:");
			
			while(result.next()) {
				System.out.println(result.getString("name"));
			}
			
			conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Person("ok");
	}
	
	public String test() {
		return "test OK";
	}
	
	/*
	 * Specify format like this:
	 * handle(method,arg1,arg2,arg3)
	 * 
	 */
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
