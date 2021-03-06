package com.company;

import java.io.*;

/**
 * Created by Sapir Michaeli on 16.03.2017.
 */
public class Caesar extends Encryption {

    @Override
    public void decrypt(int key, File file) {
        EncryptionAlgorithms encryptionAlgorithms=new EncryptionAlgorithms();
        encryptionAlgorithms.encyptOrDecrypt(key, file, false, EncryptionType.CAESAR, this.beginEndListener);
    }

    @Override
    public void encrypt(int key, File file) {
        EncryptionAlgorithms encryptionAlgorithms=new EncryptionAlgorithms();
        encryptionAlgorithms.encyptOrDecrypt(key, file, true, EncryptionType.CAESAR,this.beginEndListener);
    }


}






