package models;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;


public class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7243262813969289084L;
	private String name;
	private ArrayList<Meeting> Meetings;
	private File file;
	
	public Person(){
		setList(new ArrayList<Meeting>());
	}
	
	public Person(String name) {
		setName(name);
		setList(new ArrayList<Meeting>());
		file = new File(name);
	}
	
	public void setList(ArrayList<Meeting> m) {
		Meetings = m;
	}
	
	public void addMeeting(Meeting Meeting){
		Meetings.add(Meeting);
	}
	
	public ArrayList<Meeting> getList(){
		return Meetings;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void save(){
		System.out.println("saving " + name);
		if(!file.exists()){
			FileHandler.createFile(file);
		}
		FileHandler.serialize(this, file);
	}
	
	public void load(){
		System.out.println("loading " + name);
		this.Meetings = FileHandler.deSerialize(file).Meetings;
	}
	
}
