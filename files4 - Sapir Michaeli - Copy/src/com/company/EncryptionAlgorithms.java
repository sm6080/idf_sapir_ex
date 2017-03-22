package com.company;

import java.io.*;

/**
 * Created by Sapir Michaeli on 20.03.2017.
 */
public class EncryptionAlgorithms {

    private Integer decryptionKey = null;


    public void started(BeginEndListener beginEndListener){
            if (beginEndListener!=null)
                beginEndListener.start();
        }
    public void finished(BeginEndListener beginEndListener){
        if (beginEndListener!=null)
            beginEndListener.finish();
    }


    public void encyptOrDecrypt(int key, File file, boolean isChoiseEncrypt, EncryptionType encryptionType, BeginEndListener beginEndListener) {
        started( beginEndListener);
        String path = file.getPath();
        int x = path.lastIndexOf('.');
        if (isChoiseEncrypt) {    //if key encrypt else decrypt
            path = path.substring(0, x) + ".encrypted.txt";
        } else {
            path = path.substring(0, x) + "_decrypted.txt";
        }
        File encryptedFile = new File(path);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(encryptedFile);
            int actuallyRead = 0;
            byte currentByte;
            while ((actuallyRead = inputStream.read()) != -1) {
                if (isChoiseEncrypt) {
                    currentByte =doEncrypt(encryptionType,actuallyRead,key);
                } else
                    currentByte = doDecrypt(encryptionType,actuallyRead,key);
                outputStream.write(currentByte);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
                decryptMult(actuallyRead,key);
                int mult=decryptionKey;
                return (byte) mult;
            default:
                return 0;
        }
    }


    private void decryptMult(int actuallyRead,int key) {
        if (decryptionKey == null) {
            for (int i = 0; i < 256; i++) {
                if (((i * key) & 0x000000FF) == 1) {
                    decryptionKey = i;
                    break;
                }
            }
        }
    }


}