package client.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class CenterPanel extends JPanel{

	private JPanel calendarPanel, rightPanel;
	
	public CenterPanel(LayoutManager layout) {
		super(layout);
		
		setBorder(new TitledBorder("CENTER"));
		
		/**  DEPRECATED
		JPanel dates = new JPanel(new GridLayout(6, 6, 30, 30));
		
		//dates.setBorder(new TitledBorder("wee"));
		
		for(int i = 1; i < 37; i++) {
			
			JButton day = new JButton(i + "");
			dates.add(day);
		}
		//setBorder(new TitledBorder("NORTH"));
		*/
//		JLabel year = new JLabel("2011", JLabel.CENTER);
//		year.setFont(new Font("Serif", Font.BOLD, 48));
//		add(year, BorderLayout.SOUTH);
		
	
		
		//calendarPanel = new CalendarPanel();
		//add(calendarPanel);


		
		//add(dates);
		//add(new JButton("dummy button"), BorderLayout.SOUTH);
		//add(new JButton("CENTER!!!"), BorderLayout.EAST);
	}
	
	/**
	 * Contains the currently selected appointment from the list
	 */
	
}
