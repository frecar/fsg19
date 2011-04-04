package models;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import client.interfaces.*;

public class Meeting implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8417558628557030139L;
	private String title, date, responsible, time_start, time_end, room, numOfParticipants, description, canceled, deleted;
	
	/**
	 * All GUI listeners who is interested in the meetings
	 */
	private ArrayList<MeetingListener> meetingListeners = new ArrayList<MeetingListener>();
	
	public Meeting(String title) {
		this.title = title;
	}
	
	public Meeting(String title, String date, String responsible, String time_start,String time_end, String place,
			String numOfParticipants, String description, String canceled, String deleted) {
		super();
		this.title = title;
		this.date = date;
		this.responsible = responsible;
		this.time_start = time_start;
		this.time_end = time_end;
		this.room = place;
		this.numOfParticipants = numOfParticipants;
		this.description = description;
		this.canceled = canceled;
		this.deleted = deleted;
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTitle() {
		return title;
	}

	public String getDate() {
		return date;
	}

	public String getResponsible() {
		return responsible;
	}

	public String getTime_start() {
		return time_start;
	}

	public String getTime_end() {
		return time_end;
	}

	public String getRoom() {
		return room;
	}

	public String getNumOfParticipants() {
		return numOfParticipants;
	}

	public String getDescription() {
		return description;
	}

	public String getCanceled() {
		return canceled;
	}

	public String getDeleted() {
		return deleted;
	}

	public ArrayList<MeetingListener> getMeetingListeners() {
		return meetingListeners;
	}

	public void setTitle(String title) {
		this.title = title;
		firePropertyChanged();
	}


	public void setDate(String date) {
		this.date = date;
		firePropertyChanged();
	}

	public void setTime_start(String time) {
		this.time_start = time;
		firePropertyChanged();
	}
	

	public void setTime_end(String time) {
		this.time_start = time;
		firePropertyChanged();
	}

	public void setRoom(String room) {
		this.room = room;
		firePropertyChanged();
	}

	public void setNumOfParticipants(String numOfParticipants) {
		this.numOfParticipants = numOfParticipants;
		firePropertyChanged();
	}
	
	/**
	 * Notifies all the listers, that the meeting was changed
	 */
	private void firePropertyChanged() {
		//System.out.println("firePropertyChanged()");
		for(MeetingListener m: meetingListeners) {
			m.meetingUpdated();
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
		return title;
	}
}
