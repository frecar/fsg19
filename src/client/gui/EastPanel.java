package client.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import models.Meeting;
import models.Person;

import client.interfaces.MeetingListener;

public class EastPanel extends JPanel{
	
	private JPanel meetingPanel;
	
	private JPanel parent;
	
	public EastPanel(LayoutManager layout, JPanel parent) {
		super(layout);
		
		this.parent = parent;
		
		setBorder(new TitledBorder("EAST"));
		
		meetingPanel = new MeetingPanel();
		add(meetingPanel);
		
	}
	
	public JPanel getMeetingPanel() {
		return this.meetingPanel;
	}
	/**
	 * Contains the currently selected appointment from the list
	 */
	class MeetingPanel extends JPanel implements MeetingListener, ActionListener {
		
		
		private JLabel titleLabel;
		private JLabel dateLabel;
		private JLabel responsibleLable;
		private JLabel timeLabel;
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
		
		private JButton editButton, deleteButton;
		

		/**
		 * The meeting which this panel currently holds
		 */
		Meeting model;
		
		
		/**
		 * When a new model is set, all the GUI components are updated
		 */
		public void setModel(Meeting model) {
			this.model = model;
			
			// Add this MeetingPanel as listener on the model
			if(model != null) {
				setVisible(true);
				
				editButton.setEnabled(false);
				deleteButton.setEnabled(false);
				
				model.addMeetingListener(this);
		
				this.titleTextField.setText(this.model.getTitle());
				String name = Person.get(Integer.parseInt(this.model.getResponsible())).getName();
				this.responsibleTextField.setText(name);
				this.dateTextField.setText(this.model.getDate());
				this.timeStartTextField.setText(this.model.getTimeStart());
				this.timeEndTextField.setText(this.model.getTimeEnd());
				this.roomTextField.setText(this.model.getRoom());
				this.participantsTextField.setText(this.model.getNumOfParticipants());
				
				this.descriptionTextField.setText(this.model.getDescription());
				
				MainPanel mainPanel = (MainPanel)parent;
				Person user = mainPanel.getMainFrame().getClient().user;
				
				// if the meeting is owned by current user
				if(user.getName().equals(Person.get(Integer.parseInt(this.model.getResponsible())).getName())) {
					editButton.setEnabled(true);
					deleteButton.setEnabled(true);
				}

				
			}
			else {
				setVisible(false);
			}
	
			repaint();
		
		}
		
		public MeetingPanel() {
			
			setLayout(new GridBagLayout());
			setBorder(new TitledBorder("Selected meeting"));
			setVisible(false);
			titleLabel = new JLabel("Title:");
			responsibleLable = new JLabel("Responsible:");
			dateLabel = new JLabel("Date:");
			timeLabel = new JLabel("Time:");
			roomLabel = new JLabel("Room:");
			participantsLabel = new JLabel("Participants:");
			descriptionLabel = new JLabel("Description:");
			
			titleTextField = new JTextField("TITLE", 20);
			titleTextField.setEnabled(false);
			
			dateTextField = new JTextField("DATE", 20);
			dateTextField.setEnabled(false);
			
			responsibleTextField = new JTextField("RESPONSIBLE", 20);
			responsibleTextField.setEnabled(false);
			
			timeStartTextField = new JTextField("TIMESTART", 10);
			timeStartTextField.setEnabled(false);
			
			timeEndTextField = new JTextField("TIMEEND", 10);
			timeEndTextField.setEnabled(false);
			
			roomTextField = new JTextField("ROOM", 20);
			roomTextField.setEnabled(false);
			
			participantsTextField = new JTextField("PARTICIPANTS", 3);
			participantsTextField.setEnabled(false);
			
		
			
			descriptionTextField = new JTextField("DESCRIPTION", 20);
			descriptionTextField.setEnabled(false);
			
			editButton = new JButton("Edit");
			editButton.addActionListener(this);
			
			deleteButton = new JButton("Delete");
			deleteButton.addActionListener(this);
			
			GridBagConstraints c = new GridBagConstraints();
			//c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(10,0,0,0);
			
			c.gridx = 0;
			c.gridy = 0;
			add(titleLabel, c);
			
			c.gridx = 1;
			c.gridy = 0;
			add(titleTextField, c);
			
			c.gridx = 0;
			c.gridy = 1;
			
			add(responsibleLable, c);
			
			c.gridx = 1;
			c.gridy = 1;
			add(responsibleTextField, c);
			
			c.gridx = 0;
			c.gridy = 2;
			add(dateLabel, c);
			
			
			c.gridx = 1;
			c.gridy = 2;
			add(dateTextField, c);
			
			c.gridx = 0;
			c.gridy = 3;
			add(timeLabel, c);
			
			c.gridx = 1;
			c.gridy = 3;
			add(timeStartTextField, c);
			
			c.gridx = 2;
			c.gridy = 3;
			add(timeEndTextField, c);
			
			c.gridx = 0;
			c.gridy = 4;
			add(roomLabel, c);
			
			c.gridx = 1;
			c.gridy = 4;
			add(roomTextField, c);
			
			c.gridx = 0;
			c.gridy = 5;
			add(participantsLabel, c);
			
			c.gridx = 1;
			c.gridy = 5;
			add(participantsTextField, c);
	
			
			c.gridx = 0;
			c.gridy = 6;
			add(descriptionLabel, c);
			
			c.gridx = 1;
			c.gridy = 6;
			add(descriptionTextField, c);
			
			c.gridx = 0;
			c.gridy = 7;
			add(editButton, c);
			
			c.gridx = 1;
			c.gridy = 7;
			add(deleteButton, c);
		

//			c.gridx = 0;
//			c.gridy = 7;
//			add(addMeeting, c);
//			
//			c.gridx = 1;
//			c.gridy = 7;
//			add(closeFrame, c);
			
			//add(new JButton("rightpanel"));
		}
		
		public JTextField getTitle() {
			return this.titleTextField;
		}

		@Override
		public void meetingUpdated() {
			//System.out.println("A meetinglistener reveived a call to meetingUpdated. In other words; something changed in the model");
			
			// The setModel method will refresh the GUI elements according to the model
			setModel(this.model);
			
		}

		/**
		 * Pops up a JFrame where the meeting can be edited
		 */
		public void actionPerformed(ActionEvent event) {
		
			MainPanel mainPanel = (MainPanel)parent;
			
			if(event.getSource() == editButton) {
				mainPanel.getMainFrame().createAndShowEditMeeting(model);
			}
			else if(event.getSource() == deleteButton) {
				// Delete it from the server
				model.delete();
				
				mainPanel = (MainPanel)parent;
				WestPanel westPanel = (WestPanel)mainPanel.getWestPanel();
				DefaultListModel defModel = westPanel.getModel();
				defModel.removeElement(model);
				System.out.println("sletter");
				setModel(null);
				
				
				
				westPanel.getMeetings().setModel(westPanel.getSortedMeetingsModel());
				
				
			}
			
		}
	}

	
}