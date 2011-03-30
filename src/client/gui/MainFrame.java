package client.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	private static void createAndShowGUI() {
			
		JFrame frame = new JFrame("Epic new apploication");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		// Builds the menubar
		new MenuBarBuilder(frame);
		
		// Components
		JPanel mainPanel = new MainPanel();
		frame.add(mainPanel);
		
		//frame.pack();
		frame.setVisible(true);
		
		LoginDialog loginDialog = new LoginDialog(frame);
		loginDialog.setVisible(true);
		
		if(loginDialog.getSucceeded()) {
			//frame.setVisible(true);
		}	
	}
}
