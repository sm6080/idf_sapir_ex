package com.company;

/**
 * Created by Sapir Michaeli on 20.03.2017.
 */
public enum EncryptionType {
    CAESAR, XOR, REVERSE, MULTIPLICATION;

    public static EncryptionType getType(String choice) {
        switch (choice) {
            case "1":
                return CAESAR;
            case "2":
                return XOR;
            case "3":
                return MULTIPLICATION;
            case "4":
                return REVERSE;
            default:
                return null;
        }
    }
}
