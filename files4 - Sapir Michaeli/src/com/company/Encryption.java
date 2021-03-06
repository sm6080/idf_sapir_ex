package com.company;

import java.io.File;

/**
 * Created by Sapir Michaeli on 16.03.2017.
 */
public abstract class Encryption {

    BeginEndListener beginEndListener;

    public void setBeginEndListener(BeginEndListener beginEndListener) {
        this.beginEndListener = beginEndListener;
    }

    abstract void encrypt(int key, File file);
    abstract void decrypt(int key, File file);


}
