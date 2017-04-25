package encryptionAlgorithms;

import com.company.BeginEndListener;
import com.company.EncryptionAlgorithms;
import com.company.EncryptionType;

import java.io.*;

/**
 * Created by Sapir Michaeli on 16.03.2017.
 */
public class Caesar extends Encryption<Integer> {

    public Caesar(BeginEndListener beginEndListener){
        super(beginEndListener);
    }

    @Override
    public void decrypt(int key, File file,File decryptedFile) {
        EncryptionAlgorithms encryptionAlgorithms=new EncryptionAlgorithms();
        encryptionAlgorithms.encyptOrDecrypt(key, file,decryptedFile, false, EncryptionType.CAESAR,this.beginEndListener);
    }

    @Override
    public void encrypt(int key, File file,File encryptedFile) {
        EncryptionAlgorithms encryptionAlgorithms=new EncryptionAlgorithms();
        encryptionAlgorithms.encyptOrDecrypt(key, file,encryptedFile, true, EncryptionType.CAESAR, this.beginEndListener);
    }



}






