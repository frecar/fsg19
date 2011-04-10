package client.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Builds the menubar before and after login. When the user is not
 * logged in yet, the menubar should simply contain a button called disconnect
 */
public class MenuBarBuilder {

	public static final int BEFORE_CONNECTED_MENUBAR = 0;
	public static final int AFTER_CONNECTED_MENUBAR = 1;
	
	public MainFrame mf;
	
	public MenuBarBuilder(JFrame f, MainFrame mf, int action) {

		this.mf = mf;
		
		if(action == MenuBarBuilder.AFTER_CONNECTED_MENUBAR) {
			JMenuBar menuBar = new JMenuBar();
			f.setJMenuBar(menuBar);
			
			JMenu fileMenu = new JMenu("Calendar");
			menuBar.add(fileMenu);
			
				JMenuItem newMeetingMenuItem = new JMenuItem("New Meeting");
				fileMenu.add(newMeetingMenuItem);
				newMeetingMenuItem.addActionListener(new newMeetingListener());
				
//				JMenuItem newAppointmentMenuItem = new JMenuItem("New Appointment");
//				fileMenu.add(newAppointmentMenuItem);
//				newAppointmentMenuItem.addActionListener(new newAppointmentListener());
				
				JMenuItem seeMessagesMenuItem = new JMenuItem("See Messages");
				fileMenu.add(seeMessagesMenuItem);
				seeMessagesMenuItem.addActionListener(new seeMessagesListener());
				
				fileMenu.addSeparator();
				
				JMenuItem exitMenuItem = new JMenuItem("Exit");
				fileMenu.add(exitMenuItem);
				exitMenuItem.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
			
//			JMenu helpMenu = new JMenu("Help");
//			menuBar.add(helpMenu);
//			
//				JMenuItem lolMenuItem = new JMenuItem("You are fucked");
//				helpMenu.add(lolMenuItem);
		}
		else if(action == MenuBarBuilder.BEFORE_CONNECTED_MENUBAR) {
			JMenuBar menuBar = new JMenuBar();
			f.setJMenuBar(menuBar);
			
			JMenu serverMenu = new JMenu("Server");
			menuBar.add(serverMenu);
			
			JMenuItem connectMenuItem = new JMenuItem("Login");
			serverMenu.add(connectMenuItem);
			connectMenuItem.addActionListener(new ConnectListener());			
			
		}
	}
	
	class ConnectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			mf.createAndShowLogin();
		}
	}

	class newMeetingListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			mf.createAndShowAddMeeting();
		}
	}
	
	class newAppointmentListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		}
	}
	
	class seeMessagesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			mf.showMessages();
		}
	}

}
