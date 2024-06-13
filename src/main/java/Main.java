package main.java;

import java.io.*;
import java.util.*;

public class Main {

    //public 'ArrayList' matrix to store user's info
    public static ArrayList<ArrayList<String>> userDataMatrix = new ArrayList<>();
    public static ArrayList<String> usernames = new ArrayList<>();
    public static ArrayList<String> passwords = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {

//        Stock tesla = new Stock("TSLA");

//        System.out.println(tesla.getCompanyName());

        new LogInPage();
        new MainAppFrame();

//        //initializing the main frame
//        MainAppFrame mainAppFrame = new MainAppFrame();
//
//        //sending user data to the frame to be displayed
//        mainAppFrame.getPortfolioPanel().setUserData(sendDataForPortfolioPanel(userDataMatrix));
    }
    public static void getLoginInfo() throws IOException {
        File myFile = new File("users.txt");
        Scanner readFile = new Scanner(myFile);

        while (readFile.hasNext()){
            String line = readFile.nextLine();
            String[] tokens = line.split("  ");

            usernames.add(tokens[0]);
            passwords.add(tokens[1]);
        }
    }

//    /**
//     * Description: will take
//     * @param stringMatrix
//     */
//    public static ArrayList<ArrayList<JLabel>> sendDataForPortfolioPanel(ArrayList<ArrayList<String>> stringMatrix) {
//
//        ArrayList<ArrayList<JLabel>> labelsMatrix = new ArrayList<>();
//
//        for (int i = 0; i < stringMatrix.size(); i++) {
//
//            labelsMatrix.add(new ArrayList<>());
//
//            for (int j = 0; j < stringMatrix.get(i).size(); j++) {
//
//                labelsMatrix.get(i).add(new JLabel(stringMatrix.get(i).get(j)));
//            }
//        }
//        return labelsMatrix;
//    }
}