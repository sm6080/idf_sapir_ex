package com.company;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.*;

/**
 * Created by Sapir Michaeli on 20.03.2017.
 */
public class EncryptionAlgorithms {

    private Integer decKey = null;


    public void started(BeginEndListener beginEndListener){
            if (beginEndListener!=null)
                beginEndListener.start();
        }
    public void finished(BeginEndListener beginEndListener){
        if (beginEndListener!=null)
            beginEndListener.finish();
    }


    public void encyptOrDecrypt(int key, File file,File encOrDecFile, boolean isChoiseEncrypt, EncryptionType encryptionType, BeginEndListener beginEndListener) {
        started( beginEndListener);
       // File encOrDecFile=crateFileWithEnding(file, isChoiseEncrypt);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(encOrDecFile);
            int actuallyRead = 0;
            byte currentByte;
            while ((actuallyRead = inputStream.read()) != -1) {
                if (isChoiseEncrypt) {
                    currentByte =doEncrypt(encryptionType,actuallyRead,key);
                } else
                    currentByte = doDecrypt(encryptionType,actuallyRead,key);
                outputStream.write(currentByte);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finished( beginEndListener);
        }
    }

    private byte doEncrypt(EncryptionType encryptionType ,int actuallyRead, int key){
        switch (encryptionType){
            case CAESAR:
               return  (byte) (actuallyRead + key);
            case XOR:
                return (byte) (actuallyRead ^ key);
            case MULTIPLICATION:
                return  (byte)((actuallyRead * key) & 0x000000FF);
            default:
                return 0;
        }
    }

    private byte doDecrypt(EncryptionType decryptionType ,int actuallyRead, int key){
        switch (decryptionType){
            case CAESAR:
               return  (byte) (actuallyRead - key);
            case XOR:
                return (byte) (actuallyRead ^ key);
            case MULTIPLICATION:
                key=decryptMult(key);
                return (byte)(key*actuallyRead);
            default:
                return 0;
        }
    }


    private int decryptMult(int key){
        if (decKey ==null) {
            for (int i = 0; i < 256; i++) {
                if (((i * key) & 0x000000FF) == 1) {
                    decKey = i;
                    break;
                }
            }
        }
        return decKey;
    }

    public static File crateFileWithEnding(File file, boolean isChoiseEncrypt) {
        String path = file.getPath();
        int lastDot = path.lastIndexOf('.');
        if (isChoiseEncrypt) {    //if key encrypt else decrypt
            path = path.substring(0, lastDot) + ".encrypted.txt";
        } else {
            path = path.substring(0, lastDot) + "_decrypted.txt";
        }
        return new File(path);
    }


    public static File CreateFileWithEnding(boolean isChoiseEncrypt, File file)
    {
        String path = isChoiseEncrypt ? CreateEncryptFilePathWithEnding(file.getPath()) :
                                                    CreateDecryptFilePathWithEnding(file.getPath());

        return new File(path);
    }

    public static String CreateEncryptFilePathWithEnding(String filePath)
    {
        return filePath.substring(0, filePath.lastIndexOf('.')) + ".encrypted.txt";
    }

    public static String CreateDecryptFilePathWithEnding(String filePath)
    {
        return filePath.substring(0, filePath.lastIndexOf('.')) + "_decrypted.txt";
    }

     public static File createFileTemp(String path) {
        int dot = path.lastIndexOf('.');
        path = path.substring(0, dot) + ".tmp.txt";
        return  new File(path);
    }

}