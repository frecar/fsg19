package client.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.sun.xml.internal.bind.v2.TODO;

import client.Client;
//import client.gui.EditMeetingFrame.AddMeetingListener;

import models.Meeting;
import models.Person;

public class SeeMessagesFrame extends JFrame{
	private JPanel panel;
	private JList list;
	private DefaultListModel listModel;
	private JScrollPane listScroll;
	private JButton confirm, cancel;
	
	private MainFrame mainFrame;
	
	private ArrayList<Meeting> meetings;
	private Person user;

	public SeeMessagesFrame(MainFrame mainFrame){
		
		this.mainFrame = mainFrame;
		meetings = Client.user.get_meetings();
		user = Client.user;
		panel = new JPanel(new GridBagLayout());
		listModel = new DefaultListModel();
		
		this.fillModel(listModel, meetings);
		
		list = new JList(listModel);
		listScroll = new JScrollPane(list);
		
		confirm = new JButton("Confirm");
		confirm.addActionListener(new ConfirmListener());
		cancel = new JButton("Cancel");
		cancel.addActionListener(new CancelListener());
		
		GridBagConstraints c = new GridBagConstraints();
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,0, 0, 0);
		//c.fill = GridBagConstraints.HORIZONTAL;
	
		c.gridx = 0;
		c.gridy = 0;
		panel.add(listScroll, c);
		
		c.gridx = 1;
		c.gridy = 0;
		panel.add(confirm, c);
		
		c.gridx = 1;
		c.gridy = 0;
		panel.add(cancel, c);
		
		
		add(panel);
		//add(new JButton("key key key key hey"));
		
		
	}
	
	private void fillModel(DefaultListModel model, ArrayList<Meeting> meetings) {
		
		for(Object o: meetings) {
			Meeting m = (Meeting)o;
			model.addElement(m);
		}
	}
	
	class ConfirmListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//
			
		}
	}
	
	class CancelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//
			
		}
	}
	
}
