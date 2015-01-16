package com.project.mobile_application.sixbowls;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import com.project.mobile_application.sixbowls.Model.Constants;
import com.project.mobile_application.sixbowls.Model.GameBoard;
import com.project.mobile_application.sixbowls.Model.MatchResult;
import com.project.mobile_application.sixbowls.Model.Settings;


import java.util.ArrayList;


/**
 * Created by Martino on 11/01/2015.
 */
public class GameActivity extends Activity implements View.OnClickListener {

    Settings settings = new Settings();
    DataBaseHelper database = new DataBaseHelper(this);

    String name=null;
    EditText namePlayer1;
    EditText namePlayer2;

    GameBoard board;
    GameBoardFactory factory = new GameBoardFactory();

    ArrayList<Button> bowls1;
    ArrayList<Button> bowls2;

    Button trayP1;
    Button trayP2;

    Button startGame;

    Thread animation = new Thread();

    /**
     * Oncreate method for this activity, it adds bowls and trays as buttons,
     * sets the listener for the bowls and set which buttons are enabled and which not
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //set sceen orientation
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String gameType = getIntent().getExtras().getString("gameType");

        // create a game board
        board = factory.getGameBoard(gameType);

        bowls1=new ArrayList<Button>();
        bowls1.add((Button)findViewById(R.id.bowl_p1_0));
        bowls1.add((Button)findViewById(R.id.bowl_p1_1));
        bowls1.add((Button)findViewById(R.id.bowl_p1_2));
        bowls1.add((Button)findViewById(R.id.bowl_p1_3));
        bowls1.add((Button)findViewById(R.id.bowl_p1_4));
        bowls1.add((Button)findViewById(R.id.bowl_p1_5));

        bowls2=new ArrayList<Button>();
        bowls2.add((Button)findViewById(R.id.bowl_p2_0));
        bowls2.add((Button)findViewById(R.id.bowl_p2_1));
        bowls2.add((Button)findViewById(R.id.bowl_p2_2));
        bowls2.add((Button)findViewById(R.id.bowl_p2_3));
        bowls2.add((Button)findViewById(R.id.bowl_p2_4));
        bowls2.add((Button)findViewById(R.id.bowl_p2_5));

        trayP1 = (Button)findViewById(R.id.tray_p1);
        trayP2 = (Button)findViewById(R.id.tray_p2);

        for(int i=0;i<Constants.numberOfBowls;i++){
            bowls1.get(i).setOnClickListener(this);
            bowls2.get(i).setOnClickListener(this);
        }

        setBowlsEnabled("ALL DISABLED");
        startGame = (Button)findViewById(R.id.start_game);
        startGame.setOnClickListener(this);
        namePlayer1 = (EditText)findViewById(R.id.name_p1);
        namePlayer2 = (EditText)findViewById(R.id.name_p2);
        namePlayer1.setText("SET PLAYER ONE NAME");
        namePlayer2.setText("SET PLAYER TWO NAME");
    }

    /**
     * this method perform the actions associated to the buttons, before the game starts the only
     * enabled button is "start game", the actions for starting a game are expressed into the first
     * if branch. Then the button "start game" becomes disabled and all the bowls get enabled.
     * The actions for a bowl touched events are stated in the else branch.
     * @param v : the view object pressed on the screen
     */
    @Override
    public void onClick(View v) {
        //if the game is not started yet let's make the preparations
        if( v.getId() == R.id.start_game ){
            if( namePlayer1.getText().toString().equals("SET PLAYER ONE NAME") ){
                namePlayer1.setText("PLAYER_ONE");
            }
            if(namePlayer2.getText().toString().equals("SET PLAYER TWO NAME")){
                namePlayer2.setText("PLAYER_TWO");
            }
            if( noNamingProblems() ){
                namePlayer1.setEnabled(false);
                namePlayer2.setEnabled(false);
                startGame.setEnabled(false);
                if( board.toString().charAt(0) == '1'){
                    startGame.setText(namePlayer1.getText() + "'s TURN");
                    startGame.setBackgroundResource(R.drawable.p1_tray);
                }else{
                    startGame.setText(namePlayer2.getText() + "'s TURN");
                    startGame.setBackgroundResource(R.drawable.p2_tray);
                }
                setBowlsEnabled(board.toString());
            }
        }
        //otherwise the game has already been started, here is the normal move
        else{
            board.seedingPhase(castBowlID( v.getId()));
            int isFinished = board.checkGameOver();
            setView(board.toString());
            setBowlsEnabled(board.toString());
            if((isFinished==1)||isFinished==0||isFinished==2){
                updateDatabase(isFinished);
                endingAlert(isFinished);
                setBowlsEnabled("ALL DISABLED");
            }
        }
    }

