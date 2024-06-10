/**
 * Name: Amir Nafissi, Broden Young, Sharayh Badar
 * Date: June 7, 2024.
 * Description: This class will store the user's information, more specifically their userName, password, as well as
 * investment portfolio. TODO - should the portfolio be an arraylist?
 */
package main.java;

import javax.swing.*;
import java.util.HashMap;

public class User extends JFrame {

    private String userName, password;

    public static HashMap<String, String> userCredentials = new HashMap<>();

    public User() {
        userCredentials.put("Shay", "1234");
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
