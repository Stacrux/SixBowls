package com.project.mobile_application.sixbowls.Model.StandardGame;

import com.project.mobile_application.sixbowls.Model.Tray;

/**
 * Created by mattia on 17/11/2014.
 */
public class TrayStandard implements Tray {

    protected int numberOfSeeds;
    public TrayStandard(int initialContent){
        this.numberOfSeeds = initialContent;
    }

    @Override
    public int getSeeds() {
        return numberOfSeeds;
    }

    /**
     *  method for increasing the number of seeds contained in a tray
     *  @param amount : the number of seeds that the caller can put in the tray
     */
    @Override
    public void increaseSeedsCount(int amount) {
        numberOfSeeds += amount;
    }
}
