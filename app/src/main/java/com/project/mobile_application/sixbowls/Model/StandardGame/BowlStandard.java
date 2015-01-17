package com.project.mobile_application.sixbowls.Model.StandardGame;

import com.project.mobile_application.sixbowls.Model.Bowl;

/**
 * Created by Mattia on 17/11/2014.
 */
public class BowlStandard implements Bowl {


    protected int number_of_seeds;
    private int bowl_identifier;

    public BowlStandard(int numSeeds, int bowlIdentifier)
    {
        this.bowl_identifier = bowlIdentifier;
        this.number_of_seeds = numSeeds;
    }

    /**
     * A bowl must have the ability to return its own identifier
     * @return : bowl unique identifier
     */
    @Override
    public int getBowlIdentifier() { return bowl_identifier; }
    /**
     * A bowl must let a user know how many seeds contain
     * @return : the number of seeds contained
     */
    @Override
    public int getNumSeeds() {
        return number_of_seeds;
    }
    /**
     * method for increasing the number of seeds inside a bowl
     * @param amount : the number of seeds that the caller wants to put in the bowl
     */
    @Override
    public void incrementSeedCount(int amount) {
        number_of_seeds += amount;
    }

    /**
     * method for removing all the seeds inside a bowl, set the content to zero
     */
    @Override
    public void removeWholeContent() {
        number_of_seeds = 0;
    }



}
