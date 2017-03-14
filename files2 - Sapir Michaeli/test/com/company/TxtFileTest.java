package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hackeru on 14.03.2017.
 */
class TxtFileTest {

    Random random=new Random(System.currentTimeMillis());
   // String[] forbiddenChars={"/",":","*","?", "<" ,">","|"};
    String path=null;

    @BeforeEach
    void setUp() {
       /* for (int i = 0; i < forbiddenChars.length; i++) {
            int x = random.nextInt(path.length());
            path = path.concat(path.substring(0, x));
            path.concat(forbiddenChars[i]);
            path.concat(path.substring(x + 1, path.length()));
            TxtFile textFile = new TxtFile(path);
            if (textFile.getIsExist())
                Assertions.fail("not valid");
        }*/

       //not valid path
        TxtFile textFile = new TxtFile("C:\\Users\\hackeru.HACKERU3\\Downloads?\\filesExe1\\sap.txt");
        if (textFile.getIsExist())
            Assertions.fail("error");
        //ok and exist
        textFile = new TxtFile("C:\\Users\\hackeru.HACKERU3\\Downloads\\filesExe1\\sap.txt");
        File file = new File("C:\\Users\\hackeru.HACKERU3\\Downloads\\filesExe1\\sap.txt");
        if (file.exists() && !textFile.getIsExist())
            Assertions.fail("error");
        //ok and not exist
        textFile = new TxtFile("C:\\Users\\hackeru.HACKERU3\\Downloads10\\filesExe1\\sap.txt");
        file = new File("C:\\Users\\hackeru.HACKERU3\\Downloads10\\filesExe1\\sap.txt");
        if (!file.exists() && textFile.getIsExist())
            Assertions.fail("error");

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void read() {

    }

    @Test
    void write() {

    }

}