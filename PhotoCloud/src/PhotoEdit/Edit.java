package PhotoEdit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import user.MainFrame;
import user.Photo;
import user.Profile;
import user.User;

/**
 * The Edit class is a JFrame that displays and applies the editing methods that a user can access based on their user tier. 
 */

public class Edit extends JFrame implements ActionListener {

	static int edited_photo = 0;
	
    private JPanel panel;
    private ImageIcon image;
    private User user;
    JButton blurButton;
    JButton sharpenButton;
    JButton brightnessButton;
    JButton contrastButton;
    JButton grayscaleButton;
    JButton edgeDetectionButton;
    JLabel photoLabel;
    JSlider blurSlider;
    JSlider sharpenSlider;
    JSlider grayscaleSlider;
    JSlider brightnessSlider;
    JSlider edgeDetectionSlider;
    JSlider contrastSlider;
    private JButton backButton;
    private JButton setButton;
    JButton clickedButton;
    JButton applyChangesButton;
    JButton newbutton;
    Photo edited;
    Photo photo;
    Profile profile;
    String pathString;
    
    /**
     * Creates an instance of the Edit class.
     * @param photo The photo to be edited
     * @param user The user performing the editing
     * @param profile The editors' profile
     */
    public Edit(Photo photo, User user, Profile profile) {
    	System.out.println("burdayım");
        this.image = new ImageIcon(photo.getPath());
        this.user = user;
        this.photo=photo;
        this.profile=profile;
        
        edited = new Photo(photo.getPath(), photo.isPublic(),photo.getUser());
        pathString = edited.getPath();

        this.setBounds(0, 0, 400, 600);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(255, 226, 179));
        this.setResizable(false);

        panel = new JPanel();
        panel.setBackground(new Color(182, 225, 255));
        panel.setBounds(10, 10, 380, 550);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        //The photo that was chosen to be edited is displayed on a large JLabel
        photoLabel = new JLabel();
        photoLabel.setBounds(40, 50, 300, 300);
        panel.add(photoLabel);
        Image scaledImage = image.getImage().getScaledInstance(photoLabel.getWidth(), photoLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        photoLabel.setIcon(scaledIcon);

        //The editing buttons are enabled based on the tier of the user 
        String userType = user.getUserType();
        if (userType.equals("free")) {
            blurButton = new JButton("Blur");
            blurButton.setBounds(10, 380, 120, 30);
            panel.add(blurButton);
            blurButton.addActionListener(this);

            sharpenButton = new JButton("Sharpen");
            sharpenButton.setBounds(130, 380, 120, 30);
            panel.add(sharpenButton);
            sharpenButton.addActionListener(this);
            
        } else if (userType.equals("hobbyist")) {
            blurButton = new JButton("Blur");
            blurButton.setBounds(10, 380, 120, 30);
            panel.add(blurButton);
            blurButton.addActionListener(this);

            sharpenButton = new JButton("Sharpen");
            sharpenButton.setBounds(140, 380, 120, 30);
            panel.add(sharpenButton);
            sharpenButton.addActionListener(this);

            brightnessButton = new JButton("Brightness");
            brightnessButton.setBounds(260, 380, 120, 30);
            panel.add(brightnessButton);
            brightnessButton.addActionListener(this);
            
            contrastButton = new JButton("Contrast");
            contrastButton.setBounds(10, 430, 120, 30);
            panel.add(contrastButton);
            contrastButton.addActionListener(this);

        } else if (userType.equals("professional")||userType.equals("administrator")) {
            blurButton = new JButton("Blur");
            blurButton.setBounds(10, 380, 120, 30);
            panel.add(blurButton);
            blurButton.addActionListener(this);

            sharpenButton = new JButton("Sharpen");
            sharpenButton.setBounds(140, 380, 120, 30);
            panel.add(sharpenButton);
            sharpenButton.addActionListener(this);

            brightnessButton = new JButton("Brightness");
            brightnessButton.setBounds(260, 380, 120, 30);
            panel.add(brightnessButton);
            brightnessButton.addActionListener(this);

            contrastButton = new JButton("Contrast");
            contrastButton.setBounds(10, 430, 120, 30);
            panel.add(contrastButton);
            contrastButton.addActionListener(this);

            grayscaleButton = new JButton("Grayscale");
            grayscaleButton.setBounds(140, 430, 120, 30);
            panel.add(grayscaleButton);
            grayscaleButton.addActionListener(this);

            edgeDetectionButton = new JButton("Edge Detection");
            edgeDetectionButton.setBounds(260, 430, 120, 30);
            panel.add(edgeDetectionButton);
            edgeDetectionButton.addActionListener(this);
        }
        
        // A button to change the existing image is added
        applyChangesButton = new JButton("Apply Changes");
        applyChangesButton.setBounds(40, 480, 150, 30);
        panel.add(applyChangesButton);
        applyChangesButton.addActionListener(this);
        
        // A button to save the edited image as new is added
        newbutton = new JButton("Save as New!");
        newbutton.setBounds(210, 480, 150, 30);
        panel.add(newbutton);
        newbutton.addActionListener(this);

    }

