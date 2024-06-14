package main.java;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main {

    //public 'ArrayList' matrix to store user's info
    public static ArrayList<String> usernames = new ArrayList<>();
    public static ArrayList<String> passwords = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {

//        Stock tesla = new Stock("TSLA");
//        System.out.println(tesla.getCompanyName());

        LogInPage userLogin = new LogInPage();

        //only open the Main App Frame once the user has successfully logged in
        while (userLogin.getFrame().isVisible()) {
            Thread.sleep(1000);
        }

        //initializing the main frame
        MainAppFrame mainAppFrame = new MainAppFrame();

        //sending user data to the frame to be displayed
        mainAppFrame.getPortfolioPanel().setUserData(turnStringMatrixToJPanel(readUserData(userLogin.getFileLocation())));
        mainAppFrame.getPortfolioPanel().updateLayout();
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
     * @throws IOException: if the file is not found (it will always be found)
     */
    public static ArrayList<String[]> readUserData(String fileName) throws IOException{

        ArrayList<String[]> userDataMatrix = new ArrayList<>();

        File myFile = new File(fileName);
        Scanner readFile = new Scanner(myFile);

        while (readFile.hasNext()){

            userDataMatrix.add(new String[4]);

            String line = readFile.nextLine();

            String[] tokens = line.split(", ");

            userDataMatrix.add(tokens);
        }

        return userDataMatrix;
    }

}