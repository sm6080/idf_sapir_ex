package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Sapir Michaeli on 28.02.2017.
 */
public class Menu {


    public static void programMenu() {
        System.out.println("Enter your choice : \n 1- for encryption \n 2- for decryption ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        switch (choice) {
            case "1":
                getPath();
                Encryption encryption = new Encryption();
                encryption.operation();
                break;
            case "2":
                getPath();
                Decryption decryption = new Decryption();
                decryption.operation();
                break;
            default:
                System.out.println("Wrong choice ");
                programMenu();
                break;
        }
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
