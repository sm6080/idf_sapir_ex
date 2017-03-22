package com.company;

import java.io.*;

/**
 * Created by Sapir Michaeli on 20.03.2017.
 */
public class Reverse  extends Encryption {

    private Encryption algoReverse;

    public Reverse(Encryption algoReverse) {
        this.algoReverse = algoReverse;
    }


    @Override
    public void decrypt(int key, File file,File decryptedFile) {
        algoReverse.encrypt(key, file,decryptedFile);
    }

    @Override
    public void encrypt(int key, File file,File encryptedFile) {
        algoReverse.decrypt(key, file,encryptedFile);
    }
}





