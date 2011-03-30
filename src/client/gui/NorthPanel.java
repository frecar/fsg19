package client.gui;

import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NorthPanel extends JPanel{

	public NorthPanel(LayoutManager layout) {
		super(layout);
		
		//setBorder(new TitledBorder("NORTH"));
		ImageIcon image = new ImageIcon("/home/nab/fsg19/src/images/calendar.png");
		add(new JLabel(image));
	}
}