package main.java;


import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException, InterruptedException {

//        Stock tesla = new Stock("TSLA");

//        System.out.println(tesla.getCompanyName());
        new MainAppFrame();
        System.out.println("SKIBIDI TOILET");
        new LogInPage();
        txtReader();

    }

    public static void txtReader() throws IOException {
        File myFile = new File("user_name.txt");
        Scanner readFile = new Scanner(myFile);
        ArrayList<ArrayList<String>> userData = new ArrayList<ArrayList<String>>();

        while (readFile.hasNext()){
            String[] tokens = readFile.nextLine().split(", ");
            for (int i = 0; i < tokens.length; i++){
                userData.add(new ArrayList());
            }
            for (int i = 0; i < tokens.length; i++){
                userData.get(i).add(tokens[i]);
            }
            System.out.println(userData);
        }

    }
}