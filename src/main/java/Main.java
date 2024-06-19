package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //public 'ArrayList' matrix to store user's info
    public static ArrayList<String> usernames = new ArrayList<>();
    public static ArrayList<String> passwords = new ArrayList<>();
    public static ArrayList<String[]> userDataMatrix = new ArrayList<>();
    public static Double userBalance = 0.00;
    public static ArrayList<String> stockList = new ArrayList<>();
    public static String userDataFileLocation;

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
     * Description: Will take a filename and read data to array
     * @param fileName: desired file name to be read
     * @throws IOException: if the file is not found (it will always be found, but we need to throw for program to run)
     */
    public static void readUserData(String fileName) throws IOException{

        //initializing objects
        File myFile = new File(fileName);
        Scanner readFile = new Scanner(myFile);

        //getting user balance which is the first line
        //userBalance = Double.parseDouble(readFile.nextLine());

        //reading the rest of the file
        while (readFile.hasNext()){

            String line = readFile.nextLine();

            String[] tokens = line.split(", ");

            userDataMatrix.add(tokens);
        }
    }
    /**
     * Description: Will print contents of array to given file
     * @param fileName: file that will be written to
     * @throws IOException: bro it is an IOException
     */
    public static void printFileData(String fileName) throws IOException {

        //initializing objects
        FileWriter fWrite = new FileWriter(fileName, false);
        PrintWriter outFile = new PrintWriter(fWrite);

        //printing back to file:
        for (int i = 0; i < userDataMatrix.size(); i++) {
            for (int j = 0; j < userDataMatrix.get(i).length; j++) {

                if (j==3){ //this prevents printing placing an extra comma at the end of the line
                    outFile.print(userDataMatrix.get(i)[j]);
                    break;
                }
                else
                    outFile.print(userDataMatrix.get(i)[j] + ", ");
            }
            outFile.println();
        }

        outFile.close();
    }

    public static void getTickerSymbols() throws IOException {

        File myFile = new File("tickerSymbolsOnly.txt");
        Scanner readFile = new Scanner(myFile);

        while (readFile.hasNext()){
            String line = readFile.nextLine();
            stockList.add(line);
        }
    }
}