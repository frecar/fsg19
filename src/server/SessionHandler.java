package server;

import java.io.BufferedReader;
import java.io.IOException;

public class SessionHandler {

	/** 
	 * For now, returns input in uppercase
	 */
	public String processRequest(BufferedReader inFromClient, String ip) throws IOException{
		
		String requestLine = inFromClient.readLine();
		System.out.println(ip + " says: " + requestLine);
		
		return requestLine.toUpperCase();
	}


}
