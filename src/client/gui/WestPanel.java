package client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	private MainPanel mainPanel;
	
	
	public WestPanel(LayoutManager layout, MainPanel parent) {
		super(layout);
		
		this.parent = parent;
		
		setBorder(new TitledBorder("WEST"));
		
		MainPanel mainPanel = (MainPanel)parent;
		
		
		
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
		
		labels.add(new JButton("Previous"));
		labels.add(new JLabel("Week: 16 "));
		labels.add(new JButton("Next"));
		
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
		System.out.println("now filling meetings");
		
	
//		m1 = new Meeting("Fest hos Arne", "12.12 2011", "3", "16:15", "18:00", "Will ther be cake?", "no", "no", "R7");
//		m2 = new Meeting("Budsjett", "05.12 2011", "4", "10:15", "14:00", "No cake for you?", "no", "no", "R50");
//		m3 = new Meeting("Kurs i java", "04.12 2011", "4", "10:15", "14:00", "No cake for you?", "no", "no", "R50");
//		
		
//		model.addElement(m1);
//		model.addElement(m2);
//		model.addElement(m3);
		
		
		for(Meeting m: Client.user.get_meetings()) {
			model.addElement(m);
			System.out.println(m.getDate());
		}
		
		meetings.setModel(model);
		
		new Thread(new Runnable() {
			public void run() {
				while(true) {
					meetings.setModel(getSortedMeetingsModel());
					
					try {
						Thread.sleep(10000);
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
		
		if(Client.user != null) {
			for(Object o: Client.user.get_meetings()) {
				model.addElement(o);
				System.out.println(((Meeting)o).getResponsible());
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
}