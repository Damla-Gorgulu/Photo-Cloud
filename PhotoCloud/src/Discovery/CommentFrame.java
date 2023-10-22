package Discovery;
import javax.swing.*;

import logging.BaseLogger;
import user.Photo;
import user.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The CommentFrame class represents a JFrame that allows users to add comments to a photo.
 * It displays existing comments and updates them when new comments are added.
 */

public class CommentFrame extends JFrame {
    private JTextField commentTextField;
    private JTextArea commentsTextArea;

    private Photo photo;
    User user;
    transient BaseLogger logger = BaseLogger.getInstance();
    
    /**
     * Constructs a CommentFrame object.
     *
     * @param photo The photo to add comments to
     * @param user The currently logged-in user
     */

    public CommentFrame(Photo photo, User user) {
        this.photo = photo;
        this.user=user;

        // Comments JFrame
        setTitle("Comments");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // A panel is created to display the comments
        JPanel commentsPanel = new JPanel();
        commentsPanel.setLayout(new BorderLayout());

        // JLabel
        JLabel commentLabel = new JLabel("Add a comment:");
        commentsPanel.add(commentLabel, BorderLayout.NORTH);

        // A TextField for the user to type the message to be commented
        commentTextField = new JTextField();
        commentsPanel.add(commentTextField, BorderLayout.CENTER);

        // A JButton to send the comment
        JButton sendButton = new JButton("Send");
        commentsPanel.add(sendButton, BorderLayout.EAST);

        // A text area for displaying comments
        commentsTextArea = new JTextArea();
        commentsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(commentsTextArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(commentsPanel, BorderLayout.NORTH);
        setVisible(true);

        //When this button is clicked a comment is shared and the comments of a Photo object is updated 
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String comment = commentTextField.getText();
                String finalCommentString = user.getUsername() + ": " + comment;
                if (!comment.isEmpty()) {
                    photo.getComments().add(finalCommentString);
                    updateCommentsTextArea();
                    User.serializeUserList();
                    User.serializeMediaList();
                    commentTextField.setText("");
                    BaseLogger.logInfo(user.getUsername() + "commented on " + photo.getUser().getUsername() + "'s photo.");
                }
            }
        });
        updateCommentsTextArea();
    }
    
    /**
     * Updates the comments text area with the current comments of the photo.
     */

    private void updateCommentsTextArea() {
        List<String> comments = photo.getComments();
        StringBuilder sb = new StringBuilder();
        for (String comment : comments) {
            sb.append(comment).append("\n");
        }
        commentsTextArea.setText(sb.toString());
        User.serializeMediaList();
        User.serializeUserList();
    }
}


