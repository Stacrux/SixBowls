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
     * @param configuration : a string of type YBXBXBXBXBXBXTX Z YBxBxBxBxBxBxTx
     */
    public GameBoard generateBoard(String configuration){

        boolean active;
        int middle=configuration.indexOf("Z");
        String firstMiddle=configuration.substring(0,middle);
        String secondMiddle=configuration.substring(middle+1,configuration.length());

        if(Integer.parseInt(""+firstMiddle.charAt(0))==1)
            active=true;
        else
             active=false;

        playerOne=new Player(0);

        bowlsOne=new ArrayList<Bowl>();
        setBowls(bowlsOne,firstMiddle);
        trayOne=new TrayStandard(Integer.parseInt(""+firstMiddle.substring(firstMiddle.indexOf("T")+1,firstMiddle.length())));
        setPlayerOne=new PersonalSetStandard(playerOne,bowlsOne,trayOne,active);

            if(Integer.parseInt(""+secondMiddle.charAt(0))==1)
              active=true;
            else
              active=false;

        playerTwo=new Player(1);
        bowlsTwo=new ArrayList<Bowl>();
        setBowls(bowlsTwo,secondMiddle);
        trayTwo=new TrayStandard(Integer.parseInt(""+secondMiddle.substring(secondMiddle.indexOf("T")+1,secondMiddle.length())));
        setPlayerTwo=new PersonalSetStandard(playerTwo,bowlsTwo,trayTwo,active);

        gameboard=new GameBoard(setPlayerOne,setPlayerTwo);

        return gameboard;
    }



    private void setBowls(ArrayList<Bowl> bowls,String sub) {

        int[]bowlTemp= new int[6];
    int x=-1;

    sub=sub.substring(2,sub.length());
    for(int i=0;i<5;i++){
        x=sub.indexOf("B")-1;
        if(x==0)
            bowlTemp[i]=  Integer.parseInt(""+sub.charAt(x));
        else
            bowlTemp[i]=Integer.parseInt(sub.substring(0,x+1));
        sub=sub.substring(sub.indexOf("B")+1,sub.length());
    }

    x=sub.indexOf("T")-1;
    if(x==0)
    bowlTemp[5]= Integer.parseInt(""+sub.charAt(x));
    else
    bowlTemp[5]=Integer.parseInt(sub.substring(0,x+1));


    for (int e = 0; e < Constants.numberOfBowls; e++) {
        BowlStandard bowl = new BowlStandard(bowlTemp[e], e);
        bowls.add(bowl);
    }
}


}
