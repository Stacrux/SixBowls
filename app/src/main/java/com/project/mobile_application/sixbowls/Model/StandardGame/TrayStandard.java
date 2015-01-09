package com.project.mobile_application.sixbowls.Model.StandardGame;

import com.project.mobile_application.sixbowls.Model.Tray;

/**
 * Created by mattia on 17/11/2014.
 */
public class TrayStandard implements Tray {

    protected int number_of_seeds;
    public TrayStandard(int initial_content){
        this.number_of_seeds = initial_content;
    }

    @Override
    public int getSeeds() {
        return number_of_seeds;
    }

    /**
     *  method for increasing the number of seeds contained in a tray
     *  @param amount : the number of seeds that the caller can put in the tray
     */
    @Override
    public void increase_seeds_count(int amount) {
        number_of_seeds += amount;
    }
}
