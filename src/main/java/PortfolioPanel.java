package main.java;

import javax.swing.*;
import java.awt.*;

import static main.java.Main.userDataMatrix;

public class PortfolioPanel extends JPanel {
    

    
    

    private JPanel displayDataPanel;

    public void updateLayout() {

        //making display data panel
        displayDataPanel = new JPanel();

        //converting the mutable 'userDataMatrix' 'ArrayList' into an immutable 'userDataMatrixArray'
        String[][] userDataMatrixArray = new String[userDataMatrix.size()][4];
        for (int i = 0; i < userDataMatrix.size(); i++) {
            userDataMatrixArray[i] = new String[userDataMatrix.get(i).length];
            for (int j = 0; j < userDataMatrix.get(i).length; j++) {
                userDataMatrixArray[i][j] = userDataMatrix.get(i)[j];
            }
        }

        // creating a table of user's portfolio
        JTable displayDataTable = new JTable(userDataMatrixArray, new String[]{"Ticker Symbol", "Last Price",
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

    public JPanel getDisplayDataPanel() {
        return displayDataPanel;
    }

    public void setDisplayDataPanel(JPanel displayDataPanel) {
        this.displayDataPanel = displayDataPanel;
    }
}
