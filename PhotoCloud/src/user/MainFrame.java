package user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Administrator.AdminDiscover;
import Administrator.AdminOtherProfile;
import Discovery.Discover;
import logging.BaseLogger;

import java.awt.Color;
import java.awt.CardLayout;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame implements ActionListener {

	/************** Pledge of Honor ******************************************
	I hereby certify that I have completed this programming project on my own
	without any help from anyone else. The effort in the project thus belongs 
	completely to me. I did not search for a solution, or I did not consult any 
	program written by others or did not copy any program from other sources. 
	I read and followed the guidelines provided in the project description.
	
	
	READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
	
	SIGNATURE: <Damla Görgülü, 79489> 
	*************************************************************************/
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	transient BaseLogger logger = BaseLogger.getInstance();
	JButton registerbutton ;
	JButton loginbutton;
	Login loginPage;
	JPanel panel;
	Register registerPage;
	private JLabel lblNewLabel_1;
	Profile profilePage;
	User user;
	Settings settings;
	Discover discoverPage;
	AdminDiscover admindiscoverPage;
	AdminOtherProfile admin;
	
	 /**
     * The user is directed from the Login Page to the Register page
     */
	public MainFrame() {
		
		User.deserializeUserList();
		User.deserializeMediaList();
		getContentPane().setBackground(new Color(255, 226, 179));
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(182, 225, 255));
		panel.setBounds(10, 10, 380, 550);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		registerbutton = new JButton("Register Now!");
		registerbutton.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		registerbutton.setBounds(26, 378, 117, 29);
		registerbutton.addActionListener(this);
		panel.add(registerbutton);
		
		loginbutton = new JButton("Login");
		loginbutton.setBounds(223, 448, 117, 29);
		loginbutton.addActionListener(this); 
		panel.add(loginbutton);
		
		JLabel lblNewLabel = new JLabel("Already registered?");
		lblNewLabel.setBounds(122, 419, 128, 16);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(0, 20, 380, 340);
		
		ImageIcon logoIcon = new ImageIcon("Icons/designers-2.png");
		lblNewLabel_1.setIcon(logoIcon);
		lblNewLabel_1.setOpaque(true);
		panel.add(lblNewLabel_1);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,400,600);
		

	}

	 /**
     * @param e The action event
     * If the user clicks on the Login JButton they are directed to the Login Page whereas 
     * if the user clicks on the Register JButton they are directed to the Register Page
     */

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==loginbutton) {
			panel.setVisible(false);
			loginPage = new Login(this);
			getContentPane().add(loginPage);
		}
		
		else if (e.getSource()==registerbutton) {
			panel.setVisible(false);
			registerPage= new Register(this);
			getContentPane().add(registerPage);
		}
	}
	
	/*---------Below are the methods that were used to transition from one page to another-------------
	
	
	 /**
     * The user is directed from the Login Page to the Register page
     */

	public void changeLoginToRegister() {
		loginPage.setVisible(false);
		registerPage= new Register(this);
		getContentPane().add(registerPage);
		registerPage.setVisible(true);
		
	}
	
	 /**
     * The user is directed from the Register Page to the Login page
     */

	public void changeRegisterToLogin() {
		registerPage.setVisible(false);
		loginPage= new Login(this);
		getContentPane().add(loginPage);
		loginPage.setVisible(true);
		
	}
	
	 /**
     * The user is directed from the Profile Page to the Discover Page
     */

	public void changeProfileToDiscover() {
		profilePage.setVisible(false);
		this.setVisible(false);
		discoverPage= new Discover(this, profilePage.getUser());
		discoverPage.setVisible(true);
		
	}
	
	 /**
     * The user of type administrator is directed from the Profile Page to the Discover page
     */

	public void changeToAdminDiscover() {
		profilePage.setVisible(false);
		this.setVisible(false);
		admindiscoverPage= new AdminDiscover(this, profilePage.getUser());
		admindiscoverPage.setVisible(true);
		
	}
	
	 /**
     * The user is directed from the Discover Page to the Profile page
     */
	
	public void changeDiscoverToProfile() {
		discoverPage.setVisible(false);
		this.setVisible(true);
		profilePage.setVisible(true);
	}
	
	 /**
     * The user is directed from the Login Page to the Profile page
     */

	public void changeLoginToProfile() {
		
		loginPage.setVisible(false);
		profilePage = new Profile(this,user);
		getContentPane().add(profilePage);
		
	}

	 /**
     * The user is directed from the Register Page to the Profile page
     */

	public void changeRegisterToProfile() {
	    registerPage.setVisible(false);
	    profilePage = new Profile(this, registerPage.getUser());
	    getContentPane().add(profilePage);  
	    profilePage.setVisible(true);
	}
	
	 /**
     * The user is directed from the ProfilevPage to the Settings page
     */

	public void changeSetting() {
	    settings = new Settings(this, user, profilePage);
	    settings.setVisible(true);
	}
	
	 /**
     * The user of administrator type is directed from the Discover Page to the Profile page
     */

	public void chageAdminDiscoverToProfile() {
		admindiscoverPage.setVisible(false);
		this.setVisible(true);
		profilePage.setVisible(true);
	}
	
	//----------------- Getters & Setters -------------------------

	public Discover getDiscoverPage() {
		return discoverPage;
	}



	public void setDiscoverPage(Discover discoverPage) {
		this.discoverPage = discoverPage;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}


}


