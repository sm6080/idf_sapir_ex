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
 * Created by Sapir Michaeli on 14.03.2017.
 */
class TxtFileTest {

    @Test
    void wrongPath() {
        TxtFile textFile = new TxtFile("C:\\files?\\m.txt");
        if (textFile.getIsExist())
            Assertions.fail("error");
    }

    @Test
    void exist() {
        TxtFile textFile = new TxtFile("C:\\files\\m.txt");
        File file = new File("C:\\files\\m.txt");
        if (file.exists() && !textFile.getIsExist())
            Assertions.fail("error");
    }

    @Test
    void notExist() {
        TxtFile textFile = new TxtFile("C:\\files\\m9.txt");
        File file = new File("C:\\files\\m9.txt");
        if (!file.exists() && textFile.getIsExist())
            Assertions.fail("error");
    }

}