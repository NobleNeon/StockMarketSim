package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomNavBar extends JPanel implements ActionListener {

    //buttons
    JButton portfolioButton;
    JButton tradeButton;

    BottomNavBar(){

        //setting settings for bottom panel
        this.setLayout(new GridLayout(2,1)); //setting layout

        //creating buttons
        portfolioButton = new JButton("Portfolio");
        tradeButton = new JButton("Trade");

        //adding buttons
        this.add(portfolioButton);
        this.add(tradeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
