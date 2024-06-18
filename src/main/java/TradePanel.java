package main.java;

import com.jayway.jsonpath.PathNotFoundException;
import io.polygon.kotlin.sdk.rest.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static main.java.Main.*;


public class TradePanel extends JPanel implements ActionListener{

    private final JLabel testLabel = new JLabel(); // TODO - temporary for testing (delete this later)
    private final JTextField tickerSymbolTextField;
    private final JTextField numberOfSharesTextField;
    private final JLabel errorLabel = new JLabel();
    private Stock stock = new Stock();
    private final JPanel buttonPanel = new JPanel();
    private final JPanel longSaveButtonPanel;

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
        JButton saveDataButton = new JButton("SAVE");
        saveDataButton.addActionListener(this);
        saveDataButton.setActionCommand("save");


        // Panels for buttons
        buttonPanel.setLayout(new GridLayout(4,4));
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
        numberOfSharesTextField = new JTextField(15);

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

        longSaveButtonPanel = new JPanel();
        longSaveButtonPanel.setLayout(new GridLayout(3,0));
        longSaveButtonPanel.add(saveDataButton);


        this.setLayout(new GridLayout(4,0));
        this.add(textFieldAndLabelPanel, BorderLayout.WEST);
        this.add(buttonPanel, BorderLayout.WEST);
        buttonPanel.setVisible(false);

        //TODO - add the graph before this save button
        this.add(longSaveButtonPanel, BorderLayout.CENTER);
        longSaveButtonPanel.setVisible(false);

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
//        AggregatesDTO aggregates;
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
    public void actionPerformed(ActionEvent e) throws PathNotFoundException {
        int symbolIndex = 0;
        if ("search".equals(e.getActionCommand())) {
            symbolIndex = binarySearch(tickerSymbolTextField.getText());
            errorLabel.setText("");

            if (symbolIndex == -1) {
                errorLabel.setForeground(Color.RED);
                errorLabel.setText("Ticker Symbol Not Found!");
            } else {
                buttonPanel.setVisible(true);
                try {
                    stock = new Stock(stockList.get(symbolIndex)); // Update the stock object
                    System.out.println(stock.getCompanyName());
                    errorLabel.setForeground(Color.GREEN);
                    errorLabel.setText("Stock found: " + stock.getCompanyName());
                } catch (IOException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }

        } else if ("buy".equals(e.getActionCommand())){
            longSaveButtonPanel.setVisible(true);
            userBalance -= stock.buyStock(Integer.parseInt(numberOfSharesTextField.getText()));
            for (int i = 0; i < userDataMatrix.size(); i++) {
                for (int j = 0; j < userDataMatrix.get(i).length; j++) {
                    System.out.println(userDataMatrix.get(i)[j]);
                }
            }
        } else if ("sell".equals(e.getActionCommand())) {
            longSaveButtonPanel.setVisible(true);
            testLabel.setText("SELLING");
        } else if ("short".equals(e.getActionCommand())) {
            longSaveButtonPanel.setVisible(true);
            testLabel.setText("SHORT");
            userDataMatrix.add(new String[]{stock.getTickerSymbol(), stock.getCurrentPriceStr(), numberOfSharesTextField.getText(), "SHORT" });
        } else if ("cover".equals(e.getActionCommand())) {
            longSaveButtonPanel.setVisible(true);
            testLabel.setText("COVER");
        } else if ("save".equals(e.getActionCommand())) {
                //saving user's data:
                try {
                    printFileData(userDataFileLocation);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                //notifying user that their data has been saved
                JOptionPane.showMessageDialog(null,
                        "Your data has been saved",
                        "Message from Aura",
                        JOptionPane.INFORMATION_MESSAGE);

        }
    }
}