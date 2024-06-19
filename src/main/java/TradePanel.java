package main.java;

import com.jayway.jsonpath.PathNotFoundException;
import io.polygon.kotlin.sdk.rest.PolygonRestClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static main.java.Main.*;


public class TradePanel extends JPanel implements ActionListener{

    private final JLabel testLabel = new JLabel(); // TODO - temporary for testing (delete this later)
    private final JTextField tickerSymbolTextField;
    private static JTextField numberOfSharesTextField = null;
    private final JLabel errorLabel = new JLabel();
    private static Stock stock = new Stock();
    private final JPanel buttonPanel = new JPanel();
    private final JPanel longSaveButtonPanel;
    private boolean containsValue;
    private int dataIndexI;
    private String[] sharesArray = new String[4];
    private int sharesSold;

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

            if (symbolIndex == -1) {
                errorLabel.setForeground(Color.RED);
                errorLabel.setText("Ticker Symbol Not Found!");
            } else {
                buttonPanel.setVisible(true);
                try {
                    stock = new Stock(stockList.get(symbolIndex)); // Update the stock object
                    System.out.println(stock.getCompanyName());
                    errorLabel.setForeground(Color.BLUE);
                    errorLabel.setText("Stock found!");
                } catch (IOException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }

        } else if ("buy".equals(e.getActionCommand())){
            containsValue = false;
            for (int i = 0; i < userDataMatrix.size(); i++) {
                String[] stringArray = userDataMatrix.get(i);

                for (int j = 0; j < stringArray.length; j++) {
                    if (stringArray[j].equals(stock.getTickerSymbol())){
                        containsValue = true;
                        break;
                    }
                }
            }
            if (containsValue) {
                testLabel.setText("You already long/short this stock");
            }
            else {
                userDataMatrix.add(new String[]{
                        stock.getTickerSymbol(),
                        stock.getCurrentPriceStr(),
                        numberOfSharesTextField.getText(),
                        "BUY"});
                userBalance -= (Integer.parseInt(numberOfSharesTextField.getText()) * stock.getCurrentPrice());
                testLabel.setText("Successfully bought stock");
                longSaveButtonPanel.setVisible(true);
            }

        } else if ("sell".equals(e.getActionCommand())) {
            for (int i = 0; i < userDataMatrix.size(); i++) {
                String[] stringArray = userDataMatrix.get(i);
                dataIndexI = i;
                containsValue = false;
                for (int j = 0; j < stringArray.length; j++) {
                    if (stringArray[j].equals(stock.getTickerSymbol())){
                        containsValue = true;
                        break;
                    }
                }
            }
            if (containsValue) {
                sharesSold = Integer.parseInt(numberOfSharesTextField.getText());
                sharesArray = userDataMatrix.get(dataIndexI);
                for (int i = 0; i < sharesArray.length; i++) {
                    System.out.println(sharesArray[i]);
                }
                if (Integer.parseInt(sharesArray[2]) > sharesSold && sharesArray[3].equals("BUY")){
                    sharesArray[2] = Integer.toString(Integer.parseInt(sharesArray[2]) - sharesSold);
                    userDataMatrix.set(dataIndexI, sharesArray);
                    userBalance += (sharesSold * stock.getCurrentPrice());
                    longSaveButtonPanel.setVisible(true);
                    testLabel.setText("Successfully sold stock");
                }
                else if (Integer.parseInt(sharesArray[2]) == sharesSold && sharesArray[3].equals("BUY")){
                    userDataMatrix.remove(dataIndexI);
                    longSaveButtonPanel.setVisible(true);
                    testLabel.setText("Successfully sold stock");
                }
                else {
                    testLabel.setText("you do not own that many shares");
                }
            }
            else {
                testLabel.setText("You dont own this stock");
            }
        } else if ("short".equals(e.getActionCommand())) {
            containsValue = false;
            for (int i = 0; i < userDataMatrix.size(); i++) {
                String[] stringArray = userDataMatrix.get(i);

                for (int j = 0; j < stringArray.length; j++) {
                    if (stringArray[j].equals(stock.getTickerSymbol())){
                        containsValue = true;
                        break;
                    }
                }
            }
            if (containsValue) {
                testLabel.setText("You already long/short this stock");
            }
            else {
                userDataMatrix.add(new String[]{
                        stock.getTickerSymbol(),
                        stock.getCurrentPriceStr(),
                        numberOfSharesTextField.getText(),
                        "SHORT"});
                userBalance -= (Integer.parseInt(numberOfSharesTextField.getText()) * stock.getCurrentPrice());
                testLabel.setText("Successfully shorted stock");
                longSaveButtonPanel.setVisible(true);
            }
        } else if ("cover".equals(e.getActionCommand())) {
            containsValue = false;
            for (int i = 0; i < userDataMatrix.size(); i++) {
                String[] stringArray = userDataMatrix.get(i);
                dataIndexI = i;
                for (int j = 0; j < stringArray.length; j++) {
                    if (stringArray[j].equals(stock.getTickerSymbol())){
                        containsValue = true;
                        break;
                    }
                }
            }
            if (containsValue) {
                sharesSold = Integer.parseInt(numberOfSharesTextField.getText());
                sharesArray = userDataMatrix.get(dataIndexI);
                for (int i = 0; i < sharesArray.length; i++) {
                    System.out.println(sharesArray[i]);
                }
                if (Integer.parseInt(sharesArray[2]) > sharesSold && sharesArray[3].equals("SHORT")){
                    sharesArray[2] = Integer.toString(Integer.parseInt(sharesArray[2]) - sharesSold);
                    userDataMatrix.set(dataIndexI, sharesArray);
                    userBalance += (sharesSold * (Integer.parseInt(sharesArray[1]) - stock.getCurrentPrice()));
                    longSaveButtonPanel.setVisible(true);
                    testLabel.setText("Successfully covered stock");
                }
                else if (Integer.parseInt(sharesArray[2]) == sharesSold && sharesArray[3].equals("SHORT")){
                    userDataMatrix.remove(dataIndexI);
                    longSaveButtonPanel.setVisible(true);
                    testLabel.setText("Successfully covered stock");
                }
                else {
                    testLabel.setText("you do not short that many shares");
                }
            }
            else {
                testLabel.setText("You dont short this stock");
            }
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