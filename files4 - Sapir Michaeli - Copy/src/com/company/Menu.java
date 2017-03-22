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

public class Menu implements BeginEndListener , BadKeyException.BadKeyListener{

    public static final String EXIT = "exit";
    public static final String ENCRYPTION = "1";
    public static final String DECRYPTION = "2";
    public static final String MAIN = "please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program";
    public static final String ALGORITHM = "please choose the algorithm:\n 1. CAESAR\n 2. XOR\n 3. MULTIPLICATION\n 4. REVERSE\n";
    public static final String ENTER = "please enter again";

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

    public void getMenu()   {
        String userInput, operation = "";
        State state = State.GET_OPERATION;
        File file = null;
        Integer key=0;
        boolean lastWasReverse=false;
        output.getOutput(MAIN);
        while (!(userInput = input.getInput()).equals(EXIT)) {
            switch (state) {
                case GET_OPERATION:
                    if (userInput.equals(DECRYPTION) || userInput.equals(ENCRYPTION)) {
                        state = State.GET_PATH;
                        output.getOutput("please enter path");
                        operation = userInput;
                    } else
                        output.getOutput("the choice is not valid,"+MAIN);
                    break;
                case GET_PATH:
                    if ((file = getPath(userInput)) != null) {
                        if (operation.equals(DECRYPTION)) {
                            state = State.GET_KEY;
                            output.getOutput("please enter a key");
                        } else {
                            state = State.GET_ENC_ALGORITHM;
                            key = getKey();
                            output.getOutput(ALGORITHM);
                        }
                    } else
                        output.getOutput("the path is wrong, "+ENTER);
                    break;
                case GET_KEY:
                    key = Integer.valueOf(userInput);
                    if (key == null)
                        try {
                            throw new BadKeyException("error, Please enter the key again");
                        } catch (BadKeyException e) {
                            e.printStackTrace();
                        }
                    state = State.GET_ENC_ALGORITHM;
                    output.getOutput(ALGORITHM);
                    break;
                case GET_ENC_ALGORITHM:
                    EncryptionType encryptionType = EncryptionType.getType(userInput);
                    Encryption encryption = null;
                    if (encryptionType != EncryptionType.REVERSE) {
                        if (encryptionType != null) {
                            if (lastWasReverse) {
                                encryption = EncryptionFactory.reverseEncryption(encryptionType);
                                lastWasReverse = false;
                            } else
                                encryption = EncryptionFactory.getEncryption(encryptionType);
                            operationChoice(operation, key, file, encryption);
                            state = State.GET_OPERATION;
                            output.getOutput(MAIN); //TODO:final String
                        } else {
                            output.getOutput("error! wrong algorithm, "+ENTER);
                        }
                    } else {
                        lastWasReverse = true;
                        output.getOutput(ALGORITHM);
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

    private void operationChoice(String userInput, Integer key, File file, Encryption encriptionAlgo) {
        encriptionAlgo.setBeginEndListener(this);
        if (userInput.equals(ENCRYPTION)) {
            encriptionAlgo.encrypt(key, file);
        } else {
            encriptionAlgo.decrypt(key, file);
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


    @Override
    public void exception(String exceptionMessage) {
        output.getOutput(exceptionMessage);
    }
}