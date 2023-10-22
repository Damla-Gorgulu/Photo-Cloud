package user;

import javax.swing.ImageIcon;

import logging.BaseLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.List;

	public class User implements Serializable {
	    private String name;
	    private String surname;
	    private int age;
	    private String email;
	    private String username;
	    private String password;
	    private String userType;
	    public  ImageIcon profilePhoto;
	    transient static BaseLogger logger = BaseLogger.getInstance();


	    static List<User> userList = new ArrayList<>();
	    private List<Photo> mediaList = new ArrayList<>();
	    public static List<Photo> discoveryArray = new ArrayList<>();
	    
	    

	    /**
	     * Creates a new `User` instance with the specified profile information and profile photo.
	     * Adds the user to the user list and serializes the user list and media list.
	     *
	     * @param name         The name of the user.
	     * @param surname      The surname of the user.
	     * @param age          The age of the user.
	     * @param email        The email of the user.
	     * @param username     The username of the user.
	     * @param password     The password of the user.
	     * @param userType     The type of the user (e.g., "free", "hobbyist", "professional").
	     * @param photo        The profile photo of the user.
	     */
	    
	    public User(String name, String surname, int age, String email, String username, String password, String userType, ImageIcon photo) {
	        this.name = name;
	        this.surname = surname;
	        this.age = age;
	        this.email = email;
	        this.username = username;
	        this.password = password;
	        this.userType = userType;
	        this.profilePhoto= photo;
	        userList.add(this);
	        serializeUserList();
	        serializeMediaList();
	        
	    }  

	    //.ser files are created if they do not exist. By doing this the information of the user objects are stored after runtime.
		
		
	    /**
	     * Serializes the media list by writing it to the "allphotos.ser" file.
	     */
	    public static void serializeMediaList() {
		        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("allphotos.ser"))) {
		            oos.writeObject(discoveryArray);
		            System.out.println("Media list serialized successfully!");
		            BaseLogger.logInfo("Media list serialized successfully!");
		        } catch (IOException e) {
		            e.printStackTrace();
		            logger.logError("Error: " + e.getMessage());
		        }
		        
		        
		    }
	    
	    /**
	     * Serializes the user list by writing it to the "user.ser" file.
	     */
	    public static void serializeUserList() {
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.ser"))) {
	            oos.writeObject(userList);
	            System.out.println("User list serialized successfully!");
	            BaseLogger.logInfo("User list serialized successfully!");
	        } catch (IOException e) {
	            e.printStackTrace();
	            logger.logError("Error: " + e.getMessage());
	        }
	        
	        
	    }
	    
	    /**
	     * Deserializes the media list by reading it from the "allphotos.ser" file.
	     */
	    public static void deserializeMediaList() {
	        List<Photo> discoveryArray = new ArrayList<>();
	        String filename = "allphotos.ser";

	        File file = new File(filename);
	        if (!file.exists()) {
	            System.out.println("Media list file does not exist. Creating a new empty list.");
	            BaseLogger.logInfo("Media list file does not exist. Creating a new empty list.");
	            User.setDiscoveryArray(discoveryArray);
	            return;
	        }

	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
	            discoveryArray = (List<Photo>) ois.readObject();
	            System.out.println("Media list deserialized successfully!");
	            BaseLogger.logInfo("Media list deserialized successfully!");
	            for(User user: userList) {
	            	for(Photo image: user.getMediaList()) {
	            			discoveryArray.add(image);
	            	}
	            }
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	            logger.logError("Error: " + e.getMessage());
	        }

	        User.setUserList(userList);
	        
	        
	    }

	    /**
	     * Deserializes the user list by reading it from the "user.ser" file.
	     */
		public static void deserializeUserList() {
	        List<User> userList = new ArrayList<>();
	        String filename = "user.ser";

	        File file = new File(filename);
	        if (!file.exists()) {
	            System.out.println("User list file does not exist. Creating a new empty list.");
	            BaseLogger.logInfo("User list file does not exist. Creating a new empty list.");
	            User.setUserList(userList);
	            return;
	        }

	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
	            userList = (List<User>) ois.readObject();
	            System.out.println("User list deserialized successfully!");
	            BaseLogger.logInfo("User list deserialized successfully!");
	            for(User user: userList) {
	            	for(Photo image: user.getMediaList()) {
	
	            			discoveryArray.add(image);
	            		}
	            	}
	            }
	         catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	            logger.logError("Error: " + e.getMessage());
	        }

	        User.setUserList(userList);
	        
	        
	    }
		
// Methods for adding and removing from the array lists: 
// all these methods call serializing the lists to update information stored in the memory. 
		
	    public static void addDiscoveryArray(Photo media) {
	    	discoveryArray.add(media);
	    	serializeMediaList();
	    	
	    }
	    public static void removeDiscoveryArray(Photo media) {
	    	discoveryArray.remove(media);
	    	serializeMediaList();
	    	
	    }
	    

	    public void addPhoto(Photo image) {
	    	mediaList.add(image);
	    	serializeUserList();
	    }
	    
	    public void removePhoto(Photo image) {
	    	mediaList.remove(image);
	    	serializeUserList();
	    }
	    
//-----------------Getters and Setters -------------------------------------
	    
	    
	    public static List<Photo> getDiscoveryArray() {
			return discoveryArray;
		}


		public static void setDiscoveryArray(List<Photo> discoveryArray) {
			User.discoveryArray = discoveryArray;
		}
	    
		public void setProfilePhoto(ImageIcon profilePhoto) {
			System.out.println("new profile photo is set");
			this.profilePhoto = profilePhoto;
		}
		
		public ImageIcon getProfilePhoto() {
	        return profilePhoto;
	    }


		public List<Photo> getMediaList() {
			return mediaList;
		}

		public void setMediaList(List<Photo> mediaList) {
			this.mediaList = mediaList;
		}

		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getSurname() {
			return surname;
		}


		public void setSurname(String surname) {
			this.surname = surname;
		}


		public int getAge() {
			return age;
		}


		public void setAge(int age) {
			this.age = age;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getUserType() {
			return userType;
		}


		public void setUserType(String userType) {
			this.userType = userType;
		}


		public static List<User> getUserList() {
			return userList;
		}


		public static void setUserList(List<User> userList) {
			User.userList = userList;
		}
   
	    

	}
	

	
	    
	    


