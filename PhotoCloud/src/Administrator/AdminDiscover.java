package Administrator;

import javax.swing.*;
import Discovery.CommentFrame;
import user.MainFrame;
import user.Photo;
import user.User;
import logging.BaseLogger;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A JFrame that displays the discover page for the administrator
 * Due to the extensive authorities of the users of type administrator, all photos of the users are displayed.
 * ActionListener is implemented through which button click events are handled. These buttons allow the administrator 
 * like, dislike, comment on, remove photos and visit other users' profile pages.
 */
public class AdminDiscover extends JFrame implements ActionListener {
	
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    

    User user;
    MainFrame main;
    JButton topButton;
    ArrayList<Photo> allPhotos = new ArrayList<>();
    User otherUser;
    JFrame discoverFrame = this;
    JPanel panel;
    JButton leftButton;
    JButton rightButton;
    JButton removeButton;
    transient BaseLogger logger = BaseLogger.getInstance();

    /**
     * An AdminDiscover object is constructed.
     * 
     * @param main The main frame of the application
     * @param user The user who is currently logged in
     */
   
    public AdminDiscover(MainFrame main, User user) {
        this.user = user;
        this.main = main;
        
        //Discover JFrame
        setTitle("Discover");
        setSize(400, 600);
        getContentPane().setBackground(new Color(255, 226, 179));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //The main Panel to which other panel are added
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(380, 5000));
        mainPanel.setBackground(new Color(182, 225, 255));

        //The button to go back to the user's profile
        topButton = new JButton(user.getUsername());
        topButton.setBounds(10, 10, 160, 30);
        topButton.addActionListener(this);
        mainPanel.add(topButton);

        //The ComboBox that serves as the search bar that displays all the usernames
        //When a user is selected an instance of the OtherProfile is created  
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Search");
     
        for (User users : User.getUserList()) {
            String username = users.getUsername();
            comboBox.addItem(username);
            BaseLogger.logInfo(username + "'s photos are added to the discover page for" + user.getUsername() + "to see.");
        }

        comboBox.setSelectedItem("Search"); 
        comboBox.setBounds(180, 10, 160, 30);
        mainPanel.add(comboBox);   
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// TODO Auto-generated method stub
                JComboBox<String> source = (JComboBox<String>) e.getSource();
                String selectedUsername = (String) source.getSelectedItem();

