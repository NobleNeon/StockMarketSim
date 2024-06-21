package main.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.PathNotFoundException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
    public static java.util.List<Long> timestampsList = new ArrayList<>();
    public static List<Double> closingPricesList = new ArrayList<>();

    TradePanel() throws IOException {

        getDataForGraph();

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

        // Panel specifically for the save button
        longSaveButtonPanel = new JPanel();
        longSaveButtonPanel.setLayout(new GridLayout(3,0));
        longSaveButtonPanel.add(saveDataButton);

        // TODO - please remove later! This needs to be added only once the ticker symbol is found


        JPanel beforeGraphPanel = new JPanel();
        beforeGraphPanel.setLayout(new GridLayout(6,0));
        beforeGraphPanel.add(textFieldAndLabelPanel, BorderLayout.WEST);
        beforeGraphPanel.add(buttonPanel, BorderLayout.WEST);

        this.setLayout(new GridLayout(9 ,0));
        this.add(textFieldAndLabelPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(longSaveButtonPanel, BorderLayout.SOUTH);
        buttonPanel.setVisible(false);
        this.add(new JLabel());
        this.add(new JLabel());
        longSaveButtonPanel.setVisible(true);
        this.add(testLabel, BorderLayout.SOUTH);


        this.setBounds(0, 0, 600, 600);
    }

    public static void getDataForGraph() throws IOException {
        String apiKey = "xWzhEHlJS0D6qsOoOi1_sBrcD_umz4Sj";

        //TODO - edit this better please
        String apiURL = "https://api.polygon.io/v2/aggs/ticker/" +
                "AAPL" + //
                "/range/" +
                "1" + //
                "/day/" +
                "2023-01-09" + //
                "/" +
                "2023-02-10" + //
                "?adjusted=" +
                "true" + //
                "&sort=" +
                "asc" + //
                "&limit=" +
                "5000" + //
                "&apiKey=" + apiKey;

        URLConnection connection = new URL(apiURL).openConnection();

        InputStream inputStream = connection.getInputStream();

        String response = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        response = gson.toJson(JsonParser.parseString(response));
        System.out.println(response);

        JSONObject responseJSON = new JSONObject(response);

        org.json.JSONArray resultsArray = (org.json.JSONArray) responseJSON.get("results");

        for (int i = 0; i < resultsArray.length(); i++) {
            JSONObject resultObject = resultsArray.getJSONObject(i);
            long timestamp = resultObject.getLong("t");
            timestampsList.add(timestamp);
            double closingPrice = resultObject.getDouble("c");
            closingPricesList.add(closingPrice);
        }

        System.out.println("Timestamps: " + timestampsList);
        System.out.println("Closing Prices: " + closingPricesList);
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

    /**
     *
     * @param e the event to be processed
     * @throws PathNotFoundException
     */
    @Override
    public void actionPerformed(ActionEvent e) throws PathNotFoundException {
        int symbolIndex = 0;

        // if statement for if search button is pressed
        if ("search".equals(e.getActionCommand())) {
            symbolIndex = binarySearch(tickerSymbolTextField.getText());

            if (symbolIndex == -1) {
                errorLabel.setForeground(Color.RED);
                errorLabel.setText("Ticker Symbol Not Found!");
            }
            else {
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
        }
        // if statement for when user presses buy button
        else if ("buy".equals(e.getActionCommand())) {

            containsValue = false;

            // for loop to separate userDataMatrix rows and search for a value
            for (int i = 0; i < userDataMatrix.size(); i++) {
                String[] stringArray = userDataMatrix.get(i);

                for (int j = 0; j < stringArray.length; j++) {
                    if (stringArray[j].equals(stock.getTickerSymbol())) {
                        containsValue = true;
                        break;
                    }
                }
            }
            if (containsValue) {
                testLabel.setText("You already long/short this stock");
            }
            /*
            checking if user has enough in their balance:
            the .replaceAll("[^\\d]", "") method is used to remove all non-numeric characters from the text field
            and is taken from https://stackoverflow.com/a/10372905
             */
            else if (userBalance < Integer.parseInt(numberOfSharesTextField.getText().replaceAll("[^\\d]", ""))
                    * stock.getCurrentPrice()) {
                testLabel.setText("Insufficient funds");
            }
            // else statement adds buy transaction to portfolio and adjusts user balance
            else {
                userDataMatrix.add(new String[]{
                        stock.getTickerSymbol(),
                        stock.getCurrentPriceStr(),
                        numberOfSharesTextField.getText().replaceAll("[^\\d]", ""),
                        "BUY"});
                userBalance -= (Integer.parseInt(numberOfSharesTextField.getText().replaceAll("[^\\d]", ""))
                        * stock.getCurrentPrice());
                testLabel.setText("Successfully bought stock");
                longSaveButtonPanel.setVisible(true);
            }
        }
        // if statement for when sell is pressed
        else if ("sell".equals(e.getActionCommand())) {
            //for statement to check if a value is in userDataMatrix
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
            // if it contains the ticker symbol
            if (containsValue) {

                sharesSold = Integer.parseInt(numberOfSharesTextField.getText().replaceAll("[^\\d]", ""));
                sharesArray = userDataMatrix.get(dataIndexI);
                // if statements to allow the user to sell their shares
                if (Integer.parseInt(sharesArray[2]) > sharesSold && sharesArray[3].equals("BUY")){
                    sharesArray[2] = Integer.toString(Integer.parseInt(sharesArray[2]) - sharesSold);
                    userDataMatrix.set(dataIndexI, sharesArray);
                    userBalance += (sharesSold * stock.getCurrentPrice());
                    longSaveButtonPanel.setVisible(true);
                    testLabel.setText("Successfully sold stock");
                }
                else if (Integer.parseInt(sharesArray[2]) == sharesSold && sharesArray[3].equals("BUY")){
                    userDataMatrix.remove(dataIndexI);
                    userBalance += (sharesSold * stock.getCurrentPrice());
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
        // if statement for if the user selects short button
        }
        else if ("short".equals(e.getActionCommand())) {
            containsValue = false;

            // checks if user data contains a value
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
            //checking if user has enough in their balance
            else if (userBalance < Integer.parseInt(numberOfSharesTextField.getText().replaceAll("[^\\d]", "")) * stock.getCurrentPrice()) {
                testLabel.setText("Insufficient funds");
            }
            // adds new user data to portfolio when user buys stock
            else {
                userDataMatrix.add(new String[]{
                        stock.getTickerSymbol(),
                        stock.getCurrentPriceStr(),
                        numberOfSharesTextField.getText().replaceAll("[^\\d]", ""),
                        "SHORT"});
                userBalance -= (Integer.parseInt(numberOfSharesTextField.getText().replaceAll("[^\\d]", ""))
                        * stock.getCurrentPrice());
                testLabel.setText("Successfully shorted stock");
                longSaveButtonPanel.setVisible(true);
            }
        }
        // if statement for when user presses cover button
        else if ("cover".equals(e.getActionCommand())) {
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
                sharesSold = Integer.parseInt(numberOfSharesTextField.getText().replaceAll("[^\\d]", ""));
                sharesArray = userDataMatrix.get(dataIndexI);

                // if statement that allows user to cover a stock
                if (Integer.parseInt(sharesArray[2]) > sharesSold && sharesArray[3].equals("SHORT")){
                    sharesArray[2] = Integer.toString(Integer.parseInt(sharesArray[2]) - sharesSold);
                    userDataMatrix.set(dataIndexI, sharesArray);
                    userBalance += (sharesSold * stock.getCurrentPrice() +
                            (sharesSold * (Double.parseDouble(sharesArray[1].substring(1)) -
                                    stock.getCurrentPrice())));
                    longSaveButtonPanel.setVisible(true);
                    testLabel.setText("Successfully covered stock");
                }
                else if (Integer.parseInt(sharesArray[2]) == sharesSold && sharesArray[3].equals("SHORT")){
                    userDataMatrix.remove(dataIndexI);
                    userBalance += (sharesSold * stock.getCurrentPrice() +
                            (sharesSold * (Double.parseDouble(sharesArray[1].substring(1)) -
                                    stock.getCurrentPrice())));
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

        // if statement for when user presses save button
        }
        else if ("save".equals(e.getActionCommand())) {

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