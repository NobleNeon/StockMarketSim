/**
 * Program: Aura Traders
 * Programmer: Name: Sharayh Badar, Amir Nafissi, Broden Young
 * Date: June 20, 2024
 * Description: A application that allows users to get introduced to the stock market by trading with digital money
 */
package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //public variables to store user data throughout the program
    public static ArrayList<String> usernames = new ArrayList<>();
    public static ArrayList<String> passwords = new ArrayList<>();
    public static ArrayList<String[]> userDataMatrix = new ArrayList<>(); //contains user's portfolio
    public static Double userBalance = 0.00; //contains users balance
    public static ArrayList<String> stockList = new ArrayList<>();
    public static String userDataFileLocation; //contains user's file location

    public static void main(String[] args) throws IOException, InterruptedException {

        getTickerSymbols();

        LogInPage userLogin = new LogInPage();

        //only open the Main App Frame once the user has successfully logged in
        while (userLogin.getFrame().isVisible()) {
            Thread.sleep(1000);
        }

        //saving user's file location:
        userDataFileLocation = userLogin.getFileLocation();

        //getting user's data from their file:
        readUserData(userDataFileLocation);

        //initializing the main frame
        MainAppFrame mainAppFrame = new MainAppFrame();
    }

    /**
     * Name: readUserData
     * Description: Will take a filename and read data to 'userDataMatrix' and will also handle user's balance
     * @param fileName: file name to be read
     * @throws IOException: handles IO exception if file is not found
     */
    public static void readUserData(String fileName) throws IOException{

        //initializing necessary objects
        File myFile = new File(fileName);
        Scanner readFile = new Scanner(myFile);

        /*
        checking if user already has a balance, if true will update 'userBalance' variable
        if false (as is the case with new users) will add $10,000 to balance by default
         */
        if (readFile.hasNext())
            userBalance = Double.parseDouble(readFile.nextLine());
        else
            userBalance = 100000.0;

        //reading the rest of the file
        while (readFile.hasNext()){

            String line = readFile.nextLine();

            String[] tokens = line.split(", ");

            userDataMatrix.add(tokens); //adding data to 'userDataMatrix'
        }
    }
    /**
     * Name: printFileData
     * Description: Will print user's data back to their respected file
     * @param fileName: file that will be written to
     * @throws IOException: handles IO exception if file is not found
     */
    public static void printFileData(String fileName) throws IOException {

        //initializing objects
        FileWriter fWrite = new FileWriter(fileName, false);
        PrintWriter outFile = new PrintWriter(fWrite);

        //printing user's balance first:
        outFile.println(userBalance);

        //printing user's portfolio:
        for (int i = 0; i < userDataMatrix.size(); i++) {
            for (int j = 0; j < userDataMatrix.get(i).length; j++) {

                if (j==3){ //this prevents placing an extra comma at the end of the line
                    outFile.print(userDataMatrix.get(i)[j]);
                    break;
                }
                else
                    outFile.print(userDataMatrix.get(i)[j] + ", ");
            }
            outFile.println(); // for printing new line
        }

        outFile.close();
    }

    /**
     *
     * @throws IOException
     */
    public static void getTickerSymbols() throws IOException {

        File myFile = new File("tickerSymbolsOnly.txt");
        Scanner readFile = new Scanner(myFile);

        while (readFile.hasNext()){
            String line = readFile.nextLine();
            stockList.add(line);
        }
    }
}