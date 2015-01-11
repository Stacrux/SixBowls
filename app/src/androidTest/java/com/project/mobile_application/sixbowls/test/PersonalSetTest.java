package com.project.mobile_application.sixbowls.test;

import com.project.mobile_application.sixbowls.Model.Bowl;
import com.project.mobile_application.sixbowls.Model.Constants;
import com.project.mobile_application.sixbowls.Model.PersonalSet;
import com.project.mobile_application.sixbowls.Model.Player;
import com.project.mobile_application.sixbowls.Model.StandardGame.BowlStandard;
import com.project.mobile_application.sixbowls.Model.StandardGame.PersonalSetStandard;
import com.project.mobile_application.sixbowls.Model.StandardGame.TrayStandard;
import com.project.mobile_application.sixbowls.Model.Tray;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by MattiaBenzoni on 10/01/2015.
 */
public class PersonalSetTest extends TestCase {

    private PersonalSet personalSet;
    private Player player;
    private ArrayList<Bowl>bowls=new ArrayList<Bowl>();
    private Tray tray;



    @Override
    protected void setUp() throws Exception{

    }

    /**
     * The first test for innerSeeding method
     * The bowlId=3 and the number of seeds=10
     */
    public void innerSeedingTest(){

        // 3 3 10 3 3 3   0
        // 3 3 0  4 4 4   1          6

        personalSet = initialization(true);
        int remainder=personalSet.innerSeeding(2,10);

        assertEquals(remainder,6);
        assertEquals(personalSet.toString(),"1B3B3B0B4B4B4T1");
    }

    public void innerSeedingTest1(){



        personalSet = initialization(false);
        int remainder=personalSet.innerSeeding(2,2);

        assertEquals(remainder,0);
        assertEquals(personalSet.toString(),"0B4B4B3B3B3B3T0");
    }

    @Override
    protected void tearDown(){

    }

    private PersonalSetStandard initialization(boolean state){

       PersonalSetStandard personal;
       player=new Player(0);
       for (int e = 0; e < Constants.numberOfBowls; e++) {
            BowlStandard bowl = new BowlStandard(Constants.initialBowlContent, e);
            bowls.add(bowl);
       }
       tray=new TrayStandard(Constants.initialTrayContent);
       personal=new PersonalSetStandard(player,bowls,tray,state);

       return personal;
    }
}
