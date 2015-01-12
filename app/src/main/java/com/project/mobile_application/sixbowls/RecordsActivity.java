package com.project.mobile_application.sixbowls;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.project.mobile_application.sixbowls.Model.MatchResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This activity display all the records from previous matches saved in a database
 * Created by Martino on 11/01/2015.
 */
public class RecordsActivity extends Activity {

    DataBaseHelper database = new DataBaseHelper(this);

    //DA RIMUOVERE DOPO CHE ABBIAMO APPURATO IL FUNZIONAMENTO
    Record dummyRecord = new Record("Giacomo", 3, 2, 1, 6, 20);
    Record dummyRecord2 = new Record("Ignazio", 5, 2, 1, 8, 30);
    Record dummyRecord3 = new Record("Carlo", 5, 2, 1, 8, 44);
    Record dummyRecord4 = new Record("Marco", 5, 2, 7, 14, 13);
    Record dummyRecord5 = new Record("Giorgio", 5, 2, 3, 10, 22);
    //DA RIMUOVERE DOPO CHE ABBIAMO APPURATO IL FUNZIONAMENTO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        //imposta l'orientamento dello schermo
        //setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //nascondi statusbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //DA RIMUOVERE DOPO CHE ABBIAMO APPURATO IL FUNZIONAMENTO
        this.deleteDatabase("GameRecords");
        /*
        database.addRecord(dummyRecord, MatchResult.LOST);
        database.addRecord(dummyRecord2, MatchResult.LOST);
        database.addRecord(dummyRecord3, MatchResult.LOST);
        database.addRecord(dummyRecord4, MatchResult.LOST);
        database.addRecord(dummyRecord5, MatchResult.LOST);
        */
        //DA RIMUOVERE DOPO CHE ABBIAMO APPURATO IL FUNZIONAMENTO

        ArrayList<String> recordsStrings = new ArrayList<String>();
        if (doesDatabaseExist(this, "GameRecords")) {
            //load the list of records in memory
            List<Record> records = database.getAllRecords();
            for (Record record : records) {
                String recordString = record.getPlayerName().toUpperCase() +
                        "\nMatches won : " + record.getNumberOfVictories() +
                        " Matches lost : " + record.getNumberOfLost() +
                        "\nMatches tied :  " + record.getNumberOfTie() +
                        "\nTOTAL : " + record.getNumberOfMatches() +
                        "\nbest Tray : " + record.getNumberOfSeeds();

                recordsStrings.add(recordString);
            }
        } else {
            recordsStrings.add("THERE ARE NO STORED RECORDS YET :)");
        }

        //create a Listview
        ListView recordsListView = (ListView) findViewById(R.id.record_list);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, R.layout.record_row, R.id.textViewList, recordsStrings);


        recordsListView.setAdapter(arrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /**
     * Check if the database exist
     *
     * @return true if it exists, false if it doesn't
     */
    private static boolean doesDatabaseExist(ContextWrapper context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

}
