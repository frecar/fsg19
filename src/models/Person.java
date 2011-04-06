package models;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.Client;
import client.Config;

import com.sun.org.apache.xml.internal.serializer.ToStream;


public class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7243262813969289084L;
	private int id;
	private String name;
	private String email;
	private String username;
	private String password;
	private Company company;	
	
	private ArrayList<Meeting> meetings = new ArrayList<Meeting>();;
	
	private static ArrayList<Person> persons = new ArrayList<Person>();
	
	public Person(){
		setList(new ArrayList<Meeting>());
	}
	
	public Person(String name) {
		setName(name);
		setList(new ArrayList<Meeting>());
	}
	
	public static Person get(int id) {
		for (Person person : Person.all()) {
			if(person.getId() == id) {
				return person;
			}
		}
		return null;
	}
	
	public Person(ResultSet result) {
		try {
			this.id 		= Integer.parseInt(result.getString("id"));
			this.name 		= result.getString("name");
			this.email		= result.getString("email");
			this.username 	= result.getString("username");
			this.password 	= result.getString("password");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updatePerson(Person object) 
	{
		this.setName(object.getName());
	}
	
	public ArrayList<Meeting> get_meetings() {
		String query = "get,getMeetingsForUser,"+this.id;
		ArrayList<Object> list = Client.request(query);
		
		ArrayList<Meeting> p = new ArrayList<Meeting>();
		
		for (Object object : list) {
				boolean sat = false;
				for (Meeting meeting : meetings) {
					if(meeting.getId() == ((Meeting)object).getId()) {
						sat = true;
						meeting.updateMeeting((Meeting)object);
					}
				}
				
				if(!sat) {
					meetings.add((Meeting)object);
				}
		}
			
		for (Object meeting : list) {
			p.add((Meeting)meeting);
		}
		
		return p;
	}
	
	public static ArrayList<Person> all() {
		String query = "get,getPersons";
		ArrayList<Object> list = Client.request(query);
		
		for (Object object : list) {
				boolean sat = false;
				for (Person person : Person.persons) {
					if(person.getId() == ((Person)object).getId()) {
						sat = true;
						person.updatePerson((Person)object);
					}
				}
				
				if(!sat) {
					persons.add((Person)object);
				}
		}
	
		return persons;
	
	}
	
	public String toString() {
		return this.name;
	}

	public void save(){
		
		ByteArrayOutputStream xml = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(xml);
        encoder.writeObject(this);
        encoder.close();
        
    	Socket socket;
		try {
			socket = new Socket(Config.SERVER, Config.SERVER_PORT);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());	
			oos.writeObject("set,savePerson,"+xml);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		Person.all();
	
	}
	
	public void setList(ArrayList<Meeting> m) {
		meetings = m;
	}
	
	public void addAppointment(Meeting appointment){
		meetings.add(appointment);
	}
	
	public ArrayList<Meeting> getList(){
		return meetings;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}
}
