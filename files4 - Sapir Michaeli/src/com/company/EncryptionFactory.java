package com.company;

/**
 * Created by Sapir Michaeli on 20.03.2017.
 */
enum EncryptionType{
    CAESAR, XOR, REVERSE, MULTIPLICATION
}

public class EncryptionFactory {

    public Encryption getEncryption(EncryptionType encryptionType) {
        if (encryptionType == null)
            return null;
        switch (encryptionType) {
            case CAESAR:
                return new Caesar();
           /* case XOR:
                return new Rectangle();
            case "reverse:":
                return new Square();*/
            default:
                return null;

        }
    }
}
