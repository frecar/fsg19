package client.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import models.Person;

/**
 * the frame for creating new meetings
 */
public class NewMeetingFrame extends JFrame {

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
	
	private JButton addMeeting, closeFrame;
	
	private JList leftList, rightList;
	private DefaultListModel leftModel;
	private DefaultListModel rightModel;
	private JScrollPane leftScroll, rightScroll;
	
	private JButton addPerson, removePerson;
	
	private MainFrame mainFrame;
	
	private ArrayList<Person> persons;
	
	private Person user;
	
	public NewMeetingFrame(MainFrame mainFrame) {
		
		setTitle("New meeting HELL YEAH");
		
		this.mainFrame = mainFrame;
		this.persons = mainFrame.getClient().getPersons();
		this.user = mainFrame.getClient().getUser();
		
		panel = new JPanel(new GridBagLayout());
		
		titleLabel = new JLabel("Title:");
		responsibleLable = new JLabel("Responsible:");
		dateLabel = new JLabel("Date:");
		timeLabel = new JLabel("Time:"); 
		timeToLabel = new JLabel("to");
		roomLabel = new JLabel("Room:");
		participantsLabel = new JLabel("Participants:");
		descriptionLabel = new JLabel("Comment:");
		
		titleTextField = new JTextField("Budsjettkutt skal diskuteres", 20);
		//titleTextField.setEnabled(false);
		
		String loggedInUser;
		
		if(this.getUser() == null) {
			loggedInUser = "YOU BYPASSED THE LOGIN";
		}
		else {
			loggedInUser = this.getUser().getName();
		}
		
		responsibleTextField = new JTextField(loggedInUser, 20);
		responsibleTextField.setEnabled(false);
		
		dateTextField = new JTextField("08.05.11", 20);
		//dateTextField.setEnabled(false);
		
		timeStartTextField = new JTextField("0815", 5);
		
		timeEndTextField = new JTextField("10:00", 5);
		///timeTextField.setEnabled(false);
		
		roomTextField = new JTextField("Bøttekottet", 20);
		//placeTextField.setEnabled(false);
		
		leftModel = new DefaultListModel();
		rightModel = new DefaultListModel();
		
		this.fillModel(leftModel, persons);
		// Add the currently logged in user
		rightModel.addElement(this.getUser());
		
		leftList = new JList(leftModel);
		leftScroll = new JScrollPane(leftList);
		
		rightList = new JList(rightModel);
		rightScroll = new JScrollPane(rightList);
		
		addPerson = new JButton("Add");
		addPerson.addActionListener(new AddPersonListener());
		removePerson = new JButton("Remove");
		removePerson.addActionListener(new RemovePersonListener());
		
		participantsTextField = new JTextField("7", 3);
		//participantsTextField.setEnabled(false);
		
		descriptionTextField = new JTextField("Alle må ta med snacks", 20);
		//commentTextField.setEnabled(false);
		
		addMeeting = new JButton("Add meeting");
		addMeeting.addActionListener(new AddMeetingListener());
		
		closeFrame = new JButton("Cancel");
		closeFrame.addActionListener(new CloseFrameListener());
		
		GridBagConstraints c = new GridBagConstraints();
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
	
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
		panel.add(addMeeting, c);
		
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
	 * Fils the model with all persons, except the logged in user, which is already
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
				System.out.println((Person)rightList.getSelectedValue());
				leftModel.addElement(person);
				
				// And then remove it from the left list
				rightModel.removeElement(person);
				// TODO: add meeting to model
				//JOptionPane.showMessageDialog(null, "Person was REMOVED.");
			}
			else {
				System.out.println("the right list is empty or no element had focus");
			}
		}
	}
	
	class AddMeetingListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			// TODO: add meeting to model
			JOptionPane.showMessageDialog(null, "The meeting was added.");
			dispose();
		}
	}
	
	class CloseFrameListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(null, "The meeting was NOT added.");
			dispose();
		}
	}
}
