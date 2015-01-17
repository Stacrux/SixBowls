package com.project.mobile_application.sixbowls.Model;

/**
 * Created by Martino on 04/01/2015.
 */
public interface Tray {

    /**
     *  method for increasing the number of seeds contained in a tray
     *  @param amount : the number of seeds that the caller can put in the tray
     */
   public void increaseSeedsCount(int amount);

   public int getSeeds();
}
