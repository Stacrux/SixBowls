package com.project.mobile_application.sixbowls.Model.StandardGame;

import com.project.mobile_application.sixbowls.Model.Bowl;
import com.project.mobile_application.sixbowls.Model.Constants;
import com.project.mobile_application.sixbowls.Model.PersonalSet;
import com.project.mobile_application.sixbowls.Model.Player;
import com.project.mobile_application.sixbowls.Model.Tray;

import java.util.ArrayList;

/**
 * Created by Martino on 04/01/2015.
 * A set is what a player need to play a match, it connect one player to his bowls and tray
 * and has the method for the "seeding" move within itself (so not passing through other players' sets)
 *
 */
public class PersonalSetStandard implements PersonalSet {

    private Player player;
    private ArrayList<Bowl> bowls;
    private Tray tray;
    //the field active indicates if this set belongs to the active player, the one playing
    //during the turn
    private boolean active;
    private boolean will_be_active = false;
    private boolean lastBowlEmpty = false;
    private int lastBowlIdentifier = 0;

    public PersonalSetStandard(Player player, ArrayList<Bowl> bowls, Tray tray, boolean active){
        this.bowls = bowls;
        this.player = player;
        this.tray = tray;
        this.active = active;
    }

    /**
     * method that reveal if the current set is active (belongs to the active player in this turn) or not
     * @return : the state of the set, if this set is active return true else return false
     */
    @Override
    public boolean isActive(){
        return active;
    }

    /**
     * this method checks all the bowls in this set and return true if they are empty
     * @return : true if every bowl contains no seeds, false otherwise
     */
    @Override
    public boolean isEmptySetBowls(){
        boolean emptySetBowls = true;
        for(Bowl bowl : this.bowls){
            if(bowl.getNum_seeds() > 0){
                emptySetBowls = false;
            }
        }
        return emptySetBowls;
    }

    /**
     * method for picking up the whole content from a bowl (final number of seeds must be zero)
     * @param bowlIdentifier : the identifier expressed as integer of the bowl, within this set, that will be emptied
     * @return the number of seeds picked up
     */
    @Override
    public int pickupSeeds(int bowlIdentifier) {
        int seedMoving = 0;
        for (Bowl bowl : this.bowls) {
            if (bowl.getBowlIdentifier() == bowlIdentifier) {
                seedMoving = bowl.getNum_seeds();
                bowl.remove_whole_content();
            }
        }
        return seedMoving;
    }

    /**
     * this method check if the last bowl filled was containing no seeds and if there are no pending seeds to be
     * dropped
     * @return : a boolean stating if the last bowl filled was containing no seeds.
     */
    @Override
    public boolean isLastBowlEmpty() {
        return lastBowlEmpty;
    }

    /**
     * this method return the identifier of the last bowl filled during the turn
     */
    @Override
    public int getLastBowlIdentifier() {
        return lastBowlIdentifier;
    }

    /**
     * method which get some seeds and put them directly in the tray
     * @param seedsMoving : the seeds that are still pending to be dropped
     */
    @Override
    public void moveDirectlyToTray(int seedsMoving) {
        tray.increase_seeds_count(seedsMoving);
    }


    /**
     * method that return the current number of seeds inside the tray
     * (this will be checked for establishing who won the game)
     */
    @Override
    public int getSeedsTray() {
        return 0;
    }

    /**
     * This method is used for moving seeds within a Set. (A Set is a collection of six bowls and one tray connected to
     * a specific player). Here is also applied the rule stating that if the active player ends its turn into its own tray
     * he gain an additional turn.
     * @param bowlIdentifier : the bowl where the seeding move starts
     * @param seedsMoving : number of seeds currently picked up
     * @return : the seeds that are still pending to be dropped (this will be thrown to the next Set)
     */
    @Override
    public int innerSeeding(int bowlIdentifier, int seedsMoving) {
        //save the number of moving seeds that will be returned, for being moved in the next set
        int seedsMovingTemp = seedsMoving;
        //if : is this the set of the active player (the one who chose the move) ?
        if( active && seedsMoving != 0){
            //drop one seed inside each NEXT bowl
            for(int e  = 0 ; e < Constants.numberOfBowls; e++){
                if( bowls.get(e).getBowlIdentifier() > bowlIdentifier && seedsMoving > 0){
                    bowls.get(e).increment_seed_count(1);
                    seedsMovingTemp -= 1;
                    //if there are no more seeds to be dropped the last bowl filled id is saved
                    //let's check also if the last filled bowl contains just 1 seed, in that case
                    //it means that before contained ZERO seeds. This need to be done only for the
                    //active Set
                    if(seedsMoving == 0 && bowls.get(e).getNum_seeds() == 1){
                        lastBowlIdentifier = e;
                        lastBowlEmpty = true;
                    }
                }
            }
            will_be_active = false;
            //and in the tray as well (only if any seed is available)
            if(seedsMoving > 0){
                tray.increase_seeds_count(1);
                seedsMoving -= 1;
                if(seedsMoving == 0){ will_be_active = true; }
            }
        }
        //otherwise this is the set of the inactive player
        else if(!active && seedsMoving != 0){
            //let's drop the seeds in the bowls
            for(Bowl bowl : this.bowls){
                if( bowl.getBowlIdentifier() >= bowlIdentifier && seedsMoving > 0){
                    bowl.increment_seed_count(1);
                    seedsMovingTemp -= 1;
                }
            }
            will_be_active = true;
        }
        //note: will_be_active is true when :
        //the Set is the active one and the seeding ENDS in its tray
        //the Set is the inactive one
        if( will_be_active && seedsMoving == 0 ){
            active = true;
        }else if( !will_be_active && seedsMoving == 0 ){
            active = false;
        }

        return seedsMovingTemp;
    }

    @Override
    public String toString(){
        String configuration = new String();
        for(int e = 0; e < Constants.numberOfBowls; e++){
            //configuration += Integer.toString(this.bowls.get(e).getNum_seeds();
            configuration.concat("B");
            configuration.concat(Integer.toString(this.bowls.get(e).getNum_seeds()));
        }
        //configuration += Integer.toString(this.tray.getSeeds();
        configuration.concat("T");
        configuration.concat(Integer.toString(this.tray.getSeeds()));
        return configuration;
    }

}
