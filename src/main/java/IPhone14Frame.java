import javax.swing.*;
import java.awt.*;

public class IPhone14Frame {
    public IPhone14Frame() {
        JFrame frame = new JFrame("Stock Market Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(390, 844); // iPhone 14 screen size
        frame.getContentPane().setBackground(new Color(51, 51, 51)); // Warm black color

        // Create a text panel at the top
        JPanel textPanel = new JPanel();
        textPanel.setBackground(new Color(51, 51, 51));
        textPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 4));
        JLabel label = new JLabel("Stock Market Simulator");
        label.setForeground(Color.WHITE);
        textPanel.add(label);
        frame.add(textPanel, BorderLayout.NORTH);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(51, 51, 51));
        buttonPanel.setLayout(new GridLayout(4, 2, 5, 5)); // Add space between buttons

        // Create 8 rectangular buttons with modifiable functions
        JButton[] buttons = new JButton[8];
        String[] options = new String[8];
        options[0] = "Buy";
        options[1] = "Sell";
        options[2] = "Short";
        options[3] = "Cover";
        options[4] = "Call";
        options[5] = "Put";
        options[6] = "News";
        options[7] = "Exit";
        for (int i = 0; i < 8; i++) {
            buttons[i] = new JButton();
            buttons[i].setText(options[i]);
            buttons[i].setHorizontalAlignment(SwingConstants.CENTER);
            buttons[i].setPreferredSize(new Dimension(frame.getWidth() / 16, frame.getHeight() / 64));
            buttons[i].setBackground(new Color(102, 102, 102)); // Darker warm black color
            buttons[i].setForeground(Color.WHITE);
            buttonPanel.add(buttons[i]);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}