package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

public class SessionHandler {

	/** 
	 * For now, this is not in use
	 */
	public String processRequest(BufferedReader inFromClient, String ip) throws IOException{
		return inFromClient.readLine();
		/*String requestLine = inFromClient.readLine();
		System.out.println(ip + " says: " + requestLine);
		return requestLine.toUpperCase();
		*/
		//return processLogin(null, null); //return requestLine.toUpperCase();
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
