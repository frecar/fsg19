package client.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * the frame for creating new meetings
 */
public class NewMeetingFrame extends JFrame {

	private JPanel panel;
	private JLabel titleLabel, dateLabel, timeLabel, placeLabel, participantsLabel, commentLabel;
	private JTextField titleTextField, dateTextField, timeTextField, placeTextField, participantsTextField, commentTextField;
	private JButton addMeeting, closeFrame;
	
	public NewMeetingFrame(String title) {
		super(title);
		
		panel = new JPanel(new GridBagLayout());
		
		titleLabel = new JLabel("Title:");
		dateLabel = new JLabel("Date:");
		timeLabel = new JLabel("Time:");
		placeLabel = new JLabel("Place:");
		participantsLabel = new JLabel("Participants:");
		commentLabel = new JLabel("Comment:");
		
		titleTextField = new JTextField("Budsjettkutt skal diskuteres", 20);
		//titleTextField.setEnabled(false);
		
		dateTextField = new JTextField("08.05.11", 20);
		//dateTextField.setEnabled(false);
		
		timeTextField = new JTextField("10:15", 20);
		///timeTextField.setEnabled(false);
		
		placeTextField = new JTextField("Bøttekottet", 20);
		//placeTextField.setEnabled(false);
		
		participantsTextField = new JTextField("7", 3);
		//participantsTextField.setEnabled(false);
		
		commentTextField = new JTextField("Alle må ta med snacks", 20);
		//commentTextField.setEnabled(false);
		
		addMeeting = new JButton("Add meeting");
		addMeeting.addActionListener(new AddMeetingListener());
		
		closeFrame = new JButton("Cancel");
		closeFrame.addActionListener(new CloseFrameListener());
		
		GridBagConstraints c = new GridBagConstraints();
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,0,0,0);
		
		c.gridx = 0;
		c.gridy = 0;
		panel.add(titleLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		panel.add(titleTextField, c);
		
		c.gridx = 0;
		c.gridy = 1;
		panel.add(dateLabel, c);
		
		
		c.gridx = 1;
		c.gridy = 1;
		panel.add(dateTextField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		panel.add(timeLabel, c);
		
		c.gridx = 1;
		c.gridy = 2;
		panel.add(timeTextField, c);
		
		c.gridx = 0;
		c.gridy = 3;
		panel.add(placeLabel, c);
		
		c.gridx = 1;
		c.gridy = 3;
		panel.add(placeTextField, c);
		
		c.gridx = 0;
		c.gridy = 4;
		panel.add(participantsLabel, c);
		
		c.gridx = 1;
		c.gridy = 4;
		panel.add(participantsTextField, c);
		
		c.gridx = 0;
		c.gridy = 5;
		panel.add(commentLabel, c);
		
		c.gridx = 1;
		c.gridy = 5;
		panel.add(commentTextField, c);

		c.gridx = 0;
		c.gridy = 6;
		panel.add(addMeeting, c);
		
		c.gridx = 1;
		c.gridy = 6;
		panel.add(closeFrame, c);
		
		add(panel);
		//add(new JButton("key key key key hey"));
		
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
