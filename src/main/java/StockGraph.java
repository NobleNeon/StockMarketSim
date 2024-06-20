package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static main.java.TradePanel.closingPricesList;
import static main.java.TradePanel.timestampsList;

public class StockGraph extends JPanel {

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        int numPoints = timestampsList.size();
        int xMargin = 50;
        int yMargin = 50;
        int xRange = getWidth() - 2 * xMargin;
        int yRange = getHeight() - 2 * yMargin;

        // Draw axes
        g2d.drawLine(xMargin, yMargin, xMargin, getHeight() - yMargin);
        g2d.drawLine(xMargin, getHeight() - yMargin, getWidth() - xMargin, getHeight() - yMargin);


        ArrayList<Double> tempClosingPricesList = new ArrayList<>(closingPricesList);

        Collections.sort(tempClosingPricesList);

        double maxPrice = tempClosingPricesList.get(tempClosingPricesList.size() - 1);
        double minPrice = tempClosingPricesList.get(0);

        // Plot data points

        ArrayList<Integer> xPoints = new ArrayList<>();
        ArrayList<Integer> yPoints = new ArrayList<>();

        for (int i = 0; i < numPoints; i++) {
            double price = closingPricesList.get(i);

            int x = xMargin + (int) (xRange * (i / (double) (numPoints - 1)));
            int y = (int) (getHeight() - yMargin - yRange * ((price - minPrice) / (maxPrice - minPrice)));
            xPoints.add(x);
            yPoints.add(y);

            g2d.setColor(Color.BLUE);
            g2d.fillOval(x - 2, y - 2, 4, 4); // Draw a small circle for each data point
        }

        for (int i = 0; i < xPoints.size() - 1; i++) {
            g2d.drawLine(xPoints.get(i), yPoints.get(i), xPoints.get(i + 1), yPoints.get(i + 1));
        }
    }
}