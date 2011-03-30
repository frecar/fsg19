package client.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class WestPanel extends JPanel{

	public WestPanel(LayoutManager layout) {
		super(layout);
		
		Object[] list = { "sadasfsafadfasdasdasd", 
				"sadasfsafadfasdasdasd",
				"sadasfsafadfasdasdasd",
				"sadasfsafadfasdasdasd",
				"sadasfsafadfasdasdasd",
				"sadasfsafadfasdasdasd",
				"sadasfsafadfasdasdasd",
				"sadasfsafadfasdasdasd",
				"sadasfsafadfasdasdasd",
				"sadasfsafadfasdasdasd",
				"sadasfsafadfasdasdasd",
				"sadasfsafadfasdasdasd",
				"sadasfsafadfasdasdasd",
				"sadasfsafadfasdasdasd",
				"sadasfsafadfasdasdasd"};
		
		JList appointments = new JList(list);
		JScrollPane scroll = new JScrollPane(appointments);
		
		JPanel labels = new JPanel();
		
		labels.add(new JButton("Previous"));
		labels.add(new JLabel("Week: 16 "));
		labels.add(new JButton("Next"));
		
		add(labels, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
	}
}