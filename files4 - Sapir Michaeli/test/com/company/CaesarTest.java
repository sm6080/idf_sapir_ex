package com.company;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.io.*;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

/**
 * Created by Sapir Michaeli on 14.03.2017.
 */
class CaesarTest {


    @Test
    void encryptTest() {
        Encryption encryption = new Caesar();
        File file = new File("C:\\files\\sap.txt");
        encryption.encrypt(12345, file);
        File fileEncrypt = new File("C:\\files\\sap.encrypted.txt");
        InputStream inputStreamEncrypt = null, inputStream = null;
        try {
            inputStreamEncrypt = new FileInputStream(fileEncrypt);
            inputStream = new FileInputStream(file);
            int actuallyReadEncrypt = 0, actuallyRead = 0;
            byte b;
            while ((actuallyRead = inputStream.read()) != -1 && (actuallyReadEncrypt = inputStreamEncrypt.read()) != -1) {

                if (!((byte) (actuallyRead + 12345) == (byte) actuallyReadEncrypt))
                    fail("not encrypt");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (inputStreamEncrypt != null)
                try {
                    inputStreamEncrypt.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

//    }@Test
//    void decryptTest() {
//        Decryption decryption = new Caesar();
//        File file = new File("C:\\files\\sap.txt");
//        decryption.decrypt(1234, file);
//        File encryptFile = new File("C:\\files\\sap.encrypted");
//        InputStream inputStream = null, encryptInputStream = null;
//        try {
//            encryptInputStream = new FileInputStream(encryptFile);
//            inputStream = new FileInputStream(file);
//            int actuallyRead = 0, encryptActuallyRead = 0;
//            byte b;
//            while ((actuallyRead = inputStream.read()) != -1 && (encryptActuallyRead = encryptInputStream.read()) != -1) {
//                if (!((byte) (actuallyRead + 12345) == (byte) (encryptActuallyRead)))
//                    fail("not encrypted");
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (inputStream != null)
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            if (encryptInputStream != null)
//                try {
//                    encryptInputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }

}


