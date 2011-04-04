package client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import client.gui.MainFrame;
import models.Person;

public class Client {

	/*
	 * Client API
	 * 
	 * Kaller metoder p� klienten, som sender et nettverks-kall til server
	 * Server vil tolke strengen som er sendt, og finne tilsvarende metode
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	private ArrayList<Person> persons;
	private MainFrame mf;
	
	public Client() {
		//mf = new MainFrame(this);
		//mf.createAndShowLogin();
		
		
		
		persons = new ArrayList();
	}
	
	public static void main(String[] args){
		//Client client = new Client();
		//client.getPersons();
	}	
	
	public void getPersons() {
		try {
			Socket socket = new Socket("localhost", 8125);		
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());	
			oos.writeObject("get,getPersons");
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			String message = (String) ois.readObject();
			System.out.println("Message: " + message);
			ois.close();
			oos.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
}