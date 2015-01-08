package com.project.mobile_application.sixbowls.Model;

import java.util.ArrayList;

/**
 * Created by Martino on 04/01/2015.
 * A set is what a player need to play a match, it connect one player to his bowls and tray
 * and has the method for the "seeding" move within itself (so not passing through other players' sets)
 *
 */
public class Set extends Set_abstract {

    public Set(Player player, ArrayList<Bowl_abstract> bowls, Tray tray, boolean is_active ){
        this.bowls = bowls;
        this.player = player;
        this.tray = tray;
        this.is_active = is_active;
    }

    /**
     * This method is used for moving seeds within a Set. (A Set is a collection of six bowls and one tray connected to
     * a specific player). Here is also applied the rule stating that if the active player ends its turn into its own tray
     * he gain an additional turn.
     * @param bowl_identifier : the bowl where the seeding move starts
     * @param seeds_moving : number of seeds currently picked up
     * @return : the seeds that are still pending to be dropped (this will be thrown to the next Set)
     */
    @Override
    public int inner_seeding(int bowl_identifier, int seeds_moving) {
        //save the number of moving seeds that will be returned, for being moved in the next set
        this.seeds_moving = seeds_moving;
        //if : is this the set of the active player (the one who chose the move) ?
        if( is_active ){
            //let's pick up the whole content of the selected bowl
            for(Bowl_abstract bowl : this.bowls){
                if( bowl.getBowl_identifier() == bowl_identifier ){
                    this.seeds_moving = bowl.getNum_seeds();
                    bowl.remove_whole_content();
                }
            }
            //and drop it in the NEXT bowls
            for(Bowl_abstract bowl : this.bowls){
                if( bowl.getBowl_identifier() > bowl_identifier && seeds_moving > 0){
                    bowl.increment_seed_count(1);
                    this.seeds_moving -= 1;
                }
                if(seeds_moving == 0){
                    last_bowl_filled_id = bowl.getBowl_identifier();
                }
            }
            will_be_active = false;
            //and in the tray as well (only if any seed is available)
            if(seeds_moving > 0){
                tray.increase_seeds_count(1);
                seeds_moving -= 1;
                if(seeds_moving == 0){ will_be_active = true; }
            }
        }
        //otherwise this is the set of the inactive player, in this case the value seeds_moving come from a past method
        else{
            //let's drop the seeds in the bowls
            for(Bowl_abstract bowl : this.bowls){
                if( bowl.getBowl_identifier() >= bowl_identifier && seeds_moving > 0){
                    bowl.increment_seed_count(1);
                    this.seeds_moving -= 1;
                    if(seeds_moving == 0){
                        last_bowl_filled_id = bowl.getBowl_identifier();
                    }
                }
            }
            will_be_active = true;
        }

        //note: will_be_active is true when :
        //the Set is the active one and the seeding ENDS in its tray
        //the Set is the inactive one
        if( will_be_active && seeds_moving == 0 ){
            is_active = true;
        }else if( !will_be_active && seeds_moving == 0 ){
            is_active = false;
        }

        return this.seeds_moving;
    }
}
