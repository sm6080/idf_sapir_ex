package com.company;

/**
 * Created by Sapir Michaeli on 21.03.2017.
 */
public class BadKeyException extends Exception {

    private BadKeyListener exceptionListener;

    public BadKeyException(String exceptionMessage) {
        if (exceptionListener!=null)
            exceptionListener.exception(exceptionMessage);
    }

    public void setBadKeyListener(BadKeyListener badKeyListener) {
        this.exceptionListener = badKeyListener;
    }

    interface BadKeyListener{
        void exception(String exceptionMessage);
    }


}
