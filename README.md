# Photo-Cloud

COMP132 : Advanced Programming Programming Project Report
<Photo Cloud> <Damla Görgülü, 0079489> <Spring/2023>


***** Part 1 *****

1. Object Descriptions

The user objects in this program are not declared under a main method. Instead, the Serializable interface is used to create a file named "user.ser," which allows the users to be stored in memory even outside of runtime. The program has 9 regular users and one administrator. Additionally, as specified in the project description document, each user can select and store photo objects from their computer. Below are two tables containing user information.

<img width="505" alt="Ekran Resmi 2023-10-22 18 33 34" src="https://github.com/Damla-Gorgulu/Photo-Cloud/assets/127191236/a318140b-c946-43be-8579-c0dabd2db376">

<img width="500" alt="Ekran Resmi 2023-10-22 18 09 07" src="https://github.com/Damla-Gorgulu/Photo-Cloud/assets/127191236/552e419f-c104-4207-874d-cbb322f9dbb3">

2. Application Usage Information -Sign up/Login Guide

a) Welcome Page

When the program is started up, users are greeted with a page that includes the program's logo, as well as "Login" and "Sign Up" buttons. Previously registered users can click the Login button to access the Login page, while users who are registering for the first time are expected to click the Sign Up button, which will redirect them to the Register page where they can enter their user information to create an account.

<img width="476" alt="Ekran Resmi 2023-10-22 18 09 18" src="https://github.com/Damla-Gorgulu/Photo-Cloud/assets/127191236/46fe44ae-c110-42a9-98aa-f953442e467b">

b) Sign Up Page

When the user clicks the "Sign Up" button on the welcome page, they are directed to the page shown in Figure 2. The user is expected to enter their personal information, such as their first name, last name, age, email address, username, password (using JPasswordField), and user type, into the JTextFields provided next to the corresponding JLabels. The age and the user type can be selected from the options displayed on the JComboBoxes. If desired, the user can choose a profile photo from their own computer by clicking the "Choose a Photo" button, or they can use the default profile photo assigned to them.

Upon clicking the "Sign Up" button, the program checks the validity of the entered information through regular expressions. For the first name and last name to be considered valid, only Latin alphabet letters should be used. The entered email address must not have been used by another user and should follow the format ---@---.--. The username should be unique and consist of only letters or numbers. Lastly, the password should be at least 6 characters long to be considered valid.

If the user enters incorrect information, the program displays a JOptionPane warning, explaining the required format for the information to be considered valid. The user's inputted information is cleared, and they are prompted to re-enter the information. If the user has already registered, they can click the "Login" button below the "Sign Up" button to proceed to the Login page.

<img width="493" alt="Ekran Resmi 2023-10-22 18 09 27" src="https://github.com/Damla-Gorgulu/Photo-Cloud/assets/127191236/2abdd267-2244-4332-bd3b-d4c268562628">

c) Login Page

The Login page that is accessible from both the Welcome page and the Register page through Login buttons is displayed in Figure 3. Users who have not logged in before can click the "Sign Up" button to go to the signup page and create an account. Users who have already registered and wish to log into their account, are expected to enter their username and password correctly.

Upon clicking the Login button, the program verifies if the entered username and password match. If an incompatible username and password combination is entered, a JOptionPane message saying "The username and the password do not match" is displayed to alert the user, and they are prompted to enter the correct information. If the correct information is entered, the user's profile page is opened.

<img width="446" alt="Ekran Resmi 2023-10-22 18 09 36" src="https://github.com/Damla-Gorgulu/Photo-Cloud/assets/127191236/5ed38e9c-1aae-4863-8bce-075c41f145ea">

- User’s Guide

d) Profile Page

When a successful login is achieved from the Register page or the Login page, the user can view their own profile page as shown in Figure 4. The profile page includes the user's chosen profile photo in the top left corner, accompanied by JLabels displaying their personal information. Clicking the settings button in the top right corner redirects the user to the settings page, where they can update all their information except for the username.

In the middle of the profile page, a JPanel and a JSlider are used to create an area where shared photos can be displayed. By clicking the button with a paper plane icon in the bottom left corner, the user is directed to the discover page. Clicking the button with a camera icon in the bottom right corner allows the user to access their computer's files. Upon clicking this button, a small frame will appear displaying options for selecting public or private access as shown in Figure 5. This choice determines whether the photo will be declared public for others to view and be added to the discover page. It's important to note that only JPEG format photos can be successfully displayed on the page. The selected photo is converted into an ImageIcon using its path, and the ImageIcon is added as an icon to a predefined button with specified dimensions. When the user clicks the button containing the photo, another small JFrame appears. This frame offers the user either four or three options based on the fact that whether the photo to be edited is made public or not. If the photo was initially declared

