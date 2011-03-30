package client.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class MainPanel extends JPanel{

	private JPanel west, east, north, south, center;
	
	MainPanel() {
		
		setLayout(new BorderLayout(5, 10));
		//setBorder(new TitledBorder("KEKFACE"));
		
		north = new NorthPanel(new GridLayout(1, 1));
		south = new SouthPanel(new BorderLayout());
		center = new CenterPanel(new BorderLayout());
		west = new WestPanel(new BorderLayout());
		east = new EastPanel(new BorderLayout());
		
		add(north, BorderLayout.NORTH);
		add(south, BorderLayout.SOUTH);
		add(center, BorderLayout.CENTER);
		add(west, BorderLayout.WEST);
		add(east, BorderLayout.EAST);
	}
}
