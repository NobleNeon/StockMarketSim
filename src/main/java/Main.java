package main.java;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{

    //public 'ArrayList' matrix to store user's info
    public static ArrayList<String[]> userDataMatrix = new ArrayList<>();
    public static ArrayList<String> usernames = new ArrayList<>();
    public static ArrayList<String> passwords = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {

//        Stock tesla = new Stock("TSLA");

//        System.out.println(tesla.getCompanyName());

        System.out.println("BIDIBIDIBI TOILET");
        new LogInPage();

        //loading user data
        //readUserData("user_name.txt");
        //TODO: how to get the correct file path for above^


        //initializing the main frame
        MainAppFrame mainAppFrame = new MainAppFrame();

        //sending user data to the portfolio panel to be displayed
        mainAppFrame.getPortfolioPanel().setUserData(convertStringMatrixToJLabel(userDataMatrix));

    }

    /**
     * Name: convertStringMatrixToJLabel
     * Description: will take blablabl;lablablabla
     * @param stringMatrix: blablablablabl
     */
    public static ArrayList<ArrayList<JLabel>> convertStringMatrixToJLabel(ArrayList<String[]> stringMatrix){

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
     * Name: readUserData
     * Description: blablablablaba
     * @param userNameFile: blablablablabl
     * @throws IOException
     */
    public static void readUserData(String userNameFile) throws IOException{

        File myFile = new File(userNameFile);
        Scanner readFile = new Scanner(myFile);

        while (readFile.hasNext()){
            String line = readFile.nextLine();

            String[] tokens = line.split(", ");

            userDataMatrix.add(tokens);
        }
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
}