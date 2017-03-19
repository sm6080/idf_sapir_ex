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


    public static final String EXIT = "exit";
    // private Input input;

    Output output;
    Input input;

    public Menu() {
        output = new Output();
        input = new Input();
    }

    public Menu(Output output, Input input) {
        this.output = output;
        this.input = input;
    }

    public void menu() {
        String userInput;
        int state = 1;
        output.output("please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");
        while (!(userInput = input.input()).equals("exit")) {
            switch (state) {
                case 1:
                    if (!programMenu(userInput).equals("")) {
                        state = 2;
                        output.output("enter path");
                    } else
                        output.output("the choice is not valid,please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");
                    break;
                case 2:
                    if (getPath(userInput)) {
                        state = 1;
                        operationChoice(userInput);
                        output.output("please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");
                    } else
                        output.output("the path is wrong, please enter path again ");
                    break;
            }
        }
    }

    private void operationChoice(String userInput) {
        if (userInput.equals("decryption")) {
            Decryption decryption = new Decryption();
            decryption.operation();
        } else {
            Encryption encryption = new Encryption();
            encryption.operation();
        }
    }

    String programMenu(String choice) {
        String outPut = "";
        switch (choice) {
            case "1":
                outPut = "decryption";
                break;
            case "2":
                outPut = "encryption";
                break;
        }
        return outPut;
    }

    boolean getPath(String path) {
        File file = new File(path);
        //if (file.exists() && file.isFile())
            return (file.exists() && file.isFile());
        //return false;
    }
}