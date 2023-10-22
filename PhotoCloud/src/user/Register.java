package user;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import logging.BaseLogger;
import logging.Validator;

import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

/**
 * A JPanel for the user to enter their private registration information and Sign up. GUI components
 * such as JTextFields, JPasswrodFields, JComboBoxes and JButtons are used. The Action Listener interface 
 * is implemented through which button click events are handled. The Validator class' static methods 
 * are accessed to verify the validity of the information that is entered.
 */
public class Register extends JPanel implements ActionListener {
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField emailField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	JButton Signupbutton;
	JButton choosePhoto;
	private JLabel photo;
	MainFrame main;
	JComboBox ageComboBox;
	JComboBox<String> userComboBox;
	User user ;
	BaseLogger logger = BaseLogger.getInstance();
	
	/**
	 * A register object is constructed 
	 * @param mainframe The main frame of the application which the classes that extend JPanel are added
	 */

	public Register(MainFrame mainframe) {
		
		setLayout(null);
		this.setBackground(new Color(182, 225, 255));
		this.setBounds(10, 10, 380, 550);
		this.setLayout(null);
		
		JLabel name_label = new JLabel("Name");
		name_label.setBounds(50, 60, 61, 16);
		add(name_label);
		
		JLabel surname_label = new JLabel("Surname");
		surname_label.setBounds(50, 104, 61, 16);
		add(surname_label);
		
		JLabel age_label = new JLabel("Age");
		age_label.setBounds(50, 145, 61, 16);
		add(age_label);
		
		JLabel email_label = new JLabel("E-mail adress");
		email_label.setBounds(50, 185, 100, 16);
		add(email_label);
		
		JLabel username_label = new JLabel("Username");
		username_label.setBounds(50, 229, 74, 16);
		add(username_label);
		
		JLabel password_label = new JLabel("Password");
		password_label.setBounds(50, 271, 61, 16);
		add(password_label);
		
		//Name Field
		nameField = new JTextField();
		nameField.setBounds(175, 55, 130, 25);
		add(nameField);
		nameField.setColumns(10);
		
		surnameField = new JTextField();
		surnameField.setBounds(175, 100, 130, 25);
		add(surnameField);
		surnameField.setColumns(10);
		
		//Rather than adding bounds to the age that is entered by displaying error messages stating 
		//that the number that is entered is invalid, I have used a combo box.
		
		//To set the limits of the combo box, I have used an array list to which all the numbers in a
		//valid range was added.
		
		List<Integer> age_limit = new ArrayList<Integer>();
		for (int i = 10; i <= 120; ++i) {
		    age_limit.add(i);
		}
		
		ageComboBox = new JComboBox(age_limit.toArray());
		ageComboBox.setBackground(new Color(255, 201, 123));
		ageComboBox.setBounds(175, 140, 80, 40);
		add(ageComboBox);
		
		//To form the choices of the combo box the user types are added to an array to.
		//The administrator type can only be chosen in the sign up page.
		String[] user_type = new String[4];
				
		user_type[0]="free";
		user_type[1]="hobbyist";
		user_type[2]="professional";
		user_type[3]="administrator";
		
		userComboBox = new JComboBox<>(user_type);
		userComboBox.setBackground(new Color(255, 201, 123));
		userComboBox.setBounds(210, 310, 150, 40);
		add(userComboBox);
		
		//E-Mail Field
		emailField = new JTextField();
		emailField.setBounds(175, 180, 130, 25);
		add(emailField);
		emailField.setColumns(10);
		
		//Username Field
		usernameField = new JTextField();
		usernameField.setBounds(175, 225, 130, 25);
		add(usernameField);
		usernameField.setColumns(10);
		
		//Password Field
		passwordField = new JPasswordField();
		passwordField.setBounds(174, 265, 130, 25);
		add(passwordField);
		
		JLabel lblNewLabel_6 = new JLabel("Let's get started!");
		lblNewLabel_6.setFont(new Font("Hiragino Mincho ProN", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(40, 6, 250, 50);
		add(lblNewLabel_6);
		
		//Button to Sign Up
		Signupbutton = new JButton("Sign Up!");
		Signupbutton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		Signupbutton.setBounds(217, 475, 130, 30);
		Signupbutton.addActionListener(this);
		add(Signupbutton);
		
		//Label to display the defalut profile photo
		photo = new JLabel();
		photo.setBounds(50, 310, 150, 150);
		ImageIcon image1 = new ImageIcon(new ImageIcon("Icons/default.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
		photo.setIcon(image1);
		photo.setOpaque(true);
		add(photo);
		
		
		choosePhoto = new JButton("Choose a Photo!");
		choosePhoto.setBounds(50, 476, 150, 29);
		choosePhoto.addActionListener(this);
		add(choosePhoto);
		
		this.main=mainframe;
		
		JLabel label = new JLabel("Already registered?");
		label.setFont(new Font("Hiragino Mincho ProN", Font.PLAIN, 12));
		label.setBounds(40, 510, 150, 30);
		add(label);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(217, 510, 80, 30);
		add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				main.changeRegisterToLogin();
				BaseLogger.logInfo("User directed to the login page.");
			}
		});
	}
	


	/**
	 * @e Performed action
	 * If the user presses the Choose a Photo JButton an instance of the class JFileChooser is created
	 * for the user to be able to access the photos on their computer. If the Sign Up JButtpn was pressed
	 * the Validity of the information is checked by accessing the static methods of the class Validator. 
	 * Errors are logged in.
	 */
	@Override
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == choosePhoto) {
	      JFileChooser fileChooser = new JFileChooser();
	      fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	      int result = fileChooser.showOpenDialog(photo);
	      if (result == JFileChooser.APPROVE_OPTION) {
	    	  File selectedFile = fileChooser.getSelectedFile();
	    	  ImageIcon image = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath())
	                    .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
	           
	          photo.setIcon(image);
	          BaseLogger.logInfo("User chose a profile photo.");
	        }
	    }

		else if (e.getSource() == Signupbutton) {
	        String name = nameField.getText();
	        String surname = surnameField.getText();
	        int age = (int) ageComboBox.getSelectedItem();
	        String email = emailField.getText();
	        String username = usernameField.getText();
	        String password = new String(passwordField.getPassword());
	        String userType = (String) userComboBox.getSelectedItem();
	  

	        if (!Validator.isValidName(name)) {
	        	
	        	logger.logError("Error: name must contain only characters of the alpabeth");
	            JOptionPane.showMessageDialog(this, "Error: name must contain only characters of the alpabeth");
	            return; 
	            
	        } else if (!Validator.isValidName(surname)) {
	        	
	        	logger.logError("Error: surname must contain only characters of the alphabet");
	            JOptionPane.showMessageDialog(this, "Error: surname must contain only characters of the alphabet");
	            return; 
	            
	        } else if (!Validator.isValidMail(email)) {
	        	
	        	logger.logError("Error: surname must contain only characters of the alphabet");
	            JOptionPane.showMessageDialog(this, "Error: email should have the format ----@----.--");
	            return;
	            
	        } else if (!Validator.ValidPassword(password)) {
	        	
	        	logger.logError("Error: password must be at least 6 characters long");
	            JOptionPane.showMessageDialog(this, "Error: password must be at least 6 characters long");
	            return;
	        } 
	        
	        for (User user : User.getUserList()) {
	            if (user.getUsername().equals(username)) {
	            	
		        	logger.logError("Error: This username is taken. Please choose another one.");
	                JOptionPane.showMessageDialog(this, "This username is taken. Please choose another one.");
	                return;
	                
	            } else if (user.getEmail().equals(email)) {
	            	
	            	logger.logError("Error: This email is taken. Please choose another one.");
	                JOptionPane.showMessageDialog(this, "This email is taken. Please choose another one.");
	                return;
	            }
	        }

	        // A new user object is created.
	        user = new User(name, surname, age, email, username, password, userType, (ImageIcon)photo.getIcon());
	        BaseLogger.logInfo("User signed up successfully.");
	        main.setUser(user);
	     
	        nameField.setText("");
	        surnameField.setText("");
	        emailField.setText("");
	        usernameField.setText("");
	        passwordField.setText("");
	        ageComboBox.setSelectedIndex(0);
	        userComboBox.setSelectedIndex(0);

	        main.changeRegisterToProfile();
	        BaseLogger.logInfo("User is viewing their profile page.");
	        
	    		} 

			}


	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}


}
