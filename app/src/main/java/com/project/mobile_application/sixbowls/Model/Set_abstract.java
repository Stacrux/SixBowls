package com.project.mobile_application.sixbowls.Model;

import java.util.ArrayList;

/**
 * Created by Martino on 04/01/2015.
 * A set is what a player need to play a match, it connect one player to his bowls and tray
 * and has the method for the "seeding" move within itself (so not passing through other players' sets)
 */
public abstract class Set_abstract {

        protected Player player;
        protected ArrayList<Bowl_abstract> bowls;
        protected Tray_abstract tray;
        protected int seeds_moving;
        protected boolean is_active;
        protected boolean will_be_active = false;
        protected int last_bowl_filled_id = -1;

    /**method for moving the seeds within the set, the whole content of the selected bowl must be removed
     * and dropped in the next bowls, note that every bowl has a numeric identifier. This method must also decide who is
     * the next player that play.
     * @param bowl_identifier : the bowl where the seeding move starts
     * @param seeds_moving : number of seeds currently picked up
     * @return the number of seeds still pending to be dropped
     */
    public int inner_seeding(int bowl_identifier, int seeds_moving){
        return 0;
    }

}
