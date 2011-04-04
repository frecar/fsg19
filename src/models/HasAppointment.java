package models;

import java.io.File;

public class HasAppointment {
	private int id;
	private Person employee;
	private Meeting meeting;
	private boolean responsible;
	private boolean accepted;
	
	private File file;

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

	public void setAppointment(Meeting appointment) {
		this.meeting = appointment;
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
		System.out.println("saving " + id);
		if(!file.exists()){
			FileHandler.createFile(file);
		}
		FileHandler.serialize(this, file);
	}
}
