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
	private MainFrame mf;
	
	private String host;
	private int port;
	
	public Client() {
		mf = new MainFrame(this);	
		

		System.out.println("client started");

		this.host = "localhost";
		this.port = 8120;
		
		/*System.out.println("clieddddnt");
 		05b477c39217eab257eb81a5d9f5b5c175ac5c07
		mf = new MainFrame(this);
		mf.initGUI();
	/persons = new ArrayList(); */
		
	}
	
	public static void main(String[] args){
		Client client = new Client();
		client.getPersons();
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
			catch ( ArrayIndexOutOfBoundsException exception ) 
			{
				System.out.println("ARRAY ERROR");
		    } 
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
	
	public void getPersons() {
	
		String query = "get,getPersons";
		
		ArrayList<Object> list = this.request(query);
	
		persons = list;
	
	}
}