package client.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.gui.EastPanel.AppointmentPanel;

public class WestPanel extends JPanel implements ListSelectionListener {

	private JPanel parent;
	
	public WestPanel(LayoutManager layout, JPanel parent) {
		super(layout);
		
		this.parent = parent;
		
		setBorder(new TitledBorder("WEST"));
		
		Object[] list = { "1 sadasfsafadfasdasdasd", 
				"2 sadasfsafadfasdasdasd",
				"3 sadasfsafadfasdasdasd",
				"4 sadasfsafadfasdasdasd",
				"5 sadasfsafadfasdasdasd",
				"6 sadasfsafadfasdasdasd",
				"7 sadasfsafadfasdasdasd",
				"8 sadasfsafadfasdasdasd",
				"9 sadasfsafadfasdasdasd",
				"> 9000 sadasfsaf DENNE VAR LITT LENGRE ENN DE ANDRE",
				"10 sadasfsafadfasdasdasd",
				"11 sadasfsafadfasdasdasd",
				"12 sadasfsafadfasdasdasd",
				"13 sadasfsafadfasdasdasd",
				"14 sadasfsafadfasdasdasd"};
		
		JList appointments = new JList(list);
		appointments.addListSelectionListener(this);
		
		JScrollPane scroll = new JScrollPane(appointments);
		
		JPanel labels = new JPanel();
		
		labels.add(new JButton("Previous"));
		labels.add(new JLabel("Week: 16 "));
		labels.add(new JButton("Next"));
		
		add(labels, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		//System.out.println("mordi");
		
		/**
		 *  Herregud så stygg kode. Masse stress for å få tak i riktige kompo
		 *  nenter som skal endres
		 */
		MainPanel m = (MainPanel)parent;
		EastPanel e = (EastPanel)m.getEastPanel();
		AppointmentPanel a = (AppointmentPanel)e.getAppointmentPanel();
		JTextField titleTextField = a.getTitle();
		
		Random r = new Random();
		String token = Long.toString(Math.abs(r.nextLong()), 36);
		
		titleTextField.setText(token);
	}
}