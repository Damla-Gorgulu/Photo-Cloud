package user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import PhotoEdit.Edit;
import logging.BaseLogger;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * The `Profile` class represents the user profile panel in the application.
 * It displays the user's information, profile picture, and their uploaded photos.
 * Users can also access settings and navigate to the discover page from here.
 */

public class Profile extends JPanel implements ActionListener {
	
	User user;
	JButton settings;
	MainFrame main;
	JLabel usernameLabel;
	JLabel surnameLabel;
	JLabel profilepic;
	JButton discoverbutton;
	JScrollPane scrollPane;
	JLabel nameLabel;
	JLabel ageLabel;
	JLabel emailLabel;
	JLabel passwordLabel;
	JLabel usertypeLabel;
	JPanel photosPanel;
	Profile profile= this;
	JPanel panel = this;
	transient BaseLogger logger = BaseLogger.getInstance();


	  /**
     * Creates a new `Profile` instance with the specified `MainFrame` and `User`.
     *
     * @param main The `MainFrame` instance.
     * @param user The `User` associated with the profile.
     */
	public Profile(MainFrame main, User user) {
	    this.main = main;
	    this.user = user;

	    this.setBackground(new Color(182, 225, 255));
	    this.setBounds(10, 10, 380, 550);
	    this.setLayout(null);
	    setLayout(null);

	    photosPanel = new JPanel();
	    photosPanel.setPreferredSize(new Dimension(380, 150+ user.getMediaList().size()*110));
		photosPanel.setLayout(new FlowLayout(3, 0, 0));
	    
	    scrollPane = new JScrollPane(photosPanel);
	    scrollPane.setBounds(20, 150, 340, 340);
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    add(scrollPane, BorderLayout.CENTER);


	    profilepic = new JLabel();
	    profilepic.setBounds(25, 20, 110, 110);
	    ImageIcon originalIcon = user.getProfilePhoto();
	    int labelWidth = profilepic.getWidth();
	    int labelHeight = profilepic.getHeight();
	    Image image = originalIcon.getImage().getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
	    ImageIcon scaled = new ImageIcon(image);
	    profilepic.setIcon(scaled);
	    profilepic.setOpaque(true);
	    add(profilepic);

	    usernameLabel = new JLabel(user.getUsername());
	    usernameLabel.setBounds(150, 20, 150, 20);
	    add(usernameLabel);

	    nameLabel = new JLabel("Name: " + user.getName());
	    nameLabel.setBounds(150, 40, 150, 20);
	    add(nameLabel);

	    surnameLabel = new JLabel("Surname: " + user.getSurname());
	    surnameLabel.setBounds(150, 60, 150, 20);
	    add(surnameLabel);

	    ageLabel = new JLabel("Age: " + user.getAge());
	    ageLabel.setBounds(150, 80, 150, 20);
	    add(ageLabel);

	    emailLabel = new JLabel("Email: " + user.getEmail());
	    emailLabel.setBounds(150, 100, 150, 20);
	    add(emailLabel);

	    passwordLabel = new JLabel("Password: " + user.getPassword());
	    passwordLabel.setBounds(150, 120, 150, 20);
	    add(passwordLabel);
	    
	    usertypeLabel = new JLabel(user.getUserType());
	    usertypeLabel.setBounds(280, 40, 150, 20);
	    add(usertypeLabel);
	
	    
	    for(Photo imagePhoto : user.getMediaList()) {
	    	photosPanel.add(newButton(imagePhoto));
	    	BaseLogger.logInfo(user.getUsername() + "'s photos were successfully added to their profile page.");
	    }
	    	    
		discoverbutton = new JButton();
		discoverbutton.setBounds(60, 495, 120, 50);
		
		ImageIcon image1 = new ImageIcon(new ImageIcon("Icons/discover.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		discoverbutton.setIcon(image1);
		discoverbutton.setOpaque(true);
		discoverbutton.addActionListener(this);
		add(discoverbutton);
		
	    JButton photoButton = new JButton();
	    photoButton.setBounds(200, 495, 120, 50);
	    ImageIcon image2 = new ImageIcon(new ImageIcon("Icons/addphoto.jpg").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
	    photoButton.setIcon(image2);
	    add(photoButton);
	    photoButton.setOpaque(true);
	    photoButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame("Options");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JButton privateButton = new JButton("Private");
                JButton publicButton = new JButton("Public");

                privateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose(); 
                        
                        JFileChooser fileChooser = new JFileChooser();
                        int fileChooserResult = fileChooser.showOpenDialog(Profile.this);
                        
                        if (fileChooserResult == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            String imagePath = selectedFile.getAbsolutePath();
                        //    ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath())
                        //    					.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                            Photo media = new Photo(imagePath, false, user);
                            user.addPhoto(media);
                            updateContent(user);
                            BaseLogger.logInfo(user.getUsername() + " uploaded a private photo.");
                            
                        }
                    }
                });

                publicButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle public button action here
                        frame.dispose(); // Close the frame
                        JFileChooser fileChooser = new JFileChooser();
                        int fileChooserResult = fileChooser.showOpenDialog(Profile.this);
                        if (fileChooserResult == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            String imagePath = selectedFile.getAbsolutePath();
                        //    ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath())       //BURAYA DÖN
                        //    					.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                            Photo media = new Photo(imagePath, true, user);
                            user.addPhoto(media);
                            User.addDiscoveryArray(media);
                            updateContent(user);
                            BaseLogger.logInfo(user.getUsername() + " uploaded a private photo.");
                        }
                    }
                });

                JPanel panel = new JPanel();
                panel.add(privateButton);
                panel.add(publicButton);

                frame.getContentPane().add(panel);
                frame.pack();
                frame.setVisible(true);
            }
        });
		
		settings = new JButton("settings");
		settings.setBounds(260, 10, 120, 30);
		add(settings);
		settings.addActionListener(this);
		
		
	}
	
    /**
     * Updates the content of the profile page with the user's updated media list.
     *
     * @param user The user whose profile page is to be updated
     */
	
	public void updateContent(User user) {
		photosPanel.removeAll(); 
		
	    for(Photo imagePhoto : user.getMediaList()) {
	    	photosPanel.add(newButton(imagePhoto));
	    }
	    
	    photosPanel.revalidate();
	    photosPanel.repaint();
	    photosPanel.setPreferredSize(new Dimension(380, 150+ user.getMediaList().size()*110));
		
	    scrollPane.revalidate();
	    scrollPane.repaint();
	    this.add(scrollPane, BorderLayout.CENTER);
	    
	    this.revalidate();
	    this.repaint();
	    BaseLogger.logInfo(user.getUsername() + "'s content was successfully updated.");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { 
    	
	    if (e.getSource() == settings) {
	        main.changeSetting();
	    }
	    else if (e.getSource() == discoverbutton) {
	    	if(user.getUserType().equals("administrator")) {
	    		main.changeToAdminDiscover();
	    		BaseLogger.logInfo(user.getUsername() + " is now viewing the discover page.");
	    	}
	    	else {
	    		main.changeProfileToDiscover();
	    		BaseLogger.logInfo(user.getUsername() + " is now viewing the discover page.");
	    	}
	    }
	    
	}

    /**
     * Creates a new `JButton` for a photo with the given `Photo` instance.
     *
     * @param photo The `Photo` instance.
     * @return The created `JButton` for the photo.
     */
	private JButton newButton(Photo photo) {
		
		//The path of the photo to be assigned as the Icon of the JButton is extracted.
		//Button width and height are determined.
	    ImageIcon originalIcon = new ImageIcon(photo.getPath());
	    Image originalImage = originalIcon.getImage();
	    int buttonWidth = 95;
	    int buttonHeight = 95;

	    //The Icon is scaled
	    Image scaledImage = originalImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
	    ImageIcon scaledIcon = new ImageIcon(scaledImage);

	    JButton button = new JButton(scaledIcon);
	    button.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {

	        	//EveryButton with a photo asssigned to it displays 3 or 4 choices based on the publicity of the information.
	            JFrame frame = new JFrame("Options");
	            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            frame.setBounds(0,0,120,80);
	            JPanel panel = new JPanel(new GridLayout(2, 2)); 
	            JButton editButton = new JButton("Edit");
	            JButton removeButton = new JButton("Remove");
	            JButton makepublicButton = new JButton("Make your photo public!");
	            JButton captionButton = new JButton("Add a caption!");

	            // Upon clicking the edit button an instance of the editing frame is created.
	            editButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    frame.dispose();
	                    Edit editFrame = new Edit(photo, user, profile);
	                    editFrame.setVisible(true);
	                    BaseLogger.logInfo("Edit frame is openedd.");
	                }
	            });

	            //When the remove button is clicked the photo is removed from the MediaList of the user and their profile page.
	            removeButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    frame.dispose();
	                    for (Photo photo1 : user.getMediaList()) {
	                        if (photo1.equals(photo)) {
	                            user.getMediaList().remove(photo);
	                            break; 
	                        }
	                    }
	                    User.removeDiscoveryArray(photo);
	                    User.serializeUserList();
	                    updateContent(user);
	                    BaseLogger.logInfo(user.getUsername() + " successfully removed a photo from their profile and the discovery page.");
	                }
	            });
	            
	            //When the caption JButton is clicked an instance of the caption frame is created
	            captionButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	frame.dispose();
	                	CaptionFrame captions = new CaptionFrame(photo.getCaptions(), user, photo.getUser());
	                }
	            });

	            //Make Public JButton only appear if the photo was previously determined to be private
	            if(!photo.isPublic()) {
	            	makepublicButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							frame.dispose();
							photo.setPublic(true);
							User.addDiscoveryArray(photo);
							User.serializeMediaList();
							User.serializeUserList();
					
						}
					});
	            	panel.add(makepublicButton);
	            	
	            }
	            
	            panel.add(captionButton);
	            panel.add(editButton);
	            panel.add(removeButton);
	            frame.getContentPane().add(panel);
	            frame.pack();
	            frame.setVisible(true);
	        }
	    });

	    return button;
	}

	/**
     * Updates the information displayed in the profile page of a user
     */
    public void updateUserInformation() {
    	
        nameLabel.setText(user.getName());
        surnameLabel.setText(user.getSurname());
        passwordLabel.setText(user.getPassword());
        ageLabel.setText(String.valueOf(user.getAge()));
        emailLabel.setText(user.getEmail());
        usertypeLabel.setText(user.getUserType());
        usernameLabel.setText(user.getUsername());

        ImageIcon profilePhoto = user.getProfilePhoto();
        if (profilePhoto != null) {
        	
            Image image = profilePhoto.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
            profilepic.setIcon(new ImageIcon(image));
            
        } else {
            profilepic.setIcon(new ImageIcon("default_profile.png"));
            
        }
        BaseLogger.logInfo(user.getUsername() + " information updated succesfully.");

    }

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

		
}

