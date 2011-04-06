package models;

import java.io.File;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import client.interfaces.*;

public class Meeting implements Serializable, Comparable<Meeting>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8417558628557030139L;
	
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
	
	private ArrayList<Person> participants;
	
	/**
	 * All GUI listeners who is interested in the meetings
	 */
	private ArrayList<MeetingListener> meetingListeners = new ArrayList<MeetingListener>();
	
	public Meeting() {};
	
	public Meeting(String title) {
		this.title = title;
	}

	
	public Meeting(ResultSet result) {
		try {
			this.id 			= Integer.parseInt(result.getString("id"));
			this.title		 	= result.getString("title");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Meeting(String title, String date, String responsible, String timeStart,String timeEnd,
			 String description, String canceled, String deleted, String room) {
		
		super();
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

	
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getTitle() {
		return title;
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

	public ArrayList<Person> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<Person> participants) {
		this.participants = participants;
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
	
	public void save(){
		File file = new File(title);
		System.out.println("saving " + title);
		if(!file.exists()){
			FileHandler.createFile(file);
		}
		
		FileHandler.serialize(this, file);
	}
	
	public Meeting load(){
		System.out.println("loading " + title);
		File file = new File(title);
		Meeting temp = null;
		try {
			temp = (Meeting) FileHandler.deSerialize(file, this.getClass().newInstance());
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		return temp;
	}
	
	public String toString() {
		return title + " | " + date + " | " + timeStart + "-" + timeEnd + " | " + room;
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

	
}
