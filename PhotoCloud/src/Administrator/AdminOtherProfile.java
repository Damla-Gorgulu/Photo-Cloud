package Administrator;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import logging.BaseLogger;
import user.MainFrame;
import user.Photo;
import user.User;

/**
 * The AdminOtherProfile class is an instance of the JFrame class of Java, on which profile of a person other than the current user is displayed. 
 * All photos regardless of it being private or public is included since the administrator can view all photos.It shows the user's information, profile picture,
 * and their media list.
 * The ActionListener interface is implemented and the button click events are handled in different ways.
 */


public class AdminOtherProfile extends JFrame implements ActionListener{

		User user;
		User clickedUser;
		JButton back;
		MainFrame main;
		JLabel usernameLabel;
		JLabel surnameLabel;
		JLabel profilepic;
		JButton discoverbutton;
		JScrollPane scrollPane;
		JLabel nameLabel;
		JPanel photosPanel;
		JFrame discoverPage;
		transient BaseLogger logger = BaseLogger.getInstance();
		JFrame panel = this;
		

	    /**
	     * Constructs an AdminOtherProfile object.
	     *
	     * @param main          The main frame of the application
	     * @param clickedUser   The user whose profile is being viewed
	     * @param user          The currently logged-in user (administrator)
	     * @param discoverFrame The discover page frame
	     */
		
		public AdminOtherProfile(MainFrame main, User clickedUser, User user, JFrame discoverFrame) {
			
			//The AdminOtherProfile JFrame
			getContentPane().setBackground(new Color(182, 225, 255));
		    this.main = main;
		    this.user = user;
		    this.clickedUser=clickedUser;
		    this.discoverPage=discoverFrame;
		    this.setBackground(new Color(182, 225, 255));
		    this.setBounds(10, 10, 380, 550);
		    getContentPane().setLayout(null);
		    getContentPane().setLayout(null);

		    //Panel to display the photos
		    photosPanel = new JPanel();
		    photosPanel.setBackground(new Color(255, 226, 179));
		    photosPanel.setPreferredSize(new Dimension(380, 150+ user.getMediaList().size()*110));
			photosPanel.setLayout(new FlowLayout(3, 0, 0));
		    
			//ScrollPane assigned to the panel
		    scrollPane = new JScrollPane(photosPanel);
		    scrollPane.setBounds(20, 150, 340, 340);
		    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    getContentPane().add(scrollPane, BorderLayout.CENTER);

		    //JLable dedicated to display the profile picture of the user whose profile page is being viewed
		    profilepic = new JLabel();
		    profilepic.setBounds(25, 20, 110, 110);
		    ImageIcon originalIcon = clickedUser.getProfilePhoto();
		    int labelWidth = profilepic.getWidth();
		    int labelHeight = profilepic.getHeight();
		    Image image = originalIcon.getImage().getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
		    ImageIcon scaled = new ImageIcon(image);
		    profilepic.setIcon(scaled);
		    profilepic.setOpaque(true);
		    getContentPane().add(profilepic);

		    //JLabel to display the username
		    usernameLabel = new JLabel(clickedUser.getUsername());
		    usernameLabel.setBounds(150, 20, 150, 20);
		    getContentPane().add(usernameLabel);

		    //JLabel to display the name
		    nameLabel = new JLabel("Name: " + clickedUser.getName());
		    nameLabel.setBounds(150, 40, 150, 20);
		    getContentPane().add(nameLabel);

		    //JLabel to display the surname
		    surnameLabel = new JLabel("Surname: " + clickedUser.getSurname());
		    surnameLabel.setBounds(150, 60, 150, 20);
		    getContentPane().add(surnameLabel);
		    
		    //All photos of the user is added regardless of it being private or public
		    for(Photo imagePhoto : clickedUser.getMediaList()) {
		    
		    		photosPanel.add(newButton(imagePhoto));
		    		BaseLogger.logInfo( clickedUser.getUsername() + "'s media list is added to their profile.");
		    	
		    }		   
			
		  //Back button to direct the user back to their discovery page
			back = new JButton("Back");
			back.setBounds(240, 110, 120, 30);
			getContentPane().add(back);
			back.addActionListener(this);
			
			
		}
		
		/**
		 * @e Performed action
		 * When the back button is clicked the user is directed back to their discover page
		 * 
		 */
		
		@Override
		public void actionPerformed(ActionEvent e) { 
		    if (e.getSource() == back) {
		    	main.changeToAdminDiscover();
		    	BaseLogger.logInfo( user.getUsername() + "returned to their discovery page.");
		    }

		}

		 /**
	     * A new JButton object is created with the ImageIcon of the photo that is taken as a parameter on it.
	     * 
	     * @param photo The Photo object
	     * @return The The JButton created
	     */
		
		private JButton newButton(Photo photo) {
		    ImageIcon originalIcon = new ImageIcon(photo.getPath());
		    Image originalImage = originalIcon.getImage();
		    int buttonWidth = 95;
		    int buttonHeight = 95;

		    Image scaledImage = originalImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
		    ImageIcon scaledIcon = new ImageIcon(scaledImage);
		    JButton button = new JButton(scaledIcon);
		    button.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					   	JFrame bigPhotoFrame = new JFrame();
			            bigPhotoFrame.setLayout(null); 
			           
			            JLabel photoLabel = new JLabel();
			            photoLabel.setBounds(10, 10, 300, 300);
			           
			            ImageIcon imageIcon = new ImageIcon(photo.getPath());
			            Image image = imageIcon.getImage();
			            Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
			            ImageIcon scaledIcon = new ImageIcon(scaledImage);
			            photoLabel.setIcon(scaledIcon);

			            bigPhotoFrame.getContentPane().add(photoLabel);
			            bigPhotoFrame.setSize(320, 330); 
			            bigPhotoFrame.getContentPane().setBackground(new Color(255, 226, 179));
			            bigPhotoFrame.setLocationRelativeTo(null);
			            bigPhotoFrame.setVisible(true);
			            BaseLogger.logInfo(user.getUsername() + "viewed " + photo.getUser().getUsername() + "'s photo on a seperate frame.");
				
				}
			});

		    return button;
		}


			
	}



