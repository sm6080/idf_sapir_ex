package encryptionAlgorithms;

import com.company.EncryptionAlgorithms;

import java.io.File;

/**
 * Created by Sapir Michaeli on 22.03.2017.
 */
public class Double <T extends Encryption, M extends Encryption> extends Encryption {
    T firstEncryptAlgo;
    M secondEncryptAlgo;

    private Integer secondKeyForEncryption;
    private File tmpFile;

    public Double(T firstAlgo, M secondAlgo) {
        super();
        this.firstEncryptAlgo = firstAlgo;
        this.secondEncryptAlgo = secondAlgo;
    }

    public void setSecondKeyForEncryption(Integer secondKeyForEncryption) {
        this.secondKeyForEncryption = secondKeyForEncryption;
    }

    @Override
    public void encrypt(int key, File file, File encryptedFile) {
        tmpFile = EncryptionAlgorithms.createFileTemp(encryptedFile.getPath());
        setReverseSecondKey(key);
        firstEncryptAlgo.encrypt(key, file, tmpFile);
        secondEncryptAlgo.encrypt(secondKeyForEncryption, tmpFile, encryptedFile);
        tmpFile.delete();
    }

    @Override
    public void decrypt(int key, File file, File decryptedFile) {
        tmpFile = EncryptionAlgorithms.createFileTemp(file.getPath());
        setReverseSecondKey(key);
        secondEncryptAlgo.decrypt(secondKeyForEncryption, file, tmpFile);
        firstEncryptAlgo.decrypt(key, tmpFile, decryptedFile);
        tmpFile.delete();
    }


    private void setReverseSecondKey(int key){
        if(secondKeyForEncryption==null)
            secondKeyForEncryption=key;
    }



}







