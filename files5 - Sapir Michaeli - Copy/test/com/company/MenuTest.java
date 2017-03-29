package com.company;

import inputOutput.Input;
import inputOutput.Output;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

/**
 * Created by Sapir Michaeli on 14.03.2017.
 */
class MenuTest {

    @Test
    void rightChoiceTest() {
        Input input = mock(Input.class);
        Output output = mock(Output.class);
        Menu menu = new Menu(output, input);
        when(input.getInput()).thenReturn("1").thenReturn("C:\\files\\sap.txt").thenReturn("exit");
        menu.getMenu();
        InOrder inOrder = inOrder(output);
        inOrder.verify(output).print("please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");
        inOrder.verify(output).print("enter path");

    }

    @Test
    void wrongChoiceTest() {
        Input input = mock(Input.class);
        Output output = mock(Output.class);
        Menu menu = new Menu(output, input);
        when(input.getInput()).thenReturn("3").thenReturn("C:\\files\\sap.txt").thenReturn("exit");
        menu.getMenu();
        InOrder inOrder = inOrder(output);
        inOrder.verify(output).print("please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");
        inOrder.verify(output, times(2)).print("the choice is not valid,please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");

    }

    @Test
    void wrongPathTest() {
        Input input = mock(Input.class);
        Output output = mock(Output.class);
        Menu menu = new Menu(output, input);
        when(input.getInput()).thenReturn("1").thenReturn("C:\\fil??es\\sap.txt").thenReturn("exit");
        menu.getMenu();
        InOrder inOrder = inOrder(output);
        inOrder.verify(output).print("please choose:\n 1. encryption\n 2. decryption\n type exit at any point to exit this program");
        inOrder.verify(output).print("enter path");
        inOrder.verify(output).print("the path is wrong, please enter path again");

    }
}