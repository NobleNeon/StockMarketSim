package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PortfolioPanel extends JPanel {

    //label for just displaing (delete this later)
    JLabel portfolioPanelLabel;

    //array list that will take.....
    protected ArrayList<ArrayList<JLabel>> userData = new ArrayList<>();

    PortfolioPanel(){

        //making label
        portfolioPanelLabel = new JLabel();
        portfolioPanelLabel.setText("PORTFOLIO");

        //changing panel settings
        this.setLayout(new BorderLayout());
        this.add(portfolioPanelLabel, BorderLayout.CENTER);
        this.setBounds(0, 0, 600, 600);
    }

    //getters and setters:
    public ArrayList<ArrayList<JLabel>> getUserData() {
        return userData;
    }

    public void setUserData(ArrayList<ArrayList<JLabel>> userData) {
        this.userData = userData;
    }


}
