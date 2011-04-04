package client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.Arrays;
import java.util.Random;

import javax.swing.DefaultListModel;
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

import models.Meeting;

import client.gui.EastPanel.MeetingPanel;

public class WestPanel extends JPanel {

	private JPanel parent;
	private JList meetings;
	DefaultListModel model;
	
	private Meeting m1, m2, m3, m4, m5;
	
	
	public WestPanel(LayoutManager layout, JPanel parent) {
		super(layout);
		
		this.parent = parent;
		
		setBorder(new TitledBorder("WEST"));
		
		m1 = new Meeting("tittel", "12.12 2011", "3", "16:15", "1800", "Will ther be cake?", "no", "no", "R7");
		m2 = new Meeting("tittel222", "05.12 2011", "4", "10:15", "1400", "No cake for you?", "no", "no", "R50");
		
		
		model = new DefaultListModel();
		model.addElement(m1);
		model.addElement(m2);
		
		meetings = new JList(getSortedMeetingsModel());
		meetings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		meetings.addListSelectionListener(new MeetingSelectionListener());
		meetings.setCellRenderer(new MeetingRenderer());
		
		JScrollPane scroll = new JScrollPane(meetings);
		
		JPanel labels = new JPanel();
		
		labels.add(new JButton("Previous"));
		labels.add(new JLabel("Week: 16 "));
		labels.add(new JButton("Next"));
		
		add(labels, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		
		// Test for checking model view controller
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i = 0;
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					m1.setTitle("the numbah is: " + i);
					i += 10;
				}
			}
		}).start(); */
		
	
	}

	public DefaultListModel getSortedMeetingsModel() {
		
		Object[] meetings = model.toArray();
		Arrays.sort(meetings);
		DefaultListModel model = new DefaultListModel();
		
		for(Object o: meetings) {
			model.addElement(o);
		}
		
		return model;
	}
	
	public class MeetingRenderer implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList arg0, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			
			//System.out.println("ListCellRenderer: getListCellRendererComponent()");
			Meeting m = (Meeting) value;
			
			JLabel label = new JLabel();
			label.setText(m.toString());
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
	
	public class MeetingSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			System.out.println("A meeting was selected from the meetings JList");
			
			/**
			 *  Herregud så stygg kode. Masse stress for å få tak i riktige kompo-
			 *  nenter som skal endres
			 */
			MainPanel m = (MainPanel)parent;
			EastPanel e = (EastPanel)m.getEastPanel();
			MeetingPanel a = (MeetingPanel)e.getMeetingPanel();
			a.setModel((Meeting)meetings.getSelectedValue());
		
			
//			JTextField titleTextField = a.getTitle();
//			
//			Random r = new Random();
//			String token = Long.toString(Math.abs(r.nextLong()), 36);
//			
//			titleTextField.setText(token);
			
		}
		
	}
}