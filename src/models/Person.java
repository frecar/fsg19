package models;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;


public class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7243262813969289084L;
	private int id;
	private String name;
	private String email;
	private String username;
	private String password;
	private Company company;
	private ArrayList<Appointment> appointments;
	
	public Person(){
		setList(new ArrayList<Appointment>());
	}
	
	public Person(String name) {
		setName(name);
		setList(new ArrayList<Appointment>());
	}
	
	public void save(){
		File file = new File(name);
		System.out.println("saving " + name);
		if(!file.exists()){
			FileHandler.createFile(file);
		}
		FileHandler.serialize(this, file);
	}
	
	public void setList(ArrayList<Appointment> m) {
		appointments = m;
	}
	
	public void addAppointment(Appointment appointment){
		appointments.add(appointment);
	}
	
	public ArrayList<Appointment> getList(){
		return appointments;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public Person load(){
		System.out.println("loading " + name);
		File file = new File(name);
		Person temp = null;
		try {
			temp = (Person) FileHandler.deSerialize(file, this.getClass().newInstance());
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		return temp;	
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}
	
}
