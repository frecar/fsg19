package models;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.Client;
import client.Config;

public class PersonMeeting implements Serializable{

	int id;
	String person_id;
	String meeting_id;
	String status;

	public PersonMeeting() {}
	
	public PersonMeeting(ResultSet result) {
		try {
			this.id 		= Integer.parseInt(result.getString("id"));
			this.person_id 	= result.getString("person_id");
			this.meeting_id	= result.getString("meeting_id");
			this.status 	= result.getString("status");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			oos.writeObject("set,savePersonMeeting,"+xml);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	public String getMeeting_id() {
		return meeting_id;
	}
	public void setMeeting_id(String meeting_id) {
		this.meeting_id = meeting_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public static PersonMeeting get(int i, int id2) {
		String query = "get,getPersonMeeting,"+i+","+id2;
		ArrayList<Object> list = Client.request(query);
		return (PersonMeeting) list.get(0);
	}
	
}
