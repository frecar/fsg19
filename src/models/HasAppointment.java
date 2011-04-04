package models;

import java.io.File;

public class HasAppointment {
	private int id;
	private Person employee;
	private Meeting meeting;
	private boolean responsible;
	private boolean accepted;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setEmployee(Person employee) {
		this.employee = employee;
	}

	public Person getEmployee() {
		return employee;
	}


	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setResponsible(boolean responsible) {
		this.responsible = responsible;
	}

	public boolean isResponsible() {
		return responsible;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isAccepted() {
		return accepted;
	}
	
	public void save(){
		File file = new File(Integer.toString(id));
		System.out.println("saving " + id);
		if(!file.exists()){
			FileHandler.createFile(file);
		}
		FileHandler.serialize(this, file);
	}
	
	public HasAppointment load(){
		System.out.println("loading " + id);
		File file = new File(Integer.toString(id));
		HasAppointment temp = null;
		try {
			temp = (HasAppointment) FileHandler.deSerialize(file, this.getClass().newInstance());
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		return temp;	
	}
}
