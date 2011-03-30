import java.io.File;


public class Main {
	public static void main(String[] args) {
		Person person = new Person("Truls");
		Meating meating1 = new Meating("viktig møte");
		Meating meating2 = new Meating("pizzakveld");
		person.addMeating(meating1);
		person.addMeating(meating2);
		System.out.println(person.getList().size());
		person.save();
	
		Person person2 = new Person("Truls");
		person2.load();
		Meating meating3 = new Meating("julebord");
		person2.addMeating(meating3);
		System.out.println(person2.getList().size());
		person2.save();
	}

}
