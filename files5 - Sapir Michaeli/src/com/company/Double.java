package com.company;

import java.io.File;

/**
 * Created by Sapir Michaeli on 22.03.2017.
 */
public class Double <T extends Encryption, M extends Encryption> extends Encryption {
    T firstEncryptAlgo;
    M secondEncryptAlgo;

    Integer secondKeyForEncryption;
    private File tmpFile;

    public Double(T firstAlgo, M secondAlgo, Integer key2) {
        this.firstEncryptAlgo = firstAlgo;
        this.secondEncryptAlgo = secondAlgo;
        this.secondKeyForEncryption = key2;

    }

    public void setSecondKeyForEncryption(Integer secondKeyForEncryption) {
        this.secondKeyForEncryption = secondKeyForEncryption;
    }

    @Override
    void encrypt(int key, File file, File encryptedFile) {
        tmpFile = EncryptionAlgorithms.createFileTemp(file.getPath());
        firstEncryptAlgo.encrypt(key, file, tmpFile);
        secondEncryptAlgo.encrypt(secondKeyForEncryption, tmpFile, encryptedFile);
        tmpFile.delete();
    }

    @Override
    void decrypt(int key, File file, File decryptedFile) {
        tmpFile = EncryptionAlgorithms.createFileTemp(file.getPath());
        secondEncryptAlgo.decrypt(secondKeyForEncryption, file, tmpFile);
        firstEncryptAlgo.decrypt(key, tmpFile, decryptedFile);
        tmpFile.delete();
    }


}
