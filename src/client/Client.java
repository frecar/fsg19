package client;
import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import client.gui.MainFrame;
import models.Meeting;
import models.Person;
import models.Room;

public class Client {

	public static Client client;
	public static Person user;
	private MainFrame mf;
	
	public Client() {
		Client.client = this;
		System.out.println("client started");
		mf = new MainFrame(this);
		mf.initGUI();
		Client.client = this;
	}

	public static void main(String[] args){
		Client client = new Client();
		Thread thread1 = new Updater("updated", 8000, client);	
		
	}
	
	public static ArrayList<Object> request(String request) {
		Object object;
		FileInputStream os;

		ArrayList<Object> list = new ArrayList<Object>();

		try {
			Socket socket = new Socket(Config.SERVER, Config.SERVER_PORT);		
		
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());	
			oos.writeObject(request);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			String message = (String) ois.readObject();
					
			InputStream is = new ByteArrayInputStream(message.getBytes("UTF-8"));
			XMLDecoder decoder = new XMLDecoder(is);
			
			try {
		        while ( true ) {		        	
		        	list.add((Object)decoder.readObject());
		        }
		    } 
			catch ( ArrayIndexOutOfBoundsException exception ) {} 
			finally 
			{
		        decoder.close();
		    }
	    
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
				
		return list;
	}
	
}