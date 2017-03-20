package com.company;

public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu();
        EncryptionAlgorithms encryptionAlgorithms = new EncryptionAlgorithms();
        encryptionAlgorithms.setBeginEndListener(menu);
        menu.getMenu();
    }
}
