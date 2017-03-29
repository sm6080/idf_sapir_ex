package encryptionAlgorithms;

import com.company.BeginEndListener;

import java.io.File;

/**
 * Created by Sapir Michaeli on 16.03.2017.
 */
public abstract class Encryption<T> {

    BeginEndListener beginEndListener;

    public Encryption(){

    }
    public Encryption(BeginEndListener beginEndListener) {
        this.beginEndListener=beginEndListener;
    }

    public void setBeginEndListener(BeginEndListener beginEndListener) {
        this.beginEndListener = beginEndListener;
    }

    public abstract void encrypt(int key, File file, File encryptedFile);
    public abstract void decrypt(int key, File file, File decryptedFile);


}
