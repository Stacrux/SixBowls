package com.project.mobile_application.sixbowls.Model;

/**
 * Created by mattia on 17/11/2014.
 */
public class Tray implements Tray_interface {

    protected int number_of_seeds;
    public Tray(int initial_content){
        this.number_of_seeds = initial_content;
    }
    public int getSeeds() {
        return number_of_seeds;
    }

    @Override
    public void increase_seeds_count(int amount) {
        number_of_seeds += amount;
    }
}
