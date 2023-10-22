package user;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import logging.BaseLogger;
import logging.Validator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

/**
 * The `Settings` class represents the settings panel where users can update their profile information.
 * It allows users to modify their user information except their username. 
 */
public class Settings extends JFrame implements ActionListener {

    JPanel panel;
    JLabel name;
    JLabel surname;
    JLabel age;
    JLabel email;
    JLabel username;
    JLabel password;
    JLabel photo;
    JLabel photo2;
    JLabel user_type;
    JComboBox<Integer> ageComboBox;
    JComboBox<String> userComboBox;
    User user;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField emailField;
    private JLabel usernameField;
    private JPasswordField passwordField;
    JButton Update;
    JButton choosePhoto;
    MainFrame main;
    Profile profilePanel;
    transient BaseLogger logger = BaseLogger.getInstance();

    /**
     * Creates a new `Settings` instance with the specified `MainFrame`, `User`, and `Profile` panel.
     *
     * @param main          The MainFrame of the application
     * @param user          The user whose settings are being modified.
     * @param profilePanel  The Profile Page of the user
     */
    public Settings(MainFrame main, User user, Profile profilePanel) {
        this.user = user;
        this.main = main;
        this.profilePanel = profilePanel;
        this.setBounds(0, 0, 400, 600);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(255, 226, 179));
        this.setResizable(false);

        panel = new JPanel();
        panel.setBackground(new Color(182, 225, 255));
        panel.setBounds(10, 10, 380, 550);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Previous");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
        lblNewLabel.setBounds(20, 25, 150, 16);
        panel.add(lblNewLabel);

        JLabel lblCurrent = new JLabel("Current");
        lblCurrent.setFont(new Font("Lucida Grande", Font.BOLD, 16));
        lblCurrent.setBounds(220, 25, 150, 16);
        panel.add(lblCurrent);

        name = new JLabel("Name: " + user.getName());
        name.setBounds(20, 67, 150, 16);
        panel.add(name);

        surname = new JLabel("Surname: " + user.getSurname());
        surname.setBounds(20, 99, 150, 16);
        panel.add(surname);

        age = new JLabel("Age: " + user.getAge());
        age.setBounds(20, 127, 150, 16);
        panel.add(age);

        email = new JLabel("E-mail: " + user.getEmail());
        email.setBounds(20, 167, 150, 16);
        panel.add(email);

        username = new JLabel("Username: " + user.getUsername());
        username.setBounds(20, 195, 150, 16);
        panel.add(username);

        password = new JLabel("Password: " + user.getPassword());
        password.setBounds(20, 223, 150, 16);
        panel.add(password);

        user_type = new JLabel("User Type: " + user.getUserType());
        user_type.setBounds(20, 261, 150, 16);
        panel.add(user_type);

        photo = new JLabel();
        photo.setBounds(20, 340, 150, 150);
        ImageIcon imageIcon = new ImageIcon(user.getProfilePhoto().getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        photo.setIcon(imageIcon);
        photo.setOpaque(true);
        panel.add(photo);

        nameField = new JTextField();
        nameField.setBounds(210, 67, 130, 16);
        panel.add(nameField);
        nameField.setColumns(10);

        surnameField = new JTextField();
        surnameField.setBounds(210, 99, 130, 16);
        panel.add(surnameField);
        surnameField.setColumns(10);

        List<Integer> age_limit = new ArrayList<Integer>();
        for (int i = 10; i <= 120; ++i) {
            age_limit.add(i);
        }

        ageComboBox = new JComboBox<Integer>(age_limit.toArray(new Integer[0]));
        ageComboBox.setBackground(new Color(255, 201, 123));
        ageComboBox.setBounds(210, 116, 80, 40);
        panel.add(ageComboBox);

        //It should be noted that the administrator type is not available to be chosen in the settings frame
        
        String[] user_type = new String[3];
        user_type[0] = "free";
        user_type[1] = "hobbyist";
        user_type[2] = "professional";

        userComboBox = new JComboBox<String>(new DefaultComboBoxModel<>(user_type));
        userComboBox.setBackground(new Color(255, 201, 123));
        userComboBox.setBounds(210, 250, 130, 40);
        panel.add(userComboBox);

        emailField = new JTextField();
        emailField.setBounds(210, 167, 150, 16);
        panel.add(emailField);
        emailField.setColumns(10);

        usernameField = new JLabel("Username can not be updated.");
        usernameField.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        usernameField.setBounds(210, 196, 150, 16);
        panel.add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(210, 223, 150, 16);
        panel.add(passwordField);


        Update = new JButton("Update");
        Update.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        Update.setBounds(220, 507, 130, 25);
        panel.add(Update);
        Update.addActionListener(this);

        photo2 = new JLabel();
        photo2.setBounds(210, 340, 150, 150);
        ImageIcon image1 = new ImageIcon(new ImageIcon("Icons/default.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        photo2.setIcon(image1);
        photo2.setOpaque(true);
        panel.add(photo2);

        choosePhoto = new JButton("Choose a Photo!");
        choosePhoto.setBounds(210, 298, 150, 30);
        panel.add(choosePhoto);
        choosePhoto.addActionListener(this);
    }

	/**
	 * @e Performed action
	 * If the user presses the Choose a Photo JButton an instance of the class JFileChooser is created
	 * for the user to be able to access the photos on their computer. If the Update was pressed
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
                photo2.setIcon(image);
                user.setProfilePhoto(image);
                BaseLogger.logInfo(user.getUsername() + " chose a new profile photo.");               
            }
        } else if (e.getSource() == Update) {
            String name = nameField.getText();
            String surname = surnameField.getText();
            int age = (int) ageComboBox.getSelectedItem();
            String email = emailField.getText();
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

            user.setAge(age);
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setUserType(userType);
            user.setPassword(password);
            

            profilePanel.updateUserInformation(); 
            main.add(profilePanel);

            nameField.setText("");
            surnameField.setText("");
            emailField.setText("");
            usernameField.setText("");
            passwordField.setText("");
            ageComboBox.setSelectedIndex(0);
            userComboBox.setSelectedIndex(0);
            
            User.serializeUserList();
            
            dispose(); 
        }
    }
}


	

