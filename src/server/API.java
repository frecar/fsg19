package server;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import models.Meeting;
import models.Person;
import models.Room;

public class API {


	private Object retreiveObject(String str) {
		try {
			InputStream is = new ByteArrayInputStream(str.getBytes("UTF-8"));
			XMLDecoder decoder = new XMLDecoder(is);
			return (Object)decoder.readObject();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void savePerson(String str) {
		Person p = (Person)retreiveObject(str);

		String query;
		
		if (p.getId()==0){
			query = "INSERT INTO Person (name, email,username,password) " +
					"VALUES (" +
					"'"+p.getName()+"'," +
					"'"+p.getEmail()+"'," +
					"'"+p.getUsername()+"'," +
					"'"+p.getPassword()+"'" +
					")";
		}
		else {
			query = "UPDATE Person SET " +
				"name='"+p.getName()+"', " +
				"email='"+p.getEmail()+"', " +
				"username='"+p.getUsername()+"', " +
				"password='"+p.getPassword()+"' " +
				"WHERE id = '"+p.getId()+"'";	
		}

		performUpdateQuery(query);	    
	}
	
		
	public void arrangeMeetingsAndPersons(Meeting meeting) {
		String query;
		
		query = "DELETE FROM Meeting_Person WHERE meeting_id = '"+meeting.getId()+"'";	
		performUpdateQuery(query);	    	
	
		for (Person person : meeting.getParticipants()) {
			query = "INSERT INTO Meeting_Person (person_id, meeting_id) " +
			"VALUES (" +
			"'"+person.getId()+"'," +
			"'"+meeting.getId()+"'" +
			")";	
			performUpdateQuery(query);	    	
		}
		
	}
	
	public void saveMeeting(String str) {
		Meeting p = (Meeting)retreiveObject(str);
				
		String query;		
		if (p.getId()==0){
			query = "INSERT INTO Meeting (title, room, time_start, time_end, description, responsible) " +
					"VALUES (" +
					"'"+p.getTitle()+"'," +
					"'"+p.getRoom()+"'," +
					"'"+p.getTimeStart()+"'," +
					"'"+p.getTimeEnd()+"'," +
					"'"+p.getDescription()+"'," +
					"'"+p.getResponsible()+"'" +
					")";
		}
		else {
			query = "UPDATE Meeting SET " +
				"title='"+p.getTitle()+"', " +
				"room='"+p.getRoom()+"', " +
				"time_start='"+p.getTimeStart()+"', " +
				"time_end='"+p.getTimeEnd()+"', " +
				"responsible='"+p.getResponsible()+"' " +
				"WHERE id = '"+p.getId()+"'";	
		}
		
		if (p.getId()==0){
			p.setId(Integer.parseInt(getHighestId("Meeting")));
		}
		performUpdateQuery(query);	    
		arrangeMeetingsAndPersons(p);
	}

	private String getHighestId(String table) {
		
		ResultSet result = this.requestDatabase("SELECT max(id) as id FROM "+table);
		
		try {
			while(result.next()) {
				return result.getString("id");
			} 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
		
	}
	
	private void performUpdateQuery(String query) {
		MySQLAccess dao = new MySQLAccess();
		
		Connection conn = dao.createConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
		    String sql = query;
		    int updateCount = stmt.executeUpdate(sql);		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Object getParticipantsForMeeting(String id) {
		String query = "SELECT Person.* FROM Person, Meeting_Person WHERE Person.id = Meeting_Person.person_id AND Meeting_Person.meeting_id = "+id;
		return getObjects(query, "models.Person");
	}
	
	public Object getMeetingsForUser(String id) {
		String query = "SELECT Meeting.* FROM Meeting, Meeting_Person WHERE Meeting.id = Meeting_Person.meeting_id AND Meeting_Person.person_id = "+id;
		return getObjects(query, "models.Meeting");
	}
	
	public Object getPersons() {
		String query = "SELECT * FROM Person";
		return getObjects(query, "models.Person");
	}
	
	public Object getMeetings() {
		String query = "SELECT * FROM Meeting";
		return getObjects(query, "models.Meeting");
	}
	
	public Object getRooms() {
		String query = "SELECT * FROM Room";
		return getObjects(query, "models.Room");
	}
	
	public Object getObjects(String query, String classname) {	
		ByteArrayOutputStream xml = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(xml);
        
        ResultSet result = requestDatabase(query);

		try {
			Class<?> cls = Class.forName(classname);
            Class<?> partypes[] = new Class[1];
            partypes[0] = ResultSet.class;
            Constructor<?> ct = cls.getConstructor(partypes);
            Object arglist[] = new Object[1];

            while(result.next()) {								
            	arglist[0] = result;
            	Object retobj = ct.newInstance(arglist);
                encoder.writeObject(retobj);
            }
		}	
		catch (Throwable e) {
            System.err.println(e);
         }
		
		encoder.close();	
		return xml;
	}
	
	/*
	 * Specify format like this:
	 * handle(method,arg1,arg2,arg3)
	 * 
	 */
	public ResultSet requestDatabase(String query) {

		MySQLAccess dao = new MySQLAccess();
		
		Connection conn = dao.createConnection();
		Statement stat;
		ResultSet result = null;
		
		try {
			stat = conn.createStatement();
			result = stat.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Object handle(String request) {
		
		API api = new API();

		String[] requestSplitted = request.split(",");
		String type 	= requestSplitted[0].trim();
		String method 	= requestSplitted[1].trim();
		
	    Class[] parameters = new Class[requestSplitted.length-2];
		String[] arguments = new String[requestSplitted.length-2];
		
		for (int i = 0; i < requestSplitted.length-2; i++) {
		    parameters[i] = String.class;
		    arguments[i] = requestSplitted[i+2].trim();
	    }
		
		try {
			Method m = api.getClass().getMethod(method, parameters);
			if(type.equals("get")) {
				Object ret = (Object) m.invoke(api, arguments);
				return (Object) ret;
			}
			else {
				m.invoke(api, arguments);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return "empty";
	}
	
	
}
