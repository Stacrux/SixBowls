package com.project.mobile_application.sixbowls.Model.StandardGame;

import com.project.mobile_application.sixbowls.Model.AndroidIntelligence;
import com.project.mobile_application.sixbowls.Model.Constants;

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

        int[] myConfig=new int[6];
        int[] opponentConfig=new int[6];

        stringAnalyzer(myConfig,secondHalf);
        stringAnalyzer(opponentConfig,firstHalf);

        int selectedBowl = 0;
        int possiblewin=-1;
        boolean found=false;


       do {
           selectedBowl = Math.abs(new Random().nextInt() % 6);
       }while(myConfig[selectedBowl]==0);
     /*
        if(boardConfiguration.toString().charAt(0) == 1){
            selectedBowl = new Random().nextInt()%6;
        }else{
            selectedBowl = new Random().nextInt()%6;
        }
          */
        if(!found) {  // first control: if there is a bowl that permit to steal a seeds from opponent bowl, I choose it
            for (int i = 0; i < Constants.numberOfBowls - 1; i++) {
                if ((myConfig[i] + i < 6) && myConfig[myConfig[i] + i] == 0) {
                    if (opponentConfig[i] + myConfig[i] > 0 && opponentConfig[i] + myConfig[i] > possiblewin &&myConfig[i]>0) {
                        possiblewin = opponentConfig[i] + myConfig[i];
                        selectedBowl = i;
                        found = true;
                    }
                }
            }
        }

        if(!found){  // if there is a bowl that permit to put the last seeds in tray: I choose it
            for(int i=0;i<Constants.numberOfBowls;i++){
                if(myConfig[i]+i==6&&myConfig[i]>0){
                    found=true;
                    selectedBowl=i;
                }
            }
        }

        if(!found){  // select a bowl that permit to put all the seeds in the current user bowl
            for(int i=0;i<Constants.numberOfBowls;i++){
                if(myConfig[i]+i<6&&myConfig[i]>0){
                    selectedBowl=i;
                }
            }
        }
      return selectedBowl;
    }





  private void stringAnalyzer(int[] config,String halfSet) {
      int pointerInsideString = 1;

      for (int x = 0; x < Constants.numberOfBowls; x++) {
          pointerInsideString++;
          String bowlValue = new String();
          char separator = 'A';
          while (separator != 'B' && separator != 'T') {
              bowlValue += halfSet.charAt(pointerInsideString);
              pointerInsideString++;
              separator = halfSet.charAt(pointerInsideString);
          }

          config[x]=Integer.parseInt(bowlValue);
      }
   }
}
