package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Created by Sapir Michaeli on 14.03.2017.
 */
class MenuTest {

    @Test
    void decryptionTest(){
        if(!new Menu().programMenu("1").equals("decryption"))
            Assertions.fail("not decryption");
    }
    @Test
    void encryptionTest(){
        if(! new Menu().programMenu("2").equals("encryption"))
            Assertions.fail("not encryption");
    }
    @Test
    void stringTest(){
        if (!new Menu().programMenu("hgurhguh").equals("")){
            Assertions.fail("invalid input");
        }

    }
    @Test
    void wrongNumberTest(){
        if(!new Menu().programMenu("3").equals(""))
            Assertions.fail("invalid input");
    }
    @Test
    void emptyTest(){
        if (!new Menu().programMenu("").equals(""))
            Assertions.fail("invalid input");
    }
    @Test
    void rightChoiceTest() {
        Input input = mock(Input.class);
        Output output = mock(Output.class);
        Menu menu = new Menu(output, input);
        when(input.input()).thenReturn("1").thenReturn("C:\\files\\m").thenReturn("exit");
        menu.menu();
        InOrder inOrder= inOrder(output);
        inOrder.verify(output).output("please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");
        inOrder.verify(output).output("enter path");

    }

    @Test
    void wrongChoiceTest() {
        Input input = mock(Input.class);
        Output output = mock(Output.class);
        Menu menu = new Menu(output, input);
        when(input.input()).thenReturn("3").thenReturn("C:\\files\\m").thenReturn("exit");
        menu.menu();
        InOrder inOrder=inOrder(output);
        inOrder.verify(output).output("please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");
        inOrder.verify(output, times(2)).output("the choice is not valid,please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");

    }

}