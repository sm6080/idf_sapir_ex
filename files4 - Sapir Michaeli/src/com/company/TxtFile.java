package com.company;
import java.io.File;
import java.net.URI;

/**
 * Created by Sapir Michaeli  on 28.02.2017.
 */
public class TxtFile extends File implements FileImp{

    private boolean isExist=false;

    public TxtFile(String pathname) {
        super(pathname);
        if (exists()  && isFile()) {
            System.out.println("File Exists");
            isExist = true;
        }
        else
            System.out.println("File doesn't exists");
    }

    public boolean getIsExist() {
        return isExist;
    }


    @Override
    public void read() {
    }

    @Override
    public void write() {
    }
}
