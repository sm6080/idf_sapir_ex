package com.company;

/**
 * Created by Sapir Michaeli on 23.03.2017.
 */
public class InputException extends Exception{

    private ExceptionListener exceptionListener;

    public InputException(String exceptionMessage) {
        if (exceptionListener!=null)
            exceptionListener.exception(exceptionMessage);
    }

    public void setExceptionListener(ExceptionListener exceptionListener) {
        this.exceptionListener = exceptionListener;
    }

    interface ExceptionListener{
        void exception(String exceptionMessage);
    }
}
