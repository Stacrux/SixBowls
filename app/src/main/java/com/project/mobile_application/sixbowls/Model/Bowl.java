package com.project.mobile_application.sixbowls.Model;

/**
 * Created by Mattia on 17/11/2014.
 */
public class Bowl implements Bowl_interface {


    protected int number_of_seeds;
    private int bowl_identifier;

    public Bowl(int num_seeds, int bowl_identifier)
    {
        this.bowl_identifier = bowl_identifier;
        this.number_of_seeds = num_seeds;
    }
    public int getBowl_identifier() { return bowl_identifier; }
    public int getNum_seeds() {
        return number_of_seeds;
    }


    /**
     * method for increasing the number of seeds inside a bowl
     * @param amount : the number of seeds that the caller wants to put in the bowl
     */
    @Override
    public void increment_seed_count(int amount) {
        number_of_seeds += amount;
    }

    /**
     * method for removing all the seeds inside a bowl, set the content to zero
     */
    @Override
    public void remove_whole_content() {
        number_of_seeds = 0;
    }



}
