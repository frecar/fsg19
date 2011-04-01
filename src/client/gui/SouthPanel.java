package client.gui;

import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class SouthPanel extends JPanel{

	public SouthPanel(LayoutManager layout) {
		super(layout);
		
		setBorder(new TitledBorder("SOUTH"));
		
		add(new JLabel("STATUS: you should abort mission before all hell " +
				"breaks loose"));
	}
}