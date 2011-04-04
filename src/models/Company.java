package models;

import java.io.File;

public class Company {
	private int id;
	private String name;
	
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
	
	public void save(){
		File file = new File(name);
		System.out.println("saving " + name);
		if(!file.exists()){
			FileHandler.createFile(file);
		}
		FileHandler.serialize(this, file);
	}

}
