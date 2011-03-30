package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

	/**
	* Represents one HTTP connection. Used the HttpProtocol class to
	* construct proper response.
	*/
	public class ClientThread implements Runnable {
	
	private Socket clientSocket;
	private SessionHandler sessionHandler;
	
	private String ip;
	
	/**
	* Constructor, setting the socket and protocol
	*/
	public ClientThread(Socket clientSocket, SessionHandler sessionHandler) {
		this.clientSocket = clientSocket;
		this.sessionHandler = new SessionHandler();
		this.ip = clientSocket.getInetAddress().toString();
		
		System.out.println("Client accepted from " + this.ip);
	}
	
	public void run() {
		String lol = "";
		try {
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
	
			lol = this.sessionHandler.processRequest(inFromClient, ip);
		
			// Process request
			outToClient.writeBytes(lol);
			
			this.clientSocket.close();
		}
		catch(IOException e) {
			System.out.println("Could not handle the connection.");
			e.printStackTrace();
			System.out.println(lol);
		}
		finally {
			try {
				clientSocket.close();
			} catch(IOException e) {
		
			}
		}
	}
}