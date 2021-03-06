package client.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

import client.Client;
import client.gui.EastPanel.MeetingPanel;
import models.Meeting;
import models.Person;
import models.Room;

/**
 * the frame for creating new meetings
 */
public class EditMeetingFrame extends JFrame {

	private JPanel panel;
	
	private JLabel titleLabel;
	private JLabel dateLabel;
	private JLabel responsibleLable;
	private JLabel timeLabel;
	private JLabel timeToLabel;
	private JLabel descriptionLabel;
	private JLabel roomLabel;
	private JLabel participantsLabel;

	
	private JTextField titleTextField;
	private JTextField dateTextField;
	private JTextField responsibleTextField;
	private JTextField timeStartTextField;
	private JTextField timeEndTextField;
	private JTextField descriptionTextField;
	private JTextField roomTextField;
	private JTextField participantsTextField;
	
	private JButton saveMeeting, closeFrame;
	
	private JList leftList, rightList;
	private DefaultListModel leftModel;
	private DefaultListModel rightModel;
	private JScrollPane leftScroll, rightScroll;
	
	private JButton addPerson, removePerson;
	
	private MainFrame mainFrame;
	
	private ArrayList<Person> persons;

	private JComboBox roomsComboBox;
	private ArrayList<Room> rooms = Room.all();
	
	private Person user;
	
	private Meeting meeting;
	
	public EditMeetingFrame(MainFrame mainFrame, Meeting meeting) {

		setTitle("Edit meeting");
		
		this.mainFrame = mainFrame;
		this.persons = Person.all();
		this.user = Client.user;
		this.meeting = meeting;
		
		panel = new JPanel(new GridBagLayout());
		
		titleLabel = new JLabel("Title:");
		responsibleLable = new JLabel("Responsible:");
		dateLabel = new JLabel("Date:");
		timeLabel = new JLabel("Time:"); 
		timeToLabel = new JLabel("to");
		roomLabel = new JLabel("Room:");
		participantsLabel = new JLabel("Participants:");
		descriptionLabel = new JLabel("Comment:");
		
		titleTextField = new JTextField(meeting.getTitle(), 20);
		//titleTextField.setEnabled(false);
		
		String loggedInUser;
		
		if(this.getUser() == null) {
			loggedInUser = "YOU BYPASSED THE LOGIN YOU HACKLER";
		}
		else {
			loggedInUser = this.getUser().getName();
		}
		
		responsibleTextField = new JTextField(loggedInUser, 20);
		responsibleTextField.setEnabled(false);
		
		dateTextField = new JTextField(meeting.getDate(), 20);
		//dateTextField.setEnabled(false);
		
		timeStartTextField = new JTextField(meeting.getTimeStart(), 5);
		
		timeEndTextField = new JTextField(meeting.getTimeEnd(), 5);
		///timeTextField.setEnabled(false);
		
		String meetingRoom = meeting.getRoom();
		
		Object[] roomsStrings = rooms.toArray();
		roomsComboBox = new JComboBox(roomsStrings);
		
		// sets the combobox to the current meeting room
		for(int i = 0; i < roomsStrings.length; i++) {
			if((roomsStrings[i].toString()).equals(meetingRoom)) {
				
				roomsComboBox.setSelectedItem(roomsStrings[i]);
				System.out.println("ROOM: " + i);
			}
		}
		
		roomsComboBox.addActionListener(new SelectRoomListener());
	
		//String theFirstRoom = roomsComboBox.getItemAt(0).toString();
		roomTextField = new JTextField(meetingRoom , 10);
		roomTextField.setEnabled(false);
		
		leftModel = new DefaultListModel();
		rightModel = new DefaultListModel();
		
		this.fillModel(leftModel, persons);
		// Add the currently logged in user
		rightModel.addElement(this.getUser());
		meeting.updateParticipants();
		this.fillModel(rightModel, meeting.getParticipants());
		
		leftList = new JList(leftModel);
		leftScroll = new JScrollPane(leftList);
		
		rightList = new JList(rightModel);
		rightScroll = new JScrollPane(rightList);
		
		addPerson = new JButton("Add");
		addPerson.addActionListener(new AddPersonListener());
		removePerson = new JButton("Remove");
		removePerson.addActionListener(new RemovePersonListener());
		
		//participantsTextField = new JTextField("7", 3);  @DEPRECATED
		//participantsTextField.setEnabled(false);
		
		descriptionTextField = new JTextField(meeting.getDescription(), 20);
		//commentTextField.setEnabled(false);
		
		saveMeeting = new JButton("Save meeting");
		saveMeeting.addActionListener(new SaveMeetingListener());
		
		closeFrame = new JButton("Cancel");
		closeFrame.addActionListener(new CloseFrameListener());
		
		GridBagConstraints c = new GridBagConstraints();
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,0,0,0);
		//c.fill = GridBagConstraints.HORIZONTAL;
	
