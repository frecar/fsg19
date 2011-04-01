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

public class EastPanel extends JPanel{
	
	private JPanel appointmentPanel;
	
	public EastPanel(LayoutManager layout) {
		super(layout);
		
		setBorder(new TitledBorder("EAST"));
		
		appointmentPanel = new AppointmentPanel();
		add(appointmentPanel);
		
	}
	
	public JPanel getAppointmentPanel() {
		return this.appointmentPanel;
	}
	/**
	 * Contains the currently selected appointment from the list
	 */
	class AppointmentPanel extends JPanel {
		
		private JLabel titleLabel, dateLabel, timeLabel, placeLabel, participantsLabel, commentLabel;
		private JTextField titleTextField, dateTextField, timeTextField, placeTextField, participantsTextField, commentTextField;
		
		public AppointmentPanel() {
			
			setLayout(new GridBagLayout());
			setBorder(new TitledBorder("Selected appointment"));
			//setSize(300, 500);
			
			titleLabel = new JLabel("Title:");
			dateLabel = new JLabel("Date:");
			timeLabel = new JLabel("Time:");
			placeLabel = new JLabel("Place:");
			participantsLabel = new JLabel("Participants:");
			commentLabel = new JLabel("Comment:");
			
			titleTextField = new JTextField("Budsjettkutt skal diskuteres", 20);
			titleTextField.setEnabled(false);
			
			dateTextField = new JTextField("08.05.11", 20);
			dateTextField.setEnabled(false);
			
			timeTextField = new JTextField("10:15", 20);
			timeTextField.setEnabled(false);
			
			placeTextField = new JTextField("Bøttekottet", 20);
			placeTextField.setEnabled(false);
			
			participantsTextField = new JTextField("7", 3);
			participantsTextField.setEnabled(false);
			
			commentTextField = new JTextField("Alle må ta med snacks", 20);
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
	}
	
	
}