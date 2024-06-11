package main.java;

import javax.swing.*;
import java.awt.*;

public class TradePanel extends JPanel {


    TradePanel(){
        // buttons for the different things you can do with the stock options
        JButton buyButton = new JButton("BUY");
        JButton sellButton = new JButton("SELL");
        JButton shortButton = new JButton("SHORT");
        JButton coverButton = new JButton("COVER");

        // panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,0));
        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);
        buttonPanel.add(shortButton);
        buttonPanel.add(coverButton);

        //making labels and text fields
        JLabel tradePanelLabel = new JLabel("TRADE");
        JLabel tickerSymbolLabel = new JLabel("TICKER SYMBOL");
        JTextField tickerSymbolTextField = new JTextField(15);

        JPanel textFieldAndLabelPanel = new JPanel();
        textFieldAndLabelPanel.setLayout(new GridLayout(3,0));
        textFieldAndLabelPanel.add(tradePanelLabel);
        textFieldAndLabelPanel.add(tickerSymbolLabel);
        textFieldAndLabelPanel.add(tickerSymbolTextField);

        JTextField tickerSymbol = new JTextField("TICKER SYMBOL");


        this.setLayout(new GridLayout(3,0));
        this.add(textFieldAndLabelPanel, BorderLayout.WEST);
        this.add(buttonPanel, BorderLayout.WEST);
        this.setBounds(0, 0, 600, 600);
    }
}
