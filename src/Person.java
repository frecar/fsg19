import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;


public class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7243262813969289084L;
	private String name;
	private ArrayList<Meating> meatings;
	private File file;
	
	public Person(){
		setList(new ArrayList<Meating>());
	}
	
	public Person(String name) {
		setName(name);
		setList(new ArrayList<Meating>());
		file = new File(name);
	}
	
	public void setList(ArrayList<Meating> m) {
		meatings = m;
	}
	
	public void addMeating(Meating meating){
		meatings.add(meating);
	}
	
	public ArrayList<Meating> getList(){
		return meatings;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void save(){
		System.out.println("saving " + name);
		if(!file.exists()){
			FileHandler.createFile(file);
		}
		FileHandler.serialize(this, file);
	}
	
	public void load(){
		System.out.println("loading " + name);
		this.meatings = FileHandler.deSerialize(file).meatings;
	}
	
}
