package main.java;

import io.polygon.kotlin.sdk.rest.PolygonRestClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static main.java.Main.stockList;


public class TradePanel extends JPanel implements ActionListener{

    private final JLabel testLabel = new JLabel(); // TODO - temporary for testing (delete this later)
    private final JTextField tickerSymbolTextField;
    private final JLabel errorLabel = new JLabel();

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
        JButton searchTickerButton = new JButton("SEARCH");
        searchTickerButton.addActionListener(this);
        searchTickerButton.setActionCommand("search");

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
        tickerSymbolTextField = new JTextField(15);
        JLabel numberOfSharesLabel = new JLabel("Number of Shares: ");
        JTextField numberOfSharesTextField = new JTextField(15);



        //Panel for labels and text fields
        JPanel textFieldAndLabelPanel = new JPanel();
        textFieldAndLabelPanel.setLayout(new GridLayout(4,2));
        textFieldAndLabelPanel.add(tickerSymbolLabel);
        textFieldAndLabelPanel.add(tickerSymbolTextField);
        textFieldAndLabelPanel.add(errorLabel);
        textFieldAndLabelPanel.add(searchTickerButton);
        textFieldAndLabelPanel.add(numberOfSharesLabel);
        textFieldAndLabelPanel.add(numberOfSharesTextField);
        textFieldAndLabelPanel.add(new JLabel());


        this.setLayout(new GridLayout(4,0));
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

    public int binarySearch (String target) {
        int low = 0;
        int high = stockList.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            String midElement = stockList.get(mid);

            if (midElement.equals(target)) {
                return mid; // found the target element
            } else if (midElement.compareTo(target) < 0) {
                low = mid + 1; // target is in the upper half
            } else {
                high = mid - 1; // target is in the lower half
            }
        }

        return -1; // not found
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("search".equals(e.getActionCommand())) {
            int symbolIndex = binarySearch(tickerSymbolTextField.getText());

            if (symbolIndex == -1) {
                errorLabel.setForeground(Color.RED);
                errorLabel.setText("Ticker Symbol Not Found!");
            } else {
                errorLabel.setText("");
                try {
                    Stock stock = new Stock(stockList.get(symbolIndex));
                    System.out.println(stock.getCompanyName());
                } catch (IOException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }

        } else if ("buy".equals(e.getActionCommand())) {
            testLabel.setText("BUY");
        } else if ("sell".equals(e.getActionCommand())) {
            testLabel.setText("SELLING");
        } else if ("short".equals(e.getActionCommand())) {
            testLabel.setText("SHORT");
        } else if ("cover".equals(e.getActionCommand())) {
            testLabel.setText("COVER");
        }
    }
}
