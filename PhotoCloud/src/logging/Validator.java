package logging;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

	/**
	 * The Validator class has methods for validating different types of data, such as passwords, email addresses,
	 * names, and usernames.
	 */

public class Validator {

	
	/**
     * Checks if a password is valid, a valid password should be at least 6 characters long.
     *
     * @param password The password to validate
     * @return true if the password is valid, false otherwise
     */
	public static boolean ValidPassword(String password) {
	    return password.length() >= 6;
	}
	
	 /**
     * Checks if an email address is valid, a valid mail should be in the format ---@--.-
     *
     * @param mail The email address to validate
     * @return true if the email address is valid, false otherwise
     */
	public static boolean isValidMail(String mail) {
        String pattern = "^[^@]+@[^@]+\\.[^@]+$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(mail);
        return matcher.matches();
        
    }
	
	   /**
     * Checks if a name is valid, a valid name should only contain the letters of the alphabet.
     *
     * @param name The name to validate
     * @return true if the name is valid, false otherwise
     */
	
	public static boolean isValidName(String name) {
	    String pattern = "^[A-Za-z]+$"; 
	    Matcher matcher = Pattern.compile(pattern).matcher(name);
	    return matcher.matches();
	}
	
	  /**
     * Checks if a username is valid, a valid username can have only numbers and the letters of the alphabet.
     *
     * @param username The username to validate
     * @return true if the username is valid, false otherwise
     */
	public static boolean isValidUsername(String username) {
	    String pattern = "^[A-Za-z0-9].*";
	    Matcher matcher = Pattern.compile(pattern).matcher(username);
	    return matcher.matches();
	}

}
