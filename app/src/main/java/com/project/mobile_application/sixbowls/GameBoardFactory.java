package com.project.mobile_application.sixbowls;

import com.project.mobile_application.sixbowls.Model.AndroidIntelligence;
import com.project.mobile_application.sixbowls.Model.Bowl;
import com.project.mobile_application.sixbowls.Model.StandardGame.AndroidIntelligenceStandard;
import com.project.mobile_application.sixbowls.Model.StandardGame.BowlStandard;
import com.project.mobile_application.sixbowls.Model.Constants;
import com.project.mobile_application.sixbowls.Model.GameBoard;
import com.project.mobile_application.sixbowls.Model.PersonalSet;
import com.project.mobile_application.sixbowls.Model.StandardGame.PersonalSetStandard;
import com.project.mobile_application.sixbowls.Model.Player;
import com.project.mobile_application.sixbowls.Model.Tray;
import com.project.mobile_application.sixbowls.Model.StandardGame.TrayStandard;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Martino on 09/01/2015.
 */
public class GameBoardFactory {

    public GameBoard getGameBoard(String configuration){

        GameBoard gameboard = null;

        if( configuration.equals("p1vsp2")){
            //Player(identifier expressed as integer)
            Player playerOne = new Player(0);
            Player playerTwo = new Player(1);
            ArrayList<Bowl> bowlsPlayerOne = new ArrayList<Bowl>();
            ArrayList<Bowl> bowlsPlayerTwo = new ArrayList<Bowl>();
            for (int e = 0; e < Constants.numberOfBowls; e++) {
                BowlStandard bowl = new BowlStandard(Constants.initialBowlContent, e);
                bowlsPlayerOne.add(bowl);
            }
            for (int e = 0; e < Constants.numberOfBowls; e++) {
                BowlStandard bowl = new BowlStandard(Constants.initialBowlContent, e);
                bowlsPlayerTwo.add(bowl);
            }
            Tray trayPlayerOne = new TrayStandard(Constants.initialTrayContent);
            Tray trayPlayerTwo = new TrayStandard(Constants.initialTrayContent);

            Random randomActive = new Random();
            boolean active = randomActive.nextBoolean();

            PersonalSet setPlayerOne = new PersonalSetStandard(playerOne, bowlsPlayerOne, trayPlayerOne, active);
            PersonalSet setPlayerTwo = new PersonalSetStandard(playerTwo, bowlsPlayerTwo, trayPlayerTwo, !active);

            gameboard = new GameBoard(setPlayerOne,setPlayerTwo);
        }


        if( configuration.equals("p1vsAndroid")){
            //Player(identifier expressed as integer)
            Player playerOne = new Player(0);
            Player playerTwo = new Player(1);
            AndroidIntelligence androidIntelligence = new AndroidIntelligenceStandard();
            ArrayList<Bowl> bowlsPlayerOne = new ArrayList<Bowl>();
            ArrayList<Bowl> bowlsPlayerTwo = new ArrayList<Bowl>();
            for (int e = 0; e < Constants.numberOfBowls; e++) {
                BowlStandard bowl = new BowlStandard(Constants.initialBowlContent, e);
                bowlsPlayerOne.add(bowl);
            }
            for (int e = 0; e < Constants.numberOfBowls; e++) {
                BowlStandard bowl = new BowlStandard(Constants.initialBowlContent, e);
                bowlsPlayerTwo.add(bowl);
            }
            Tray trayPlayerOne = new TrayStandard(Constants.initialTrayContent);
            Tray trayPlayerTwo = new TrayStandard(Constants.initialTrayContent);

            Random randomActive = new Random();
            boolean active = randomActive.nextBoolean();

            PersonalSet setPlayerOne = new PersonalSetStandard(playerOne, bowlsPlayerOne, trayPlayerOne, active);
            PersonalSet setPlayerTwo = new PersonalSetStandard(playerTwo, bowlsPlayerTwo, trayPlayerTwo, !active);

            gameboard = new GameBoard(setPlayerOne,setPlayerTwo, androidIntelligence);
        }




        return gameboard;
    }





}
