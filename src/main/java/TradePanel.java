package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TradePanel extends JPanel implements ActionListener{

    private JLabel testLabel = new JLabel(); // TODO - temporary for testing (delete this later)


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


        JPanel textFieldAndLabelPanel = new JPanel();
        textFieldAndLabelPanel.setLayout(new GridLayout(2,2));
        textFieldAndLabelPanel.add(tickerSymbolLabel);
        textFieldAndLabelPanel.add(tickerSymbolTextField);
        textFieldAndLabelPanel.add(numberOfSharesLabel);
        textFieldAndLabelPanel.add(numberOfSharesTextField);

        this.setLayout(new GridLayout(3,0));
        this.add(textFieldAndLabelPanel, BorderLayout.WEST);
        this.add(buttonPanel, BorderLayout.WEST);
        this.add(testLabel);//TODO - temporary
        this.setBounds(0, 0, 600, 600);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("buy".equals(e.getActionCommand())) {
            testLabel.setText("BUYING");
        } else if ("sell".equals(e.getActionCommand())) {
            testLabel.setText("SELLING");
        } else if ("short".equals(e.getActionCommand())) {
            testLabel.setText("SHORT");
        } else if ("cover".equals(e.getActionCommand())) {
            testLabel.setText("COVER");
        }
    }
}
