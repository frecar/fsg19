package client.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class LoginDialog extends JDialog{

	private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JButton cancelButton;
    private boolean succeeded;

   
    public LoginDialog(JFrame parent) {
    	super(parent, "Login", true);
    	
    	JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

    	usernameLabel = new JLabel("Username: ");
    	panel.add(usernameLabel);
    	
    	usernameTextField = new JTextField(20);
    	panel.add(usernameTextField);
    	
    	passwordLabel = new JLabel("Password: ");
    	panel.add(passwordLabel);
    	
    	passwordTextField = new JPasswordField(20);
    	panel.add(passwordTextField);
    	
    	panel.setBorder(new LineBorder(Color.GRAY));
    	
    	loginButton = new JButton("Login");
    	loginButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    			// TODO: check with Person list if password was correct
    			
    			if(usernameTextField.getText().equals("a")) {
    				succeeded = true;
    			}
    			
    			dispose();
    		}
    	});
    	panel.add(loginButton);
    	
    	cancelButton = new JButton("Cancel");
    	cancelButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    			dispose();
    		}
    	});
    	panel.add(cancelButton);
    	
    	add(panel);
    	

    	pack();
    	setLocationRelativeTo(parent);
    	setResizable(false);
    }
    
    public boolean getSucceeded() {
    	return succeeded;
    }
}
