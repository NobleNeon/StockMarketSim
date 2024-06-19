package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainAppFrame extends JFrame implements ActionListener {

    //initializing panels
    PortfolioPanel portfolioPanel;
    TradePanel tradePanel;
    JPanel instructionsPanel;
    JPanel defaultPanelLayer; //will be used to stack trade and portfolio panels
    JPanel bottomNavBar; //this panel will hold the buttons that will change the main screen panel
    JFrame frame = new JFrame("Aura Traders");

    //initializing buttons
    //buttons
    JButton portfolioButton;
    JButton tradeButton;


    MainAppFrame() {

        //changing logo
        ImageIcon logo = new ImageIcon("stockIcon.png");
        frame.setIconImage(logo.getImage());

        //creating panels
        portfolioPanel = new PortfolioPanel();
        tradePanel = new TradePanel();
        instructionsPanel = new JPanel();
        defaultPanelLayer = new JPanel();
        bottomNavBar = new JPanel();

        //adding portfolio, trade, and instructions panels to default panel layer
        defaultPanelLayer.add(portfolioPanel);
        defaultPanelLayer.add(tradePanel);
        defaultPanelLayer.add(instructionsPanel);

        //changing visibility of panels (will be changed later once user selects buttons)
        portfolioPanel.setVisible(false);
        tradePanel.setVisible(false);
        instructionsPanel.setVisible(true);
        defaultPanelLayer.setVisible(true);

        //adding instructions to instructions panel layer
        //TODO - fix this formating
        instructionsPanel.add(new JLabel("Welcome to Aura Traders!"));
        instructionsPanel.add(new JLabel("Welcome to Aura Traders!"));
        instructionsPanel.add(new JLabel("Here's how to use the program:"));
        instructionsPanel.add(new JLabel("You can check your portfolio buy clicking the 'Portfolio' button below\""));
        instructionsPanel.add(new JLabel("The portfolio shows the amount of stocks you own, and your total buying power"));
        instructionsPanel.add(new JLabel("You can make trades by clicking the 'Trade' button below"));
        instructionsPanel.add(new JLabel("Make sure you save your progress! You can do this by clicking the 'save' button after making a trade"));
        instructionsPanel.add(new JLabel("Have fun!"));

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
            instructionsPanel.setVisible(false);

            //Changing button's enabled to prevent from double-clicking:
            portfolioButton.setEnabled(false);
            tradeButton.setEnabled(true);


            portfolioPanel.updateLayout(); //updating portfolio panel to be up-to-date with user's data
            portfolioPanel.setVisible(true); //making the panel visible to the user

            //changing frame's title to match the panel the user is currently on
            frame.setTitle("Portfolio");
            frame.setVisible(true);
        }
        if (e.getSource() == tradeButton){

            //changing visibility of panels accordingly
            portfolioPanel.setVisible(false);
            instructionsPanel.setVisible(false);
            tradePanel.setVisible(true);

            //Changing button's enabled to prevent from double-clicking:
            tradeButton.setEnabled(false);
            portfolioButton.setEnabled(true);

            //clearing portfolio panel to make room for new data once user clicks on 'portfolioButton' again
            portfolioPanel.remove(portfolioPanel.getDisplayDataPanel());

            //changing frame's title to match the panel the user is currently on
            frame.setTitle("Trade");
            frame.setVisible(true);
        }
    }
}
