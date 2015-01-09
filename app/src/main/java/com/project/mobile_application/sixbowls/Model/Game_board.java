package com.project.mobile_application.sixbowls.Model;

import java.util.ArrayList;

/**
 * Created by Martino on 04/01/2015.
 */
public class Game_board {

    private Set set_player_one;
    private Set set_player_two;
    //boolean variable used to specify who start the game, true means player_one, false means player_two
    private boolean active_player = true;

    /**
     * Constructor of the class, it create two sets
     */
    public Game_board(){
        //Player(identifier expressed as integer)
        Player player_one = new Player(0);
        Player player_two = new Player(1);
        ArrayList<Bowl> bowls_player_one = new ArrayList<Bowl>();
        ArrayList<Bowl> bowls_player_two = new ArrayList<Bowl>();
        for (int e = 0; e < Constants.num_bowls; e++) {
            Bowl bowl = new Bowl(Constants.seeds_initial_bowls, e);
            bowls_player_one.add(bowl);
        }
        for (int e = 0; e < Constants.num_bowls; e++) {
            Bowl bowl = new Bowl(Constants.seeds_initial_bowls, e);
            bowls_player_two.add(bowl);
        }
        Tray tray_player_one = new Tray(Constants.seeds_initial_tray);
        Tray tray_player_two = new Tray(Constants.seeds_initial_tray);
        set_player_one = new Set(player_one, bowls_player_one, tray_player_one, active_player);
        active_player = !active_player;
        set_player_two = new Set(player_two, bowls_player_two, tray_player_two, active_player);
    }


    /**
     * This method takes care of the seeding phase, here the moves within sets are called
     * and the rule about stealing seeds is applied
     * @param bowl_identifier : the id (as integer) of the "touched" bowl on the screen
     * @return : an array of integer stating which bowl is the last one to be filled, [0,x] means the xth bowl in the
     * first set; [1,y] means the yth bowl in the second set
     */
    public int[] seeding_phase(int bowl_identifier){
        //let's declare an identifier for the last bowl filled
        int[] last_bowl_filled = new int[2];
        //number of seeds to be moved and perform the cycle
        int transit_seeds = 0;
        //beginning with player one
        if(set_player_one.is_active){
          transit_seeds = set_player_one.inner_seeding(bowl_identifier,transit_seeds, true);
           //start the seeding phase
          while(transit_seeds > 0){
                transit_seeds = set_player_two.inner_seeding(bowl_identifier,transit_seeds, false);
                transit_seeds = set_player_one.inner_seeding(bowl_identifier,transit_seeds, false);
          }
          //let's check if the last bowl filled belongs to the active player,
          // in that case the seeds from the opponent are stolen
          if(set_player_one.last_bowl_filled_id > -1){
             //now let's check if it contained zero seeds before it has been filled
             int last_bowl_id = set_player_one.last_bowl_filled_id;
             Bowl last_bowl = (Bowl)set_player_one.bowls.get(last_bowl_id);
             if( last_bowl.getNum_seeds() == 1 ){
                set_player_one.tray.increase_seeds_count(1);
                last_bowl.remove_whole_content();
                int stolen_seeds = set_player_two.bowls.get(last_bowl_id).getNum_seeds();
                set_player_one.tray.increase_seeds_count(stolen_seeds);
                set_player_two.bowls.get(last_bowl_id).remove_whole_content();

             }
          }
        }
        //beginning with player two
        else{
          transit_seeds = set_player_two.inner_seeding(bowl_identifier,0, true);
          //start the seeding phase
          while(transit_seeds > 0){
            transit_seeds = set_player_one.inner_seeding(bowl_identifier,transit_seeds, false);
            transit_seeds = set_player_two.inner_seeding(bowl_identifier,transit_seeds, false);
          }
            //let's check if the last bowl filled belongs to the active player,
            // in that case the seeds from the opponent are stolen
            if(set_player_two.last_bowl_filled_id > -1){
                //now let's check if it contained zero seeds before it has been filled
                int last_bowl_id = set_player_two.last_bowl_filled_id;
                Bowl last_bowl = set_player_two.bowls.get(last_bowl_id);
                if( last_bowl.getNum_seeds() == 1 ){
                    set_player_two.tray.increase_seeds_count(1);
                    last_bowl.remove_whole_content();
                    int stolen_seeds = set_player_one.bowls.get(last_bowl_id).getNum_seeds();
                    set_player_two.tray.increase_seeds_count(stolen_seeds);
                    set_player_one.bowls.get(last_bowl_id).remove_whole_content();

                }
            }
        }
        //now we need to specify the return value, it is an array declaring the last set and bowl identifier
        // set_player_one has id = 0, set_player_two has id = 1
        if(set_player_one.last_bowl_filled_id > -1){
            last_bowl_filled[0] = 0;
            last_bowl_filled[1] = set_player_one.last_bowl_filled_id;
            set_player_one.last_bowl_filled_id = -1;
        }
        else if(set_player_two.last_bowl_filled_id > -1){
            last_bowl_filled[0] = 1;
            last_bowl_filled[1] = set_player_two.last_bowl_filled_id;
            set_player_two.last_bowl_filled_id = -1;
        }

        return last_bowl_filled;
    }

    /**
     * Check if the game is over by counting the seeds in the bowls of the last active player
     * according to the rules when he has zero seeds in his bowls the game ends, then he collect
     * all the remaining seeds in the others bowls
     * @return : the id of the winner;
     * 0 for player one, 1 for player 2
     */
    public int check_game_over(){
        boolean game_is_over = true;
        //winner = -1, game still running
        int winner = -1;
        //let's check if there is a Set with all the bowls empty
        for(Bowl bowl : set_player_one.bowls){
           if(bowl.getNum_seeds() > 0){
              game_is_over = false;
           }
        }
        for(Bowl bowl : set_player_two.bowls){
          if(bowl.getNum_seeds() > 0){
            game_is_over = false;
          }
        }
        //if the game is actually over we need to move all the remaining
        //seeds in the correct tray
        if(game_is_over){
            for(Bowl bowl : set_player_one.bowls){
                set_player_one.tray.increase_seeds_count(bowl.getNum_seeds());
                bowl.remove_whole_content();
                }
            for(Bowl bowl : set_player_two.bowls){
                set_player_two.tray.increase_seeds_count(bowl.getNum_seeds());
                bowl.remove_whole_content();
            }
        }
        if(game_is_over && set_player_one.tray.number_of_seeds > set_player_two.tray.number_of_seeds){ winner = 0;}
        else if(game_is_over && set_player_one.tray.number_of_seeds < set_player_two.tray.number_of_seeds){ winner = 1;}
        //let's check if there is no winner, it's a tie
        else if(game_is_over && game_is_over && set_player_one.tray.number_of_seeds == set_player_two.tray.number_of_seeds){ winner = 2;}
        return winner;
    }


    public Set getSet_player_two() {
        return set_player_two;
    }
    public Set getSet_player_one() {
        return set_player_one;
    }
}
