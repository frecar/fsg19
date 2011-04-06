package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

	/**
	* Represents one connection. Uses the SessionHandler class to
	* construct proper response.
	*/
	public class ClientThread implements Runnable {
	
	private Socket threadSocket;
	private SessionHandler sessionHandler;
	
	private String ip;
	
	/**
	* Constructor, setting the socket and protocol
	*/
	public ClientThread(Socket socket, SessionHandler sessionHandler) {
		this.threadSocket = socket;
		this.sessionHandler = new SessionHandler();
		this.ip = socket.getInetAddress().toString();
		
		System.out.println("Client accepted from " + this.ip);
	}
	
	public void run() {
		String lol = "";
		try {
			ObjectInputStream ois = new ObjectInputStream(threadSocket.getInputStream());
			String message = (String) ois.readObject();
			//System.out.println("Client says: " + message);
			Object a = API.handle(message);
		    ObjectOutputStream oos = new ObjectOutputStream(threadSocket.getOutputStream());
		    		    
		    oos.writeObject(a.toString());
		    oos.close();
		}
		
		catch(IOException e) {
			System.out.println("Could not handle the connection.");
			e.printStackTrace();
			System.out.println(lol);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				threadSocket.close();
			} catch(IOException e) {
		
			}
		}
	}
}