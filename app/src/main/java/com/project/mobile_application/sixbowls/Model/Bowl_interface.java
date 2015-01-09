package com.project.mobile_application.sixbowls.Model;

/**
 * Created by Martino on 03/01/2015.
 */
public interface Bowl_interface {

    /**
     * method for increasing the number of seeds inside a bowl
     * @param amount : the number of seeds that the caller wants to put in the bowl
     */
    public void increment_seed_count(int amount);

    /**
     * method for removing all the seeds inside a bowl, set the content to zero
     */
    public void remove_whole_content();

}
