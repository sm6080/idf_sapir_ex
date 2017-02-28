package com.company;
import java.io.File;
import java.net.URI;

/**
 * Created by Sapir Michaeli  on 28.02.2017.
 */
public class TxtFile extends File implements FileImp{

    // check the
    public TxtFile(String pathname) {
        super(pathname);
        if (exists()  && isFile())
            System.out.println("File Exists");
        else
            System.out.println("File doesn't exists");

    }


    @Override
    public void read() {

    }

    @Override
    public void write() {

    }
}
