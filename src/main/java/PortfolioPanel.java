package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PortfolioPanel extends JPanel {

    //label for just displaing (delete this later)
    JLabel portfolioPanelLabel;

    //array list to store user data
    private ArrayList<ArrayList<JLabel>> userData = new ArrayList<>();
    private JPanel displayDataPanel;

    PortfolioPanel(){

        //making label
        portfolioPanelLabel = new JLabel();
        portfolioPanelLabel.setText("PORTFOLIO");

        //making display data panel
        displayDataPanel = new JPanel();

        //setting grid layout size to fit the 'userData' matrix
        displayDataPanel.setLayout(new GridLayout(userData.get(0).size(), userData.size()));

        //adding user data from 'userData' matrix to display panel
        for (int i = 0; i < userData.size(); i++) {
            for (int j = 0; j < userData.get(i).size(); j++) {
                displayDataPanel.add(userData.get(i).get(j));
            }
        }

        //adding 'displayDataPanel' to portfolio panel
        this.add(displayDataPanel, BorderLayout.CENTER);

        //changing panel settings
        this.setLayout(new BorderLayout());
        this.add(portfolioPanelLabel, BorderLayout.NORTH);
        this.setBounds(0, 0, 600, 600);
    }

    //getters and setters:
    public ArrayList<ArrayList<JLabel>> getUserData() {
        return userData;
    }

    public void setUserData(ArrayList<ArrayList<JLabel>> userData) {
        this.userData = userData;
    }

    public JPanel getDisplayDataPanel() {
        return displayDataPanel;
    }

    public void setDisplayDataPanel(JPanel displayDataPanel) {
        this.displayDataPanel = displayDataPanel;
    }
}
