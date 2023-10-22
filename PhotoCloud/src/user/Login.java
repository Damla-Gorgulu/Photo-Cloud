package user;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import logging.BaseLogger;
import logging.Validator;

import javax.swing.JPasswordField;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends JPanel implements ActionListener {
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton loginbutton;
	private JLabel lblNewLabel;
	private JButton registerbutton;
	Register registerpage;
	MainFrame mainframe;
	transient BaseLogger logger = BaseLogger.getInstance();
	
    /**
     * Constructs a Login panel on the MainFrame for the already registered users to visit their profiles
     * upon successful information entry.
     * @param main The MainFrame of the program
     */

	public Login(MainFrame main) {
		setLayout(null);
		this.setBackground(new Color(182, 225, 255));
		this.setBounds(10, 10, 380, 550);
		this.setLayout(null);
		
		JLabel usernamelable = new JLabel("Username");
		usernamelable.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		usernamelable.setBounds(54, 174, 90, 16);
		add(usernamelable);
		
		textField = new JTextField();
		textField.setBounds(156, 158, 143, 48);
		add(textField);
		textField.setColumns(10);
		
		JLabel passwordlabel = new JLabel("Password");
		passwordlabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		passwordlabel.setBounds(57, 231, 82, 16);
		add(passwordlabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(156, 217, 143, 45);
		add(passwordField);
		
		loginbutton = new JButton("Login");
		loginbutton.setBounds(166, 287, 117, 29);
		loginbutton.addActionListener(this);
		add(loginbutton);
		
		lblNewLabel = new JLabel("Not Registered Yet?");
		lblNewLabel.setFont(new Font("Hiragino Mincho ProN", Font.PLAIN, 20));
		lblNewLabel.setBounds(90, 350, 225, 50);
		add(lblNewLabel);
		
		registerbutton= new JButton("Register");
		registerbutton.setBounds(166, 416, 117, 29);
		registerbutton.addActionListener(this);
		add(registerbutton);
		
		
		this.setVisible(true);
		this.mainframe=main;
	}
	
	 /**
     * @param e The action event
     * If the user has accidentally clicked the Login Jbutton of the MainFrame, they are directed to the SignUp Page.
     * If the user clicks on the Login JButton the compatibility of the username and password is checked by looking for 
     * a match through the user list.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == registerbutton) {
	        mainframe.changeLoginToRegister();
	    } else if (e.getSource() == loginbutton) {
	        String username = textField.getText();
	        String password = new String(passwordField.getPassword());

	        if (!Validator.isValidUsername(username)) {
	        	logger.logError("Error: username must start with a letter or a number");
	            JOptionPane.showMessageDialog(this, "Error: username must start with a letter or a number");
	            
	        } else if (!Validator.ValidPassword(password)) {
	        	logger.logError("Error: password must be at least 6 characters long");
	            JOptionPane.showMessageDialog(this, "Error: password must be at least 6 characters long");
	            
	        } else {

	            for (User user : User.getUserList()) {
	                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
	                    mainframe.setUser(user);
	                    mainframe.changeLoginToProfile();
	                    BaseLogger.logInfo(username + " has successfully logged in.");
	                    return; 
	                }
	            }
	            logger.logError("Error: The username and password do not match!");
	            JOptionPane.showMessageDialog(this, "Error: The username and password do not match!");
	        }
	    }
	}

}



