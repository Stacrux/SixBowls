package com.project.mobile_application.sixbowls;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.project.mobile_application.sixbowls.Model.MatchResult;

import java.util.List;

/**
 * Created by Martino on 11/01/2015.
 */
public class RecordsActivity extends Activity {

    DataBaseHelper database = new DataBaseHelper(this);

    Record dummyRecord = new Record("Giacomo",3,2,1,6,20);
    Record dummyRecord2 = new Record("Ignazio",5,2,1,8,30);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        //imposta l'orientamento dello schermo
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //nascondi statusbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //DA RIMUOVERE DOPO CHE ABBIAMO APPURATO IL FUNZIONAMENTO
        this.deleteDatabase("GameRecords");
        database.addRecord(dummyRecord, MatchResult.LOST);
        database.addRecord(dummyRecord2, MatchResult.LOST);
        //DA RIMUOVERE DOPO CHE ABBIAMO APPURATO IL FUNZIONAMENTO


        //load the list of records in memory
        List<Record> records = database.getAllRecords();

        //create a Listview
        ListView recordsListView = (ListView) findViewById(R.id.record_list);

        for(Record record : records){
            TextView recordTextView = new TextView(this);
            recordTextView.setText(record.getPlayerName()+" "+
                                   "WIN : " + record.getNumberOfVictories() +
                                    "LOST : " + record.getNumberOfLost() +
                                    "TIE :  " + record.getNumberOfTie() +
                                    "TOTAL : " + record.getNumberOfMatches() +
                                    "SEEDS MAX : " + record.getNumberOfSeeds());

            recordsListView.addHeaderView(recordTextView);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
