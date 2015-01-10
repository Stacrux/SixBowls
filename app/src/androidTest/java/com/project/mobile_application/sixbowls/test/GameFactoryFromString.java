package com.project.mobile_application.sixbowls.test;

import com.project.mobile_application.sixbowls.Model.Bowl;
import com.project.mobile_application.sixbowls.Model.Constants;
import com.project.mobile_application.sixbowls.Model.GameBoard;
import com.project.mobile_application.sixbowls.Model.PersonalSet;
import com.project.mobile_application.sixbowls.Model.Player;
import com.project.mobile_application.sixbowls.Model.StandardGame.BowlStandard;
import com.project.mobile_application.sixbowls.Model.StandardGame.PersonalSetStandard;
import com.project.mobile_application.sixbowls.Model.StandardGame.TrayStandard;
import com.project.mobile_application.sixbowls.Model.Tray;

import java.util.ArrayList;

/**
 * Created by Martino on 10/01/2015.
 */
public class GameFactoryFromString {

    GameBoard gameboard = null;

    private PersonalSet setPlayerOne;
    private PersonalSet setPlayerTwo;
    private Player playerOne;
    private Player playerTwo;
    private ArrayList<Bowl> bowlsOne;
    private ArrayList<Bowl> bowlsTwo;
    private Tray trayOne;
    private Tray trayTwo;



    public GameFactoryFromString(){

    }

    /**
     *
     * @param configuration : a string of type YBXBXBXBXBXBXTX yBxBxBxBxBxBxTx
     */
    public GameBoard generateBoard(String configuration){

        boolean active;

        if(configuration.charAt(0)==1)
            active=true;
        else
             active=false;

        playerOne=new Player(0);

        String sub=configuration.substring(1,6);
        bowlsOne=new ArrayList<Bowl>();
        setBowls(bowlsOne,sub);
        trayOne=new TrayStandard(configuration.charAt(7));
        setPlayerOne=new PersonalSetStandard(playerOne,bowlsOne,trayOne,active);

        if(configuration.charAt(8)==1)
            active=true;
        else
            active=false;
        sub=configuration.substring(9,14);
        playerTwo=new Player(1);
        bowlsTwo=new ArrayList<Bowl>();
        setBowls(bowlsTwo,sub);
        trayTwo=new TrayStandard(configuration.charAt(15));
        setPlayerTwo=new PersonalSetStandard(playerTwo,bowlsTwo,trayTwo,active);

        gameboard=new GameBoard(setPlayerOne,setPlayerTwo);

        return gameboard;
    }

    private void setBowls(ArrayList<Bowl> bowls,String sub) {
        for (int e = 0; e < Constants.numberOfBowls; e++) {
            BowlStandard bowl = new BowlStandard(sub.charAt(e), e);
            bowls.add(bowl);
        }
    }


}
