/**
 * Name: MainAppFrame
 * Description: This class contains the main JFrame that contains the application
 * This is also where the 'portfolioPanel' and 'tradePanel' are initialized and added to the frame
 */
package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static main.java.Main.userDataMatrix;

public class MainAppFrame extends JFrame implements ActionListener {

    // Formatting as per given pattern in the argument
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

    // to be used in the parameter for the new stock
    String currentDate = ft.format(new Date());

    //initializing panels
    PortfolioPanel portfolioPanel;
    TradePanel tradePanel;
    JPanel instructionsPanel;
    JPanel defaultPanelLayer; //will be used to stack portfolio, trade, and instruction panels
    JPanel bottomNavBar; //this panel will hold the buttons that will change the main screen panel
    JPanel graphPanel; // this panel will show the stocks of the company

    //initializing the main frame
    JFrame frame = new JFrame("Aura Traders");

    //initializing buttons
    JButton portfolioButton;
    JButton tradeButton;
    static JButton graphButton;

    //no arg constructor
    public MainAppFrame() throws IOException {

        //changing logo
        ImageIcon logo = new ImageIcon("stockIcon.png");
        frame.setIconImage(logo.getImage());

        //creating panels
        portfolioPanel = new PortfolioPanel();
        tradePanel = new TradePanel();
        instructionsPanel = new JPanel();
        defaultPanelLayer = new JPanel();
        bottomNavBar = new JPanel();
        graphPanel = new JPanel();
        graphPanel.setSize(500, 500);

        //adding portfolio, trade, and instructions panels to default panel layer
        defaultPanelLayer.add(portfolioPanel);
        defaultPanelLayer.add(tradePanel);
        defaultPanelLayer.add(instructionsPanel);
        defaultPanelLayer.add(graphPanel);

        //changing visibility of panels (will be changed later once user selects buttons)
        portfolioPanel.setVisible(false);
        tradePanel.setVisible(false);
        graphPanel.setVisible(false);
        instructionsPanel.setVisible(true);
        defaultPanelLayer.setVisible(true);

        //initializing instructions paragraph using HTML formatting
        String instructionsString = "<html><p>Welcome to Aura Traders!" +
                "<br><br>Here's how to use the program:" +
                "<br>You can check your portfolio buy clicking the 'Portfolio' button below" +
                "<br>The portfolio shows the stocks you own and your balance" +
                "<br>You can make trades by clicking  the 'Trade' button below" +
                "<br>Make sure you press the 'save' button after making a trade!</p></html>";

        //adding instructionsString to instructions panel layer
        instructionsPanel.add(new JLabel(instructionsString));

        //adding portfolio and trade buttons to bottom nav bar panel
        //setting for bottom panel
        bottomNavBar.setLayout(new GridLayout(1,3)); //setting layout

        //creating buttons
        portfolioButton = new JButton("Portfolio");
        graphButton = new JButton("Graph");
        graphButton.setEnabled(false);
        tradeButton = new JButton("Trade");

        //adding action listener to buttons
        portfolioButton.addActionListener(this);
        tradeButton.addActionListener(this);
        graphButton.addActionListener(this);

        //adding buttons
        bottomNavBar.add(portfolioButton);
        bottomNavBar.add(tradeButton);
        bottomNavBar.add(graphButton);

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

    public static JButton getGraphButton() {
        return graphButton;
    }

    public void setGraphButton(JButton graphButton) {
        MainAppFrame.graphButton = graphButton;
    }

    public JPanel getGraphPanel() {
        return graphPanel;
    }

    public void setGraphPanel(JPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    /**
     * Name: actionPerformed
     * Description: this method is used to detect if a button is pressed
     *              as well as the code that will run if those buttons are pressed
     * @param e: the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == portfolioButton){

            //changing visibility of panels accordingly
            tradePanel.setVisible(false);
            instructionsPanel.setVisible(false);
            graphPanel.setVisible(false);

            //Changing button's enabled to prevent from double-clicking:
            portfolioButton.setEnabled(false);
            tradeButton.setEnabled(true);
            graphButton.setEnabled(TradePanel.isTickerSymbolValid());

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
            graphPanel.setVisible(false);
            tradePanel.setVisible(true);

            //Changing button's enabled to prevent from double-clicking:
            tradeButton.setEnabled(false);
            graphButton.setEnabled(TradePanel.isTickerSymbolValid());
            portfolioButton.setEnabled(true);

            //clearing portfolio panel to make room for new data once user clicks on 'portfolioButton' again
            if (userDataMatrix.isEmpty())
                portfolioPanel.remove(portfolioPanel.getDisplayEmptyDataLabel()); //remove this label if no data
            else
                portfolioPanel.remove(portfolioPanel.getDisplayDataPanel()); //remove this label if have data

            portfolioPanel.remove(portfolioPanel.getDisplayUserBalance()); //always remove this label

            //changing frame's title to match the panel the user is currently on
            frame.setTitle("Trade");
            frame.setVisible(true);
        }
        // when the graph button is pressed
        if (e.getSource() == graphButton){

            // change the visibility of the panels accordingly
            portfolioPanel.setVisible(false);
            instructionsPanel.setVisible(false);
            tradePanel.setVisible(false);

            //Changing button's enabled to prevent from double-clicking:
            graphButton.setEnabled(false);
            tradeButton.setEnabled(true);
            portfolioButton.setEnabled(true);

            // remove the previously stored graph (if there was one)
            try {
                graphPanel.remove(0);
            } catch (ArrayIndexOutOfBoundsException ignored) {

            }

            // add the new graph with the ticker symbol that the user inputted.
            try {
                graphPanel.add(new StockGraph(TradePanel.getStock().getTickerSymbol(), currentDate));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // show the new graph and set it with the title with the specific ticker symbol
            graphPanel.setVisible(true);
            frame.setTitle(TradePanel.getStock().getTickerSymbol() + " Stock Price | From 1 Year Ago Until Now");
        }
    }
}