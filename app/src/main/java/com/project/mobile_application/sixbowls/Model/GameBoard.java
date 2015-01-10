package com.project.mobile_application.sixbowls.Model;

import android.util.Log;

/**
 * Created by Martino on 04/01/2015.
 */
public class GameBoard {

    private PersonalSet setPlayerOne;
    private PersonalSet setPlayerTwo;

    /**
     * Constructor of the class, it create two sets
     */

    public GameBoard(PersonalSet setPlayerOne, PersonalSet setPlayerTwo){
        this.setPlayerOne = setPlayerOne;
        this.setPlayerTwo = setPlayerTwo;
    }


    /**
     * This method takes care of the seeding phase, here the moves within sets are called
     * and the rule about stealing seeds is applied
     * @param bowlIdentifier : the id (as integer) of the "touched" bowl on the screen
     * @return : an array of integer stating which bowl is the last one to be filled, [0,x] means the xth bowl in the
     * first set; [1,y] means the yth bowl in the second set
     */
    public void seedingPhase(int bowlIdentifier){

        //number of seeds to be moved and perform the cycle
        int seedMoving = 0;

        //let's pick up the seeds
        if( setPlayerOne.isActive() ){
            seedMoving = setPlayerOne.pickupSeeds(bowlIdentifier);
        }else{
            seedMoving = setPlayerTwo.pickupSeeds(bowlIdentifier);
        }

        //beginning the seeding phase with player one
        if( setPlayerOne.isActive() ){
           seedMoving = setPlayerOne.innerSeeding(bowlIdentifier + 1, seedMoving);
           //start the seeding loop
          while(seedMoving > 0){
                seedMoving = setPlayerTwo.innerSeeding(0,seedMoving);
                seedMoving = setPlayerOne.innerSeeding(0,seedMoving);
          }
          //let's check if the last bowl filled belongs to the active player,
          // in that case the seeds from the opponent are stolen
          if( setPlayerOne.isLastBowlEmpty()){
             //steal all seeds from opponent symmetric bowl plus one seed of active player
              seedMoving = setPlayerTwo.pickupSeeds(setPlayerOne.getLastBowlIdentifier()) +
              setPlayerOne.pickupSeeds(setPlayerOne.getLastBowlIdentifier());
             //then let's give the seeds to player one
              setPlayerOne.moveDirectlyToTray(seedMoving);
          }
        }
        //beginning with player two
        else{
            seedMoving = setPlayerTwo.innerSeeding(bowlIdentifier + 1, seedMoving);
            //start the seeding phase
            while(seedMoving > 0){
                seedMoving = setPlayerOne.innerSeeding(0,seedMoving);
                seedMoving = setPlayerTwo.innerSeeding(0,seedMoving);
            }
            //let's check if the last bowl filled belongs to the active player,
            // in that case the seeds from the opponent are stolen
            if( setPlayerTwo.isLastBowlEmpty()){
                //steal all seeds from opponent symmetric bowl plus one seed of active player
                seedMoving = setPlayerOne.pickupSeeds(setPlayerTwo.getLastBowlIdentifier()) +
                        setPlayerTwo.pickupSeeds(setPlayerTwo.getLastBowlIdentifier());
                //then let's give the seeds to player one
                setPlayerTwo.moveDirectlyToTray(seedMoving);
            }
        }
    }

    /**
     * Check if the game is over by counting the seeds in the bowls of the last active player
     * according to the rules when he has zero seeds in his bowls the game ends, then he collect
     * all the remaining seeds in the others bowls
     * @return : the id of the winner;
     * 0 for player one, 1 for player 2
     */
    public int checkGameOver(){
        boolean gameOver = false;
        //winner = -1, game still running
        int winner = -1;
        //let's check if there is a Set with all the bowls empty
        if( setPlayerOne.isEmptySetBowls() || setPlayerTwo.isEmptySetBowls()){
            gameOver = true;
        }
        //if the game is actually over we need to move all the remaining
        //seeds in the correct tray
        if(gameOver){
            for(int e = 0 ; e < Constants.numberOfBowls; e++){
                int seedMovingOne = setPlayerOne.pickupSeeds(e);
                int seedMovingTwo = setPlayerTwo.pickupSeeds(e);
                setPlayerOne.moveDirectlyToTray(seedMovingOne);
                setPlayerTwo.moveDirectlyToTray(seedMovingTwo);
            }
            if( setPlayerOne.getSeedsTray() > setPlayerTwo.getSeedsTray() ){
                winner = 0;
            }
            else if( setPlayerTwo.getSeedsTray() > setPlayerOne.getSeedsTray() ){
                winner = 1;
            }
            else{
                winner = 2;
            }
        }
        return winner;
    }

    /**
     * return the current configuration of the gameboard, starting with player one set
     * @return : the string with the current configuration of the gameboard
     * using this format : YB1B2B3B4B5B6T1YB1B2B3B4B5B6T2
     */
    @Override
    public String toString() {
        String configuration = new String();
        configuration.concat(setPlayerOne.toString());
        configuration.concat(setPlayerTwo.toString());
        return configuration;
    }
}
