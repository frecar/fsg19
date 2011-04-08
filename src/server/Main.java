package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

	/**
	* Listening port number
	*/
	public int port;

	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.out.println("Usage: java -jar Main.jar <port> \n" +
			"For example: java -jar Main.jar 80");
			System.exit(0);
		}
		else {
			new Main("8121");
		}
	}

	/**
	* Constructor, starts the main listen loop using provided port
	*/
	public Main(String port) {
		
		this.port = Integer.parseInt(port);
		startListenLoop();
	}

	/**
	* Starts the server on this port. Accept connections repeatedly in
	* a while loop. Starts a thread for each new connection.
	*/
	private void startListenLoop() {
		
		ServerSocket serverSocket = null;
		
		SessionHandler sessionHandler = new SessionHandler();

		try {
			serverSocket = new ServerSocket(this.port);
			System.out.println("Server is now listening on port " + this.port);
		}
		catch(IOException e) {
			System.out.println("Could not listen on port " + this.port);
			System.exit(0);
		}
	
		while(true) {
			try {
				new Thread(new ClientThread(serverSocket.accept(), sessionHandler)).start();
			}
			catch(IOException e) {
				System.out.println("Could not spawn a connection thread: ");
			}
		}
	}
}
