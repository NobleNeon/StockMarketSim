package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PortfolioPanel extends JPanel {

    //array list to store user data
    private ArrayList<ArrayList<JLabel>> userData = new ArrayList<>();
    private JPanel displayDataPanel;

    PortfolioPanel() {

    }


    public void updateLayout() {
        //making display data panel
        displayDataPanel = new JPanel();
        //setting grid layout size to fit the 'userData' matrix
        displayDataPanel.setLayout(new GridLayout(userData.size(), userData.get(0).size()));

        //adding user data from 'userData' matrix to display panel
        for (int i = 0; i < userData.size(); i++) {

            for (int j = 0; j < userData.get(i).size(); j++) {
                displayDataPanel.add(userData.get(i).get(j), BorderLayout.CENTER);
            }
        }

        userData.remove(0);

        String[][] userDataArray = new String[userData.size()][userData.get(0).size()];
        for (int i = 0; i < userData.size(); i++) {
            for (int j = 0; j < userData.get(i).size(); j++) {
                userDataArray[i][j] = userData.get(i).get(j).getText();
            }
        }
//        this.add(displayDataPanel, BorderLayout.CENTER);

//        System.out.println(Arrays.deepToString(userDataArray));


        // creating a table of user's portfolio
        JTable displayDataTable = new JTable(userDataArray, new String[]{"Ticker Symbol", "Last Price",
                                                                         "Shares Owned", "Transaction Type"});

        JScrollPane displayDataScrollPane = new JScrollPane(displayDataTable);
        displayDataPanel.add(displayDataScrollPane, BorderLayout.CENTER);
        this.add(displayDataScrollPane);
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
