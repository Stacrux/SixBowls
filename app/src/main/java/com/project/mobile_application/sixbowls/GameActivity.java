package com.project.mobile_application.sixbowls;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.project.mobile_application.sixbowls.Model.Bowl;
import com.project.mobile_application.sixbowls.Model.Constants;
import com.project.mobile_application.sixbowls.Model.GameBoard;
import com.project.mobile_application.sixbowls.Model.Player;
import com.project.mobile_application.sixbowls.Model.StandardGame.BowlStandard;
import com.project.mobile_application.sixbowls.Model.StandardGame.PersonalSetStandard;
import com.project.mobile_application.sixbowls.Model.StandardGame.TrayStandard;

import java.util.ArrayList;


/**
 * Created by Martino on 11/01/2015.
 */
public class GameActivity extends Activity implements View.OnClickListener {

    TextView txt;

    TextView namePlayer1;
    TextView namePlayer2;

    GameBoard board;
    GameBoardFactory factory = new GameBoardFactory();

    ArrayList<Button> bowls1;
    ArrayList<Button> bowls2;

    Button bowl1;
    Button bowl2;
    Button bowl3;
    Button bowl4;
    Button bowl5;
    Button bowl6;
    Button tr1;

    Button bowl7;
    Button bowl8;
    Button bowl9;
    Button bowl10;
    Button bowl11;
    Button bowl12;
    Button tr2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //imposta l'orientamento dello schermo
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //nascondi statusbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String gameType = getIntent().getExtras().getString("gameType");

        board = factory.getGameBoard(gameType);  // create a game board

        bowls1=new ArrayList<Button>();
        bowls1.add((Button)findViewById(R.id.button5));
        bowls1.add((Button)findViewById(R.id.button6));
        bowls1.add((Button)findViewById(R.id.button7));
        bowls1.add((Button)findViewById(R.id.button8));
        bowls1.add((Button)findViewById(R.id.button11));
        bowls1.add((Button)findViewById(R.id.button12));

        bowls2=new ArrayList<Button>();
        bowls2.add((Button)findViewById(R.id.button13));
        bowls2.add((Button)findViewById(R.id.button14));
        bowls2.add((Button)findViewById(R.id.button15));
        bowls2.add((Button)findViewById(R.id.button16));
        bowls2.add((Button)findViewById(R.id.button17));
        bowls2.add((Button)findViewById(R.id.button18));

        tr1 = (Button)findViewById(R.id.button9);
        tr2 = (Button)findViewById(R.id.button10);

        namePlayer1=(TextView)findViewById(R.id.editText);
        namePlayer1.setEnabled(false);
        namePlayer2=(TextView)findViewById(R.id.editText2);
        namePlayer2.setEnabled(false);


       /*
        //bowl1.setOnClickListener(this);
        bowl2 = (Button)findViewById(R.id.button6);
        //bowl2.setOnClickListener(this);

        bowl3 = (Button)findViewById(R.id.button7);
        //bowl3.setOnClickListener(this);

        bowl4 = (Button)findViewById(R.id.button8);
        //bowl4.setOnClickListener(this);

        bowl5 = (Button)findViewById(R.id.button11);
        //bowl5.setOnClickListener(this);

        bowl6 = (Button)findViewById(R.id.button12);
        //bowl6.setOnClickListener(this);

        bowl7 = (Button)findViewById(R.id.button13);
        //bowl7.setOnClickListener(this);
        bowl8 = (Button)findViewById(R.id.button14);
        //bowl8.setOnClickListener(this);
        bowl9 = (Button)findViewById(R.id.button15);
        //bowl9.setOnClickListener(this);
        bowl10 = (Button)findViewById(R.id.button16);
        //bowl10.setOnClickListener(this);
        bowl11 = (Button)findViewById(R.id.button17);
        //bowl11.setOnClickListener(this);
        bowl12 = (Button)findViewById(R.id.button18);
        //bowl12.setOnClickListener(this);

       */

