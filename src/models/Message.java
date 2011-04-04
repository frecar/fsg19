package models;

import java.io.File;

public class Message {
	private int id;
	private String text;
	private Has_appointment has_appointment;
	
	private File file;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setHas_appointment(Has_appointment has_appointment) {
		this.has_appointment = has_appointment;
	}

	public Has_appointment getHas_appointment() {
		return has_appointment;
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
