package client.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import models.Person;

public class LoginDialog extends JDialog implements ActionListener {

	private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JButton cancelButton;
    private boolean succeeded;

    private MainFrame mainFrame;
    
    /**
     * A reference with different name was needed because the method in the
     * inner method, could not reference it otherwise. See the first actionPerformed()
     */
    private MainFrame mainFrame2;
    
    private ArrayList<Person> persons;
    
    public LoginDialog(JFrame frame, MainFrame mainFrame) {
    	super(frame, "Login", true);
    	
    	this.mainFrame = mainFrame;
    	
    	mainFrame2 = mainFrame;
    	
    	persons = mainFrame.getClient().getPersons();
    	
    	JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

    	usernameLabel = new JLabel("Username: (bokstaven a gir tilgang) ");
    	panel.add(usernameLabel);
    	
    	usernameTextField = new JTextField(20);
    	panel.add(usernameTextField);
    	
    	passwordLabel = new JLabel("Password: ");
    	panel.add(passwordLabel);
    	
    	passwordTextField = new JPasswordField(20);
    	panel.add(passwordTextField);
    	passwordTextField.addActionListener(this);
    	
    	panel.setBorder(new LineBorder(Color.GRAY));
    	
    	loginButton = new JButton("Login");
    	loginButton.addActionListener(this);
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
    	setLocationRelativeTo(frame);
    	setResizable(false);
    }
    
    public boolean getSucceeded() {
    	return succeeded;
    }
    

	public void actionPerformed(ActionEvent e) {
		
		// BYPASS:
		//succeeded = true;
		
		
		// TODO: check with Person list if password was correct
		
		int index = 0;
		
		for(Object o: persons) {
			String username = ((Person)o).getUsername();
			String password = ((Person)o).getPassword();
			
			//System.out.println("user: " + username + " pass: " + password);
			
			// TODO: check if this actually works
			if(usernameTextField.getText().equals(username) && new String(passwordTextField.getPassword()).equals(password)) {
				succeeded = true;
				
				// Sets the currently logged in user
				Person user = (Person) persons.get(index);
				mainFrame2.getClient().setUser(user);
				
				// Update the status
				MainPanel mainPanel = (MainPanel)mainFrame2.getMainPanel();
				SouthPanel southPanel = (SouthPanel)mainPanel.getSouthPanel();
				JLabel status = southPanel.getStatus();
				status.setText("Logged in as: " + username + " Email: " + user.getEmail());
				break;
			}
			
			index++;
		}
	
		dispose();
	}
    
}
