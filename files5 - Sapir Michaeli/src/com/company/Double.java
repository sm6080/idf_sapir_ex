package com.company;

import java.io.File;

/**
 * Created by Sapir Michaeli on 22.03.2017.
 */
public class Double <T extends Encryption, M extends Encryption> extends Encryption {
    T firstAlgo;
    M secondAlgo;

    Integer secondKeyForEncryption;

    public Double(T firstAlgo, M secondAlgo) {
        this.firstAlgo = firstAlgo;
        this.secondAlgo = secondAlgo;
    }
    public void setSecondKeyForEncryption(Integer secondKeyForEncryption) {
        this.secondKeyForEncryption = secondKeyForEncryption;
    }

    @Override
    void encrypt(int key, File file) {
        firstAlgo.encrypt(key, file);
        File encryptedFile=new EncryptionAlgorithms().crateFileWithEnding(file, true);
        secondAlgo.encrypt(secondKeyForEncryption, encryptedFile);
        encryptedFile.delete();
    }

    @Override
    void decrypt(int key, File file) {
        firstAlgo.decrypt(key, file);
        File decryptedFile=new EncryptionAlgorithms().crateFileWithEnding(file, false);
        secondAlgo.encrypt(secondKeyForEncryption, decryptedFile);
        decryptedFile.delete();
    }
}
