package com.project.mobile_application.sixbowls.Model;

import java.util.ArrayList;

/**
 * Created by mattia on 17/11/2014.
 */
public class Player {

    public int getTurn_identifier() {
        return turn_identifier;
    }
    private int turn_identifier;
    public Player(int turn_identifier){
        this.turn_identifier = turn_identifier;
    }

}
