package client.gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import models.Meeting;

import client.Client;

public class MainFrame {

	private LoginDialog loginDialog;
	private JFrame frame;
	private JFrame newMeeting, editMeeting, seeMessages;
	private JPanel mainPanel;
	
	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	private Client client;
	
	public MainFrame(Client client) {
		this.client = client;
	}
	
	public Client getClient() {
		return this.client;
	}
	
	public void initGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	private void createAndShowGUI() {
			
		
		
		/* try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {} */

		// the main application frame
		frame = new JFrame("Calendar 9000");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());		
		

		
		// Creates and add mainpanel, but does not show it yet(must login first)
		mainPanel = new MainPanel(this);
		mainPanel.setVisible(false);
		frame.add(mainPanel);
		
		//frame.pack();
		frame.setVisible(true);
		
		// Adds the "before-login" menubar
		new MenuBarBuilder(frame, this, MenuBarBuilder.BEFORE_CONNECTED_MENUBAR);
		
		// Creates and show a login dialog
		
		createAndShowLogin();
		/**
		 * Bypasses the login box because it became annoying after a while
//		 */	
		//mainPanel.setVisible(true);
		//new MenuBarBuilder(frame, this, MenuBarBuilder.AFTER_CONNECTED_MENUBAR);
		
	}
	
	public void createAndShowLogin() {
		loginDialog = new LoginDialog(frame, this);
		loginDialog.setVisible(true);
		
		/**
		 * If the login was successful, show the main panel, and create the full 
		 *	menubar
		 */
		if(loginDialog.getSucceeded()) {
			
			mainPanel.setVisible(true);
			MainPanel mainPanel2 = (MainPanel)mainPanel;			
			WestPanel westPanel = (WestPanel)mainPanel2.getWestPanel();
			//westPanel.fillMeetings();
			
			new MenuBarBuilder(frame, this, MenuBarBuilder.AFTER_CONNECTED_MENUBAR);
		}
		else {
			// User must now click Server -> Connect to try to login again
			JOptionPane.showMessageDialog(null, "The username and password does not match.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void createAndShowAddMeeting() {
		newMeeting = new NewMeetingFrame(this);
		newMeeting.setSize(800, 600);
		newMeeting.setLocationRelativeTo(null);
		//newMeeting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//newMeeting.setLayout(new GridBagLayout());
		newMeeting.setVisible(true);
	}
	
	public void createAndShowEditMeeting(Meeting meeting) {
		editMeeting = new EditMeetingFrame(this, meeting);
		editMeeting.setSize(800, 600);
		editMeeting.setLocationRelativeTo(null);
		//newMeeting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//newMeeting.setLayout(new GridBagLayout());
		editMeeting.setVisible(true);	
	}
	
	public void showMessages() {
		seeMessages = new SeeMessagesFrame(this);
		seeMessages.setSize(800, 600);
		seeMessages.setLocationRelativeTo(null);
		//newMeeting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//newMeeting.setLayout(new GridBagLayout());
		seeMessages.setVisible(true);
	}
}