    	// Based on the button that is clicked, the editing selection buttons disappear and 
    	// the slider corresponding to that action back and set buttons are added.
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            clickedButton = (JButton) e.getSource();

            if (clickedButton.getText().equals("Blur")) {
                buttonsDisappear();
                System.out.println("bir de burdayım");
                // Create and add the slider
                blurSlider = new JSlider(0, 10);
                blurSlider.setBounds(10, 380, 300, 30);
                panel.add(blurSlider);
                panel.revalidate();
                panel.repaint();

               
                backButton();
                setButton();
            }
            
            else if (clickedButton.getText().equals("Sharpen")) {
               buttonsDisappear();

               sharpenSlider = new JSlider(0, 10);
               sharpenSlider.setBounds(10, 380, 300, 30);
               panel.add(sharpenSlider);
               panel.revalidate();
               panel.repaint();

               backButton();
               setButton();
            }
            else if (clickedButton.getText().equals("Grayscale")) {
                buttonsDisappear();

                grayscaleSlider = new JSlider(0, 10);
                grayscaleSlider.setBounds(10, 380, 300, 30);
                panel.add(grayscaleSlider);
                panel.revalidate();
                panel.repaint();

                backButton();
                setButton();
             }
            else if (clickedButton.getText().equals("Edge Detection")) {
                buttonsDisappear();

                edgeDetectionSlider = new JSlider(0, 10);
                edgeDetectionSlider.setBounds(10, 380, 300, 30);
                panel.add(edgeDetectionSlider);
                panel.revalidate();
                panel.repaint();

                backButton();
                setButton();
             }
            else if (clickedButton.getText().equals("Brightness")) {
                buttonsDisappear();

                brightnessSlider = new JSlider(0, 10);
                brightnessSlider.setBounds(10, 380, 300, 30);
                panel.add(brightnessSlider);
                panel.revalidate();
                panel.repaint();

                backButton();
                setButton();
             }
            else if (clickedButton.getText().equals("Contrast")) {
                buttonsDisappear();

                contrastSlider = new JSlider(0, 10);
                contrastSlider.setBounds(10, 380, 300, 30);
                panel.add(contrastSlider);
                panel.revalidate();
                panel.repaint();

                backButton();
                setButton();
             }
            
            else if (clickedButton == applyChangesButton) {
            	
            	user.getMediaList().remove(photo);
            	User.removeDiscoveryArray(photo);
            	user.getMediaList().add(edited);
            	profile.updateContent(user);
            	User.addDiscoveryArray(edited);


                dispose();
            }


