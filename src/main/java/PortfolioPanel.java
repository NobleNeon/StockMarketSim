package main.java;

import javax.swing.*;
import java.awt.*;

public class PortfolioPanel extends JPanel {


    //label for just displaing (delete this later)
    JLabel portfolioPanelLabel;

    PortfolioPanel(){

        //making label
        portfolioPanelLabel = new JLabel();
        portfolioPanelLabel.setText("PORTFOLIO");

        //changing panel settings
        this.setLayout(new BorderLayout());
        this.add(portfolioPanelLabel, BorderLayout.CENTER);
        this.setBounds(0, 0, 600, 600);
    }



}