to be private the “Make Your Photo Public!” button appears. If the user clicks this button the photo becomes public and is displayed on the discover page for everyone to see. Another option is to add a caption to the photograph. Captions can be viewed from the discover page and the user can add to their caption from their profile. Clicking the remove button will remove the photo from both the user's page and, if it was previously shared publicly on the discover page, it will be removed from there as well. Clicking the edit button opens the editing page.

<img width="558" alt="Ekran Resmi 2023-10-22 18 09 53" src="https://github.com/Damla-Gorgulu/Photo-Cloud/assets/127191236/0357f430-6643-4d40-b350-bd0862c175c4">

- Photo Editing and Filters’ Guide

e) Editing Page
  
When the edit button is clicked on the Options JFrame, the editing page opens. The editing page displays an enlarged version of the photo to be edited in a JLabel. The buttons below the photo vary depending on the user's tier. In Figure 7, the editing page shows a user categorised as a professional user, so there are 6 buttons for performing 6 editing operations. Clicking any of these buttons will hide the other buttons that contain different editing options and display the JSlider corresponding to the selected operation, along with the "Back" and "Set" buttons.

The editing frame when the GrayScale button is clicked, is shown in Figure 8 as an example to demonstrate how the slider and set/back buttons are added. The user adjusts the editing level and determines the amount of change using the slider. If the user presses the "Back" button, no changes are applied, and they return to the editing options page. However, if the "Set" button is pressed, the selected editing operation is applied to the photo before returning to the page with other editing options. The newly edited photo is displayed in a JLabel. If the user wishes to continue with further editing, other buttons can be used in the same manner.

Once the editing operations on the photo are completed, the user needs to click either the "Apply Changes" or "Save as New!" buttons located in the bottom section of the editing page to save the changes. Clicking the "Apply Changes" button updates the photo that was previously added to the profile. Clicking the "Save as New!" button saves the edited photo as a new photo to be displayed on the profile page.

<img width="962" alt="Ekran Resmi 2023-10-22 18 15 39" src="https://github.com/Damla-Gorgulu/Photo-Cloud/assets/127191236/2648fa69-2d0c-47b2-90da-8ea75e959788">

f) Settings Page
  
Users can edit their information at any time by clicking the settings button on their profile page. They can update their first name, last name, age, email address, user type, password, and profile photo, but the username cannot be changed. The validity of the entered information is checked using regular expressions. If the user decides to cancel the update, they can simply close the JFrame, and the information will remain unchanged.

<img width="880" alt="Ekran Resmi 2023-10-22 18 15 50" src="https://github.com/Damla-Gorgulu/Photo-Cloud/assets/127191236/8ffad991-de61-4757-b891-7eb624ced00a">

g) Discovery Page

As mentioned in previous sections of the report, users can navigate to the discovery page by clicking the paper plane symbol on their profile pages. On the discovery page, users can view the photos that have been declared public by all users of the program, each displayed in separate panels. Each panel contains five JButtons and a JLabel indicating the user's type. The button with a heart symbol increases the photo's like count by 1 when clicked, while the button with a thumbs down symbol increases the dislike count by 1.

If users wish, they can click on the JButton displaying the shared photo to view it in a larger screen, along with the caption added by the user who shared it, if available. Additionally, by clicking the button next to the photo, which contains the username of the user who shared it, users can navigate to the profile page of the photo's owner. On the owner's profile page, the owner's public information and photos are displayed.
 
Users can leave comments on other users' photos. When the "Comment" button is clicked, a new Comments JFrame opens, as shown in Figure 11. After typing the desired comment in the JTextLabel located at the bottom of the frame, clicking the "Send" button adds the user's username and message as a new comment below any existing comments, if present, or at the beginning of the JTextField otherwise.

As has shown in Figure 12, the top section of the discovery page includes a JButton containing the username of the logged-in user and a JComboBox with the "search" item selected. These components serve the purpose of redirecting the user to their own profile page and, similar to the button containing the usernames on the panels dedicated for each post, displaying the profile pages of other users where their public information is shown respectively.

<img width="758" alt="Ekran Resmi 2023-10-22 18 17 39" src="https://github.com/Damla-Gorgulu/Photo-Cloud/assets/127191236/d362ff42-d697-46c4-865b-7e6f52a1252c">

