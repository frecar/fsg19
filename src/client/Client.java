package client;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
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
import models.Person;

public class Client {

	private ArrayList<Person> persons;
	private MainFrame mf;
	
	public Client() {
		mf = new MainFrame(this);	
		
		System.out.println("clieddddnt");
		mf = new MainFrame(this);
		mf.initGUI();
		//persons = new ArrayList();
		
	}
	
	public static void main(String[] args){
		Client client = new Client();
		//client.getPersons();
	}	
	
	public String convertXMLFileToString(String fileName) 
    { 
      try{ 
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance(); 
        InputStream inputStream = new FileInputStream(new File(fileName)); 
        org.w3c.dom.Document doc = documentBuilderFactory.newDocumentBuilder().parse(inputStream); 
        StringWriter stw = new StringWriter(); 
        Transformer serializer = TransformerFactory.newInstance().newTransformer(); 
        serializer.transform(new DOMSource(doc), new StreamResult(stw)); 
        return stw.toString(); 
      } 
      catch (Exception e) { 
        e.printStackTrace(); 
      } 
        return null; 
    }
	
	public void getPersons() {
		Object object;
		FileInputStream os;

		try {
			Socket socket = new Socket("localhost", 8126);		
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());	
			oos.writeObject("get,getPersons");
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			String message = (String) ois.readObject();
						
			
			InputStream is = new ByteArrayInputStream(message.getBytes("UTF-8"));
			XMLDecoder decoder = new XMLDecoder(is);
			
			ArrayList list = new ArrayList();
		    try {
		        while ( true ) {
		            list.add(decoder.readObject() );
		        }
		    } catch ( ArrayIndexOutOfBoundsException exception ) {
		    } finally {
		        decoder.close();
		    }

		    
		    for (Object object2 : list) {
				System.out.println(object2);
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
	}
}