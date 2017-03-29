package com.company;

import encryptionAlgorithms.*;

public class EncryptionFactory {

    public static Encryption getEncryption(EncryptionType encryptionType) {
        if (encryptionType == null)
            return null;
        switch (encryptionType) {
            case CAESAR:
                return new Caesar(null);
           case XOR:
                return new Xor(null);
            case MULTIPLICATION:
                return new Multiplication(null);
            default:
                return null;
        }
    }

    public static Encryption reverseEncryption(EncryptionType type) {
        return new Reverse(getEncryption(type));
    }
}

