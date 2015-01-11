package com.project.mobile_application.sixbowls;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project.mobile_application.sixbowls.Model.MatchResult;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Martino on 11/01/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "GameRecords";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // records table name
    private static final String TABLE_RECORDS = "records";

    // records Table Columns names
    private static final String KEY_ID = "name";
    private static final String KEY_WIN = "victories";
    private static final String KEY_LOST = "lost";
    private static final String KEY_TIE =  "tie";
    private static final String KEY_TOTAL =  "total";
    private static final String KEY_MAX_SEEDS =  "max seeds";

    private static final String[] COLUMNS = {KEY_ID,KEY_WIN,KEY_LOST,KEY_TIE,KEY_TOTAL,KEY_MAX_SEEDS};

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create the records table
        String CREATE_BOOK_TABLE = "CREATE TABLE records ( " +
                "name TEXT PRIMARY KEY, " +
                "victories INTEGER, "+
                "lost INTEGER, " +
                "tie INTEGER, " +
                "total INTEGER, "+
                "max seeds INTEGER )";

        // create books table
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS records");
        // create fresh books table
        this.onCreate(db);
    }


    /**
     * method for inserting a new player to the database
     * @param result : WIN, LOST, TIE
     */
    public void addRecord(Record record, MatchResult result){

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();

        switch (result){
            case WIN:
                values.put(KEY_ID, record.getPlayerName());
                values.put(KEY_WIN, 1);
                values.put(KEY_LOST, 0);
                values.put(KEY_TIE, 0);
                values.put(KEY_TOTAL, 1);
                values.put(KEY_MAX_SEEDS, record.getNumberOfSeeds());
                break;
            case LOST:
                values.put(KEY_ID, record.getPlayerName());
                values.put(KEY_WIN, 0);
                values.put(KEY_LOST, 1);
                values.put(KEY_TIE, 0);
                values.put(KEY_TOTAL, 1);
                values.put(KEY_MAX_SEEDS, record.getNumberOfSeeds());
                break;
            case TIE:
                values.put(KEY_ID, record.getPlayerName());
                values.put(KEY_WIN, 0);
                values.put(KEY_LOST, 0);
                values.put(KEY_TIE, 1);
                values.put(KEY_TOTAL, 1);
                values.put(KEY_MAX_SEEDS, record.getNumberOfSeeds());
                break;
        }

        // 3. insert
        db.insert(TABLE_RECORDS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    /**
     * this method give access to the database returning a Record object
     * @param name : the name of the requested player used as key
     * @return : Record object (name of the player, number of victories, number of lost, number of tie, number of matches played, maximum number of seeds collected)
     */
    public Record getRecord(String name){
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Record record = null;

        // 2. build query
        Cursor cursor =
                db.query(TABLE_RECORDS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(name) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null) {
            cursor.moveToFirst();

            // 4. build record object
            record = new Record();
            record.setPlayerName(cursor.getString(0));
            record.setNumberOfVictories(cursor.getInt(1));
            record.setNumberOfLost(cursor.getInt(2));
            record.setNumberOfTie(cursor.getInt(3));
            record.setNumberOfMatches(cursor.getInt(4));
            record.setNumberOfSeeds(cursor.getInt(5));

        }
        // 5. return record
        return record;
    }


    /**
     * this method update the database of records, if the record passed contains a name not yet used this method create the new row
     * calling addRecord
     * @param newRecord : a new object record, it is mandatory to have playerName and
     *                  numberOfSeeds filled with a right value. The other fields can be null.
     * @param result : WIN, LOST, TIE, one of these three condition
     * @return : i, the result of the operation, see the manual of SQlite for the method update
     */
    public int updateRecord(Record newRecord, MatchResult result) {

        int success = 0;
        Record lastRecord = getRecord(newRecord.getPlayerName());

        if( getRecord(newRecord.getPlayerName()) == null ){
            addRecord(newRecord,result);
        }
        else {
            // 1. get reference to writable DB
            SQLiteDatabase db = this.getWritableDatabase();
            // 2. create ContentValues to add key "column"/value
            ContentValues values = new ContentValues();
            switch (result) {
                case WIN:
                    values.put(KEY_ID, lastRecord.getPlayerName());
                    values.put(KEY_WIN, lastRecord.getNumberOfVictories() + 1);
                    values.put(KEY_LOST, lastRecord.getNumberOfLost());
                    values.put(KEY_TIE, lastRecord.getNumberOfTie());
                    values.put(KEY_TOTAL, lastRecord.getNumberOfMatches() + 1);
                    if (newRecord.getNumberOfSeeds() > lastRecord.getNumberOfSeeds()) {
                        values.put(KEY_MAX_SEEDS, newRecord.getNumberOfSeeds());
                    } else {
                        values.put(KEY_MAX_SEEDS, lastRecord.getNumberOfSeeds());
                    }
                    break;
                case LOST:
                    values.put(KEY_ID, lastRecord.getPlayerName());
                    values.put(KEY_WIN, lastRecord.getNumberOfVictories());
                    values.put(KEY_LOST, lastRecord.getNumberOfLost() + 1);
                    values.put(KEY_TIE, lastRecord.getNumberOfTie());
                    values.put(KEY_TOTAL, lastRecord.getNumberOfMatches() + 1);
                    if (newRecord.getNumberOfSeeds() > lastRecord.getNumberOfSeeds()) {
                        values.put(KEY_MAX_SEEDS, newRecord.getNumberOfSeeds());
                    } else {
                        values.put(KEY_MAX_SEEDS, lastRecord.getNumberOfSeeds());
                    }
                    break;
                case TIE:
                    values.put(KEY_ID, lastRecord.getPlayerName());
                    values.put(KEY_WIN, lastRecord.getNumberOfVictories());
                    values.put(KEY_LOST, lastRecord.getNumberOfLost());
                    values.put(KEY_TIE, lastRecord.getNumberOfTie() + 1);
                    values.put(KEY_TOTAL, lastRecord.getNumberOfMatches() + 1);
                    if (newRecord.getNumberOfSeeds() > lastRecord.getNumberOfSeeds()) {
                        values.put(KEY_MAX_SEEDS, newRecord.getNumberOfSeeds());
                    } else {
                        values.put(KEY_MAX_SEEDS, lastRecord.getNumberOfSeeds());
                    }
                    break;
            }
            // 3. updating row
            success = db.update(TABLE_RECORDS, //table
                    values, // column/value
                    KEY_ID + " = ?", // selections
                    new String[]{String.valueOf(newRecord.getPlayerName())}); //selection args
            // 4. close
            db.close();
        }
        return success;
    }

    /**
     * This method receive a record ande delete it from the database
     * @param record : a record, it is mandatory to have the field playerName filled, other fields can be null
     */
    public void deleteBook(Record record) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_RECORDS, //table name
                KEY_ID+" = ?",  // selections
                new String[] { String.valueOf(record.getPlayerName()) }); //selections args

        // 3. close
        db.close();

    }


    /**
     * this method gives a list of all the rows in the database
     * @return : a list of records
     */
    public List<Record> getAllRecords() {
        List<Record> recordList = new LinkedList<Record>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_RECORDS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Record record = null;
        if (cursor.moveToFirst()) {
            do {
                record = new Record();
                record.setPlayerName(cursor.getString(0));
                record.setNumberOfVictories(cursor.getInt(1));
                record.setNumberOfLost(cursor.getInt(2));
                record.setNumberOfTie(cursor.getInt(3));
                record.setNumberOfMatches(cursor.getInt(4));
                record.setNumberOfSeeds(cursor.getInt(5));

                // Add book to books
                recordList.add(record);
            } while (cursor.moveToNext());
        }

        // return records
        return recordList;
    }

}
