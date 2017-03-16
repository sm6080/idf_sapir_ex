package com.company;

import java.io.*;
import java.util.Random;

/**
 * Created by Sapir Michaeli on 16.03.2017.
 */
public class Caesar implements FileOperation ,Encryption, Decryption {
    @Override
    public void operation() {  // למה??????????????????
    }

    @Override
    public void encrypt( int key, File file) {
        encOrdec(key,file, false);
    }

    @Override
    public void decrypt(int key, File file) {
        encOrdec(key,file,true);
    }

    private void encOrdec(int key, File file, boolean isChoiseEncrypt) {
        String path = file.getPath();
        int x = path.lastIndexOf('.');

        if (isChoiseEncrypt) { //1 - encrypt   else decrypt
            path = path.substring(0, x) + ".encrypted";
        } else {
            path = path.substring(0, x) + "_decrypted";
        }

        File encryptedFile = new File(path);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(encryptedFile);
            int actuallyRead;
            byte b;
            while ((actuallyRead = inputStream.read()) != -1) {
                if (isChoiseEncrypt) {
                    b = (byte) (actuallyRead + key);
                } else
                    b = (byte) (actuallyRead - key);
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
        }
    }

}
