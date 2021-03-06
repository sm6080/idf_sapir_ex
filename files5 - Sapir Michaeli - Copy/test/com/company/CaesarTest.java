package com.company;

import encryptionAlgorithms.Caesar;
import encryptionAlgorithms.Encryption;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Created by Sapir Michaeli on 14.03.2017.
 */
class CaesarTest {


    @Test
    void encryptTest() {
        Encryption encryption = new Caesar(null);
        File file = new File("C:\\files\\sap.txt");
        File fileEncrypt = new File("C:\\files\\sap.encrypted.txt");
        encryption.encrypt(12345, file,fileEncrypt);
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
            if (actuallyRead == -1) {
                actuallyReadEncrypt = inputStreamEncrypt.read();
                if ((actuallyReadEncrypt != -1))
                    fail("not encrypt");
            } else
                fail("not encrypt");

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

    @Test
    void decryptTest(){
        Encryption encryption = new Caesar(null);
        File file = new File("C:\\files\\sap.txt");
        File decryptedFile = new File("C:\\files\\sap.encrypted_decrypted.txt");
        encryption.decrypt(12345, file,decryptedFile);
        InputStream decryptedInputstream = null, inputStream = null;
        try {
            decryptedInputstream = new FileInputStream(decryptedFile);
            inputStream = new FileInputStream(file);
            int decryptActuallyRead = 0, actuallyRead = 0;
            byte b;
            while ((actuallyRead = inputStream.read()) != -1 && (decryptActuallyRead = decryptedInputstream.read()) != -1) {
                if (actuallyRead != decryptActuallyRead)
                    fail("not decrypt");
            }
            if (actuallyRead == -1) {
                decryptActuallyRead = decryptedInputstream.read();
                if ((decryptActuallyRead != -1))
                    fail("not decrypt");
            } else
                fail("not decrypt");
        }catch (FileNotFoundException e) {
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
            if (decryptedInputstream != null)
                try {
                    decryptedInputstream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}


