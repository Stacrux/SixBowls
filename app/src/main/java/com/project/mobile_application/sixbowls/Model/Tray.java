package com.project.mobile_application.sixbowls.Model;

/**
 * Created by mattia on 17/11/2014.
 */
public class Tray extends Tray_abstract {

    public Tray(int initial_content){
        this.seeds = initial_content;
    }

    public int getSeeds() {
        return seeds;
    }
    @Override
    public void increase_seeds_count(int amount) {
        seeds += amount;
    }
}