		c.gridx = 0;
		c.gridy = 0;
		panel.add(titleLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		panel.add(titleTextField, c);
		
		c.gridx = 0;
		c.gridy = 1;
		panel.add(responsibleLable, c);
		
		c.gridx = 1;
		c.gridy = 1;
		panel.add(responsibleTextField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		panel.add(dateLabel, c);
		
		
		c.gridx = 1;
		c.gridy = 2;
		panel.add(dateTextField, c);
		
		c.gridx = 0;
		c.gridy = 3;
		panel.add(timeLabel, c);
		
		c.gridx = 1;
		c.gridy = 3;
		panel.add(timeStartTextField, c);
		
		c.gridx = 2;
		c.gridy = 3;
		panel.add(timeToLabel, c);
		
		c.gridx = 3;
		c.gridy = 3;
		panel.add(timeEndTextField, c);
		
		c.gridx = 0;
		c.gridy = 4;
		panel.add(roomLabel, c);
		
		c.gridx = 1;
		c.gridy = 4;
		panel.add(roomsComboBox, c);
		
		c.gridx = 2;
		c.gridy = 4;
		panel.add(roomTextField, c);
		
		c.gridx = 0;
		c.gridy = 5;
		panel.add(participantsLabel, c);
		
		c.gridx = 1;
		c.gridy = 5;
		//panel.add(participantsTextField, c);
		panel.add(leftScroll, c);
		
		c.gridx = 2;
		c.gridy = 5;
		panel.add(addPerson, c);
		
		c.gridx = 3;
		c.gridy = 5;
		panel.add(removePerson, c);
		
		c.gridx = 4;
		c.gridy = 5;
		//panel.add(participantsTextField, c);
		panel.add(rightScroll, c);
		
		c.gridx = 0;
		c.gridy = 6;
		panel.add(descriptionLabel, c);
		
		c.gridx = 1;
		c.gridy = 6;
		panel.add(descriptionTextField, c);

		c.gridx = 1;
		c.gridy = 7;
		panel.add(saveMeeting, c);
		
		c.gridx = 2;
		c.gridy = 7;
		panel.add(closeFrame, c);
		
		add(panel);
		//add(new JButton("key key key key hey"));
		
	}
	
	public Person getUser() {
		return user;
	}

	public void setUser(Person user) {
		this.user = user;
	}

	/**
	 * Fills the model with all persons, except the logged in user, which is already
	 * in the right list
	 */
	private void fillModel(DefaultListModel model, ArrayList<Person> persons) {
		
		for(Object o: persons) {
			Person p = (Person)o;
			if(p.getId() != this.getUser().getId()) {
				model.addElement(p);
			}
		}
	}
	
	class SelectRoomListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String room = (String) roomsComboBox.getSelectedItem().toString();
			roomTextField.setText(room);
		}
	}
	class AddPersonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(! leftModel.isEmpty() && leftList.getSelectedIndex() != -1) {
				
				// Add the selected person to the right list
				Person person = (Person)leftList.getSelectedValue();
				rightModel.addElement(person);
				
				// And then remove it from the left list
				leftModel.removeElement(person);
				//JOptionPane.showMessageDialog(null, "Person was added.");
			}
			else {
				System.out.println("The left list is empty or no element has focus");
			}
		}
	}
	
	class RemovePersonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(! rightModel.isEmpty() && rightList.getSelectedIndex() != -1) {
				
				Person person = (Person)rightList.getSelectedValue();
				
				// Dont allow people removing themselves from the meeting
				if(person.getId() == getUser().getId()) {
					JOptionPane.showMessageDialog(null, "You cant remove yourself.");
				}
				else {
					leftModel.addElement(person);
					rightModel.removeElement(person);
				}
			}
			else {
				System.out.println("the right list is empty or no element had focus");
			}
		}
	}
	
	class SaveMeetingListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			// TODO: Gather all data and make a meeting object, and save() it
			String title = titleTextField.getText();
			String responsible = getUser().getName();
			String date = dateTextField.getText();
			String timeStart = timeStartTextField.getText();
			String timeEnd = timeEndTextField.getText();
			String room = roomTextField.getText();
			String description = descriptionTextField.getText();
			
			meeting.setTitle(title);
			meeting.setResponsible("" + Client.user.getId());
			meeting.setDate(date);
			meeting.setTimeStart(timeStart);
			meeting.setTimeEnd(timeEnd);
			meeting.setRoom(room);
			meeting.setDescription(description);
			
			
			meeting.clearParticipants();
			
			for(int i = 0; i < rightModel.size(); i++) {
				Person p = (Person)rightModel.get(i);
				meeting.addParticipant(p);
			}
			// TODO: add meeting to model
			//Meeting meeting = new Meeting(title, date, responsible, timeStart, timeEnd, description, "no", "no", room);
			
			System.out.println("KAKE: " + meeting.getParticipants());
			meeting.save();
			
			// update meetingpanel
			MainPanel mainPanel = (MainPanel)mainFrame.getMainPanel();
			EastPanel eastPanel = (EastPanel)mainPanel.getEastPanel();
			MeetingPanel meetingPanel = (MeetingPanel)eastPanel.getMeetingPanel();
			meetingPanel.setModel(meeting);
			
			JOptionPane.showMessageDialog(null, "The meeting was changed.");
			dispose();
		}
	}
	
	class CloseFrameListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(null, "The meeting was NOT edited.");
			dispose();
		}
	}
}
