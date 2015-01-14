package com.project.mobile_application.sixbowls;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

    String name=null;
    EditText namePlayer1;
    EditText namePlayer2;

    GameBoard board;
    GameBoardFactory factory = new GameBoardFactory();

    ArrayList<Button> bowls1;
    ArrayList<Button> bowls2;

    Button tr1;
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

        setOnClick();

      // -------------------------------------------------------  Per settare i nomi
        //setNamePlayer();
        //namePlayer1.setText(name);
     // ------------------------------------------------------------------------------------
        //disableAll();

        setBowlEnable(board.toString());


    }

  /*  private void disableAll() {

        for(int i=0;i<Constants.numberOfBowls;i++){
            bowls1.get(i).setEnabled(false);
            bowls2.get(i).setEnabled(false);
        }

    }
*/
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

        int isFinish= board.checkGameOver();
        setView(board.toString());
        setBowlEnable(board.toString());

        if((isFinish==1)||isFinish==0||isFinish==2){ OnBack(isFinish);}

    }


    // method to show an alert message
    private void OnBack(int finish) {

        AlertDialog.Builder alertDlg= new AlertDialog.Builder(this);
           if(!(finish==2))alertDlg.setMessage("Player"+(finish+1)+" is the winner!");
           else
              alertDlg.setMessage(" The game ended in a draw ");
        alertDlg.setCancelable(false);
        alertDlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent i=new Intent(GameActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        alertDlg.create().show();
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

    private void setNamePlayer(){

        final EditText input = new EditText(this);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setMessage("Set name palyer 1");

        // Set an EditText view to get user input
        alert.setView(input);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                name=input.getText().toString();
            }
        });

        alert.show();
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
