package client.gui;

import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class SouthPanel extends JPanel{

	private JPanel parent;
	
	private JLabel status;
	
	public SouthPanel(LayoutManager layout, JPanel parent) {
		super(layout);
		
		this.parent = parent;
		
		setBorder(new TitledBorder("SOUTH"));
		
		status = new JLabel("STATUS: you should abort mission before all hell " +
				"breaks loose");
		add(status);
	}

	public JLabel getStatus() {
		return status;
	}
}