package client.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainFrame {

	private static LoginDialog loginDialog;
	private static JFrame frame;
	private static JPanel mainPanel;
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	private static void createAndShowGUI() {
			
		
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		frame = new JFrame("Epic new application");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		// Creates and add mainpanel, but does not show it yet(must login first)
		mainPanel = new MainPanel();
		mainPanel.setVisible(false);
		frame.add(mainPanel);
		
		frame.pack();
		frame.setVisible(true);
		
		// Adds the "before-login" menubar
		new MenuBarBuilder(frame, MenuBarBuilder.BEFORE_CONNECTED_MENUBAR);
		
		// Creates and show a login dialog
		showLogin();
		
		/**
		 * Bypasses the login box because it became annoying after a while
		 */
//		mainPanel.setVisible(true);
//		new MenuBarBuilder(frame, MenuBarBuilder.AFTER_CONNECTED_MENUBAR);
		
	}
	
	public static void showLogin() {
		loginDialog = new LoginDialog(frame);
		loginDialog.setVisible(true);
		
		/**
		 * If the login was successful, show the main panel, and create the full 
		 *	menubar
		 */
		if(loginDialog.getSucceeded()) {
			
			mainPanel.setVisible(true);
			new MenuBarBuilder(frame, MenuBarBuilder.AFTER_CONNECTED_MENUBAR);
		}
		else {
			// User must now click Server -> Connect to try to login again
		}
	}
}
