package models;


public class TestMain {
	public static void main(String[] args) {
		Person person = new Person("Truls");
		Meeting meeting1 = new Meeting("viktig møte");
		Meeting meeting2 = new Meeting("pizzakveld");
		person.addMeeting(meeting1);
		person.addMeeting(meeting2);
		System.out.println(person.getList().size());
		person.save();
	
		Person person2 = new Person("Truls");
		person2.load();
		Meeting meeting3 = new Meeting("julebord");
		person2.addMeeting(meeting3);
		System.out.println(person2.getList().size());
		person2.save();
	}

}
