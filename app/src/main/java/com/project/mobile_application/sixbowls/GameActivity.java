package com.project.mobile_application.sixbowls;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.project.mobile_application.sixbowls.Model.GameBoard;


/**
 * Created by Martino on 11/01/2015.
 */
public class GameActivity extends Activity implements View.OnClickListener {

    TextView txt;

    GameBoard board;
    GameBoardFactory factory = new GameBoardFactory();

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

        bowl1 = (Button)findViewById(R.id.button5);
        bowl1.setOnClickListener(this);

        bowl2 = (Button)findViewById(R.id.button6);
        bowl2.setOnClickListener(this);

        bowl3 = (Button)findViewById(R.id.button7);
        bowl3.setOnClickListener(this);

        bowl4 = (Button)findViewById(R.id.button8);
        bowl4.setOnClickListener(this);

        bowl5 = (Button)findViewById(R.id.button11);
        bowl5.setOnClickListener(this);

        bowl6 = (Button)findViewById(R.id.button12);
        bowl6.setOnClickListener(this);
        tr1 = (Button)findViewById(R.id.button9);

        bowl7 = (Button)findViewById(R.id.button13);
        bowl7.setOnClickListener(this);
        bowl8 = (Button)findViewById(R.id.button14);
        bowl8.setOnClickListener(this);
        bowl9 = (Button)findViewById(R.id.button15);
        bowl9.setOnClickListener(this);
        bowl10 = (Button)findViewById(R.id.button16);
        bowl10.setOnClickListener(this);
        bowl11 = (Button)findViewById(R.id.button17);
        bowl11.setOnClickListener(this);
        bowl12 = (Button)findViewById(R.id.button18);
        bowl12.setOnClickListener(this);
        tr2 = (Button)findViewById(R.id.button10);

        setBowlEnable(board.toString());


    }

    @Override
    public void onClick(View v) {

        board.seedingPhase(v.getId());
        int isFinish= board.checkGameOver();

        setView(board.toString());
        if((isFinish==1)||isFinish==0||isFinish==2){ /* cosa succede quando finisce la partita */ }
    }

    private void setBowlEnable(String conf){

        if(Integer.parseInt(""+conf.charAt(0))==1){ // is active palyer 1  disable palyer 2
            bowl7.setEnabled(false);
            bowl8.setEnabled(false);
            bowl9.setEnabled(false);
            bowl10.setEnabled(false);
            bowl11.setEnabled(false);
            bowl12.setEnabled(false);

            bowl1.setEnabled(true);
            bowl2.setEnabled(true);
            bowl3.setEnabled(true);
            bowl4.setEnabled(true);
            bowl5.setEnabled(true);
            bowl6.setEnabled(true);
        }
        else {

            bowl7.setEnabled(true);
            bowl8.setEnabled(true);
            bowl9.setEnabled(true);
            bowl10.setEnabled(true);
            bowl11.setEnabled(true);
            bowl12.setEnabled(true);

            bowl1.setEnabled(false);
            bowl2.setEnabled(false);
            bowl3.setEnabled(false);
            bowl4.setEnabled(false);
            bowl5.setEnabled(false);
            bowl6.setEnabled(false);
        }
    }

    private void setView(String s) {

        // metodo per settare la view con la nuova configurazione
    }
}
