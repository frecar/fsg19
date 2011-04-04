package models;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import client.interfaces.*;

public class Meeting implements Serializable, Comparable<Meeting>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8417558628557030139L;
	private String title, date, time, place, numOfParticipants, comment;
	
	/**
	 * All GUI listeners who is interested in the meetings
	 */
	private ArrayList<MeetingListener> meetingListeners = new ArrayList<MeetingListener>();
	
	public Meeting(String title) {
		this.title = title;
	}
	
	
	public Meeting(String title, String date, String time, String place,
			String numOfParticipants, String comment) {
		super();
		this.title = title;
		this.date = date;
		this.time = time;
		this.place = place;
		this.numOfParticipants = numOfParticipants;
		this.comment = comment;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		firePropertyChanged();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
		firePropertyChanged();
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
		firePropertyChanged();
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
		firePropertyChanged();
	}

	public String getNumOfParticipants() {
		return numOfParticipants;
	}

	public void setNumOfParticipants(String numOfParticipants) {
		this.numOfParticipants = numOfParticipants;
		firePropertyChanged();
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
		firePropertyChanged();
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
		return title + " | " + date + " | " + time + " | " + place;
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
