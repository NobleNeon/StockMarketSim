package main.java;

import javax.swing.*;
import java.awt.*;

import static main.java.Main.userBalance;
import static main.java.Main.userDataMatrix;

public class PortfolioPanel extends JPanel {

    private JPanel displayDataPanel; //for adding the grid containing the user's data onto
    private JLabel displayEmptyDataLabel; //for letting the user know that their portfolio is empty
    private JLabel displayUserBalance; //for displaying user's balance


    public void updateLayout() {

        //initializing display data panel
        displayDataPanel = new JPanel();

        //initializing labels
        displayEmptyDataLabel = new JLabel("Portfolio empty!");
        displayUserBalance = new JLabel("Your current balance: $" + String.valueOf(userBalance));

        //setting portfolio's layout:
        this.setLayout(new BorderLayout());

        //adding user's balance:
        this.add(displayUserBalance, BorderLayout.NORTH);

        //
        if (userDataMatrix.isEmpty()) {
            this.add(displayEmptyDataLabel, BorderLayout.CENTER);
        }
        else {

            //converting the mutable 'userDataMatrix' into an immutable 'userDataArray'
            String[][] userDataArray = new String[userDataMatrix.size()][userDataMatrix.get(0).length];

            for (int i = 0; i < userDataMatrix.size(); i++) {
                userDataArray[i] = new String[userDataMatrix.get(i).length];
                for (int j = 0; j < userDataMatrix.get(i).length; j++) {
                    userDataArray[i][j] = userDataMatrix.get(i)[j];
                }
            }

            // creating a table from user's data
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
            this.add(displayDataPanel, BorderLayout.CENTER);
        }
    }

    //getters and setters:
    public JPanel getDisplayDataPanel() {
        return displayDataPanel;
    }

    public void setDisplayDataPanel(JPanel displayDataPanel) {
        this.displayDataPanel = displayDataPanel;
    }

    public JLabel getDisplayEmptyDataLabel() {
        return displayEmptyDataLabel;
    }

    public void setDisplayEmptyDataLabel(JLabel displayEmptyDataLabel) {
        this.displayEmptyDataLabel = displayEmptyDataLabel;
    }

    public JLabel getDisplayUserBalance() {
        return displayUserBalance;
    }

    public void setDisplayUserBalance(JLabel displayUserBalance) {
        this.displayUserBalance = displayUserBalance;
    }
}
