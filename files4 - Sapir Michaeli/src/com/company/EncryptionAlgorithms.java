package com.company;

import java.io.*;

/**
 * Created by Sapir Michaeli on 20.03.2017.
 */
public class EncryptionAlgorithms {

    static BeginEndListener beginEndListener;

    public void setBeginEndListener(BeginEndListener beginEndListener) {
        this.beginEndListener = beginEndListener;
    }

    public void started(){
            if (beginEndListener!=null)
                beginEndListener.start();
        }
    public void finished(){
        if (beginEndListener!=null)
            beginEndListener.finish();
    }


    public void encyptOrDecrypt(int key, File file, boolean isChoiseEncrypt, EncryptionType encryptionType) {
        started();
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
            byte b;
            while ((actuallyRead = inputStream.read()) != -1) {
                if (isChoiseEncrypt) {
                    b =doEncrypt(encryptionType,actuallyRead,key);
                } else
                    b = doDecrypt(encryptionType,actuallyRead,key);
                outputStream.write(b);
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
                finished();
        }
    }

    private byte doEncrypt(EncryptionType encryptionType ,int actuallyRead, int key){
        switch (encryptionType){
            case CAESAR:
               return  (byte) (actuallyRead + key);
            case XOR:
                return (byte) (actuallyRead ^ key);
            case MULTIPLICATION:
                return (byte) (actuallyRead * key);
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

            default:
                return 0;
        }
    }

    interface BeginEndListener{
        void start();
        void finish();
    }
}
