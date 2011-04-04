package models;

import java.io.File;

public class Message {
	private int id;
	private String text;

	private HasAppointment hasAppointment;
	
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

	public void setHas_appointment(HasAppointment hasAppointment) {
		this.hasAppointment = hasAppointment;
	}

	public HasAppointment getHasAppointment() {
		return hasAppointment;
	}
	
	public void save(){
		File file = new File(Integer.toString(id));
		System.out.println("saving " + id);
		if(!file.exists()){
			FileHandler.createFile(file);
		}
		FileHandler.serialize(this, file);
	}
	
	public Message load(){
		System.out.println("loading " + id);
		File file = new File(Integer.toString(id));
		Message temp = null;
		try {
			temp = (Message) FileHandler.deSerialize(file, this.getClass().newInstance());
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		return temp;	
	}

}
