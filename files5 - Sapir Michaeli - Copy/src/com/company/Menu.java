package com.company;

import encryptionAlgorithms.*;
import encryptionAlgorithms.Double;
import inputOutput.Input;
import inputOutput.Output;
import key.BadKeyException;
import key.RandomKey;

import java.io.*;

/**
 * Created by Sapir Michaeli on 16.03.2017.
 */

enum State {
    GET_OPERATION, GET_PATH, GET_KEY
}

public class Menu implements BeginEndListener , InputException.ExceptionListener{

    public static final String EXIT = "exit";
    public static final String ENCRYPTION = "1";
    public static final String DECRYPTION = "2";
    public static final String MAIN = "please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program";
    public static final String ENTET_KEY = "please enter key path";
    public static final String ENTER = "please enter again";

    private Output output;
    private Input input;
    private String userInput;
    private State state = State.GET_OPERATION;
    private Integer key = 0, key2 = 1;
    private File file = null;
    String operation="";

    public Menu() {
        output = new Output();
        input = new Input();
    }

    public Menu(Output output, Input input) {
        this.output = output;
        this.input = input;
    }

    public void getMenu() {
        output.print(MAIN);
        while (!(userInput = input.getInput()).equals(EXIT))
        {
            switch (state) {
                case GET_OPERATION:
                    operation = getOperation();
                    break;
                case GET_PATH:
                    getPathFunction(operation);
                    break;
                case GET_KEY:
                    try {
                        File keyFile= getKeyFileFromUser();
                        readKeyFromFile(keyFile);
                        encrypt();
                        state = State.GET_OPERATION;
                        output.print(MAIN);
                    } catch (InputException e) {
                        e.setExceptionListener(this);
                        e.getMessage();
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    private File getKeyFileFromUser() throws InputException {
        File keyFile= getPath(userInput);
        if (keyFile==null)
                throw new BadKeyException("error path"+ENTER);
        return keyFile;
    }

   /* private int getKeyFromUser() throws BadKeyException {
        Integer myKey = Integer.valueOf(userInput);
        if (myKey == null ||myKey==key)
            throw new BadKeyException("error key, "+ ENTER);
        return myKey;
    }*/

    private void getPathFunction(String operation) {
        if ((file = getPath(userInput)) != null) {
            if (operation.equals(DECRYPTION)) {
                state = State.GET_KEY;
                output.print(ENTET_KEY);
            } else {
                getKey();
                encrypt();
                state = State.GET_OPERATION;
                output.print(MAIN);
            }
        } else
            output.print("the path is wrong, "+ENTER);
    }
    private String getOperation() {
        if (userInput.equals(DECRYPTION) || userInput.equals(ENCRYPTION))
        {
            state = State.GET_PATH;
            output.print("enter path");
            return userInput;
        } else
            output.print("the choice is not valid, "+MAIN);
        return "";
    }

    private void getKey() {
        RandomKey keyC = new RandomKey();
        key= (Integer) keyC.getKey();
       /* if (key==0)
            key++;*/
        output.print("your first key:" + key);
        key2= (Integer) keyC.getKey();
        /*if (key==key2 ||key2==0)
            key2++;*/
        output.print("your second key :" + key2);
        writeKeyToFile();
    }

    /*private void writeKeyToFile() {
        File keyFile = createKeyFile();
        ObjectOutputStream objectOutputStream = null;
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(keyFile);
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(key);
            objectOutputStream.writeObject(key2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null)
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }*/

    private void writeKeyToFile()
    {
        try {
            new FileTrafficHandler(createKeyFile()).writeToFile(key, key2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readKeyFromFile(File keyFile) {
        InputStream inputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            inputStream = new FileInputStream(keyFile);
            objectInputStream = new ObjectInputStream(inputStream);
            key = (Integer) objectInputStream.readObject();
            key2 = (Integer) objectInputStream.readObject();
            System.out.println(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null)
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private void encrypt() {
        Encryption encryptionR = new Reverse(new Double<>(new Caesar(null), new Xor(null)));
        Encryption encryption = new Double<>(new Xor(this), encryptionR);
        Double d = (Double) encryption;
        d.setSecondKeyForEncryption(key2);
        if (operation.equals(DECRYPTION))
            encryption.decrypt(key, file, EncryptionAlgorithms.crateFileWithEnding(file,false ));
        else
            encryption.encrypt(key, file, EncryptionAlgorithms.crateFileWithEnding(file,true));
    }

    File getPath(String path) {
        File file = new File(path);
        if (file.exists() && file.isFile())
            return file;
        return null;
    }
    private File createKeyFile(){
        int lastDot = file.getPath().lastIndexOf('.');
        String newPath;
        newPath=file.getPath().substring(0, lastDot)+ ".enryptKey.bin";
        File newFile=new File(newPath);
        return newFile;
    }


    @Override
    public void start() {
        output.print("start");
    }

    @Override
    public void finish() {
        output.print("finish");
    }

    @Override
    public void exception(String exceptionMessage) {
        output.print(exceptionMessage);
    }
}