package com.project.mobile_application.sixbowls.test;

import com.project.mobile_application.sixbowls.Model.Bowl;
import com.project.mobile_application.sixbowls.Model.PersonalSet;
import com.project.mobile_application.sixbowls.Model.Player;
import com.project.mobile_application.sixbowls.Model.StandardGame.PersonalSetStandard;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by MattiaBenzoni on 10/01/2015.
 */
public class PersonalSetTest extends TestCase {

    PersonalSet personalSet;
    Player player;
    ArrayList<Bowl>bowls=new ArrayList<Bowl>();


    @Override
    protected void setUp() throws Exception{

    }

    /**
     * The first test for innerSeeding method
     */
    public void innerSeedingTest(){
        personalSet = initialization();
    }

    @Override
    protected void tearDown(){

    }

    private PersonalSetStandard initialization(){
       player=new Player(0);

       return null;
    }
}
