package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Sapir Michaeli on 16.03.2017.
 */
public class Menu {


    public static final String EXIT = "exit";
    // private Input input;

    Output output;
    Input input;
    Random random=new Random(System.currentTimeMillis());

    public Menu() {
        output=new Output();
        input=new Input();
    }

    public Menu(Output output, Input input) {
        this.output = output;
        this.input = input;
    }

    public void menu(){
        String userInput, operation="";
        int state=1;
        File file = null;
        output.output("please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");
        while (!(userInput=input.input()).equals("exit")){
            switch (state){
                case 1:
                    if(!(operation=programMenu(userInput)).equals("")) {
                        state = 2;
                        output.output("enter path");
                    }
                    else
                        output.output("the choice is not valid,please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");
                    break;
                case 2:
                    if((file=getPath(userInput))!=null)
                    {
                        if (operation.equals("decryption")) {
                            state = 3;
                            output.output("please enter a key");
                        }else {
                            state = 1;
                            int x = random.nextInt();
                            output.output("your key : " + x);
                            operationChoice(operation, x, file);
                            output.output("please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");
                        }
                    }
                    else
                        output.output("the path is wrong, please enter path again ");
                    break;
                case 3:
                    int y=Integer.valueOf(userInput);
                    operationChoice (operation,y, file);
                    state=1;
                    output.output("please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");
                    break;
            }
        }
    }

    private void operationChoice(String userInput, Integer key, File file) {
        Caesar caesar=new Caesar();
        if(userInput.equals("encryption")) {
            caesar.encrypt(key,file);
        }
        else {
            caesar.decrypt(key,file);
        }
    }
    String programMenu(String choice){
        String outPut="";
        switch (choice) {
            case "1":
                outPut= "encryption";
                break;
            case "2":
                outPut= "decryption";
                break;
        }
        return outPut;
    }

    File getPath(String path) {
        File file = new File(path);
        if (file.exists()&&file.isFile())
            return file;
        return null;
    }


}