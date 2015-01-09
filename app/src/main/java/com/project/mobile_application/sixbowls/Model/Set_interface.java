package com.project.mobile_application.sixbowls.Model;

import java.util.ArrayList;

/**
 * Created by Martino on 04/01/2015.
 * A set is what a player need to play a match, it connect one player to his bowls and tray
 * and has the method for the "seeding" move within itself (so not passing through other players' sets)
 */
public interface Set_interface {

    /**method for moving the seeds within the set, the whole content of the selected bowl must be removed
     * and dropped in the next bowls, note that every bowl has a numeric identifier. This method must also decide who is
     * the next player that play.
     * @param bowl_identifier : the bowl where the seeding move starts
     * @param seeds_moving : number of seeds currently picked up
     * @param first_step : indicate if this is the first step of a seeding phase or it is a step inside a loop
     * @return the number of seeds still pending to be dropped
     */
    public int inner_seeding(int bowl_identifier, int seeds_moving, boolean first_step);

}
