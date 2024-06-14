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
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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

    // for storing usernames and passwords from file
    private final ArrayList<String> userNamesArray = new ArrayList<>();
    private final ArrayList<String> passwordsArray = new ArrayList<>();

    public LogInPage() throws FileNotFoundException {

        File myFile = new File("users.txt");
        Scanner readFile = new Scanner(myFile);

        while (readFile.hasNext()){
            String line = readFile.nextLine();
            String[] tokens = line.split("\t");

            userNamesArray.add(tokens[0]);
            passwordsArray.add(tokens[1]);
        }

        System.out.println(userNamesArray);
        System.out.println(passwordsArray);

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

        errorLabel.setForeground(Color.RED); // set text color to red
        logInPanel.add(errorLabel);

        // adding the log-in panel to the frame
        frame.add(logInPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE); //user shouldn't be able to close program by accident!
        frame.setTitle("Paper Trader");
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null); // centring the JFrame
        frame.setVisible(true);
    }

    public JPasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public void setConfirmPasswordField(JPasswordField confirmPasswordField) {
        this.confirmPasswordField = confirmPasswordField;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getLogInPanel() {
        return logInPanel;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public ArrayList<String> getPasswordsArray() {
        return passwordsArray;
    }

    public JPanel getSignUpPanel() {
        return signUpPanel;
    }

    public JLabel getUserName() {
        return userName;
    }

    public JTextField getUserNameField() {
        return userNameField;
    }

    public ArrayList<String> getUserNamesArray() {
        return userNamesArray;
    }

    public String getFileLocation() {
        return "Users/" + userNameField.getText() + ".txt";
    }


    /**
     * Name: actionPerformed
     * Description: It does specific actions based on certain Swing objects. For example, this method takes the user
     * to the Sign-up page when they press the "Sign up" button they are shown.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e){

        // if the user presses "OK"
        if ("ok".equals(e.getActionCommand())) {

            // if the OK button is in the log-in page...
            if (logInPanel.isVisible()) {

                //if username doesn't exist or the password is invalid
                if (!userNamesArray.contains(userNameField.getText()) ||
                    userNamesArray.indexOf(userNameField.getText()) != passwordsArray.indexOf(passwordField.getText())) {
                    errorLabel.setText("Invalid username/password");
                // if the username exists AND the password matches the account username
                } else {
                    errorLabel.setText("SUCCESS"); // TODO - temporary (delete this later)
                    frame.dispose();
                }


            // if the OK button is in the sign-up page...
            } else if (signUpPanel.isVisible()) {
                errorLabel.setText("");

                // if username already exists
                if (userNamesArray.contains(userNameField.getText())) {
                    errorLabel.setText("Username already exists!");
                // if the username, password, or confirm password fields are still empty
                } else if (userNameField.getText().isEmpty()
                        || passwordField.getText().isEmpty()
                        || confirmPasswordField.getText().isEmpty()) {
                    errorLabel.setText(""); // shouldn't be necessary to specify what to do (it's implied)

                // if the username has not been created, but the passwords do not match
                } else if (!passwordField.getText().equals(confirmPasswordField.getText())) {
                    errorLabel.setText("Passwords do not match!");
                    // if the username exists AND the password matches the account username
                } else {
                    errorLabel.setText("SUCCESS"); // TODO - temporary (delete this later)
                    userNamesArray.add(userNameField.getText());
                    passwordsArray.add(passwordField.getText());
                    frame.dispose();

                    //add the new username and password into the users.txt file and make a file in the Users folder
                    try {
                        File newUser = new File("Users/" + userNameField.getText() + ".txt");

                        // we only want this method to create the file, no point in using the boolean
                        newUser.createNewFile();

                        PrintWriter userInfo = new PrintWriter(new FileWriter("users.txt"), true);

                        for (int i = 0; i < userNamesArray.size(); i++) {
                            userInfo.println(userNamesArray.get(i) + "\t" + passwordsArray.get(i));
                        }
                    } catch (IOException ignored) {}
                }
            }

        // if the user presses the "Sign Up" button...
        } else if ("signup".equals(e.getActionCommand())) {
            // close the log-in page
            logInPanel.setVisible(false);
            errorLabel.setText(""); // remove any previous errors from the log-in page

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
