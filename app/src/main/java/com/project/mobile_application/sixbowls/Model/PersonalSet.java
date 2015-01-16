package com.project.mobile_application.sixbowls.Model;

/**
 * Created by Martino on 04/01/2015.
 * A set is what a player need to play a match, it connect one player to his bowls and tray
 * and has the method for the "seeding" move within itself (so not passing through other players' sets)
 */
public interface PersonalSet {

    /**
     * method for moving the seeds within the set, the seeds received as argument must
     * be dropped in the next bowls (from bowlIdentifier), note that every bowl has a numeric identifier. This method must also decide who is
     * the next player that play.
     *
     * @param bowlIdentifier : the bowl where the seeding move starts
     * @param seeds_moving   : number of seeds currently picked up
     * @return the number of seeds still pending to be dropped
     */
    public int innerSeeding(int bowlIdentifier, int seeds_moving);

    /**
     * method that reveal if the current set is active (belongs to the active player in this turn) or not
     * @return : the state of the set, if this set is active return true else return false
     */
    public boolean isActive();

    /**
     * method that reveal if the last seed was dropped in the tray
     * @return : true if last seed was dropped in the tray, false otherwise
     */
    public boolean isTurnSwitcher();

    /**
     * this method checks all the bowls in this set and return true if they are empty
     * @return : true if every bowl contains no seeds, false otherwise
     */
    public boolean isEmptySetBowls();


    /**
     * method for picking up the whole content from a bowl (final number of seeds must be zero)
     * @param bowlIdentifier : the identifier expressed as integer of the bowl, within this set, that will be emptied
     * @return the number of seeds picked up
     */
    public int pickupSeeds(int bowlIdentifier);

    /**
     * this method check if the last bowl filled was containing no seeds and if there are no pending seeds to be
     * dropped
     * @return : a boolean stating if the last bowl filled was containing no seeds.
     */
    public boolean isLastBowlEmpty();


    /**
     * this method return the identifier of the last bowl filled during the turn
     */
    public int getLastBowlIdentifier();

    /**
     * method which get some seeds and put them directly in the tray
     * @param seedsMoving : the seeds that are still pending to be dropped
     */
    public void moveDirectlyToTray(int seedsMoving);


    /**
     * method that return the current number of seeds inside the tray
     * (this will be checked for establishing who won the game)
     */
    public int getSeedsTray();


    /**
     * setter for parameter active, it depends on the other set state so must be used outside this set
     * @param nextState : next state this set will be
     */
    public void setActive(boolean nextState);
}