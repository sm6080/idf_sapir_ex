package com.company;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Sapir Michaeli on 19.03.2017.
 */
public class Key implements KeyInterface , Serializable{

    Random random;
    Integer key;

    public Key() {
        random = new Random(System.currentTimeMillis());
    }


    @Override
    public Object getKey() {
        key=random.nextInt();
        return key;
    }

    @Override
    public void setKey(Object key) {
        this.key=(Integer)key;
    }


}
