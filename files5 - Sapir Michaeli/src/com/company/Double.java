package com.company;

import java.io.File;

/**
 * Created by Sapir Michaeli on 22.03.2017.
 */
public class Double <T extends Encryption, M extends Encryption> extends Encryption {
    T firstEncryptAlgo;
    M secondEncryptAlgo;

    Integer secondKeyForEncryption;

    public Double(T firstAlgo, M secondAlgo, Integer key2) {
        this.firstEncryptAlgo = firstAlgo;
        this.secondEncryptAlgo = secondAlgo;
        this.secondKeyForEncryption=key2;
    }
    public void setSecondKeyForEncryption(Integer secondKeyForEncryption) {
        this.secondKeyForEncryption = secondKeyForEncryption;
    }

    @Override
    void encrypt(int key, File file,File encryptedFile) {
        firstEncryptAlgo.encrypt(key, file,encryptedFile );
        secondEncryptAlgo.encrypt(secondKeyForEncryption, encryptedFile,encryptedFile);
    }

    @Override
    void decrypt(int key, File file,File decryptedFile) {
        secondEncryptAlgo.decrypt(secondKeyForEncryption, file,file);
        firstEncryptAlgo.decrypt(key,file, decryptedFile);
    }
}
