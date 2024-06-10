package main.java;

import javax.swing.*;
import java.awt.*;

public class MainAppFrame extends JFrame {

    //initializing panels
    BottomNavBar bottomNavBar;
    PortfolioPanel portfolioPanel;
    TradePanel tradePanel;
    JPanel defaultPanelLayer; //will be used to stack trade and portfolio panels

    MainAppFrame(){

        //creating panels
        bottomNavBar = new BottomNavBar();
        portfolioPanel = new PortfolioPanel();
        tradePanel = new TradePanel();
        defaultPanelLayer = new JPanel();

        //adding portfolio and trade panels to default panel layer
        defaultPanelLayer.add(portfolioPanel);
        defaultPanelLayer.add(tradePanel);

        //adding nav bar and default panel to the frame
        this.setLayout(new BorderLayout()); //setting layout
        this.add(bottomNavBar, BorderLayout.SOUTH);
        this.add(defaultPanelLayer, BorderLayout.CENTER);

        //settings for the frame
        this.setTitle("Paper Trader");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);
        this.setSize(500, 750);
        this.setVisible(true);


    }




}
