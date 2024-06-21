package main.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.HashMap;
public class Stock {

    DecimalFormat df = new DecimalFormat("$#.##");

    public Stock () {

    }

    public Stock(String tickerSymbol) throws IOException, InterruptedException {

        // For Prettifying - not my code, from Rapid API's article on how to parse through their outputs
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // getting symbol and stock price from one of the modules of the API
        String symbolAndStockPrice = gson.toJson(JsonParser.parseString(pullRequest(tickerSymbol, "STOCKS")));


        // We only need the following information from the JSON String we receive from the API
        requiredInfo.put("symbol", JsonPath.read(symbolAndStockPrice, "body.symbol"));
        requiredInfo.put("companyName", JsonPath.read(symbolAndStockPrice, "body.companyName"));
        requiredInfo.put("currentPrice", JsonPath.read(symbolAndStockPrice, "body.primaryData.lastSalePrice"));

    }

    private final HashMap<String, String> requiredInfo = new HashMap<>();

    public String getTickerSymbol() {
        return requiredInfo.get("symbol");
    }

    public String getCompanyName() {
        return requiredInfo.get("companyName");
    }

    public double getCurrentPrice() {
        return Double.parseDouble(getCurrentPriceStr().substring(1));

    }

    public String getCurrentPriceStr() {
        return df.format(Double.parseDouble(requiredInfo.get("currentPrice").substring(1)));
    }

    public static String pullRequest(String tickerSymbol, String type) throws IOException, InterruptedException {

        // code for pulling HTTP requests is not mine, but is necessary to receive information from API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://yahoo-finance15.p.rapidapi.com/api/v1/markets/quote?ticker="
                        + tickerSymbol
                        + "&type=" + type))
                .header("X-RapidAPI-Key", "06d015991bmshb27e5f7157037a7p143f8fjsn59dc9cb0648e")
                .header("X-RapidAPI-Host", "yahoo-finance15.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public double buyStock(int shares){
        return getCurrentPrice() * shares;
    }

    public double sellStock(int shares){
        return getCurrentPrice() * shares;
    }

    public double shortStock(int shares){
        return getCurrentPrice() * shares;
    }

    public double cover(int shares){
        return getCurrentPrice() * shares;
    }
}