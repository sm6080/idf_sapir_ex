package com.company;

public class EncryptionFactory {

    public Encryption getEncryption(EncryptionType encryptionType) {
        if (encryptionType == null)
            return null;
        switch (encryptionType) {
            case CAESAR:
                return new Caesar();
           case XOR:
                return new Xor();
            case MULTIPLICATION:
                return new Multiplication();
            default:
                return null;
        }
    }

    public Encryption reverseEncryption(EncryptionType type) {
        return new Reverse(getEncryption(type));
    }
}

