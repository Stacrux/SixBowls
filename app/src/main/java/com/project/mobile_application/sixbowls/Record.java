package com.project.mobile_application.sixbowls;

import com.project.mobile_application.sixbowls.Model.Player;

/**
 * Created by Martino on 11/01/2015.
 */
public class Record {

    private String playerName;
    private int numberOfVictories;
    private int numberOfLost;
    private int numberOfTie;
    private int numberOfMatches;
    private int numberOfSeeds;

    public Record(String playerName, int numberOfVictories, int numberOfLost, int numberOfTie, int numberOfMatches, int numberOfSeeds){
        this.playerName = playerName;
        this.numberOfVictories = numberOfVictories;
        this.numberOfLost = numberOfLost;
        this.numberOfTie = numberOfTie;
        this.numberOfMatches  = numberOfMatches;
        this.numberOfSeeds = numberOfSeeds;
    }

    public int getNumberOfVictories() {
        return numberOfVictories;
    }
    public int getNumberOfLost() {
        return numberOfLost;
    }
    public int getNumberOfTie() {
        return numberOfTie;
    }
    public int getNumberOfMatches() {
        return numberOfMatches;
    }
    public int getNumberOfSeeds() {
        return numberOfSeeds;
    }
    public String getPlayerName() {
        return playerName;
    }

}
