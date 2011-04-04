package client.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import models.Meeting;

import client.interfaces.MeetingListener;

public class EastPanel extends JPanel{
	
	private JPanel meetingPanel;
	
	public EastPanel(LayoutManager layout) {
		super(layout);
		
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
	class MeetingPanel extends JPanel implements MeetingListener{
		
		private JLabel titleLabel, dateLabel, timeLabel, placeLabel, participantsLabel, commentLabel;
		private JTextField titleTextField, dateTextField, timeTextField, placeTextField, participantsTextField, commentTextField;
		
		/**
		 * The meeting which this panel currently holds
		 */
		private Meeting meeting;
		
		/**
		 * When a new model is set, all the GUI components are updated
		 */
		public void setModel(Meeting meeting) {
			this.meeting = meeting;
			
			// Add this EastPanel as listener on the model
			this.meeting.addMeetingListener(this);
			
			this.titleTextField.setText(this.meeting.getTitle());
			this.dateTextField.setText(this.meeting.getDate());
			this.timeTextField.setText(this.meeting.getTime());
			this.placeTextField.setText(this.meeting.getPlace());
			this.participantsTextField.setText(this.meeting.getNumOfParticipants());
			this.commentTextField.setText(this.meeting.getComment());
		}
		
		public MeetingPanel() {
			
			setLayout(new GridBagLayout());
			setBorder(new TitledBorder("Selected appointment"));
			
			titleLabel = new JLabel("Title:");
			dateLabel = new JLabel("Date:");
			timeLabel = new JLabel("Time:");
			placeLabel = new JLabel("Place:");
			participantsLabel = new JLabel("Participants:");
			commentLabel = new JLabel("Comment:");
			
			titleTextField = new JTextField("EMPTY", 20);
			titleTextField.setEnabled(false);
			
			dateTextField = new JTextField("EMPTY", 20);
			dateTextField.setEnabled(false);
			
			timeTextField = new JTextField("EMPTY", 20);
			timeTextField.setEnabled(false);
			
			placeTextField = new JTextField("EMPTY", 20);
			placeTextField.setEnabled(false);
			
			participantsTextField = new JTextField("EMPTY", 3);
			participantsTextField.setEnabled(false);
			
			commentTextField = new JTextField("EMPTY", 20);
			commentTextField.setEnabled(false);
			
			
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
			add(dateLabel, c);
			
			
			c.gridx = 1;
			c.gridy = 1;
			add(dateTextField, c);
			
			c.gridx = 0;
			c.gridy = 2;
			add(timeLabel, c);
			
			c.gridx = 1;
			c.gridy = 2;
			add(timeTextField, c);
			
			c.gridx = 0;
			c.gridy = 3;
			add(placeLabel, c);
			
			c.gridx = 1;
			c.gridy = 3;
			add(placeTextField, c);
			
			c.gridx = 0;
			c.gridy = 4;
			add(participantsLabel, c);
			
			c.gridx = 1;
			c.gridy = 4;
			add(participantsTextField, c);
			
			c.gridx = 0;
			c.gridy = 5;
			add(commentLabel, c);
			
			c.gridx = 1;
			c.gridy = 5;
			add(commentTextField, c);
			
			
		
			
			c.gridx = 0;
			c.gridy = 1;
			
			c.gridx = 1;
			c.gridy = 1;
			
			//add(new JButton("rightpanel"));
		}
		
		public JTextField getTitle() {
			return this.titleTextField;
		}

		@Override
		public void meetingUpdated() {
			System.out.println("oh my god, something was changed in the model");
			// The setModel method will refresh the GUI elements according to the model
			setModel(this.meeting);
			
		}
	}
	
	
}