                if (selectedUsername.equals("Search")) {
                    otherUser = null;
                }
                else {
                    for (User user : User.getUserList()) {
                        if (user.getUsername().equals(selectedUsername)) {
                            otherUser = user;
                            break;
                        }
                    }
                    
                    BaseLogger.logInfo(user.getUsername() + " visited " + user.getUsername() + "'s profile.");
                    AdminOtherProfile otherProfile = new AdminOtherProfile(main, otherUser, user, discoverFrame);
                    discoverFrame.setVisible(false);
                    otherProfile.setVisible(true);               
                    }
            }
        });

        //Small panel that displays the information regarding the photo object such as like, dislike, the user who shared it, the photo itself and comment
        //Number of panels is determined by the number of public photos in the all photos array
        int panelHeight = 150;
        int gap = 10;
        int startY = 60;
        int panelIndex = 0;
        for (Photo photo : User.discoveryArray) {
            
                panel = new JPanel();
                panel.setLayout(null);
                panel.setBounds(10, startY + (panelHeight + gap) * panelIndex, 360, panelHeight);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.setBackground(new Color(255, 226, 179));

                //When this button is clicked the photo displayed on the button can be viewed on a larger frame
                leftButton = new JButton();
                leftButton.setBounds(10, 0, 150, 150);
                ImageIcon imageIcon = new ImageIcon(photo.getPath());
                Image scaledImage = imageIcon.getImage().getScaledInstance(leftButton.getWidth(), leftButton.getHeight(), Image.SCALE_SMOOTH);
                leftButton.setIcon(new ImageIcon(scaledImage));
                panel.add(leftButton);
                leftButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JFrame bigPhotoFrame = new JFrame();
				        bigPhotoFrame.setLayout(null); 

				        JLabel photoLabel = new JLabel();
				        photoLabel.setBounds(10, 10, 300, 300);
				        
				        JLabel captionJLabel = new JLabel();
				        captionJLabel.setBounds(10, 320, 100, 100);
				        StringBuilder captionBuilder = new StringBuilder("<html>");

				        for (String caption : photo.getCaptions()) {
				            captionBuilder.append(caption).append(" ");
				        }

				        captionBuilder.append("</html>");
				        captionJLabel.setText(captionBuilder.toString());


				        ImageIcon imageIcon = new ImageIcon(photo.getPath());
				        Image image = imageIcon.getImage();
				        Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
				        ImageIcon scaledIcon = new ImageIcon(scaledImage);
				        photoLabel.setIcon(scaledIcon);
				        
				        bigPhotoFrame.getContentPane().add(captionJLabel);
				        bigPhotoFrame.getContentPane().add(photoLabel);
				        bigPhotoFrame.setBounds(0,0,320,440);
				        bigPhotoFrame.getContentPane().setBackground(new Color(255, 226, 179));
				        bigPhotoFrame.setLocationRelativeTo(null);
				        bigPhotoFrame.setVisible(true);
				        BaseLogger.logInfo(user.getUsername() + "viewed " + photo.getUser().getUsername() + "'s photo on a seperate frame.");
				      		
					}
				});
 
                //Only user's of the type administrator are capable of removing photos from the discovery page and the profiles of other users
                //When this button is clciked the photo is removed from both the array that has al the photo objects and the media array of the user
                removeButton = new JButton("remove");
                removeButton.setBounds(280, 110, 80, 30);
                panel.add(removeButton);
                removeButton.addActionListener(new ActionListener() {				
					@Override
					public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
						User.removeDiscoveryArray(photo);
						photo.getUser().removePhoto(photo);
						User.serializeUserList();
						repaint();
						revalidate();
						BaseLogger.logInfo(user.getUsername() + " removed " + photo.getUser().getUsername() + "'s photo from the discover page and their profile.");
					}
				});
                
                //When this button is clicked the profile page with only the public information of the user who shared the photo can be viewed
                rightButton = new JButton(photo.getUser().getUsername());
                rightButton.setBounds(170, 10, 110, 30);
                panel.add(rightButton);
                rightButton.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						 AdminOtherProfile otherProfile = new AdminOtherProfile(main, photo.getUser(), user, discoverFrame);
				         discoverFrame.setVisible(false);
				         otherProfile.setVisible(true); 
				         BaseLogger.logInfo( user.getUsername() + "visited " + photo.getUser().getUsername() + "'s profile.");
					}
				});
                
                JLabel tierLabel = new JLabel(photo.getUser().getUserType());
                tierLabel.setBounds(300, 10, 70, 30);
                tierLabel.setBackground(new Color(182, 225, 255));
                tierLabel.setFont(new Font("Arial", Font.PLAIN, 10)); // Set font size to 10
                panel.add(tierLabel);

                //When this button is clicked the number of likes is incremented 
                JButton smallButton1 = new JButton();
                smallButton1.setBounds(170, 50, 75, 45); // Increase width to accommodate the text
                ImageIcon image1 = new ImageIcon(new ImageIcon("Icons/heart.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                smallButton1.setIcon(image1);
                smallButton1.setHorizontalTextPosition(SwingConstants.LEFT); 
                smallButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        photo.setLikes(photo.getLikes() + 1);
                        smallButton1.setText(String.valueOf(photo.getLikes()));
                        User.serializeUserList();
                        User.serializeMediaList();
                        BaseLogger.logInfo(user.getUsername() + " liked " + photo.getUser().getUsername() + "'s photo.");
 
                    }
                });
                panel.add(smallButton1);

                //When this button is clicked the number of dislikes is incremented
                JButton smallButton3 = new JButton();
                smallButton3.setBounds(270, 50, 75, 45); 
                ImageIcon image3 = new ImageIcon(new ImageIcon("Icons/dislike.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                smallButton3.setIcon(image3);
                smallButton3.setHorizontalTextPosition(SwingConstants.LEFT); 
                smallButton3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        photo.setDislikes(photo.getDislikes() + 1);
                        smallButton3.setText(String.valueOf(photo.getDislikes()));
                        User.serializeUserList();
                        User.serializeMediaList();
                        BaseLogger.logInfo(user.getUsername() + " disliked " + photo.getUser().getUsername() + "'s photo.");
                       
                    }
                });
                panel.add(smallButton3);


                //When this button is clicked a Comment Frame is opened
                JButton smallButton2 = new JButton("Comments");
                smallButton2.setBounds(170, 110, 100, 30);
                panel.add(smallButton2);
                smallButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CommentFrame commentFrame = new CommentFrame(photo, user);
                    }
                });
                
                mainPanel.add(panel);
                panelIndex++;
            
        }

        int numOfPanels = panelIndex;
        mainPanel.setPreferredSize(new Dimension(380, startY + (panelHeight + gap) * numOfPanels));


        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 10, 380, 580);
        add(scrollPane);

        setVisible(true);
    }

    /**
     * @param e The action event
     * User can return to their profile page from their discovery page
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	// TODO Auto-generated method stub
        if (e.getSource() == topButton) {
        	main.chageAdminDiscoverToProfile();
        	BaseLogger.logInfo( user.getUsername() + " visited their own profile page.");
       
        }
    }


}

