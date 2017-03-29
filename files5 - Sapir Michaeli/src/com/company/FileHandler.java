package com.company;

import java.io.*;

/**
 * Created by Sapir Michaeli on 23.03.2017.
 */
public class FileHandler
{
    ObjectOutputStream objectOutputStream = null;
    OutputStream outputStream = null;
    InputStream inputStream = null;
    ObjectInputStream objectInputStream = null;
    File keyFile;

    public FileHandler(File keyFile) throws IOException {
        this.keyFile = keyFile;

    }


    public void writeObjectToFile(Integer... keys) {
        try {
            outputStream = new FileOutputStream(keyFile);
            objectOutputStream = new ObjectOutputStream(outputStream);

            writeKeysToObject(keys);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            handleCloseOfStreams();
        }
    }
    public void readObjectFromFile(File keyFile) {
        try {
            inputStream = new FileInputStream(keyFile);
            objectInputStream = new ObjectInputStream(inputStream);
            key = (Integer) objectInputStream.readObject();
            key2 = (Integer) objectInputStream.readObject();
            System.out.println(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handleCloseOfStreams();
        }
    }

    private void writeKeysToObject(Integer[] keys) throws IOException {
        for (Integer key : keys)
        {
            objectOutputStream.writeObject(key);
        }
    }

    private void handleCloseOfStreams() {
        if (objectOutputStream != null)
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        if (outputStream != null)
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
