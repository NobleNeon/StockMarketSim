package main.java;

import io.polygon.kotlin.sdk.rest.PolygonRestClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static main.java.Main.userDataMatrix;


public class TradePanel extends JPanel implements ActionListener{

    private final JLabel testLabel = new JLabel(); // TODO - temporary for testing (delete this later)


    TradePanel(){
        // Creating buttons and their action listeners
        JButton buyButton = new JButton("BUY");
        buyButton.addActionListener(this);
        buyButton.setActionCommand("buy");
        JButton sellButton = new JButton("SELL");
        sellButton.addActionListener(this);
        sellButton.setActionCommand("sell");
        JButton shortButton = new JButton("SHORT");
        shortButton.addActionListener(this);
        shortButton.setActionCommand("short");
        JButton coverButton = new JButton("COVER");
        coverButton.addActionListener(this);
        coverButton.setActionCommand("cover");

        // Panels for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,4));
        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);
        buttonPanel.add(shortButton);
        buttonPanel.add(coverButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());


        //Labels and Text Fields
        JLabel tickerSymbolLabel = new JLabel("Ticker Symbol: ");
        JTextField tickerSymbolTextField = new JTextField(15);
        JLabel numberOfSharesLabel = new JLabel("Number of Shares: ");
        JTextField numberOfSharesTextField = new JTextField(15);
        JLabel errorLabel = new JLabel("Ticker Symbol Not Found!");
        errorLabel.setForeground(Color.RED);


        //Panel for labels and text fields
        JPanel textFieldAndLabelPanel = new JPanel();
        textFieldAndLabelPanel.setLayout(new GridLayout(2,2));
        textFieldAndLabelPanel.add(tickerSymbolLabel);
        textFieldAndLabelPanel.add(tickerSymbolTextField);
        textFieldAndLabelPanel.add(numberOfSharesLabel);
        textFieldAndLabelPanel.add(numberOfSharesTextField);
        textFieldAndLabelPanel.add(errorLabel);

        this.setLayout(new GridLayout(3,0));
        this.add(textFieldAndLabelPanel, BorderLayout.WEST);
        this.add(buttonPanel, BorderLayout.WEST);
        this.add(testLabel);//TODO - temporary

        this.setBounds(0, 0, 600, 600);
    }

    public void createGraph() {
        String apiKey = "xWzhEHlJS0D6qsOoOi1_sBrcD_umz4Sj";
        PolygonRestClient client = new PolygonRestClient(apiKey);

        // Set up the parameters for the aggregates request
//        AggregatesParameters params = new AggregatesParameters.Builder()
//                .withTicker("AAPL") // The ticker symbol for the stock
//                .withMultiplier(1) // The size of the timespan multiplier
//                .withTimespan("day") // The size of the time window (e.g., "minute", "hour", "day")
//                .withFrom("2020-01-01") // Start date for the aggregate window
//                .withTo("2020-01-05") // End date for the aggregate window
//                .build();
//
//        // Fetch the aggregate bars
//        AggregatesDTO aggregates = client.getRestClient().getStocksClient().getAggregatesBlocking(params);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("buy".equals(e.getActionCommand())) {
        } else if ("sell".equals(e.getActionCommand())) {
            testLabel.setText("SELLING");
        } else if ("short".equals(e.getActionCommand())) {
            testLabel.setText("SHORT");
        } else if ("cover".equals(e.getActionCommand())) {
            testLabel.setText("COVER");
        }
    }
}
