package models;

import java.io.File;

public class Room {
	private int id;
	private String name;
	private Company company;
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	public Company getCompany() {
		return company;
	}
	
	public void save(){
		File file = new File(name);
		System.out.println("saving " + name);
		if(!file.exists()){
			FileHandler.createFile(file);
		}
		FileHandler.serialize(this, file);
	}
	
	public Room load(){
		System.out.println("loading " + name);
		File file = new File(name);
		Room temp = null;
		try {
			temp = (Room) FileHandler.deSerialize(file, this.getClass().newInstance());
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		return temp;	
	}

}
