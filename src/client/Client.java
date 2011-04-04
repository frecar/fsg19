package client;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

//import com.sun.tools.javac.util.List;

import client.gui.MainFrame;
import models.FileHandler;
import models.Person;

public class Client {

	private ArrayList<Object> persons;
	private ArrayList<Object> meetings;
	
	private MainFrame mf;
	
	private String host;
	private int port;
	
	public Client() {
		mf = new MainFrame(this);	
		

		System.out.println("client started");

		this.host = "localhost";
		this.port = 8120;
		
	
		mf = new MainFrame(this);
		mf.initGUI();
	   
		
	}
	
	public static void main(String[] args){
		Client client = new Client();
		client.getPersons();
		client.getMeetings();
	}
	
	public ArrayList<Object> request(String request) {
		
		Object object;
		FileInputStream os;

		ArrayList<Object> list = new ArrayList<Object>();

		try {
			Socket socket = new Socket(this.host, this.port);		
		
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());	
			oos.writeObject(request);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			String message = (String) ois.readObject();
			
			InputStream is = new ByteArrayInputStream(message.getBytes("UTF-8"));
			XMLDecoder decoder = new XMLDecoder(is);
			
			try 
			{
		        while ( true ) 
		        {
	        		list.add((Object)decoder.readObject());
		        }
		    } 
			catch ( ArrayIndexOutOfBoundsException exception ) {} 
			finally 
			{
		        decoder.close();
		    }
	    
			ois.close();
			oos.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public ArrayList<Object> getPersons() {
		String query = "get,getPersons";
		ArrayList<Object> list = this.request(query);
		persons = list;
		System.out.println(persons);
		return persons;
	}
	
	public ArrayList<Object> getMeetings() {
		String query = "get,getMeetings";
		ArrayList<Object> list = this.request(query);
		meetings = list;
		System.out.println(meetings);
		return meetings;
	}
}