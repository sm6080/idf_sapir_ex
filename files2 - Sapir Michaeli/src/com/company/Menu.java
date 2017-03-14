package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Sapir Michaeli on 28.02.2017.
 */
public class Menu {

     static String getInputFromUser(){
        System.out.println("Enter your choice : \n 1- for encryption \n 2- for decryption ");
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        try {
            bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "exit";
    }

    public static void menu(){
        String input;
        while ((input=getInputFromUser())!="exit"){
            programMenu(input);
        }
    }


    static String programMenu(String input) {
        String output=null;
        switch (input) {
            case "1":
                getPath();
                Encryption encryption = new Encryption();
                encryption.operation();
                return "Encryption";
            case "2":
                getPath();
                Decryption decryption = new Decryption();
                decryption.operation();
                return "Decryption";
            default:
                System.out.println("Wrong input ");
                menu();
                break;
        }
        return output;
    }


    // get path and check if exist and the path is correct
    private static void getPath() {
        System.out.println("Enter the absolute path ( Directory/File )"); // full path+ file name+ file type
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.next();
        File file = new File(filePath);
        if (file == null)
            getPath();

    }


}
