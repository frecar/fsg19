package models;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileHandler {
	
	public static void createFile(File file){
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Object deSerialize(File file, Object object) {
		FileInputStream os;
		try {
			os = new FileInputStream(file);
			XMLDecoder decoder = new XMLDecoder(os);
			object = (Object)decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return object;
	}
	
	public static void serialize(Object object, File file){
		FileOutputStream os;
		try {
			os = new FileOutputStream(file);
			XMLEncoder encoder = new XMLEncoder(os);
			encoder.writeObject(object);
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
