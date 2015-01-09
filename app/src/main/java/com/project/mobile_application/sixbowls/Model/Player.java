package com.project.mobile_application.sixbowls.Model;

/**
 * Created by mattia on 17/11/2014.
 */
public class Player {

    public int getTurnIdentifier() {
        return turnIdentifier;
    }
    private int turnIdentifier;
    public Player(int turnIdentifier){
        this.turnIdentifier = turnIdentifier;
    }

}
