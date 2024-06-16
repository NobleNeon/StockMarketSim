package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PortfolioPanel extends JPanel {

    //array list to store user data
    private ArrayList<ArrayList<JLabel>> userData = new ArrayList<>();
    private JPanel displayDataPanel;

    public void updateLayout() {

        //making display data panel
        displayDataPanel = new JPanel();

        //removing first empty layer
        userData.remove(0);

        //converting the mutable 'userData' 'ArrayList' into an immutable 'userDataArray'
        String[][] userDataArray = new String[userData.size()][];
        for (int i = 0; i < userData.size(); i++) {
            userDataArray[i] = new String[userData.get(i).size()];
            for (int j = 0; j < userData.get(i).size(); j++) {
                userDataArray[i][j] = userData.get(i).get(j).getText();
            }
        }


        // creating a table of user's portfolio
        JTable displayDataTable = new JTable(userDataArray, new String[]{"Ticker Symbol", "Last Price",
                "Shares Owned", "Transaction Type"});

        /*
        disabling user's ability to edit table:
        the for loop below was copy and pasted from https://stackoverflow.com/a/20568608
        */
        for (int c = 0; c < displayDataTable.getColumnCount(); c++) {
            Class<?> col_class = displayDataTable.getColumnClass(c);
            displayDataTable.setDefaultEditor(col_class, null);
        }

        //creating a scrolling pane and adding the grid
        JScrollPane displayDataScrollPane = new JScrollPane(displayDataTable);

        //adding the scrolling pane to 'displayDataPanel'
        displayDataPanel.add(displayDataScrollPane, BorderLayout.CENTER);

        //adding 'displayDataPanel' to 'PortfolioPanel'
        this.add(displayDataPanel);
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
