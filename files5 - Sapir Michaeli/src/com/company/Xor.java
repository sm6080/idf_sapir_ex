package com.company;

import java.io.File;

/**
 * Created by Sapir Michaeli on 20.03.2017.
 */
public class Xor extends Encryption {
    public Xor(BeginEndListener beginEndListener){
        super(beginEndListener);
    }

    @Override
    public void decrypt(int key, File file,File decryptedFile) {
        EncryptionAlgorithms encryptionAlgorithms=new EncryptionAlgorithms();
        encryptionAlgorithms.encyptOrDecrypt(key, file,decryptedFile, false, EncryptionType.XOR,this.beginEndListener);
    }

    @Override
    public void encrypt(int key, File file,File encryptedFile) {
        EncryptionAlgorithms encryptionAlgorithms=new EncryptionAlgorithms();
        encryptionAlgorithms.encyptOrDecrypt(key, file,encryptedFile, true, EncryptionType.XOR, this.beginEndListener);
    }


}






