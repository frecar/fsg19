package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

public class SessionHandler {

	/** 
	 * For now, returns input in uppercase
	 */
	public String processRequest(BufferedReader inFromClient, String ip) throws IOException{
		
		String requestLine = inFromClient.readLine();
		System.out.println(ip + " says: " + requestLine);
		
		return processLogin(null, null); //return requestLine.toUpperCase();
	}
	
	/**
	 * Returns a session identifier if the username/password combination
	 * exists
	 */
	public String processLogin(String username, String password) {
		
		String uid = UUID.randomUUID().toString().replaceAll("-", "");
		return uid;
	}


}
