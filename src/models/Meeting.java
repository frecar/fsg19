package models;

import java.beans.XMLEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

//import sun.tools.tree.ThisExpression;

import client.Client;
import client.Config;
import client.interfaces.*;

public class Meeting implements Serializable, Comparable<Meeting>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8417558628557030139L;

	
	private static ArrayList<Meeting> meetings = new ArrayList<Meeting>();

	private int id;
	private String title;
	private String date;
	private String responsible;
	private String timeStart;
	private String timeEnd;
	private String description;
	private String cancelled;
	private String deleted;
	private String room;
	
	private ArrayList<Person> participants = new ArrayList<Person>();

	/**
	 * All GUI listeners who is interested in the meetings
	 */
	private ArrayList<MeetingListener> meetingListeners = new ArrayList<MeetingListener>();
	
	public Meeting() {};
	
	public Meeting(String title) {
		this.title = title;
		this.date = "";
		this.responsible = "";
		this.timeStart = "";
		this.timeEnd = "";
		this.description = "";
		this.cancelled = "";
		this.deleted = "";
		this.room = "";
	}
	
	public Meeting(String title, String date, String responsible, String timeStart,String timeEnd,
			 String description, String canceled, String deleted, String room) {
		
		this.title = title;
		this.date = date;
		this.responsible = responsible;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.description = description;
		this.cancelled = canceled;
		this.deleted = deleted;
		this.room = room;
		
		participants = new ArrayList<Person>();
	}
	
	public String getParticipantStatus(Person person) {
		PersonMeeting m = PersonMeeting.get(person.getId(), this.id);
		if(m.getStatus().equals("1")) 
			return "Attending";
		else if(m.getStatus().equals("2")) 
			return "Rejected";
		else
			return "Pending";
	}

	public Meeting(ResultSet result) {
		try {
			this.id 			= Integer.parseInt(result.getString("id"));
			this.title 			= result.getString("title");
			this.timeStart		= result.getString("time_start");
			this.timeEnd 		= result.getString("time_end");
			this.room 			= result.getString("room");
			this.date 			= result.getString("date");
			this.description 	= result.getString("description");
			this.responsible 	= result.getString("responsible");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Meeting get(int id) {
		for (Meeting meeting : Meeting.all()) {
			if(meeting.getId() == id) {
				return meeting;
			}
		}
		return null;
	}
	
	public static ArrayList<Meeting> all() {
		String query = "get,getMeetings";
		ArrayList<Object> list = Client.request(query);
		
		ArrayList<Meeting> meets = new ArrayList<Meeting>();
		
		for (Object obj : list) {
			meets.add(((Meeting)obj));
		}
	
		meetings = meets;
		
		return meetings;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void delete() {
		Socket socket;
		try {
			socket = new Socket(Config.SERVER, Config.SERVER_PORT);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());	
			oos.writeObject("delete,deleteMeeting,"+this.id);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public ArrayList<Person> getParticipants() {
		return this.participants;
	}
	
	public ArrayList<Person> updateParticipants() {
		String query = "get,getParticipantsForMeeting,"+this.id;
		ArrayList<Object> list = Client.request(query);
		
		ArrayList<Person> persons = new ArrayList<Person>();
			
		for (Object ob : list) {
			persons.add((Person)ob);
		}	
		
		participants = persons;
		return persons;	
	}

	public void setParticipants(ArrayList<Person> participants) {
		this.participants = participants;
	}
	
	public void clearParticipants() {
		this.participants = new ArrayList<Person>();
	}
	
	public void addParticipant(Person p ) {
		this.participants.add(p);
	}
	
	public void removeParticipant(Person p ) {
		this.participants.remove(p);
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String time_start) {
		this.timeStart = time_start;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String time_end) {
		this.timeEnd = time_end;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCanceled() {
		return cancelled;
	}

	public void setCanceled(String canceled) {
		this.cancelled = canceled;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getNumOfParticipants() {
		if(participants.size() == 0) {
			return "0";
		}
		
		return participants.size() + "";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ArrayList<MeetingListener> getMeetingListeners() {
		return meetingListeners;
	}
	
	/**
	 * Notifies all the listers, that the meeting was changed
	 */
	private void firePropertyChanged() {
		//System.out.println("firePropertyChanged()");
		for(MeetingListener meetingListener: meetingListeners) {
			meetingListener.meetingUpdated();
		}
	}
	
	/**
	 * Adds listeners who wants to know about changes in meetings
	 */
	public void addMeetingListener(MeetingListener m) {
		if(! meetingListeners.contains(m)) {
			meetingListeners.add(m);
		}
	}
	
	public void updateMeeting(Meeting object) {
		this.setTimeStart(object.getTimeStart());
		this.setTimeEnd(object.getTimeEnd());
		this.setTitle(object.getTitle());
		this.setDate(object.getDate());
		this.setRoom(object.getRoom());
		this.setResponsible(object.getResponsible());
		this.setDescription(object.getDescription());
		this.setCanceled(object.getCanceled());	
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
			oos.writeObject("set,saveMeeting,"+xml);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public String toString() {
		return title + " | " + date + " | " + timeStart + "-" + timeEnd+ " | " + room;
	}

	/**
	 * Used for sorting dates in increasing order
	 */
	public int compareTo(Meeting m) {
		String date = m.getDate();
		int year = Integer.parseInt(date.substring(6, 10));
		int month = Integer.parseInt(date.substring(3, 5));
		int day = Integer.parseInt(date.substring(0, 2));
		
		
		int thisYear = Integer.parseInt(this.getDate().substring(6, 10));
		int thisMonth = Integer.parseInt(this.getDate().substring(3, 5));
		int thisDay = Integer.parseInt(this.getDate().substring(0, 2));
		
		Date mdate = new Date(year, month, day);
		Date thisDate = new Date(thisYear, thisMonth, thisDay);
		
		return thisDate.compareTo(mdate);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