            else if(clickedButton == newbutton) {
            	this.user.addPhoto(edited);
            	profile.updateContent(user);
            	User.addDiscoveryArray(edited);
            	
            	dispose();
            	}

        }
    }

    // This method removes the buttons that were previously added based on the user tier.
    private void buttonsDisappear() {
    	    if (blurButton != null) {
    	        panel.remove(blurButton);
    	    }
    	    if (sharpenButton != null) {
    	        panel.remove(sharpenButton);
    	    }
    	    if (brightnessButton != null) {
    	        panel.remove(brightnessButton);
    	    }
    	    if (contrastButton != null) {
    	        panel.remove(contrastButton);
    	    }
    	    if (grayscaleButton != null) {
    	        panel.remove(grayscaleButton);
    	    }
    	    if (edgeDetectionButton != null) {
    	        panel.remove(edgeDetectionButton);
    	    }
    	    if (applyChangesButton!= null) {
    	        panel.remove(applyChangesButton);
    	    }
    	    if (newbutton != null) {
    	        panel.remove(newbutton);
    	    }
    	    panel.revalidate();
    	    panel.repaint();
    	}

   
    // When the set button is clicked the chosen editing operation is applied to the degree that is set through the JSlider dedicated to that operation.
    // Inside every conditional block, the path of the edited image is copied and is scaled to the JLabel that is on the editing options panel.
    // Then the set, back buttons and the JSlider is removed. 
    private void setButton() {
        setButton = new JButton("Set");
        setButton.setBounds(150, 420, 100, 30);
        panel.add(setButton);
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	System.out.println(edited.getPath());
                       	
                if (clickedButton.getText().equals("Blur")) {
                	Blur.applyBlur(edited, blurSlider.getValue());
	            	ImageIcon ImageIcon = new ImageIcon(edited.getPath());
	                Image originalImage = ImageIcon.getImage();
	                Image scaledImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
	                ImageIcon scaledIcon = new ImageIcon(scaledImage);
	            	
                	photoLabel.setIcon(scaledIcon);
                	panel.remove(blurSlider);
                    panel.remove(backButton);
                    panel.remove(setButton);
                    panel.revalidate();
                    
                    panel.repaint();
                    userButtons();
                }
                
                else if (clickedButton.getText().equals("Sharpen")) {	
                	Sharpen.applySharpen(edited, sharpenSlider.getValue());
                	ImageIcon scaledIcon = new ImageIcon(edited.getPath());
                	Image scaledImage = scaledIcon.getImage().getScaledInstance(photoLabel.getWidth(), photoLabel.getHeight(), Image.SCALE_SMOOTH);
                	ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

                	photoLabel.setIcon(scaledImageIcon);
                	panel.remove(sharpenSlider);
                	panel.remove(backButton);
                	panel.remove(setButton);
                	panel.revalidate();
                	
                	panel.repaint();
                	userButtons();
                }
             
                
                else if (clickedButton.getText().equals("Grayscale")) {	
                	GrayScale.applyGrayscale(edited, grayscaleSlider.getValue());
                	ImageIcon ImageIcon = new ImageIcon(edited.getPath());
                    Image originalImage = ImageIcon.getImage();
                    Image scaledImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    photoLabel.setIcon(scaledIcon);
                    panel.remove(grayscaleSlider);
                    panel.remove(backButton);
                    panel.remove(setButton);
                    panel.revalidate();
                   
                    panel.repaint();
                    userButtons();
                }
                else if (clickedButton.getText().equals("Edge Detection")) {	
                    EdgeDetection.applyEdgeDetection(edited, edgeDetectionSlider.getValue());
                    ImageIcon ImageIcon = new ImageIcon(edited.getPath());
                    Image originalImage = ImageIcon.getImage();
                    Image scaledImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);

                    
                    photoLabel.setIcon(scaledIcon);
                    panel.remove(edgeDetectionSlider);
                    panel.remove(backButton);
                    panel.remove(setButton);
                    panel.revalidate();
                    
                    panel.repaint();
                    userButtons();
                }
                else if (clickedButton.getText().equals("Brightness")) {	
                    Brightness.applyBrightness(edited, brightnessSlider.getValue());
                    ImageIcon ImageIcon = new ImageIcon(edited.getPath());
                    Image originalImage = ImageIcon.getImage();
                    Image scaledImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    photoLabel.setIcon(scaledIcon);
                    panel.remove(brightnessSlider);
                    panel.remove(backButton);
                    panel.remove(setButton);
                    panel.revalidate();
                  
                    panel.repaint();
                    userButtons();
                }
                else if (clickedButton.getText().equals("Contrast")) {	
                    Contrast.applyContrast(edited, contrastSlider.getValue());
                    ImageIcon ImageIcon = new ImageIcon(edited.getPath());
                    Image originalImage = ImageIcon.getImage();
                    Image scaledImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    photoLabel.setIcon(scaledIcon);
                    panel.remove(contrastSlider);
                    panel.remove(backButton);
                    panel.remove(setButton);
                    panel.revalidate();
                  
                    panel.repaint();
                    userButtons();
                }

                Photo new_photo = new Photo(edited.getPath(), edited.isPublic(), edited.getUser());
                edited=new_photo;
       
            }
        });
    }

  // This method is to re-add the rest of the buttons on the frame that has the editing options if the user does not want to keep the changes.
  private void backButton() {
        backButton = new JButton("Back");
        backButton.setBounds(10, 420, 100, 30);
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        	    if (blurSlider != null) {
        	        panel.remove(blurSlider);
        	    }
        	    if (sharpenSlider != null) {
        	        panel.remove(sharpenSlider);
        	    }
        	    if (brightnessSlider != null) {
        	        panel.remove(brightnessSlider);
        	    }
        	    if (contrastSlider!= null) {
        	        panel.remove(contrastSlider);
        	    }
        	    if (grayscaleSlider != null) {
        	        panel.remove(grayscaleSlider);
        	    }
        	    if (edgeDetectionSlider!= null) {
        	        panel.remove(edgeDetectionSlider);
        	    }
    
                panel.remove(backButton);
                panel.remove(setButton);
                panel.revalidate();
                panel.repaint();
                userButtons();
                
                
            }
        });
    }

  // This method is to re-add the user buttons when the set or back button is clicked. The buttons are added based on the tiers of the users.     
    private void userButtons() {
        String userType = user.getUserType();
        if (userType.equals("free")) {
            panel.add(blurButton);
            panel.add(sharpenButton);
            panel.add(applyChangesButton);
            panel.add(newbutton);
        } else if (userType.equals("hobbyist")) {
            panel.add(blurButton);
            panel.add(sharpenButton);
            panel.add(brightnessButton);
            panel.add(contrastButton);
            panel.add(applyChangesButton);
            panel.add(newbutton);
        } else if (userType.equals("professional")) {
            panel.add(blurButton);
            panel.add(sharpenButton);
            panel.add(brightnessButton);
            panel.add(contrastButton);
            panel.add(grayscaleButton);
            panel.add(edgeDetectionButton);
            panel.add(applyChangesButton);
            panel.add(newbutton);
        }
        else if (userType.equals("administrator")) {
        	 panel.add(blurButton);
             panel.add(sharpenButton);
             panel.add(brightnessButton);
             panel.add(contrastButton);
             panel.add(grayscaleButton);
             panel.add(edgeDetectionButton);
             panel.add(applyChangesButton);
             panel.add(newbutton);
        }
    }
 



}
