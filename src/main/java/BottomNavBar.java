/**
 *
 *
 *
 *
 *
 *
 *
 *
 * This class ended up not being used but let's not delete it just in case
 */

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
        this.setLayout(new GridLayout(1,2)); //setting layout

        //creating buttons
        portfolioButton = new JButton("Portfolio");
        tradeButton = new JButton("Trade");

        //adding action listener to buttons
        portfolioButton.addActionListener(this);
        tradeButton.addActionListener(this);

        //adding buttons
        this.add(portfolioButton);
        this.add(tradeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == portfolioButton){
            System.out.println("Lil number 1");
        }
        if (e.getSource() == tradeButton){
            System.out.println("Lol number 2");
        }
    }
}
