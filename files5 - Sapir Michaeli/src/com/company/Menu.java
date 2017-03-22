package com.company;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.File;
import java.util.Random;

/**
 * Created by Sapir Michaeli on 16.03.2017.
 */

enum State {
    GET_OPERATION, GET_PATH, GET_KEY, GET_ALGORITHM
}

public class Menu implements BeginEndListener , BadKeyException.BadKeyListener{

    public static final String EXIT = "exit";
    public static final String ENCRYPTION = "1";
    public static final String DECRYPTION = "2";
    public static final String MAIN = "please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program";
    public static final String ALGORITHM = "please choose the algorithm:\n 1. CAESAR\n 2. XOR\n 3. MULTIPLICATION\n 4. REVERSE\n";
    public static final String ENTER = "please enter again";


    private Output output;
    private Input input;
    private  String userInput;
    private State state =State.GET_OPERATION;
    private Integer key=0;
    private  File file=null;
    private String operation="";
    private Encryption encryption=null;

    public Menu() {
        output = new Output();
        input = new Input();
    }

    public Menu(Output output, Input input) {
        this.output = output;
        this.input = input;
    }

    public void getMenu() {
        boolean wasReverse=false;
        output.getOutput(MAIN);
        while (!(userInput = input.getInput()).equals(EXIT)) {
            switch (state) {
                case GET_OPERATION:
                    operation = getOperation();
                    break;
                case GET_PATH:
                    getPathFunction();
                    break;
                case GET_ALGORITHM:
                    getAlgorithm(wasReverse);
                    break;
                case GET_KEY:
                    try {
                        getKeyFromUser();
                        state = State.GET_OPERATION;
                        operationChoice(operation, key, file, encryption);
                        output.getOutput(MAIN);
                    } catch (BadKeyException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    private void getKeyFromUser() throws BadKeyException {
        key = Integer.valueOf(userInput);
        if(key==null)
            throw new BadKeyException("error key "+ENTER);
    }

    private void getAlgorithm(boolean wasReverse) {
        EncryptionType type = EncryptionType.getType(userInput);
        if (type != EncryptionType.REVERSE) {
            if(type!=null) {
                if (wasReverse) {
                    encryption = EncryptionFactory.reverseEncryption(type);
                    wasReverse = false;
                } else
                    encryption = EncryptionFactory.getEncryption(type);
                state = ifKeyNeeded();
            }
            else
                output.getOutput("error input algorithm "+ENTER);
        }
        else{
            wasReverse = true;
            output.getOutput(ALGORITHM);
        }
    }

    private State ifKeyNeeded() {
        if(operation.equals(DECRYPTION)) {
            output.getOutput("enter key");
            return State.GET_KEY;
        }
        getRandomKey();
        operationChoice(operation, key, file,encryption);
        output.getOutput(MAIN);
        return State.GET_OPERATION;
    }

    private void getPathFunction() {
        if ((file = getPath(userInput)) != null) {
            output.getOutput(ALGORITHM);
            state=State.GET_ALGORITHM;
        }
        else
            output.getOutput("the path is wrong,"+ENTER);
    }

    private String getOperation() {
        if (userInput.equals(DECRYPTION) || userInput.equals(ENCRYPTION)) {
            state = State.GET_PATH;
            output.getOutput("enter path");
            return userInput;
        } else
            output.getOutput("the choice is not valid,"+MAIN);
        return "";
    }

    private void getRandomKey() {
        Key randomKey=new Key();
        key = randomKey.getKey();
        output.getOutput("your key:"+key);
        if(key%2==0&&operation.equals(EncryptionType.MULTIPLICATION)&&key==0)
            key++;
    }

    private void operationChoice(String userInput,Integer key,File file,Encryption encryptionAlgorithm) {
        encryptionAlgorithm.setBeginEndListener(this);
        if (userInput.equals(DECRYPTION)) {
            encryptionAlgorithm.decrypt(key,file);
        } else {
            encryptionAlgorithm.encrypt(key,file);
        }
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