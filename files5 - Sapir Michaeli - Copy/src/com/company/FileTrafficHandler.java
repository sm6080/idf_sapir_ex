package com.company;

import java.io.*;

/**
 * Created by Sapir Michaeli on 23.03.2017.
 */
public class FileTrafficHandler
{
    ObjectOutputStream objectOutputStream = null;
    OutputStream outputStream = null;
    File keyFile;

    public FileTrafficHandler(File keyFile) throws FileNotFoundException, IOException
    {

        this.keyFile = keyFile;

    }


    public void writeToFile(Integer... keys)
    {
        try {
            outputStream = new FileOutputStream(keyFile);
            objectOutputStream = new ObjectOutputStream(outputStream);

            writeKeysToObject(keys);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
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
