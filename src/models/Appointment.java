package models;

import java.io.Serializable;

public class Appointment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8417558628557030139L;
	private String name;
	
	public Appointment(){
		
	}
	
	public Appointment(String name){
		this.setName(name);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
