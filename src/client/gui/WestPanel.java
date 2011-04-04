package client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
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
		
		JList meetings = new JList(list);
		meetings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		meetings.addListSelectionListener(this);
		meetings.setCellRenderer(new MeetingRenderer());
		
		JScrollPane scroll = new JScrollPane(meetings);
		
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
		 *  Herregud så stygg kode. Masse stress for å få tak i riktige kompo-
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
	
	public class MeetingRenderer implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList arg0, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			
			//PersonModel p = (PersonModel) value;   TODO: use meeting model
			String s = (String) value;
			
			JLabel label = new JLabel();
			label.setText(s);
			label.setOpaque(true);

			if (isSelected) {
		        label.setBackground(Color.GREEN);
		    } 
		    else {
		    	label.setBackground(Color.BLUE);
		    }
			return label;
		}

	}
}