package client.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Builds the menubar
 */
public class MenuBarBuilder {

	public MenuBarBuilder(JFrame f) {

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
}
