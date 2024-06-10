package main.java;

import javax.swing.*;
import java.awt.*;

public class TradePanel extends JPanel {


    //lable for just displaing (delete this later)
    JLabel tradePanelLabel;

    TradePanel(){

        //making label
        tradePanelLabel = new JLabel();
        tradePanelLabel.setText("TRADE");

        //changing panel settings
        this.setLayout(new BorderLayout());
        this.add(tradePanelLabel, BorderLayout.CENTER);
        this.setBounds(0, 0, 600, 600);
    }
}
