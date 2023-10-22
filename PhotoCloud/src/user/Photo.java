package user;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import PhotoEdit.ImageMatrix;
import logging.BaseLogger;

	/**
	 * Photo class constructs a photo object and implements the serializable interface for the 
	 * photos of each user to be stored after the runtime. It can also convert the images to image matrixes.
	 */
public class Photo implements Serializable{

	transient BaseLogger logger = BaseLogger.getInstance();
	private String path;
    private boolean isPublic;
    private int likes;
    private int dislikes;
    private List<String> comments;
    private List<String> captions;
    User user;
    
    
    /**
     * Constructs a Photo object.
     * @param path The path of the photo file.
     * @param isPublic A boolean indicating whether the photo is public or private.
     * @param user The owner of the photo.
     */
    public Photo(String path, boolean isPublic, User user) {
        this.user = user;
        this.isPublic = isPublic;
        this.likes = 0;
        this.dislikes = 0;
        this.comments = new ArrayList<>();
        this.captions = new ArrayList<>();

        try {  
           //A copy of the photo is made in the same path and with the name Edited_copy_of original file name.
            Path originalFilePath = Path.of(path);        
            String originalFileName = new File(path).getName();            
            Path copiedFilePath = Path.of("Edited" + "copy_of_" + originalFileName );       
            Files.copy(originalFilePath, copiedFilePath, StandardCopyOption.REPLACE_EXISTING);          
            this.path = copiedFilePath.toString();
            BaseLogger.logInfo("Image was successfully copied to " + copiedFilePath);

            
        } catch (IOException e) {
            System.out.println("Error copying the file: " + e.getMessage());
            logger.logError("Error occured when the file was being copied.");
        }
    }

    /**
     * The photo is converted to an Image Matrix
     * @return The ImageMatrix representation of the photo.
     */
    public ImageMatrix convertToImageMatrix() {
        ImageIcon imageIcon = new ImageIcon(path);
        BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        imageIcon.paintIcon(null, graphics, 0, 0);
        graphics.dispose();
        BaseLogger.logInfo("Image successfully converted to image matrix.");
        
        return new ImageMatrix(bufferedImage);
    }
    
  //----------------- Getters & Setters -------------------------
    
	public boolean isPublic() {
		return isPublic;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public List<String> getCaptions() {
		return captions;
	}

	public void setCaptions(List<String> captions) {
		this.captions = captions;
	}



	

}




