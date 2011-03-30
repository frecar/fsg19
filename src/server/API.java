package server;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import models.Person;
import server.DatabaseHandler;

public class API {

	
	public String getPersonById(String id) {
		return "Hei";
		
	}
	
	public String test() {
		return "test OK";
	}
	
	/*
	 * Specify format like this:
	 * handle(method, id)
	 * 
	 */
	public static Object handle(String request) {
		
		API api = new API();
		
		String[] requestSplitted = request.split(",");
		
		String method = requestSplitted[0];
		String id = requestSplitted[1];
		
		Object arguments[] = {"hei",};
		
		try {
			Method m = api.getClass().getMethod(method, new Class[] {});
			Object ret = m.invoke(api, arguments);
			return (Object) ret;
		
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return "empty";
	}
	
	
}