<img width="340" alt="Ekran Resmi 2023-10-22 18 19 07" src="https://github.com/Damla-Gorgulu/Photo-Cloud/assets/127191236/bbc8700d-5b5a-49d1-850c-a2e08799ac4e">

h) Other User’s Profile Page

If the relevant JButton on the panels is clicked or if a user is selected on the JComboBox located at the top of the page, the profile pages of other users can be viewed.As has been displayed in Figure 13, these pages show the profile information of other users, which do not include private details such as email address, password, age, and user type. Only public photos can be displayed in the dedicated panel for photos. Clicking on a photo allows it to be viewed in a larger format on a separate page. To return to the discovery page, a JButton labelled as "Back" is also available.

<img width="371" alt="Ekran Resmi 2023-10-22 18 19 14" src="https://github.com/Damla-Gorgulu/Photo-Cloud/assets/127191236/fb35d23e-158f-453b-959f-f407430ffdef">

- Administrator’s Guide
  
In the PhotoCloud application, only administrators can log in through the Register page. The settings page, which allows users to update their information, does not include the administrator option in the JComboBox. The login and registration procedures for administrators are the same as for other users. Administrators can utilise the filters available to professional-tier users. They can share their own photos as private or public, add captions and comments. Additionally, administrators have the capability to view private photos of other users on the discovery page and, if necessary, remove the photo from both the user's page and the discover page.
i) Administrator’s Discovery Page
Both users' and administrators' discovery pages, consist of JPanels dedicated to each photo. The functionality of the like, dislike, comment buttons, and JButtons containing the user's name and the shared photo is the same as for regular users. However, there is an additional "remove" JButton located in the bottom right corner of each panel on the administrator's page. Clicking this button allows for the photo to be deleted from both the user's profile page and the discovery page. The Administrator’s discover page is shown in Figure 14.

<img width="745" alt="Ekran Resmi 2023-10-22 18 19 27" src="https://github.com/Damla-Gorgulu/Photo-Cloud/assets/127191236/202e50a2-7f99-4ef0-b6ca-3b2e765d5bb0">

j) Administrator’s Other User Page

Like other users, administrators can view other users' profiles, but they also have the ability to view private photos of other users. Similarly to other users' profile pages, administrators can use the Back JButton to return to their own discovery page. It should be noted that when administrators remove a photo from the discovery page, there should be a transition to another page in order to observe the change made. This transition can be achieved through the JButton located in the top left corner, which displays the username, or by viewing other users' pages.

<img width="329" alt="Ekran Resmi 2023-10-22 18 22 38" src="https://github.com/Damla-Gorgulu/Photo-Cloud/assets/127191236/8facd48a-d084-4a18-b258-946ee586c2b9">

***** Part 2 *****

-Project Design Description

a) Class Relations and Inheritances

The project contains 5 packages that contain 24 classes in total.

The first package is the User package that contains the classes MainFrame, Login, Register, Profile, User, Photo, Settings and Caption Frame.

● The MainFrame is where my main method is located. All the classes that extend JPanel are added on top of the MainFrame through methods whose names are in the form of “changeToAFromB()”. The Logo of the application is displayed on a JLabel when the Program is started until one of the two JButtons that direct the user to either the Login Page or the Sign Up Page are clicked. This class implements the ActionListener interface.

● Login is a class that extends the JPanel class where users can enter their username and password to log in. The JPanel is set with an absolute layout and contains components such as JLabels, JTextFields, JPasswordFields, and JButtons are created and positioned on the panel to create the login form. The ActionListener interface is implemented and the ActionPerformed() method is overridden to handle the page changing and validating events. The static class validator is used to check whether the username and the password entered by the user are valid. If a match is found, the mainframe's setUser() method is called to set the logged-in user, and the view is switched to the profile page whereas if no error was found an error message is displayed and the error is logged by calling the BaseLogger class. This video was used as a source to get a better understanding of the login concept [1].

● The register class extends Jpanel and allows the user to enter personal information to create an account. Components such as JLabels, JTextfields, JPasswordFields, JComboBoxes and JButtons are used in creating the registration form and the ActionListener interface is implemented and the actionPerformed() method is overridden in order to handle button click events. The Choose a Photo JButton enables the user to set a profile page from the files in their computer desktop [2].The methods of class validator that uses regular expressions are called in order to check the validity of the information given by the user. If invalid information was entered a JOption pane that mentions the issue about the information appears [3]. Upon successful registration, a new User object is created.

