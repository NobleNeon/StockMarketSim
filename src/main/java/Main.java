package main.java;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //public 'ArrayList' matrix to store user's info
    public static ArrayList<String> usernames = new ArrayList<>();
    public static ArrayList<String> passwords = new ArrayList<>();
    public static ArrayList<String[]> userDataMatrix = new ArrayList<>();
    public static Double userBalance = 0.00;
    public static ArrayList<String> stockList = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {

//        Stock tesla = new Stock("TSLA");
//        System.out.println(tesla.getCompanyName());

        getTickerSymbols();

        LogInPage userLogin = new LogInPage();

        //only open the Main App Frame once the user has successfully logged in
        while (userLogin.getFrame().isVisible()) {
            Thread.sleep(1000);
        }

        //initializing the main frame
        MainAppFrame mainAppFrame = new MainAppFrame();

        readUserData(userLogin.getFileLocation());

        //sending user data to the frame to be displayed
        //mainAppFrame.getPortfolioPanel().setUserData(turnStringMatrixToJPanel(readUserData(userLogin.getFileLocation())));
        //mainAppFrame.getPortfolioPanel().updateLayout();
    }

    /**
     * Description: will take
     * @param stringMatrix
     * */
    public static ArrayList<ArrayList<JLabel>> turnStringMatrixToJPanel(ArrayList<String[]> stringMatrix) {

        ArrayList<ArrayList<JLabel>> labelsMatrix = new ArrayList<>();

        for (int i = 0; i < stringMatrix.size(); i++) {

            labelsMatrix.add(new ArrayList<>());

            for (int j = 0; j < stringMatrix.get(i).length; j++) {

                labelsMatrix.get(i).add(new JLabel(stringMatrix.get(i)[j]));
            }
        }
        return labelsMatrix;
    }

    /**
     * Description: Will take a filename and read data to array
     * @param fileName: desired file name to be read
     * @throws IOException: if the file is not found (it will always be found, but we need to throw for program to run)
     */
    public static ArrayList<String[]> readUserData(String fileName) throws IOException{

        File myFile = new File(fileName);
        Scanner readFile = new Scanner(myFile);

        String soak;

        while (readFile.hasNext()){
            soak = readFile.nextLine();
            String[] tokens = soak.split(", ");

            userDataMatrix.add(tokens);
        }


        return userDataMatrix;
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