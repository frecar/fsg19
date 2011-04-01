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
	
	public MenuBarBuilder(JFrame f, int action) {

		if(action == MenuBarBuilder.AFTER_CONNECTED_MENUBAR) {
			JMenuBar menuBar = new JMenuBar();
			f.setJMenuBar(menuBar);
			
			JMenu fileMenu = new JMenu("File");
			menuBar.add(fileMenu);
			
				JMenuItem newMenuItem = new JMenuItem("New");
				fileMenu.add(newMenuItem);
				
				JMenuItem openMenuItem = new JMenuItem("Open");
				fileMenu.add(openMenuItem);
				
				fileMenu.addSeparator();
				
				JMenuItem exitMenuItem = new JMenuItem("Exit");
				fileMenu.add(exitMenuItem);
				exitMenuItem.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
			
			JMenu helpMenu = new JMenu("Help");
			menuBar.add(helpMenu);
			
				JMenuItem lolMenuItem = new JMenuItem("You are fucked");
				helpMenu.add(lolMenuItem);
		}
		else if(action == MenuBarBuilder.BEFORE_CONNECTED_MENUBAR) {
			JMenuBar menuBar = new JMenuBar();
			f.setJMenuBar(menuBar);
			
			JMenu serverMenu = new JMenu("Server");
			menuBar.add(serverMenu);
			
			JMenuItem connectMenuItem = new JMenuItem("Connect");
			serverMenu.add(connectMenuItem);
			
			connectMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.showLogin();
				}
			});			
			
		}
	}
}
