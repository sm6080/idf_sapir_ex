package com.company;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.File;
import java.util.Random;

/**
 * Created by Sapir Michaeli on 16.03.2017.
 */

enum State {
    GET_OPERATION, GET_PATH, GET_KEY, GET_ENC_ALGORITHM
}

public class Menu implements EncryptionAlgorithms.BeginEndListener{

    public static final String EXIT = "exit";
    public static final String ENCRYPTION = "1";
    public static final String DECRYPTION = "2";


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

    public void getMenu() {
        String userInput, operation = "";
        State state = State.GET_OPERATION;
        File file = null;
        int key=0;
        boolean lastWasReverse=false;
        output.getOutput("please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");
        while (!(userInput = input.getInput()).equals(EXIT)) {
            switch (state) {
                case GET_OPERATION:
                    if(userInput.equals(DECRYPTION)|| userInput.equals(ENCRYPTION)) {
                        state = State.GET_PATH;
                        output.getOutput("enter path");
                        operation=userInput;
                    }
                    else
                        output.getOutput("the choice is not valid,please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");
                    break;
                case GET_PATH:
                    if ((file = getPath(userInput)) != null) {
                        if (operation.equals(DECRYPTION)) {
                            state = State.GET_KEY;
                            output.getOutput("please enter a key");
                        } else {
                            state = State.GET_ENC_ALGORITHM;
                            key=getKey();
                            //operationChoice(operation, getKey(), file);
                            output.getOutput("please choose the algorithm:\n 1. CAESAR\n 2. XOR\n 3. MULTIPLICATION\n 4. REVERSE\n ");
                        }
                    } else
                        output.getOutput("the path is wrong, please enter path again");
                    break;
                case GET_KEY:
                     key = Integer.valueOf(userInput);
                    //operationChoice(operation,key , file);
                    state = State.GET_ENC_ALGORITHM;
                    output.getOutput("please choose the algorithm:\n 1. CAESAR\n 2. XOR\n 3. MULTIPLICATION\n 4. REVERSE\n ");
                    break;
                case GET_ENC_ALGORITHM:
                    EncryptionType encryptionType=EncryptionType.getType(userInput);
                    Encryption encryption;
                    if (encryptionType!=EncryptionType.REVERSE) {
                        if (lastWasReverse){
                            encryption=EncryptionFactory.reverseEncryption(encryptionType);
                            lastWasReverse=false;
                        }
                        else
                            encryption=EncryptionFactory.getEncryption(encryptionType);
                        operationChoice(operation,key , file);
                        state = State.GET_OPERATION;
                        output.getOutput("please choose :\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");
                    }
                    else {
                        lastWasReverse = true;
                        output.getOutput("please choose the algorithm:\n 1. CAESAR\n 2. XOR\n 3. MULTIPLICATION\n 4. REVERSE\n ");
                    }
            }
        }
    }

    private Integer getKey() {
        Key key=new Key();
        int keyInt=key.getKey();
        output.getOutput("your key : " + keyInt);
        return keyInt;
    }

    private void operationChoice(String userInput, Integer key, File file) {
        Caesar caesar = new Caesar();
        if (userInput.equals(ENCRYPTION)) {
            caesar.encrypt(key, file);
        } else {
            caesar.decrypt(key, file);
        }
    }

    String programMenu(String choice) {
        String outPut = "";
        switch (choice) {
            case ENCRYPTION:
                outPut = "encryption";
                break;
            case DECRYPTION:
                outPut = "decryption";
                break;
        }
        return outPut;
    }

    File getPath(String path) {
        File file = new File(path);
        if (file.exists() && file.isFile())
            return file;
        return null;
    }


    @Override
    public void start() {
        output.getOutput("start");
    }

    @Override
    public void finish() {
        output.getOutput("finish");
    }
}