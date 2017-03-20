package com.company;

import java.io.*;

/**
 * Created by Sapir Michaeli on 20.03.2017.
 */
public class Reverse implements FileOperation ,Encryption {

    private Encryption algoReverse;

    public Reverse(Encryption algoReverse) {
        this.algoReverse = algoReverse;
    }

    @Override
    public void operation() {
    }

    @Override
    public void decrypt(int key, File file) {
        algoReverse.encrypt(key, file);
    }

    @Override
    public void encrypt(int key, File file) {
        algoReverse.decrypt(key, file);

    }
}





