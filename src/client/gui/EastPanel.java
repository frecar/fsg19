package client.gui;

import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EastPanel extends JPanel{

	public EastPanel(LayoutManager layout) {
		super(layout);
		
		//setBorder(new TitledBorder("NORTH"));
		
		add(new JButton("EAST!!!"));
	}
}