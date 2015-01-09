package com.project.mobile_application.sixbowls;

import com.project.mobile_application.sixbowls.Model.Bowl;
import com.project.mobile_application.sixbowls.Model.BowlStandard;
import com.project.mobile_application.sixbowls.Model.Constants;
import com.project.mobile_application.sixbowls.Model.GameBoard;
import com.project.mobile_application.sixbowls.Model.PersonalSet;
import com.project.mobile_application.sixbowls.Model.PersonalSetStandard;
import com.project.mobile_application.sixbowls.Model.Player;
import com.project.mobile_application.sixbowls.Model.Tray;
import com.project.mobile_application.sixbowls.Model.TrayStandard;

import java.util.ArrayList;

/**
 * Created by Martino on 09/01/2015.
 */
public class GameBoardFactory {

    public GameBoard getGameBoard(String configuration){

        GameBoard gameboard = null;

        if( configuration.equals("STANDARD")){
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
            PersonalSet setPlayerOne = new PersonalSetStandard(playerOne, bowlsPlayerOne, trayPlayerOne, true);
            PersonalSet setPlayerTwo = new PersonalSetStandard(playerTwo, bowlsPlayerTwo, trayPlayerTwo, false);
            gameboard = new GameBoard(setPlayerOne,setPlayerTwo);
        }
        return gameboard;
    }


}
