package client;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import server.API;

public class Main {

	public static void main(String[] args){
		
		Object a = server.API.handle("getPersonById,4");
		
		System.out.println(a);
		
	}
	
	
}
