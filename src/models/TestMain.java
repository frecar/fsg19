package models;

import java.io.File;


public class TestMain {
	public static void main(String[] args) {
		Person person = new Person("Truls");
		Meeting appointment1 = new Meeting("viktig møte");
		Meeting appointment2 = new Meeting("pizzakveld");
		person.addAppointment(appointment1);
		person.addAppointment(appointment2);
		System.out.println(person.getList().size());
		person.save();
	
//		File file = new File("Truls");
//		Person person2 = (Person) FileHandler.deSerialize(file, new Person());
//		Appointment appointment3 = new Appointment("julebord");
//		person2.addAppointment(appointment3);
//		System.out.println(person2.getList().size());
//		person2.save();
		
		Person person2 = new Person("Truls");
		person2.load();
		Meeting appointment3 = new Meeting("julebord");
		person2.addAppointment(appointment3);
		System.out.println(person2.getList().size());
		person2.save();
	}

}
