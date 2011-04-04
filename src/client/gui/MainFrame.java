package client.gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import client.Client;

public class MainFrame {

	private LoginDialog loginDialog;
	private JFrame frame;
	private JFrame newMeeting;
	private JPanel mainPanel;
	
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
			
		
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		// the main application frame
		frame = new JFrame("Epic new application");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());		
		
		// Creates and add mainpanel, but does not show it yet(must login first)
		mainPanel = new MainPanel();
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
		loginDialog = new LoginDialog(frame);
		loginDialog.setVisible(true);
		
		/**
		 * If the login was successful, show the main panel, and create the full 
		 *	menubar
		 */
		if(loginDialog.getSucceeded()) {
			
			mainPanel.setVisible(true);
			new MenuBarBuilder(frame, this, MenuBarBuilder.AFTER_CONNECTED_MENUBAR);
		}
		else {
			// User must now click Server -> Connect to try to login again
		}
	}
	
	public void createAndShowAddMeeting() {
		newMeeting = new NewMeetingFrame("New meeting YEAH");
		newMeeting.setSize(600, 500);
		newMeeting.setLocationRelativeTo(null);
		//newMeeting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//newMeeting.setLayout(new GridBagLayout());
		newMeeting.setVisible(true);
	}
}
