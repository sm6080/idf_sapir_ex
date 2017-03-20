package com.company;

import java.io.File;

/**
 * Created by Sapir Michaeli on 20.03.2017.
 */
public class Multiplication implements FileOperation ,Encryption {
    @Override
    public void operation() {

    }

    @Override
    public void decrypt(int key, File file) {
        EncryptionAlgorithms.encyptOrDecrypt(key, file, false, EncryptionType.MULTIPLICATION);
    }

    @Override
    public void encrypt(int key, File file) {
        EncryptionAlgorithms.encyptOrDecrypt(key, file, true, EncryptionType.MULTIPLICATION);
    }


}






