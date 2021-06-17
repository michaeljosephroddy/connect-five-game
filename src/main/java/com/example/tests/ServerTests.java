package com.example.tests;

import com.example.server.GameService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class ServerTests {

    GameService gameService = new GameService();

    // ================================= TESTING CONVERT COLUMN NUMBER =================================== //

    // test data
    private static Object[][] convertColumnTestData = new Object[][] {
            // id, variable1, variable2, ... variableN, expected
            {"T1",1, 1},
            {"T2",2, 3},
            {"T3",3, 5},
            {"T4",4, 7},
            {"T5",5, 9},
            {"T6",6, 11},
            {"T7",7, 13},
            {"T8",8, 15},
            {"T9",9, 17},
    };

    // Marks a method as supplying data for a test method
    // the annotated method must return an Object[][] where each Object[] can be assigned the parameter list of the test method
    @DataProvider(name = "convertColumnData")
    public Object[][] getConvertColumnTestData() {
        return convertColumnTestData;
    }

    // Marks a class or a method as part of the test.
    @Test(dataProvider = "convertColumnData")
    public void testConvertColumn(String id, int input, int expected) {
        assertEquals(gameService.convertColumnNumber(input), expected);
    }

    // ==================================== TESTING CHECK WIN ====================================== //

    // check horizontal
    static char[][] board1 = {
            {'|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|'},
            {'|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|'},
            {'|','O','|','O','|','O','|','O','|','X','|','X','|','X','|','X','|','X','|'},
            {'|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|'},
            {'|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|'},
            {'|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|'},
    };
    // check vertical
   static char[][] board2 = {
           {'|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|'},
           {'|','X','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|'},
           {'|','X','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|'},
           {'|','X','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|'},
           {'|','X','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|'},
           {'|','X','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|'},
   };
    // check diagonal top right to bottom left
    static char[][] board3 = {
            {'|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','X','|'},
            {'|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','X','|','O','|'},
            {'|','O','|','O','|','O','|','O','|','O','|','O','|','X','|','O','|','O','|'},
            {'|','O','|','O','|','O','|','O','|','O','|','X','|','O','|','O','|','O','|'},
            {'|','O','|','O','|','O','|','O','|','X','|','O','|','O','|','O','|','O','|'},
            {'|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|'},
    };
    // check diagonal top left to bottom right
    static char[][] board4 = {
            {'|','O','|','O','|','O','|','O','|','X','|','O','|','O','|','O','|','O','|'},
            {'|','O','|','O','|','O','|','O','|','O','|','X','|','O','|','O','|','O','|'},
            {'|','O','|','O','|','O','|','O','|','O','|','O','|','X','|','O','|','O','|'},
            {'|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','X','|','O','|'},
            {'|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','X','|'},
            {'|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|','O','|'},
    };
    // no win
    static char[][] board5 = {
            {'|','O','|','X','|','O','|','X','|','O','|','X','|','O','|','X','|','O','|'},
            {'|','X','|','O','|','X','|','O','|','X','|','O','|','X','|','O','|','X','|'},
            {'|','X','|','O','|','X','|','O','|','X','|','O','|','X','|','O','|','X','|'},
            {'|','O','|','X','|','O','|','X','|','O','|','X','|','O','|','X','|','O','|'},
            {'|','X','|','O','|','X','|','O','|','X','|','O','|','X','|','O','|','X','|'},
            {'|','O','|','X','|','O','|','X','|','O','|','X','|','O','|','X','|','O','|'},
    };

    private static Object[][] checkWinTestData = new Object[][] {
            // id, variable1, variable2, ... variableN, expected
            {"T1", board1, true},
            {"T2", board2, true},
            {"T3", board3, true},
            {"T4", board4, true},
            {"T5", board5, false},
    };

    // Marks a method as supplying data for a test method
    @DataProvider(name = "checkWinData")
    public Object[][] getCheckWinTestData() {
        return checkWinTestData;
    }

    // Marks a class or a method as part of the test.
    @Test(dataProvider = "checkWinData")
    public void testCheckWin(String id, char[][] input, boolean expected) {
        assertEquals(gameService.checkWin(input), expected);
    }

    // =================================== TESTING IS BOARD FULL ====================================== //

    static char[][] fullBoard = {
            {'|','X','|','X','|','X','|','X','|','X','|','x','|','O','|','O','|','O','|'},
            {'|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|'},
            {'|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|'},
            {'|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|'},
            {'|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|'},
            {'|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|'},
    };
    static char[][] notFullBoard = {
            {'|','X','|','X','|','X','|','X','|','X','|','_','|','O','|','O','|','O','|'},
            {'|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|'},
            {'|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|'},
            {'|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|'},
            {'|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|'},
            {'|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|','_','|'},
    };

    private static Object[][] isBoardFullTestData = new Object[][] {
            // id, variable1, variable2, ... variableN, expected
            {"T1", fullBoard, true},
            {"T2", notFullBoard, false},
    };

    // Marks a method as supplying data for a test method
    @DataProvider(name = "isBoardFullTestData")
    public Object[][] getIsBoardFullTestData() {
        return isBoardFullTestData;
    }

    // Marks a class or a method as part of the test.
    @Test(dataProvider = "isBoardFullTestData")
    public void testIsBoardFull(String id, char[][] input, boolean expected) {
        assertEquals(gameService.isBoardFull(input), expected);
    }

    // ==================================== TESTING IS VALID INPUT ======================================= //

    private static Object[][] isValidInputTestData = new Object[][] {
            // id, variable1, variable2, ... variableN, expected
            {"T1", "1", true},
            {"T1", "2", true},
            {"T1", "3", true},
            {"T1", "4", true},
            {"T1", "5", true},
            {"T1", "6", true},
            {"T1", "7", true},
            {"T1", "8", true},
            {"T1", "9", true},
            {"T1", "check-status", true},
            {"T1", "quit", true},
            {"T1", "asedf", false},
    };

    // Marks a method as supplying data for a test method
    @DataProvider(name = "isValidInputTestData")
    public Object[][] getIsValidInputTestData() {
        return isValidInputTestData;
    }

    // Marks a class or a method as part of the test.
    @Test(dataProvider = "isValidInputTestData")
    public void testIsValidInput(String id, String input, boolean expected) {
        assertEquals(gameService.isValidInput(input), expected);
    }

}