    /**
     * this method checks the names inserted, a valid
     * @return : false if there are issues, true otherwise
     */
    private boolean noNamingProblems() {
        boolean namesAreAccepted = true;
        String nameP1 = namePlayer1.getText().toString();
        String nameP2 = namePlayer2.getText().toString();

        //checks if the two players have inserted the same names
        if( nameP1.equals(nameP2) ){
            namesAreAccepted = false;
            //alert box stating that the players must have different nicknames
            AlertDialog.Builder alertDlg= new AlertDialog.Builder(this);
            alertDlg.setMessage(" Players can't have the same name!!");
            alertDlg.setCancelable(true);
            alertDlg.setPositiveButton("OK", null);
            alertDlg.create().show();
        }

        //check if the two players have used forbidden chars
        for( int e = 0; e < nameP1.length(); e++){
            if(nameP1.charAt(e) == ' '){
                namesAreAccepted = false;
                //alert box stating that names cannot contain blank spaces
                AlertDialog.Builder alertDlg= new AlertDialog.Builder(this);
                alertDlg.setMessage(" Names cannot contain blank spaces");
                alertDlg.setCancelable(true);
                alertDlg.setPositiveButton("OK", null);
                alertDlg.create().show();
            }
        }
        for( int e = 0; e < nameP2.length(); e++) {
            if (nameP2.charAt(e) == ' ') {
                namesAreAccepted = false;
                //alert box stating that names cannot contain blank spaces
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
                alertDlg.setMessage(" Names cannot contain blank spaces");
                alertDlg.setCancelable(true);
                alertDlg.setPositiveButton("OK", null);
                alertDlg.create().show();
            }
        }

        //checks if the one of the names inserted is an empty string
        if( nameP1.length() == 0 || nameP2.length() == 0) {
            namesAreAccepted = false;
            //alert box stating that names cannot contain an empry string
            AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
            alertDlg.setMessage("The names' field cannot be empty");
            alertDlg.setCancelable(true);
            alertDlg.setPositiveButton("OK", null);
            alertDlg.create().show();
        }

        return namesAreAccepted;
    }

    /**
     * method for updating the database called at the end of a match, the data are stored only if
     * a name was set at the beginning
     * @param isFinished : teh result of the match, 0 for player one winning, 1 for player two winning, 2 for a tie
     */
    private void updateDatabase(int isFinished) {
        //saving player one records
        if( !namePlayer1.getText().toString().equals("PLAYER_ONE") ){
            Record recordP1 = new Record( namePlayer1.getText().toString(), 0,0,0,0, Integer.parseInt(trayP1.getText().toString()));
            switch (isFinished){
                case 0 : database.updateRecord(recordP1, MatchResult.WIN); break;
                case 1 : database.updateRecord(recordP1, MatchResult.LOST); break;
                case 2 : database.updateRecord(recordP1, MatchResult.TIE); break;
                default : break;
            }
        }
        //saving player two record
        if( !namePlayer2.getText().toString().equals("PLAYER_TWO") ){
            Record recordP2 = new Record( namePlayer2.getText().toString(), 0,0,0,0, Integer.parseInt(trayP2.getText().toString()));
            switch (isFinished){
                case 0 : database.updateRecord(recordP2, MatchResult.LOST); break;
                case 1 : database.updateRecord(recordP2, MatchResult.WIN); break;
                case 2 : database.updateRecord(recordP2, MatchResult.TIE); break;
                default : break;
            }
        }
    }


