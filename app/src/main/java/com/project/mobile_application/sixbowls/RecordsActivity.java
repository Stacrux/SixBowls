package com.project.mobile_application.sixbowls;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        //set the screen into landscape mode
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        deleteButton = (Button)findViewById(R.id.delete_all_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( v.getId() == R.id.delete_all_button){
                    dropRecords();
                }
            }
        });

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

    private void dropRecords() {

        this.deleteDatabase("GameRecords");
        //alert box stating that names cannot contain blank spaces
        AlertDialog.Builder alertDlg= new AlertDialog.Builder(this);
        alertDlg.setMessage(" All data have been removed");
        alertDlg.setCancelable(true);
        alertDlg.setPositiveButton("OK", null);
        alertDlg.create().show();

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
