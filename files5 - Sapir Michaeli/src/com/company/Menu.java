package com.company;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.File;
import java.util.Random;

/**
 * Created by Sapir Michaeli on 16.03.2017.
 */

enum State {
    GET_OPERATION, GET_PATH, GET_KEY, GET_KEY2
}

public class Menu implements BeginEndListener , BadKeyException.BadKeyListener{

    public static final String EXIT = "exit";
    public static final String ENCRYPTION = "1";
    public static final String DECRYPTION = "2";
    public static final String MAIN = "please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program";
    //public static final String ALGORITHM = "please choose the algorithm:\n 1. CAESAR\n 2. XOR\n 3. MULTIPLICATION\n 4. REVERSE\n";
    public static final String ENTER = "please enter again";


    private Output output;
    private Input input;
    private  String userInput;
    private State state =State.GET_OPERATION;
    private Integer key=0, key2=0;
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
                case GET_KEY:
                    try {
                        key=getKeyFromUser(false);
                    } catch (BadKeyException e) {
                        e.printStackTrace();
                    }
                    state=State.GET_KEY2;
                    output.getOutput("enter second key");
                    break;
                case GET_KEY2:
                    try {
                        key2=getKeyFromUser(true);
                    } catch (BadKeyException e) {
                        e.printStackTrace();
                    }
                    state=State.GET_OPERATION;
                    output.getOutput(MAIN);
                    break;
            }
        }
    }

    private Integer getKeyFromUser(boolean equalKey2) throws BadKeyException {
        key = Integer.valueOf(userInput);
        if(key==null && equalKey2 && key==key2)
            throw new BadKeyException("error key "+ENTER);
        return key;
    }

    private void ifKeyNeeded() {
        if(operation.equals(DECRYPTION)) {
            output.getOutput("enter first key");
            state= State.GET_KEY;
        }
        getRandomKey();
        encrypt();
        output.getOutput(MAIN);
        state= State.GET_OPERATION;
    }

    private void getPathFunction() {
        if ((file = getPath(userInput)) != null) {
           ifKeyNeeded();
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
        /*if(key%2==0&&operation.equals(EncryptionType.MULTIPLICATION)&&key==0)
            key++;*/
        key2 = randomKey.getKey();
        output.getOutput("your second key:"+key2);
    }

    File getPath(String path) {
        File file = new File(path);
        if (file.exists() && file.isFile())
            return file;
        return null;
    }

    private void encrypt(){
        Encryption reverse =new Reverse(new Double<Encryption,Encryption>( new Caesar(),new Multiplication(),key2));
        File encryptedFile=createFile();
        Encryption encryption =new Double<Encryption,Encryption>(new Xor(),reverse,key2);
        encOrDec(encryption,file,encryptedFile);
        //encOrDec(reverse,encryptedFile,encryptedFile);

    }

    private void encOrDec(Encryption encryption,File sourceFile, File desFile){
        if (operation.equals(DECRYPTION))
            encryption.decrypt(key,sourceFile,desFile);
        else
            encryption.encrypt(key,sourceFile,desFile);
    }

    private File createFile(){
        if (operation.equals(DECRYPTION)) {
            return EncryptionAlgorithms.crateFileWithEnding(file, false);
        }
        return EncryptionAlgorithms.crateFileWithEnding(file, true);
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