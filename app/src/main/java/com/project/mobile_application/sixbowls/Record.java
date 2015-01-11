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


    public int getNumberOfVictories() {
        return numberOfVictories;
    }

    public void setNumberOfVictories(int numberOfVictories) {
        this.numberOfVictories = numberOfVictories;
    }

    public int getNumberOfLost() {
        return numberOfLost;
    }

    public void setNumberOfLost(int numberOfLost) {
        this.numberOfLost = numberOfLost;
    }

    public int getNumberOfTie() {
        return numberOfTie;
    }

    public void setNumberOfTie(int numberOfTie) {
        this.numberOfTie = numberOfTie;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public void setNumberOfMatches(int numberOfMatches) {
        this.numberOfMatches = numberOfMatches;
    }

    public int getNumberOfSeeds() {
        return numberOfSeeds;
    }

    public void setNumberOfSeeds(int numberOfSeeds) {
        this.numberOfSeeds = numberOfSeeds;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
