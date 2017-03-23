package com.company;

import java.io.File;

/**
 * Created by Sapir Michaeli on 20.03.2017.
 */
public class Multiplication extends Encryption {
    public Multiplication(BeginEndListener beginEndListener){
        super(beginEndListener);
    }

    @Override
    public void decrypt(int key, File file,File decryptedFile) {
        EncryptionAlgorithms encryptionAlgorithms=new EncryptionAlgorithms();
        encryptionAlgorithms.encyptOrDecrypt(key, file,decryptedFile, false, EncryptionType.MULTIPLICATION,this.beginEndListener);
    }

    @Override
    public void encrypt(int key, File file,File encryptedFile) {
        EncryptionAlgorithms encryptionAlgorithms=new EncryptionAlgorithms();
        encryptionAlgorithms.encyptOrDecrypt(key, file,encryptedFile, true, EncryptionType.MULTIPLICATION, this.beginEndListener);
    }


}






