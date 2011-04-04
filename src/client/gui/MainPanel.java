package client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
		//center.setOpaque(true);
		//center.setBackground(Color.BLUE);
		
		west = new WestPanel(new BorderLayout(), this);
		east = new EastPanel(new FlowLayout());
		
		add(north, BorderLayout.NORTH);
		add(south, BorderLayout.SOUTH);
		add(center, BorderLayout.CENTER);
		add(west, BorderLayout.WEST);
		add(east, BorderLayout.EAST);
	}
	
	public JPanel getEastPanel() {
		return this.east;
	}
	
	public JPanel getWestPanel() {
		return this.west;
	}
	
	public JPanel getSouthPanel() {
		return this.south;
	}
	
	public JPanel getNorthPanel() {
		return this.north;
	}
	
	public JPanel getCenterPanel() {
		return this.center;
	}
}
