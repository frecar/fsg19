package client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import client.Client;

public class MainPanel extends JPanel{

	private JPanel west, east, north, south, center;
	
	private MainFrame mainFrame;
	
	public MainPanel(MainFrame mainFrame) {
		
		this.mainFrame = mainFrame;
		
		setLayout(new BorderLayout(5, 10));
		//setBorder(new TitledBorder("KEKFACE"));
		
		center = new CenterPanel(new BorderLayout());
		south = new SouthPanel(new BorderLayout(), this);	
		north = new NorthPanel(new GridLayout(1, 1));		
		west = new WestPanel(new BorderLayout(), this);
		east = new EastPanel(new FlowLayout(), this);
		
		add(north, BorderLayout.NORTH);
		add(south, BorderLayout.SOUTH);
		add(center, BorderLayout.CENTER);
		add(west, BorderLayout.WEST);
		add(east, BorderLayout.EAST);
		
	}
	


	public MainFrame getMainFrame() {
		return mainFrame;
	}



	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
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
