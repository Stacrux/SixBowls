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

    private PersonalSet setPlayerOne;
    private PersonalSet setPlayerTwo;
    private Player playerOne;
    private Player playerTwo;
    private ArrayList<Bowl> bowlsOne;
    private ArrayList<Bowl> bowlsTwo;
    private Tray trayOne;
    private Tray trayTwo;


    @Override
    protected void setUp() throws Exception{
       super.setUp();

       playerOne=new Player(0);
       bowlsOne=new ArrayList<Bowl>();
       setBowls(bowlsOne);
       trayOne=new TrayStandard(0);
       setPlayerOne=new PersonalSetStandard(playerOne,bowlsOne,trayOne,true);

       playerTwo=new Player(1);
       bowlsTwo=new ArrayList<Bowl>();
       setBowls(bowlsTwo);
       trayTwo=new TrayStandard(0);
       setPlayerTwo=new PersonalSetStandard(playerTwo,bowlsTwo,trayTwo,false);
    }

    private void setBowls(ArrayList<Bowl> bowls) {

        for (int e = 0; e < Constants.numberOfBowls; e++) {
            BowlStandard bowl = new BowlStandard(Constants.initialBowlContent, e);
            bowls.add(bowl);
        }
    }


      /*private void testFromStringConfiguration(char[] inputConfiguration) {

    }*/

    public void testSeedingPhase() throws Exception {

        gameboard=new GameBoard(setPlayerOne,setPlayerTwo);
        gameboard.seedingPhase(2);

        //     3 3 3 3 3 3
        //    0           0
        //     3 3 0 4 4 4
        //verifichiamo

        String confExpectation="0B3B3B0B4B4B4T01B3B3B3B3B3B3T0";
        String conf=gameboard.toString();
        assertEquals(conf,confExpectation);
    }

    /*
    public void testCheckGameOver() throws Exception {

    }*/



    @Override
    protected void tearDown() throws Exception{
       super.tearDown();
    }



}