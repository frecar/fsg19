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

	private Person user;
	private ArrayList<Person> persons;
	private ArrayList<Meeting> meetings;
	private ArrayList<Room> rooms;
	
	private MainFrame mf;
	
	private String host;
	private int port;
	
	public Client() {
		
		System.out.println("client started");

		this.host = "78.91.2.18";
		this.port = 8120;
	
		
		persons = new ArrayList<Person>();
		rooms = new ArrayList<Room>();
		meetings = new ArrayList<Meeting>();
		
		mf = new MainFrame(this);
		mf.initGUI();
	}
	
	public static void main(String[] args){
		Client client = new Client();
		//Set auto-update
		Thread thread1 = new Updater("updated", 1000, client);
	}
	
	public ArrayList<Object> request(String request) {
		
		Object object;
		FileInputStream os;

		ArrayList<Object> list = new ArrayList<Object>();

		try {
			Socket socket = new Socket(this.host, this.port);		
		
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());	
			oos.writeObject(request);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			String message = (String) ois.readObject();
			
			InputStream is = new ByteArrayInputStream(message.getBytes("UTF-8"));
			XMLDecoder decoder = new XMLDecoder(is);
			
			try 
			{
		        while ( true ) 
		        {
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
	
	public void updatePersons() {
		String query = "get,getPersons";
		ArrayList<Object> list = this.request(query);
		
		for (Object object : list) {
				boolean sat = false;
				for (Person person : this.persons) 
				{	
					if(person.getId() == ((Person)object).getId()) 
					{
						sat = true;
						person.setName(((Person)object).getName());
					}
				}
				if(!sat) 
				{
					persons.add((Person)object);
				}
		}
	}
	
	public void updateMeetings() {
		String query = "get,getMeetings";
		ArrayList<Object> list = this.request(query);
	}
	
	
	
	public void updateRooms() {
		String query = "get,getRooms";
		ArrayList<Object> list = this.request(query);
		
		for (Object object : list) {
				boolean sat = false;
				for (Room room : this.rooms) 
				{	
					if(room.getId() == ((Room)object).getId()) 
					{
						sat = true;
						room.setName(((Room)object).getName());
					}
				}
				if(!sat) 
				{
					this.rooms.add((Room)object);
				}
				
		}
		
	}
	
	public ArrayList<Room> getRooms() {
		return this.rooms;
	}
	public ArrayList<Person> getPersons() {
		return this.persons;
	}
	public ArrayList<Meeting> getMeetings() {
		return this.meetings;
	}
	
	public void setUser(Person user) {
		this.user = user;
	}
	
	public Person getUser() {
		return this.user;
	}
}