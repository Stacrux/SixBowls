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


    /*
    private PersonalSet setPlayerOne;
    private PersonalSet setPlayerTwo;
    private Player playerOne;
    private Player playerTwo;
    private ArrayList<Bowl> bowlsOne;
    private ArrayList<Bowl> bowlsTwo;
    private Tray trayOne;
    private Tray trayTwo;*/


    @Override
    protected void setUp() throws Exception{
       super.setUp();
      /*
       boolean active = true;
       playerOne=new Player(0);
       bowlsOne=new ArrayList<Bowl>();
       setBowls(bowlsOne);
       trayOne=new TrayStandard(0);
       setPlayerOne=new PersonalSetStandard(playerOne,bowlsOne,trayOne,active);

       playerTwo=new Player(1);
       bowlsTwo=new ArrayList<Bowl>();
       setBowls(bowlsTwo);
       trayTwo=new TrayStandard(0);
       setPlayerTwo=new PersonalSetStandard(playerTwo,bowlsTwo,trayTwo,!active);*/
       gameFactory=new GameFactoryFromString();

    }

    private void setBowls(ArrayList<Bowl> bowls) {

        for (int e = 0; e < Constants.numberOfBowls; e++) {
            BowlStandard bowl = new BowlStandard(Constants.initialBowlContent, e);
            bowls.add(bowl);
        }
    }


      /*private void testFromStringConfiguration(char[] inputConfiguration) {

    }*/

    public void testSeedingPhase(){

        gameboard = gameFactory.generateBoard("0B3B3B0B4B4B4T01B3B3B3B3B3B3T0"); //new GameBoard(setPlayerOne,setPlayerTwo);
        gameboard.seedingPhase(2);

        //     3 3 3 3 3 3
        //    0           0
        //     3 3 0 4 4 4
        //verifichiamo

        String confExpectation="0B3B3B0B4B4B4T01B3B3B3B3B3B3T0";
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