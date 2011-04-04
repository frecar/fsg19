package client;

public class Updater extends Thread {
	boolean runs = true;
	int sleepTime = 1000;
	Client client;
	
	
	public Updater() {
	}
	
	public Updater(String name, int sleepTime, Client client){
		super(name);
		this.sleepTime = sleepTime;
		this.client = client;
		start();
	}
	
	public Updater(String name, Client client){
		super(name);
		this.sleepTime = 10000;
		this.client = client;
		start();
	}
	
	public void run(){
		while (runs){
			client.updateMeetings();
			internalSleep();
			client.updatePersons();
			internalSleep();
			client.updateRooms();
			internalSleep();
			System.out.println(Thread.currentThread().getName());
		}
	}
	
	@SuppressWarnings("static-access")
	public void internalSleep(){
		try {
			this.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
