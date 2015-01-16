package com.project.mobile_application.sixbowls.Model.StandardGame;

import com.project.mobile_application.sixbowls.Model.AndroidIntelligence;

import java.util.Random;

/**
 * Created by Martino on 16/01/2015.
 */
public class AndroidIntelligenceStandard implements AndroidIntelligence {
    @Override
    public int chooseBowl(String boardConfiguration) {

        int middle = boardConfiguration.indexOf("Z");
        String firstHalf = boardConfiguration.substring(0,middle);
        String secondHalf = boardConfiguration.substring(middle+1,boardConfiguration.length());

        int selectedBowl = 0;

        if(boardConfiguration.toString().charAt(0) == 1){
            selectedBowl = new Random().nextInt()%6;
        }else{
            selectedBowl = new Random().nextInt()%6;
        }

        return selectedBowl;
    }
}
