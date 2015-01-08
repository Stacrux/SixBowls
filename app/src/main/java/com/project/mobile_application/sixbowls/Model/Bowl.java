package com.project.mobile_application.sixbowls.Model;

/**
 * Created by mattia on 17/11/2014.
 */
public class Bowl extends Bowl_abstract {

    public int getBowl_identifier() {
        return bowl_identifier;
    }

    private int bowl_identifier;
    //= Constants.seeds_initial_bowls;

    public Bowl(int num_seeds, int bowl_identifier)
    {
        this.bowl_identifier = bowl_identifier;
        this.num_seeds = num_seeds;
    }

    @Override
    public void increment_seed_count(int amount) {
        num_seeds += amount;
    }

    @Override
    public void remove_whole_content() {
        num_seeds = 0;
    }

    public int getNum_seeds() {
        return num_seeds;
    }
}
