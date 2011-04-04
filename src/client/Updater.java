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
	
	@SuppressWarnings("static-access")
	public void run(){
		while (runs){

			System.out.println(Thread.currentThread().getName());
			try {
				this.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