        setOnClick();
        setNamePlayer();
        setBowlEnable(board.toString());


    }

    private void setNamePlayer() {

        namePlayer1.setEnabled(true);
        namePlayer1.setFocusable(true);
        while(namePlayer1.isInEditMode()){ }
        namePlayer1.setEnabled(false);

        namePlayer2.setEnabled(true);
        namePlayer2.setFocusable(true);
        while(namePlayer2.isInEditMode()){ }
        namePlayer2.setEnabled(false);

    }


    @Override
    public void onClick(View v) {

        int index=0;
        int i= Integer.parseInt("" + board.toString().charAt(0));
       if(i==1){
           for(int k=0;k<Constants.numberOfBowls;k++){
               if(bowls1.get(k).getId()==v.getId())index=(5-k);
           }
        }
        else{
            for(int k=0;k<Constants.numberOfBowls;k++){
                if(bowls2.get(k).getId()==v.getId())index=k;
            }
        }

      // moving seeds
        board.seedingPhase(index);

        setBowlEnable(board.toString());
        int isFinish= board.checkGameOver();
        setView(board.toString());
        setBowlEnable(board.toString());

        if((isFinish==1)||isFinish==0||isFinish==2){ /* cosa succede quando finisce la partita */ }

    }

    private void setBowlEnable(String conf){

        if(Integer.parseInt(""+conf.charAt(0))==1){ // is active palyer 1  disable palyer 2
           for(int i=0;i<Constants.numberOfBowls;i++){
               bowls1.get(i).setEnabled(true);
               bowls2.get(i).setEnabled(false);
             }
        }
        else {

            for(int i=0;i<Constants.numberOfBowls;i++) {
                bowls2.get(i).setEnabled(true);
                bowls1.get(i).setEnabled(false);
            }
        }
    }

    private void setOnClick() {

        for(int i=0;i<Constants.numberOfBowls;i++){
            bowls1.get(i).setOnClickListener(this);
            bowls2.get(i).setOnClickListener(this);
        }
    }

    private void setView(String s) {

        int[]bowlTemp1;
        int[]bowlTemp2;

        int middle=s.indexOf("Z");
        String firstMiddle=s.substring(0,middle);
        String secondMiddle=s.substring(middle+1,s.length());

        bowlTemp1=parsString(firstMiddle);
        bowlTemp2=parsString(secondMiddle);

         for(int x=0;x<Constants.numberOfBowls;x++){
             bowls2.get(x).setText(String.valueOf(bowlTemp2[x]));
            bowls1.get(x).setText(String.valueOf(bowlTemp1[5-x]));
         }


        tr1.setText(String.valueOf(bowlTemp1[6]));
        tr2.setText(String.valueOf(bowlTemp2[6]));

     }

    private int[] parsString(String firstMiddle) {

        int[] bowlTemp=new int[7];
        firstMiddle=firstMiddle.substring(2,firstMiddle.length());
        int x;
        for(int i=0;i<5;i++){
            x=firstMiddle.indexOf("B")-1;
            if(x==0)
                bowlTemp[i]=  Integer.parseInt(""+firstMiddle.charAt(x));
            else
                bowlTemp[i]=Integer.parseInt(firstMiddle.substring(0, x + 1));
            firstMiddle=firstMiddle.substring(firstMiddle.indexOf("B") + 1, firstMiddle.length());
        }

        x=firstMiddle.indexOf("T")-1;
        if(x==0)
            bowlTemp[5]= Integer.parseInt(""+firstMiddle.charAt(x));
        else
            bowlTemp[5]=Integer.parseInt(firstMiddle.substring(0, x + 1));

        bowlTemp[6]=Integer.parseInt(""+firstMiddle.substring(firstMiddle.indexOf("T")+1,firstMiddle.length()));

        return  bowlTemp;
    }
}
