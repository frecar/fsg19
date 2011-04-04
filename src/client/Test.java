package client;

public class Test {

	public static void main(String[] args) {
		Thread thread1 = new Updater("fitte", 1000, new Client());
	}
}
