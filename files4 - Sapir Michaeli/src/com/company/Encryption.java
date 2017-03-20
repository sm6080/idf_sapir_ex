package com.company;

import java.io.File;

/**
 * Created by Sapir Michaeli on 16.03.2017.
 */
public interface Encryption {
    void encrypt(int key,File file);
    void decrypt(int key, File file);
}
