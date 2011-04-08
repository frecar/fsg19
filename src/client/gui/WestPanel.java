package client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.Meeting;
import models.Person;

import client.Client;
import client.gui.EastPanel.MeetingPanel;

public class WestPanel extends JPanel {

	private MainPanel parent;
	private JList meetings;
	private DefaultListModel model;
	
	private ArrayList<Meeting> meetingsList;
	
	private Meeting m1, m2, m3, m4, m5;
	
	private Calendar now = new GregorianCalendar();

	private MainPanel mainPanel;
	
	private JButton next, prev;
	
	private int realWeek, currentWeek, currentDay;

	
	private JLabel currentWeekLabel;
	private int currentWeekOfYear;
	
	public WestPanel(LayoutManager layout, MainPanel parent) {
		super(layout);
		
		currentWeekOfYear = now.get(Calendar.WEEK_OF_YEAR);
		
		this.parent = parent;
	
		setBorder(new TitledBorder("WEST"));
		
		MainPanel mainPanel = (MainPanel)parent;
		
		currentDay = now.get(Calendar.DAY_OF_WEEK);
		//create an array of days
//		String[] strDays = new String[]{
//		"Sunday",
//		"Monday",
//		"Tuesday",
//		"Wednesday",
//		"Thusday",
//		"Friday",
//		"Saturday"};
//		
//		realDay = now.get(Calendar.DAY_OF_WEEK);
//		System.out.println(strDays[realDay - 1]);
//		
//		minPrev = realDay - 2;
//		maxNext = 7 - realDay + 1; 
//		System.out.println(minPrev + " " + maxNext);
		//System.exit(0);
		//meetingsList = Meeting.all();
		
//		for(Meeting m: meetingsList) {
//			// System.out.println(m.getTitle());
//		}
//		
		//System.exit(0);
		
		model = new DefaultListModel();
	

		
//		
//		for(Meeting m: Client.user.get_meetings()) {
//			System.out.println(m);
//		}
		
		meetings = new JList(getSortedMeetingsModel());
		meetings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		meetings.addListSelectionListener(new MeetingSelectionListener());
		meetings.setCellRenderer(new MeetingRenderer());
		
		JScrollPane scroll = new JScrollPane(meetings);
		
		JPanel labels = new JPanel();
		
		realWeek = now.get(Calendar.WEEK_OF_YEAR) - 1;
		currentWeek = realWeek;
		
		prev = new JButton("Previous");
		prev.addActionListener(new PrevMeetingListener());
		
		next = new JButton("Next");
		next.addActionListener(new NextMeetingListener());
		
		currentWeekLabel = new JLabel("Week: "  + realWeek + "");
		
		labels.add(prev);
		labels.add(currentWeekLabel);
		labels.add(next);
		
		add(labels, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		
	
		
		// Test for checking model view controller
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				int i = 0;
//				//while(true) {
//					try {
//						Thread.sleep(4000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					model.removeElement(m3);
//					meetings.setModel(model);
//					System.out.println("boyah!");
//					//m1.setTitle("the numbah is: " + i);
//					//i += 10;
//				//}
//			}
//		}).start();
		
	
		
	}

	public void fillMeetings() {
		
		
	
//		m1 = new Meeting("Fest hos Arne", "12.12 2011", "3", "16:15", "18:00", "Will ther be cake?", "no", "no", "R7");
//		m2 = new Meeting("Budsjett", "05.12 2011", "4", "10:15", "14:00", "No cake for you?", "no", "no", "R50");
//		m3 = new Meeting("Kurs i java", "04.12 2011", "4", "10:15", "14:00", "No cake for you?", "no", "no", "R50");
//		
		
//		model.addElement(m1);
//		model.addElement(m2);
//		model.addElement(m3);
		
		

		
		meetings.setModel(model);
		
		new Thread(new Runnable() {
			public void run() {
				while(true) {
					DefaultListModel m = getSortedMeetingsModel();
			
					meetings.setModel(m);
					//System.out.println("now filling meeting(this is done each 10 sec)");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	
	}
	
	public JList getMeetings() {
		return meetings;
	}

	public void setMeetings(JList meetings) {
		this.meetings = meetings;
	}

	public DefaultListModel getModel() {
		return model;
	}


	public DefaultListModel getSortedMeetingsModel() {
		
		Object[] meetings = model.toArray();
		Arrays.sort(meetings);
		DefaultListModel model = new DefaultListModel();
		
		System.out.println(meetings.length);
		if(Client.user != null) {
			for(Object o: Client.user.get_meetings()) {
				
				Meeting m1 = (Meeting)o;
				
				String date = m1.getDate();
				int year = Integer.parseInt(date.substring(6, 10));
				int month = Integer.parseInt(date.substring(3, 5));
				int day = Integer.parseInt(date.substring(0, 2));
			
				Calendar cal = new GregorianCalendar(year, month - 1, day);
				
				
				
				if((cal.get(Calendar.WEEK_OF_YEAR) == currentWeekOfYear)) {
					model.addElement(o);
					//System.out.println("Adding: " + o);
				}
				else {
					//System.out.println("Not adding: " + cal.get(Calendar.WEEK_OF_YEAR) + ":" + now.get(Calendar.WEEK_OF_YEAR));
					//System.out.println(now);
				}
						
				
			
				//System.out.println(((Meeting)o).getResponsible());
			}
		}
		
		return model;
	}
	
	public class MeetingRenderer implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList arg0, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			
			//System.out.println("ListCellRenderer: getListCellRendererComponent()");
			Meeting meeting = (Meeting) value;
			
			// Find the meeting with the longest title, used for nice JLabel alignment
//			Object[] meetings = model.toArray();
//			int longest = 0;
//			for(int i = 0; i < meetings.length; i++) {
//				Meeting m1 = (Meeting)meetings[i];
//				Meeting m2 = (Meeting)meetings[longest];
//				
//				if(m1.getTitle().length() > m2.getTitle().length()) {
//					longest = i;
//				}
//			}
//			Meeting meetingWithLongestTitle = (Meeting)meetings[longest];
//			
//			String labelText = meeting.getTitle();
//			
//			int meetingLength = meeting.getTitle().length();
//			
//			while(meetingLength <= meetingWithLongestTitle.getTitle().length()) {
//				labelText += "  ";
//				meetingLength++;
//			}
			String sep = " | ";
			
			String labelText = meeting.getTitle() + sep + 
				meeting.getDate() + sep + meeting.getTimeStart() + "-" +
					meeting.getTimeEnd() + sep + meeting.getRoom();
			
			Icon icon = new ImageIcon("src/images/meeting_icon.gif");
			JLabel label = new JLabel(icon, JLabel.LEFT);
			label.setText(labelText);
			label.setOpaque(true);

			if (isSelected) {
		        label.setBackground(Color.GREEN);
		    } 
		    else {
		    	//label.setBackground(Color.BLUE);
		    }
			return label;
		}

	}
	
	public class MeetingSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			//System.out.println("A meeting was selected from the meetings JList");
			
			/**
			 *  Herregud så stygg kode. Masse stress for å få tak i riktige komponenter som skal endres
			 */
			
			if(meetings.getSelectedIndex() != -1) {
				MainPanel m = (MainPanel)parent;
				EastPanel e = (EastPanel)m.getEastPanel();
				MeetingPanel a = (MeetingPanel)e.getMeetingPanel();
				a.setModel((Meeting)meetings.getSelectedValue());
			}
			
//			JTextField titleTextField = a.getTitle();
//			
//			Random r = new Random();
//			String token = Long.toString(Math.abs(r.nextLong()), 36);
//			
//			titleTextField.setText(token);
			
		}
		
	}
	
	public class NextMeetingListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if(currentWeek == 51) {
				currentWeek = 0;
				currentWeekOfYear = 0;
			}
			else {
				currentWeek++;
				currentWeekOfYear++;
			}
			
			currentWeekLabel.setText("Week: " + ((1 + currentWeek)));
			System.out.println("NEXT");
		}
	}
	
	public class PrevMeetingListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if(currentWeek == 0) {
				currentWeek = 51;
				currentWeekOfYear = 51;
			}
			else {
				currentWeek--;
				currentWeekOfYear--;
			}

			currentWeekLabel.setText("Week: " + ((currentWeek+1)));
			System.out.println("PREV");
		}
	}
}