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
    JFrame frame = new JFrame("Aura Traders");

    //initializing buttons
    //buttons
    JButton portfolioButton;
    JButton tradeButton;

    MainAppFrame() {

        ImageIcon img = new ImageIcon("stockIcon.png");
        frame.setIconImage(img.getImage());
        //creating panels
        portfolioPanel = new PortfolioPanel();
        tradePanel = new TradePanel();
        defaultPanelLayer = new JPanel();
        bottomNavBar = new JPanel();

        //adding portfolio and trade panels to default panel layer
        defaultPanelLayer.add(portfolioPanel);
        defaultPanelLayer.add(tradePanel);

        //changing visibility of panels (will be changed later once user selects buttons)
        portfolioPanel.setVisible(false);
        tradePanel.setVisible(false);
        defaultPanelLayer.setVisible(true);

        //adding portfolio and trade buttons to bottom nav bar panel
        //setting for bottom panel
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
        frame.setLayout(new BorderLayout()); //setting layout
        frame.add(bottomNavBar, BorderLayout.SOUTH); //adding to the button
        frame.add(defaultPanelLayer, BorderLayout.CENTER); //adding to the main area AKA center

        //settings for the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
            frame.setTitle("Portfolio");
            frame.setVisible(true);
        }
        if (e.getSource() == tradeButton){

            //changing visibility of panels accordingly
            portfolioPanel.setVisible(false);
            tradePanel.setVisible(true);
            frame.setTitle("Trade");
            frame.setVisible(true);
        }
    }
}
