package com.company;

import java.util.Random;

/**
 * Created by Sapir Michaeli on 19.03.2017.
 */
public class Key implements KeyInterface{

    Random random = new Random(System.currentTimeMillis());

    @Override
    public int getKey() {
        return random.nextInt();
    }


}