● The Profile class also extends JPanel and is added on top of the MainFrame when the methods dedicated to transitioning between pages that are located in the MainFrame are called. This Page displays the private information of the user such as their mail address and password. The JPanel with the JSlider is where the photos of the user are added and stored. This class also extends the Action Listener interface and overrides the actionPerformed() method such that when the settings JButton and the JButton with the paper plane Icon is clicked the user can transition to the settings page and discover page respectively. To override the actionListener method in another method I have used this tutorial [4].

● The Photo class implements the Serializable interface, for the photo objects to be converted into a byte stream that can be accessed after the deserialization process. The serializable interface enables the creation of a “Memory” so that when the runtime is exceeded and the program is shut down the photo objects of the users are still stored. Due to this during thedemonstration it might be necessary to turn off the application and re-run it to display another user’s profile page.

● The Settings class that can be accessed through a user’s profile page upon clicking the JButton labelled as “Settings”, implements the ActionListener interface in order to perform button clicking actions. The class overrides the actionPerformed() method such that when the JButton labelled as Update is clicked the information entered in the JTextfields, JComboBoxes and the JPasswordField are checked for validity. The same error messages that are displayed in the Register Page are written in a JOption pane and such errors are logged in the application_error.txt.

● The User class, similar to the Photo class, also implements the Serializable interface to store them as byte streams for the user objects to be accessible after the runtime. The list that contains all photo objects is initialised and the method for deserializing and serialising lists are also included in this class.

● The CaptionFrame class also implements the ActionListener interface and overrides the actionPerformed() method in a way such that when the JButton labelled as “Send” is clicked the caption that could only be written by the owner of the photo and is written on the JTextfield at the very top of the JFrame is posted to the JTextArea below it.

The second package is the PhotoEdit Package that has 9 classes named Blur, Brightness, Contrast, Edge Detection, Sharpen, GrayScale, Edit, ImageMatrix and ImageSecretary.
● The ImageMatrix and ImageSecretary classes were provided in the project outline. They are dedicated to converting a Buffered Image to an Image Matrix, and performing I/O operations on images respectively. Although I have used the method I have written in the Photo class to convert my images to image matrixes, the methods declared in the ImageMatrix class such as getRGB() were used in the implementation of all the Filter classes. ImageSecretary was used to save the edited image matrix.

● All 6 filter classes, GrayScale[5], Blur [6], Brightness[7], EdgeDetection[8], Contrast[9] [10], and Sharpen [11],were constructed in the same manner and a variety of resources were used in their implementation. There are two main methods that are implemented in all classes: apply........() and apply.......IM(). Since the getters and setters declared in the ImageMatrix class are applicable on image matrices there are methods in the format apply.......IM(). The apply......() methods are used to call the methods that apply the filters on image matrixes and save the new images in the path of the photo. Step by step descriptions of the filter methods are included in the source code as comments.

● The final class in this package is the Edit class in which all the image processing actions were implemented. This class also extends the ActionListener interface and overrides several actionPerformed() methods for different buttons. Everytime one of the JButtons that display the editing options is clicked the buttonsDisappear(), getButton() and setButton() methods are called.These methods allow for the creation of an editing page with 2 JButtons whose purpose were defined previously and the JSlider that corresponds to the selected editing option.

The third package is called logging which has 2 classes that are defined under it, the validator class and BaseLogger class.

● The BaseLogger class that also inherits the serializable interface, has the methods that are dedicated to logging informational and error messages, managing log writers for info and log errors and automatically closing the writers when the program is shut down for which a shutdownHook is used [12]. The logInfo() and logError() methods which are declared as static are used in every class to log various events such as the case when a user visits another's profile. The logError() method is included in all of the classes that contain try-catch blocks and import java.io.IOException. The rest of the exceptions are handled using if else statements. BaseLogger class has a composition association with the BufferedWriter infoWriter, BufferedWriter errorWriter and SimpleDateFormat date Format.

● The Validator class does not inherit any classes, implement any interfaces or have any abstract classes and therefore is a standalone class. The methods that are included in it are static methods dedicated to validating various types of information through regular expressions such as usernames, passwords and mail addresses.

The fourth package is called Discovery and has three classes, Discover, OtherProfile and CommentFrame.

● The CommentFrame class extends JFrame and provides the user an environment in which they can add comments under a photo and display comments of other users. This class has a composition association with several other components such as the commentTextField of type JTextField, commentsTextArea of type JTextArea, user of type User, logger of type BaseLogger and photo of type Photo.

