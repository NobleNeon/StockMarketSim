package main.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StockGraph extends JPanel{

    // pre-initialized variables
    public static java.util.List<Long> timestampsList = new ArrayList<>();
    public static List<Double> closingPricesList = new ArrayList<>();

    private String tickerSymbol;

    public StockGraph(String tickerSymbol, String startDay) throws IOException {
        this.tickerSymbol = tickerSymbol;

        // Parse through the parameter to figure out the date one year ago
        String endDay = startDay.substring(0,3)
                        + (Integer.parseInt(String.valueOf(startDay.charAt(3))) - 1)
                        + startDay.substring(4);

        // Use the parameters to create a custom url for to get the HTTP response
        String apiURL = "https://api.polygon.io/v2/aggs/ticker/" +
                this.tickerSymbol +
                "/range/" +
                "1" +
                "/day/" +
                endDay + // yyyy-mm-dd
                "/" +
                startDay + // yyyy-mm-dd
                "?adjusted=true&sort=asc&limit=5000&apiKey=xWzhEHlJS0D6qsOoOi1_sBrcD_umz4Sj";

        // This is teh code to get the data from the URL
        URLConnection connection = new URL(apiURL).openConnection();

        InputStream inputStream = connection.getInputStream();

        String response = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        response = gson.toJson(JsonParser.parseString(response));

        JSONObject responseJSON = new JSONObject(response);

        org.json.JSONArray resultsArray = (org.json.JSONArray) responseJSON.get("results");

        for (int i = 0; i < resultsArray.length(); i++) {
            JSONObject resultObject = resultsArray.getJSONObject(i);
            long timestamp = resultObject.getLong("t");
            timestampsList.add(timestamp);
            double closingPrice = resultObject.getDouble("c");
            closingPricesList.add(closingPrice);
        }

        this.setSize(500,500);

        // Create a BufferedImage with the same size as the TradePanel
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Create a Graphics2D object from the BufferedImage
        Graphics2D graphics = image.createGraphics();

        // Call the paint() method of the TradePanel with the Graphics2D object as the argument
        this.paint(graphics);

        // Dispose of the Graphics2D object
        graphics.dispose();

        // Create an ImageIcon from the BufferedImage
        ImageIcon screenshotIcon = new ImageIcon(image);

        // Add JLabel with the ImageIcon
        this.add(new JLabel(screenshotIcon));

    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = this.tickerSymbol;
    }

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