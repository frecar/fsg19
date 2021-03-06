package models;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.Client;

public class Room {
	private int id;
	private String name;
	private Company company;
	private static ArrayList<Room> rooms = new ArrayList<Room>();

	public Room() {};
	
	public Room(ResultSet result) {
		try {
			this.id 		= Integer.parseInt(result.getString("id"));
			this.name 		= result.getString("name");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Room> all() {
		String query = "get,getRooms";
		ArrayList<Object> list = Client.request(query);
		
		for (Object object : list) {
				boolean sat = false;
				for (Room room: Room.rooms) 
				{	
					if(room.getId() == ((Room)object).getId()) 
					{
						sat = true;
						room.setName(((Room)object).getName());
					}
				}
				if(!sat) 
				{
					rooms.add((Room)object);
				}
		}
	
		return rooms;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public String toString() {
		return this.name;
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

}