● The Discover class that is dedicated to enable users to view the photos that are declared as public by their owners, extends JFrame and implements the ActionListener interface. The class which consists of multiple GUI components such as buttons, panels, scroll panes, and combo boxes to facilitate user interaction, handles several button click events and provides access to several other pages such as the user’s own profile page, other users’ profile page and comments JFrame. User’s can also view photos on a greater scale and view the captions the owner of the photo wrote.

● The OtherProfile class that displays the public information of other users on their profile page, also extends JFrame and implements the ActionListener interface to handle various button clicking events. This JFrame subclass dynamically adds photo buttons on the profile panel equipped with a JScrollPane.. The class handles button click events to provide functionality for viewing photos on a separate frame and navigating back to the discover page.

The fifth and final package is named as Administrator and it includes AdminDiscover and AdminOtherProfile classes. These classes are similar to their counterparts addressed in the description for the previous package, in terms of their functionality and GUI components. The AdminDiscover Page displays all the photos regardless of them being declared as private or public and has an additional instance of JButton that is labelled as “Remove”. When this button is clicked the photo is removed from both the user’s profile page and the discover page. Similar to the AdminDiscover, the AdminOtherProfile class also includes public photos of the user. The functionality of the GUI components is the same as the OtherProfile class.

b) GUI Components - The Window Builder Tool

The WindowBuilder design tool provided by the Eclipse Foundation allows for the users to add GUI components without need to extensively code every element. Through the usage of this tool users can design their frames by visually having a reference and easily customise component properties.

The layout of the WindowBuilder is given in Figure 16. As it can be seen the left hand side is dedicated to displaying the layers of the current frame through indentations whereas the right hand side displays the designed frame. The changes that are made are updated instantaneously therefore the job of the person coding is eased since the changes that would usually take more than a few program start ups to determine for certain are visualised.

It can also be seen that there is a toolbar present in the middle of the frame that displays all the GUI components available. When the mouse of the user approaches the label on which the tool is displayed a brief explanation of the tool is provided. To be able to add a component the user should first click on the tool once, and then click on their design on the right hand side. A component with a default size and name will be displayed on the screen. If desired the user can always change the setting of the components such as their labels, size, font type and size and background colour on the frame that appears below the layered depiction of the project on the left-hand side. For this project the font “Lucida Grande” was selected.

Through the utilisation of the WindowBuilder tool, developers can easily create and add components to the frame, eliminating the need for manual coding and constant testing to visualise the frame. In this project I have used the WindowBuilder tool which allowed me to design my GUI’s faster and use my limited time more efficiently to develop the PhotoCloud application [13].

- GUI Components
As described briefly in other sections I have used various GUI tools to enhance the user interaction. The components I have used are listed below.
● JLabel
● JTextField
● JPasswordField ● JButton
● JComboBox
● JSlider
● JTextArea
● JPanel
● JFrame
● JScrollPane
● JFileChooser

In most of the JFrames and JPanels I have used the null layout to use the WindowBuilder tool efficiently. In the cases when I needed to add photos to the profile pages I have used the border layout. Also
ImageIcon was used to display the photos on the buttons. I have gotten a better understanding of the components through several resources.

-Additional Notes

● Since the project description requested for the user’s to have the capability to upload photos from their computers, the JFileChoosers were directed to the user’s computer. For this case it might be necessary to also download the Photos folder that contains the images that were used for this project and is included in the zip file.

● Since the program has a memory formed by the files with the .ser extensions during the demonstration it is necessary to restart the program to switch users.

References
[1]https://www.youtube.com/watch?v=iE8tZ0hn2Ws [2]https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html [3]https://www.javatpoint.com/java-joptionpan [4]https://www.codingninjas.com/codestudio/library/java-actionlistener [5]https://www.tutorialspoint.com/java_dip/grayscale_conversion.htm [6]https://docs.oracle.com/javase/tutorial/essential/concurrency/examples/ForkBlur.java [7]https://www.geeksforgeeks.org/java-program-to-increase-or-decrease-brightness-of-an-image/ [8]https://www.youtube.com/watch?v=YRz0sxCVx9A [9]https://www.geeksforgeeks.org/image-processing-in-java-contrast-enhancement/ [10]https://stackoverflow.com/questions/10105521/how-to-change-the-contrast-and-brightness-of-a n-image-stored-as-pixel-values [11]https://www.geeksforgeeks.org/image-processing-in-java- sharpness-enhancement/
[12] https://www.baeldung.com/jvm-shutdown-hooks [13]https://www.eclipse.org/windowbuilder/ [14]https://www.youtube.com/watch?v=Kmgo00avvEw

NOTE: Further explanation regarding the classes can also be found in the comments.





