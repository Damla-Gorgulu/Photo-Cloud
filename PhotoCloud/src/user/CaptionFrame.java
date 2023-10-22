package user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CaptionFrame extends JFrame {
    private List<String> captions;
    private JTextArea textArea;
    private JTextField textField;
    
    /**
     * Constructs a CaptionFrame with a list of captions that were previously 
     * written by the owner of the photo. A new caption could be written in the 
     * JTextField and can be posted if the add button is clicked.
     * @param captions The list of captions to display.
     * @param user The user that is allowed to write captions
     * @param other The other user that is not allowed to write captions
     */

    public CaptionFrame(List<String> captions, User user, User other) {
        this.captions = captions;
        setTitle("Captions");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // An add button to post the caption is created.
        JPanel sendPanel = new JPanel(new BorderLayout());
        this.textField = new JTextField();
        JButton addButton = new JButton("Add");

        //Only the owner of the photo can add a caption.
        if (user.equals(other)) {
            addButton.setPreferredSize(new Dimension(50, 25)); 
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newCaption = textField.getText();
                    if (!newCaption.isEmpty()) {
                        captions.add(newCaption);
                        updateTextArea();
                        textField.setText("");
                        User.serializeMediaList();
                        User.serializeUserList();
                    }
                }
            });
            sendPanel.add(this.textField, BorderLayout.CENTER);
            sendPanel.add(addButton, BorderLayout.EAST);
        } else {
            sendPanel.add(this.textField, BorderLayout.CENTER);
        }

        panel.add(sendPanel, BorderLayout.NORTH);

        // A text-area that can not be edited is created
        this.textArea = new JTextArea();
        this.textArea.setEditable(false);
        this.textArea.setBounds(0, 0, 300, 400);
        JScrollPane scrollPane = new JScrollPane(this.textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        updateTextArea();

        add(panel);
        setVisible(true);
    }

    /**
     * The text area is updated by adding the most recent entry by the user
     */
    private void updateTextArea() {
        this.textArea.setText(String.join("\n", captions));
    }

}
