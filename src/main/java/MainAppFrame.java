package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainAppFrame extends JFrame implements ActionListener {

    //initializing panels
    PortfolioPanel portfolioPanel;
    TradePanel tradePanel;
    JPanel defaultPanelLayer; //will be used to stack trade and portfolio panels
    JPanel bottomNavBar; //this panel will hold the buttons that will change the main screen panel

    //initializing buttons
    //buttons
    JButton portfolioButton;
    JButton tradeButton;

    MainAppFrame(){

        //creating panels
        portfolioPanel = new PortfolioPanel();
        tradePanel = new TradePanel();
        defaultPanelLayer = new JPanel();
        bottomNavBar = new JPanel();

        //adding portfolio and trade panels to default panel layer
        defaultPanelLayer.add(portfolioPanel);
        defaultPanelLayer.add(tradePanel);

        //adding portfolio and trade buttons to bottom nav bar panel
        //setting settings for bottom panel
        bottomNavBar.setLayout(new GridLayout(1,2)); //setting layout

        //creating buttons
        portfolioButton = new JButton("Portfolio");
        tradeButton = new JButton("Trade");

        //adding action listener to buttons
        portfolioButton.addActionListener(this);
        tradeButton.addActionListener(this);

        //adding buttons
        bottomNavBar.add(portfolioButton);
        bottomNavBar.add(tradeButton);

        //adding nav bar and default panel to the frame
        this.setLayout(new BorderLayout()); //setting layout
        this.add(bottomNavBar, BorderLayout.SOUTH); //adding to the button
        this.add(defaultPanelLayer, BorderLayout.CENTER); //adding to the main area AKA center

        //settings for the frame
        this.setTitle("Paper Trader");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 750);
        this.setVisible(true);


    }

    //getters and setters
    public PortfolioPanel getPortfolioPanel() {
        return portfolioPanel;
    }

    public void setPortfolioPanel(PortfolioPanel portfolioPanel) {
        this.portfolioPanel = portfolioPanel;
    }

    public TradePanel getTradePanel() {
        return tradePanel;
    }

    public void setTradePanel(TradePanel tradePanel) {
        this.tradePanel = tradePanel;
    }

    public JPanel getDefaultPanelLayer() {
        return defaultPanelLayer;
    }

    public void setDefaultPanelLayer(JPanel defaultPanelLayer) {
        this.defaultPanelLayer = defaultPanelLayer;
    }

    public JPanel getBottomNavBar() {
        return bottomNavBar;
    }

    public void setBottomNavBar(JPanel bottomNavBar) {
        this.bottomNavBar = bottomNavBar;
    }

    public JButton getPortfolioButton() {
        return portfolioButton;
    }

    public void setPortfolioButton(JButton portfolioButton) {
        this.portfolioButton = portfolioButton;
    }

    public JButton getTradeButton() {
        return tradeButton;
    }

    public void setTradeButton(JButton tradeButton) {
        this.tradeButton = tradeButton;
    }

    /**
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == portfolioButton){

            //changing visibility of panels accordingly
            tradePanel.setVisible(false);
            portfolioPanel.setVisible(true);
        }
        if (e.getSource() == tradeButton){

            //changing visibility of panels accordingly
            portfolioPanel.setVisible(false);
            tradePanel.setVisible(true);
        }
    }
}
