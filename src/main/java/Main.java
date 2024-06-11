package main.java;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main{

    //public 'ArrayList' matrix to store user's info
    public static ArrayList<ArrayList<String>> userDataMatrix = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {

//        Stock tesla = new Stock("TSLA");

//        System.out.println(tesla.getCompanyName());

        System.out.println("BIDIBIDIB TOILET");
        new LogInPage();

        //initializing the main frame
        MainAppFrame mainAppFrame = new MainAppFrame();

        //sending user data to the frame to be displayed
        mainAppFrame.getPortfolioPanel().setUserData(convertStringMatrixToJLabel(userDataMatrix));

    }

    /**
     * Name: convertStringMatrixToJLabel
     * Description: will take blablabl;lablablabla
     * @param stringMatrix: blablablablabl
     */
    public static ArrayList<ArrayList<JLabel>> convertStringMatrixToJLabel(ArrayList<ArrayList<String>> stringMatrix){

        ArrayList<ArrayList<JLabel>> labelsMatrix = new ArrayList<>();

        for (int i = 0; i < stringMatrix.size(); i++) {

            labelsMatrix.add(new ArrayList<>());

            for (int j = 0; j < stringMatrix.get(i).size(); j++) {

                labelsMatrix.get(i).add(new JLabel(stringMatrix.get(i).get(j)));
            }
        }
        return labelsMatrix;
    }


}