    /**
     * this method shows an alert displayng who won the match and getting the users to the initial activity
     * @param finish : the result of the game, 0 for player one winning, 1 for player two winning, 2 for a tie
     */
    private void endingAlert(int finish) {
        AlertDialog.Builder alertDlg= new AlertDialog.Builder(this);
           if( finish == 0) {
               alertDlg.setMessage(namePlayer1.getText().toString() + " is the winner!");
           }else if(finish == 1){
               alertDlg.setMessage(namePlayer2.getText().toString() + " is the winner!");
           }
           else{
               alertDlg.setMessage(" The game ended in a draw ");
           }
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


    /**
     * this method enables all the buttons belonging to the active player and disable all the others
     * @param conf : the configuration of the current game board (gameboard.tostring), special
     * command is conf = ALL DISABLED as expected disable all bowls
     */
    private void setBowlsEnabled(String conf){
        if( conf.equals("ALL DISABLED")){
            for(int i=0;i<Constants.numberOfBowls;i++) {
                bowls1.get(i).setEnabled(false);
                bowls2.get(i).setEnabled(false);
            }
        }else{
            if(Integer.parseInt(""+conf.charAt(0))==1){ // is active player 1  disable player 2
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
    }

    /**
     * this method breaks the gameboard configuration string in the two sets strings and call the method for
     * updating each one
     * @param boardConfiguration : current game board configuration (gameboard.tostring)
     */
    private void setView(String boardConfiguration) {
          
        int middle = boardConfiguration.indexOf("Z");
        String firstHalf = boardConfiguration.substring(0,middle);
        String secondHalf = boardConfiguration.substring(middle+1,boardConfiguration.length());

        setBowlsValues(bowls1, trayP1, firstHalf);
        setBowlsValues(bowls2, trayP2, secondHalf);

        if( firstHalf.charAt(0) == '1'){
            startGame.setText(namePlayer1.getText() + "'s TURN");
            startGame.setBackgroundResource(R.drawable.p1_tray);
        }else{
            startGame.setText(namePlayer2.getText() + "'s TURN");
            startGame.setBackgroundResource(R.drawable.p2_tray);
        }

    }



    /**
     * this methdod set the right values to be displayed inside a line of 6 button (the bowls) and a tray
     * @param bowls : the line of buttons to be updated
     * @param tray : the tray to be updated
     * @param halfSet : a string of a Set configuration (YBXBXBXBXBXBXTX)
     */
    private void setBowlsValues(ArrayList<Button> bowls, Button tray, String halfSet) {
        //String is of type YBXBXBXBXBXBXTX
        //          pointer 0123456789.....
        //this is why we start from position 1 and then we start the for cicle with pointerInsideString++
        int pointerInsideString = 1;
        for(int x = 0; x < Constants.numberOfBowls; x++){
            pointerInsideString++;
            String bowlValue = new String();
            char separator = 'A';
            while( separator != 'B' && separator != 'T' ){
                bowlValue += halfSet.charAt(pointerInsideString);
                pointerInsideString++;
                separator = halfSet.charAt(pointerInsideString);
            }
            bowls.get(x).setText(bowlValue);
        }
        String trayValue = new String();
        //here I have reached the 'T' , let's move on the next char
        pointerInsideString++;
        while(pointerInsideString < halfSet.length()){
            trayValue += halfSet.charAt(pointerInsideString++);
        }
        tray.setText(trayValue);
        
    }

    /**
     * this method cast a specific id in the range of bowls ids to an INT between 0 and 5
     * @param actualID : the identifier specified automatically
     * @return the actual bowl identifier we need to use
     */
    private int castBowlID(int actualID){

        int selectedBowl = -1;

        if(bowls1.get(0).getId() == actualID || bowls2.get(0).getId() == actualID){
            selectedBowl = 0;
        }
        if(bowls1.get(1).getId() == actualID || bowls2.get(1).getId() == actualID){
            selectedBowl = 1;
        }
        if(bowls1.get(2).getId() == actualID || bowls2.get(2).getId() == actualID){
            selectedBowl = 2;
        }
        if(bowls1.get(3).getId() == actualID || bowls2.get(3).getId() == actualID){
            selectedBowl = 3;
        }
        if(bowls1.get(4).getId() == actualID || bowls2.get(4).getId() == actualID){
            selectedBowl = 4;
        }
        if(bowls1.get(5).getId() == actualID || bowls2.get(5).getId() == actualID){
            selectedBowl = 5;
        }
        return selectedBowl;
    }

}
