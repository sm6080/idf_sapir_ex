package com.company;

import java.io.File;

/**
 * Created by Sapir Michaeli on 20.03.2017.
 */
public class Xor implements FileOperation ,Encryption {
    @Override
    public void operation() {

    }

    @Override
    public void decrypt(int key, File file) {
        EncryptionAlgorithms encryptionAlgorithms=new EncryptionAlgorithms();
        encryptionAlgorithms.encyptOrDecrypt(key, file, false, EncryptionType.XOR);
    }

    @Override
    public void encrypt(int key, File file) {
        EncryptionAlgorithms encryptionAlgorithms=new EncryptionAlgorithms();
        encryptionAlgorithms.encyptOrDecrypt(key, file, true, EncryptionType.XOR);
    }


}






