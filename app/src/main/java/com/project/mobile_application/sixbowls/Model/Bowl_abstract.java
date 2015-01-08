package com.project.mobile_application.sixbowls.Model;

/**
 * Created by Martino on 03/01/2015.
 */
public abstract class Bowl_abstract {

    protected int num_seeds;
    private int bowl_identifier;

    //method for increasing the number of seeds inside a bowl
    public void increment_seed_count(int amount){}
    public void remove_whole_content(){}

    public int getBowl_identifier() {
        return bowl_identifier;
    }

    public int getNum_seeds() {
        return num_seeds;
    }
}
