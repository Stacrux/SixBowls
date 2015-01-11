package com.project.mobile_application.sixbowls.test;

import android.util.Log;

import com.project.mobile_application.sixbowls.Model.Bowl;
import com.project.mobile_application.sixbowls.Model.Constants;
import com.project.mobile_application.sixbowls.Model.GameBoard;
import com.project.mobile_application.sixbowls.Model.PersonalSet;
import com.project.mobile_application.sixbowls.Model.Player;
import com.project.mobile_application.sixbowls.Model.StandardGame.BowlStandard;
import com.project.mobile_application.sixbowls.Model.StandardGame.PersonalSetStandard;
import com.project.mobile_application.sixbowls.Model.StandardGame.TrayStandard;
import com.project.mobile_application.sixbowls.Model.Tray;

import junit.framework.TestCase;

import java.util.ArrayList;


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
        // 3 3 3 3 3 3    0   /    3 3 3 0 4 4   1  ( expettation   ) active player 1
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

    public void testSeedingPhase2(){  // non funziona qualcosa

        gameboard = gameFactory.generateBoard("1B3B3B3B3B4B3T0Z0B3B3B3B3B3B3T0");
        gameboard.seedingPhase(4);

        // 3 3 3 3 4 3    0   /    3 3 3 3 3 3   0  ( configuration ) active player 1
        // 3 3 3 3 0 4    1   /    4 4 3 3 3 3   0  ( expettation   ) active player 2
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


    /*
    public void testCheckGameOver() throws Exception {

    }*/



    @Override
    protected void tearDown() throws Exception{
       super.tearDown();
    }



}