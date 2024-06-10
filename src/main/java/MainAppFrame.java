package main.java;

import javax.swing.*;
import java.awt.*;

public class MainAppFrame extends JFrame {


    //initializing panels
    BottomNavBar bottomNavBar;


    MainAppFrame(){

        //creating 'BottomNavBar' panel
        bottomNavBar = new BottomNavBar();


        //adding panels to the frame
        this.setLayout(new BorderLayout()); //setting layout
        this.add(bottomNavBar, BorderLayout.SOUTH);



        //settings for the frame
        this.setTitle("Paper Trader");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);
        this.setSize(500, 750);
        this.setVisible(true);


    }


}
