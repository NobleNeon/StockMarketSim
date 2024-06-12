/**
 * Name: Amir Nafissi, Broden Young, Sharayh Badar
 * Date: June 6, 2024.
 * Project Name: Paper Traders
 * Description: The class displays a graphic user interface to the user that the user can play with. This class has the
 * code to display the different pages the user might see. For example, when the program starts, the user is shown the
 * Log-in page, but if they want to sign up, they're taken to the Sign-up page.
 */

package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInPage extends JFrame implements ActionListener {

    // initialized fields - no need for getters and setters as they are not supposed to be used outside the class
    private final JTextField userNameField;
    private final JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private final JFrame frame;
    private final JPanel logInPanel;
    private final JPanel signUpPanel = new JPanel();
    private final JLabel errorLabel = new JLabel();
    private final JLabel userName;
    private final JButton okButton;

    public LogInPage() {

        frame = new JFrame();

        // adding buttons and their action listeners
        okButton = new JButton("OK");
        okButton.addActionListener(this);
        okButton.setActionCommand("ok");

        // only used for log-in page
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(this);
        signUpButton.setActionCommand("signup");

        // labels for more clear UI
        userName = new JLabel("Username: ");
        userNameField = new JTextField(15);

        passwordField = new JPasswordField(15); // note: password is not visible - this uses ⏺ character

        // for the log-in page, the previously declared objects need to be added
        // the sign-up page will use these same objects too, but its best not use them if we don't HAVE to
        logInPanel = new JPanel();
        logInPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        logInPanel.setLayout(new GridLayout(0, 1));
        logInPanel.add(userName);
        logInPanel.add(userNameField);
        logInPanel.add(new JLabel("Password: "));
        logInPanel.add(passwordField);
        logInPanel.add(okButton);

        // since the "New User ?" JLabel below will only be needed in the log-in page, we don't to create a reference
        // variable for it
        logInPanel.add(new JLabel("New User? "));
        logInPanel.add(signUpButton);


        // adding the log-in panel to the frame
        frame.add(logInPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE); //user shouldn't be able to close program by accident!
        frame.setTitle("Paper Trader");
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Name: actionPerformed
     * Description: It does specific actions based on certain Swing objects. For example, this method takes the user
     * to the Sign-up page when they press the "Sign up" button they are shown.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // if the user presses "OK"
        if ("ok".equals(e.getActionCommand())) {

            // if the OK button is in the log-in page...
            if (logInPanel.isVisible()) {

                System.out.println("We're still in the log-in page.");
                System.out.println("Username: " + userNameField.getText() + "\nPassword: " + passwordField.getText());

                //TODO finish making these once Broden and Amir figure out how to store user information
//                // if username doesn't exist
//                if () {
//                    errorLabel.setText("Username does not exist.");
//                // if the username exists, but the password does not match
//                } else if () {
//                    System.out.println("Username/Password is invalid.");
//                // if the username exists AND the password matches the account username
//                } else {
//
//                }

            // if the OK button is in the sign-up page...
            } else if (signUpPanel.isVisible()) {

                //TODO same thing above

                System.out.println("We are now in the Sign Up Page");
                System.out.println("Username: " + userNameField.getText()
                                + "\nPassword: " + passwordField.getText()
                                + "\nConfirm Password: " + confirmPasswordField.getText());
            }

            //TODO take the userNameField and passwordField and check if they are already in the file
            // Broden will make the file?
        // if the user presses the "Sign Up" button...
        } else if ("signup".equals(e.getActionCommand())) {
            // close the log-in page
            logInPanel.setVisible(false);

            // now use the signUpPanel object and...
                // add the previous objects from the log-in page (no other efficient way to do this)
            signUpPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
            signUpPanel.setLayout(new GridLayout(0, 1));
            signUpPanel.add(userName);
            signUpPanel.add(userNameField);
            signUpPanel.add(new JLabel("Create password: "));
            signUpPanel.add(passwordField);

                // and add an extra "confirm password" label and password field
            confirmPasswordField = new JPasswordField(15);
            signUpPanel.add(new JLabel("Confirm Password: "));

            signUpPanel.add(confirmPasswordField);
            signUpPanel.add(okButton);

                // add an extra JLabel to display errors made by the user - this label will be updated if the user makes
                // a mistake
            errorLabel.setForeground(Color.RED); // set text color to red
            signUpPanel.add(errorLabel);

            // show this sign-up page instead of the log-in page
            frame.add(signUpPanel);
            frame.setResizable(false);
            frame.setVisible(true);
        }
    }
}
