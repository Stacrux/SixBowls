package com.project.mobile_application.sixbowls.test;

import com.project.mobile_application.sixbowls.Model.GameBoard;


import junit.framework.TestCase;

/**
 * Created by MattiaBenzoni on 08/01/2015.
 */

public class GameBoardTest extends TestCase {

    private GameBoard gameboard;
    private GameFactoryFromString gameFactory;


    @Override
    protected void setUp() throws Exception{
       super.setUp();
       gameFactory=new GameFactoryFromString();

    }

   /*private void testFromStringConfiguration(char[] inputConfiguration) {

    }*/




    /**
     *
     * @ the configuration is a string of type YBXBXBXBXBXBXTX Z YBXBXBXBXBXBXTX
     */

     public void testSeedingPhase(){

        gameboard = gameFactory.generateBoard("1B3B3B3B10B3B3T10Z0B3B3B3B3B3B3T0");
        gameboard.seedingPhase(2);

        // 3 3 3 10 3 3   10   /    3 3 3 3 3 3 3 0  ( configuration )
        // 3 3 0 11 4 4   10   /    3 3 3 3 3 3 3 0  ( expettation )
        String confExpectation="0B3B3B0B11B4B4T10Z1B3B3B3B3B3B3T0";
        String actualOutput="";
        try {
            actualOutput = gameboard.toString();
        }catch(Exception e){
            e.printStackTrace();
            fail();
        }

        assertEquals(confExpectation, actualOutput);
    }

    public void testSeedingPhase1(){

        gameboard = gameFactory.generateBoard("0B3B3B3B3B3B3T0Z1B3B3B3B3B3B3T0");
        gameboard.seedingPhase(3);

        // 3 3 3 3 3 3    0   /    3 3 3 3 3 3   0  ( configuration ) active player 1
        // 3 3 3 3 3 3    0   /    3 3 3 0 4 4   1  ( expectation   ) active player 1
        // bowlId  3
        String confExpectation="0B3B3B3B3B3B3T0Z1B3B3B3B0B4B4T1";
        String actualOutput="";
        try {
            actualOutput = gameboard.toString();
        }catch(Exception e){
            e.printStackTrace();
            fail();
        }
        assertEquals(confExpectation, actualOutput);
    }

    public void testSeedingPhase2(){

        gameboard = gameFactory.generateBoard("1B3B3B3B3B4B3T0Z0B3B3B3B3B3B3T0");
        gameboard.seedingPhase(4);

        // 3 3 3 3 4 3    0   /    3 3 3 3 3 3   0  ( configuration ) active player 1
        // 3 3 3 3 0 4    1   /    4 4 3 3 3 3   0  ( expectation   ) active player 2
        // bowlId  3
        String confExpectation="0B3B3B3B3B0B4T1Z1B4B4B3B3B3B3T0";
        String actualOutput="";
        try {
            actualOutput = gameboard.toString();
        }catch(Exception e){
            e.printStackTrace();
            fail();
        }
        assertEquals(confExpectation, actualOutput);
    }


    public void testSeedingPhase3(){

        gameboard = gameFactory.generateBoard("1B3B3B3B3B11B3T0Z0B3B3B3B3B3B3T0");
        gameboard.seedingPhase(4);

        // 3 3 3 3 11 3   0   /    3 3 3 3 3 3   0  ( configuration ) active player 1
        // 4 4 4 3 0 4    1   /    4 4 4 4 4 4   0  ( expectation   ) active player 2
        // bowlId  3

        String confExpectation="0B4B4B4B3B0B4T1Z1B4B4B4B4B4B4T0";
        String actualOutput="";
        try {
            actualOutput = gameboard.toString();
        }catch(Exception e){
            e.printStackTrace();
            fail();
        }
        assertEquals(confExpectation, actualOutput);
    }



    //// 3 3 3 3 3 3    0   /    3 3 3 16 3 3   0  ( configuration ) active player 2
    // 4 4 4 4 4 4    0   /    4 4 4 1 5 5   2  ( expectation   ) active player 2
    // bowlId  3
    public void testSeedingPhase4(){

        gameboard = gameFactory.generateBoard("0B3B3B3B3B3B3T0Z1B3B3B3B16B3B3T0");
        gameboard.seedingPhase(3);



        String confExpectation="0B4B4B4B4B4B4T0Z1B4B4B4B1B5B5T2";
        String actualOutput="";
        try {
            actualOutput = gameboard.toString();
        }catch(Exception e){
            e.printStackTrace();
            fail();
        }
        assertEquals(confExpectation, actualOutput);
    }




    public void testCheckGameOver() throws Exception {

        //    0 2 0 3 0 0
        //  19            11
        //    0 0 0 0 0 1

        gameboard = gameFactory.generateBoard("1B0B0B0B0B0B1T11Z0B0B2B0B3B0B0T19");
        gameboard.seedingPhase(5);
        int winner= gameboard.checkGameOver();

        assertEquals(1,winner);
    }

    public void testCheckGameOver2() throws Exception {

        //    0 2 0 3 0 0
        //  12            18
        //    0 0 0 0 0 1

        gameboard = gameFactory.generateBoard("1B0B0B0B0B0B1T18Z0B0B2B0B3B0B0T12");
        gameboard.seedingPhase(5);
        int winner= gameboard.checkGameOver();

        assertEquals(0,winner);
    }

    public void testCheckGameOver3() throws Exception {

        //    0 0 0 3 2 0
        //  12            18
        //    0 0 1 0 0 0

        gameboard = gameFactory.generateBoard("0B0B0B1B0B0B0T18Z1B0B2B3B0B0B0T12");
        gameboard.seedingPhase(1);
        int winner= gameboard.checkGameOver();

        assertEquals(2,winner);
    }

     //  P2  5 4 3 2 1 0
     //  P2  0 1 2 3 4 5


    @Override
    protected void tearDown() throws Exception{
       super.tearDown();
    }



}