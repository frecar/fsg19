package client;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import client.gui.MainFrame;

import models.Person;

import server.API;

public class Client {

	private ArrayList<Person> persons = new ArrayList();
	
	public static void main(String[] args){
		
		Object a = server.API.handle("get,getPersonById, heisann,hallo");
		
		new Client().test();
		/*
		try {
			Socket socket = new Socket("localhost", 8125);
			
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());	
			oos.writeObject("Hello There...");
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			String message = (String) ois.readObject();
			System.out.println("Message: " + message);
			ois.close();
			oos.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			qqe.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
	}	
	
	void test() {
		new MainFrame(this).initGUI();
	}
}