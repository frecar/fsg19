package models;

import java.io.File;

public class Has_appointment {
	private int id;
	private Person employee;
	private Appointment appointment;
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

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Appointment getAppointment() {
		return appointment;
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
}
