package com.company;

import java.io.Serializable;

/**
 * Created by  Sapir Michaeli on 23.03.2017.
 */
public class SerializationKey implements Serializable {
    private int key;
    String path;

    public SerializationKey(int key, String path) {
        this.key = key;
        this.path = path;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }


}
