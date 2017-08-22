package com.fransunisoft.drivingguide.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DrivingGuideDBHelper extends SQLiteOpenHelper {
    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.fransunisoft.drivingguide/databases/";

    private static final int DATABASE_VERSION = 3;

    public static String DB_NAME = "DrivingGuide.db";
    public static String RAW_DB_NAME = "DrivingGuide.db";
    public static String TABLE_GUIDE = "Guide";
    public static String TABLE_TUTORIALS = "Tutorials";

    private SQLiteDatabase myDataBase;


    private final Context myContext;

    /**
     * * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application
     * assets and resources.
     *
     * @param context
     */

    public DrivingGuideDBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        try {
            CopyFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application. * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + RAW_DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            //database dont exist yet
            e.printStackTrace();
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the * system folder, from where it can be accessed and handled. * This is done by transfering bytestream. *
     */
    private void CopyFiles() throws IOException {
        InputStream myInput = myContext.getAssets().open(RAW_DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    private void deleteDataBase() {
        (new File((new StringBuilder(String.valueOf(DB_PATH))).append("DrivingGuide").toString())).delete();
    }

    //open database
    public boolean open() {

        String myPath = DB_PATH + RAW_DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        return true;

    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {


        boolean dbExist = checkDataBase();
        if (dbExist) {
            //do nothing - database already exist
        } else {
            this.getWritableDatabase();
            try {
                CopyFiles();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    public String Signaling () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Signaling = null;
        if (cursor.moveToNext()) {
            Signaling = cursor.getString(2);

        }
        cursor.close();
        myDataBase.close();
        return Signaling;
    }

    public String SignalingTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Signalingtittle = null;
        if (cursor.moveToNext()) {
            Signalingtittle = cursor.getString(1);
        }
        cursor.close();
        myDataBase.close();
        return Signalingtittle;
    }
    public String TailLight () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 2 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String taillight = null;
        if (cursor.moveToNext()) {
            taillight = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return taillight;
    }
    public String TailLightTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 2 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String taillighttittle = null;
        if (cursor.moveToNext()) {
            taillighttittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return taillighttittle;
    }

    public String HeadLight () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 3 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String headlight = null;
        if (cursor.moveToNext()) {
            headlight = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return headlight;
    }
    public String HeadLightTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 3 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String headlighttittle = null;
        if (cursor.moveToNext()) {
            headlighttittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return headlighttittle;
    }
    public String Brakes () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 4 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Brakes = null;
        if (cursor.moveToNext()) {
            Brakes = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Brakes;
    }
    public String BrakesTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 4 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Brakestittle = null;
        if (cursor.moveToNext()) {
            Brakestittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Brakestittle;
    }

    public String Steering () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 5 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Steering = null;
        if (cursor.moveToNext()) {
            Steering = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Steering;
    }
    public String SteeringTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 5 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Steeringtittle = null;
        if (cursor.moveToNext()) {
            Steeringtittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Steeringtittle;
    }

    public String Tires () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 6 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Tires = null;
        if (cursor.moveToNext()) {
            Tires = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Tires;
    }
    public String TiresTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 6 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Tirestittle = null;
        if (cursor.moveToNext()) {
            Tirestittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Tirestittle;
    }

    public String Safety () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 7 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Safety = null;
        if (cursor.moveToNext()) {
            Safety = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Safety;
    }
    public String SafetyTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 7 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Safetytittle = null;
        if (cursor.moveToNext()) {
            Safetytittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Safetytittle;
    }

    public String Wind () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 8 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Wind = null;
        if (cursor.moveToNext()) {
            Wind = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Wind;
    }
    public String WindTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 8 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Windtittle = null;
        if (cursor.moveToNext()) {
            Windtittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Windtittle;
    }

    public String Vision () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 9 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Vision = null;
        if (cursor.moveToNext()) {
            Vision = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Vision;
    }
    public String VisionTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 9 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Visiontittle = null;
        if (cursor.moveToNext()) {
            Visiontittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Visiontittle;
    }

    public String WindShield () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 10 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String WindShield = null;
        if (cursor.moveToNext()) {
            WindShield = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return WindShield;
    }
    public String WindShieldTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 10 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String WindShieldtittle = null;
        if (cursor.moveToNext()) {
            WindShieldtittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return WindShieldtittle;
    }

    public String Seat () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 11 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Seat = null;
        if (cursor.moveToNext()) {
            Seat = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Seat;
    }
    public String SeatTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 11 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Seattittle = null;
        if (cursor.moveToNext()) {
            Seattittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Seattittle;
    }

    public String Horn () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 12 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Horn = null;
        if (cursor.moveToNext()) {
            Horn = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Horn;
    }
    public String HornTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 12 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Horntittle = null;
        if (cursor.moveToNext()) {
            Horntittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Horntittle;
    }

    public String Exhaust () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 13 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Exhaust = null;
        if (cursor.moveToNext()) {
            Exhaust = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Exhaust;
    }
    public String ExhaustTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 13 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Exhausttittle = null;
        if (cursor.moveToNext()) {
            Exhausttittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Exhausttittle;
    }

    public String Mirrors () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 14 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Mirrors = null;
        if (cursor.moveToNext()) {
            Mirrors = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Mirrors;
    }
    public String MirrorsTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 14 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Mirrorstittle = null;
        if (cursor.moveToNext()) {
            Mirrorstittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Mirrorstittle;
    }

    public String Air () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 15 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Air = null;
        if (cursor.moveToNext()) {
            Air = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Air;
    }
    public String AirTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 15 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Airtittle = null;
        if (cursor.moveToNext()) {
            Airtittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Airtittle;
    }

    public String Fuel () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 16 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Fuel = null;
        if (cursor.moveToNext()) {
            Fuel = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Fuel;
    }
    public String FuelTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 16 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Fueltittle = null;
        if (cursor.moveToNext()) {
            Fueltittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Fueltittle;
    }

    public String Mud () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 17 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Mud = null;
        if (cursor.moveToNext()) {
            Mud = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Mud;
    }
    public String MudTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 17 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Mudtittle = null;
        if (cursor.moveToNext()) {
            Mudtittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Mudtittle;
    }

    public String Bumpers () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 18 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Bumpers = null;
        if (cursor.moveToNext()) {
            Bumpers = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Bumpers;
    }
    public String BumpersTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 18 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Bumperstittle = null;
        if (cursor.moveToNext()) {
            Bumperstittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Bumperstittle;
    }

    public String Licence () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 19 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Licence = null;
        if (cursor.moveToNext()) {
            Licence = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Licence;
    }
    public String LicenceTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 19 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Licencetittle = null;
        if (cursor.moveToNext()) {
            Licencetittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Licencetittle;
    }

    public String LicencePlate () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 20 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String LicencePlate = null;
        if (cursor.moveToNext()) {
            LicencePlate = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return LicencePlate;
    }
    public String LicencePlateTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 20 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String LicencePlatetittle = null;
        if (cursor.moveToNext()) {
            LicencePlatetittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return LicencePlatetittle;
    }

    public String Projections () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 21 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Projections = null;
        if (cursor.moveToNext()) {
            Projections = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Projections;
    }
    public String ProjectionsTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 21 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Projectionstittle = null;
        if (cursor.moveToNext()) {
            Projectionstittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Projectionstittle;
    }

    public String Town () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 22 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Town = null;
        if (cursor.moveToNext()) {
            Town = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Town;
    }
    public String TownTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 22 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Towntittle = null;
        if (cursor.moveToNext()) {
            Towntittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Towntittle;
    }

    public String Slow () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 23 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Slow = null;
        if (cursor.moveToNext()) {
            Slow = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Slow;
    }
    public String SlowTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 23 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Slowtittle = null;
        if (cursor.moveToNext()) {
            Slowtittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Slowtittle;
    }

    public String Studden () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 24 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Studden = null;
        if (cursor.moveToNext()) {
            Studden = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Studden;
    }
    public String StuddenTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 24 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Studdentittle = null;
        if (cursor.moveToNext()) {
            Studdentittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Studdentittle;
    }

    public String Window () {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 25 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Window = null;
        if (cursor.moveToNext()) {
            Window = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Window;
    }
    public String WindowTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TUTORIALS + " WHERE " + "_id" + "=" + " 25 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Windowtittle = null;
        if (cursor.moveToNext()) {
            Windowtittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Windowtittle;
    }





















    public String SteeringFailure() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String steeringfailure = null;
        if (cursor.moveToNext()) {
            steeringfailure = cursor.getString(2);
        }
        cursor.close();
        myDataBase.close();
        return steeringfailure;
    }
    public String SteeringFailureTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String steeringfailuretittle = null;
        if (cursor.moveToNext()) {
            steeringfailuretittle = cursor.getString(1);
        }
        cursor.close();
        myDataBase.close();
        return steeringfailuretittle;
    }

    public String Stuck() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 2 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Stuck = null;
        if (cursor.moveToNext()) {
            Stuck = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Stuck;
    }
    public String StuckTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 2 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Stucktittle = null;
        if (cursor.moveToNext()) {
            Stucktittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Stucktittle;
    }

    public String NightDriving() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 3 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String NightDriving = null;
        if (cursor.moveToNext()) {
            NightDriving = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return NightDriving;
    }
    public String NightDrivingTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 3 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String NightDrivingtittle = null;
        if (cursor.moveToNext()) {
            NightDrivingtittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return NightDrivingtittle;
    }

    public String BrakeFailure() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 4 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String BrakeFailure = null;
        if (cursor.moveToNext()) {
            BrakeFailure = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return BrakeFailure;
    }
    public String BrakeFailureTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 4 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String BrakeFailuretittle = null;
        if (cursor.moveToNext()) {
            BrakeFailuretittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return BrakeFailuretittle;
    }

    public String HeadlightFailure() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 5 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String HeadlightFailure = null;
        if (cursor.moveToNext()) {
            HeadlightFailure = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return HeadlightFailure;
    }
    public String HeadlightFailureTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 5 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String HeadlightFailuretittle = null;
        if (cursor.moveToNext()) {
            HeadlightFailuretittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return HeadlightFailuretittle;
    }

    public String Handling() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 6 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Handling = null;
        if (cursor.moveToNext()) {
            Handling = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Handling;
    }
    public String HandlingTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 6 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Handlingtittle = null;
        if (cursor.moveToNext()) {
            Handlingtittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Handlingtittle;
    }

    public String Antilock() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 7 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Antilock = null;
        if (cursor.moveToNext()) {
            Antilock = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Antilock;
    }
    public String AntilockTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 7 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Antilocktittle = null;
        if (cursor.moveToNext()) {
            Antilocktittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Antilocktittle;
    }

    public String Skidding() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 8 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Skidding = null;
        if (cursor.moveToNext()) {
            Skidding = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Skidding;
    }
    public String SkiddingTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 8 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Skiddingtittle = null;
        if (cursor.moveToNext()) {
            Skiddingtittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Skiddingtittle;
    }

    public String HeadLightUse() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 9 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String HeadLightUse = null;
        if (cursor.moveToNext()) {
            HeadLightUse = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return HeadLightUse;
    }
    public String HeadLightUseTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 9 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String HeadLightUsetittle = null;
        if (cursor.moveToNext()) {
            HeadLightUsetittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return HeadLightUsetittle;
    }

    public String WinterDriving() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 10 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String WinterDriving = null;
        if (cursor.moveToNext()) {
            WinterDriving = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return WinterDriving;
    }
    public String WinterDrivingTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 10 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String WinterDrivingtittle = null;
        if (cursor.moveToNext()) {
            WinterDrivingtittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return WinterDrivingtittle;
    }

    public String Fog() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 11 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Fog = null;
        if (cursor.moveToNext()) {
            Fog = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Fog;
    }
    public String FogTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 11 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Fogtittle = null;
        if (cursor.moveToNext()) {
            Fogtittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Fogtittle;
    }

    public String Stall() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 12 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Stall = null;
        if (cursor.moveToNext()) {
            Stall = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Stall;
    }
    public String StallTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 12 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Stalltittle = null;
        if (cursor.moveToNext()) {
            Stalltittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Stalltittle;
    }

    public String BlockedVision() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 13 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String BlockedVision = null;
        if (cursor.moveToNext()) {
            BlockedVision = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return BlockedVision;
    }
    public String BlockedVisionTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 13 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String BlockedVisiontittle = null;
        if (cursor.moveToNext()) {
            BlockedVisiontittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return BlockedVisiontittle;
    }

    public String StallingOnRailRoad() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 14 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String StallingOnRailRoad = null;
        if (cursor.moveToNext()) {
            StallingOnRailRoad = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return StallingOnRailRoad;
    }
    public String StallingOnRailRoadTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 14 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String StallingOnRailRoadtittle = null;
        if (cursor.moveToNext()) {
            StallingOnRailRoadtittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return StallingOnRailRoadtittle;
    }

    public String Vehicle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 15 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Vehicle = null;
        if (cursor.moveToNext()) {
            Vehicle = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Vehicle;
    }
    public String VehicleTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 15 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Vehicletittle = null;
        if (cursor.moveToNext()) {
            Vehicletittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return Vehicletittle;
    }

    public String Running() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 16 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String Running = null;
        if (cursor.moveToNext()) {
            Running = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return Running;
    }
    public String RunningTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 16 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String RunningTittle = null;
        if (cursor.moveToNext()) {
            RunningTittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return RunningTittle;
    }

    public String TireBlow() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 17 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String TireBlow = null;
        if (cursor.moveToNext()) {
            TireBlow = cursor.getString(2);
        }

        cursor.close();
        myDataBase.close();
        return TireBlow;
    }
    public String TireBlowTittle() {
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String myPath = DB_PATH + RAW_DB_NAME;
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_GUIDE + " WHERE " + "_id" + "=" + " 17 ";

        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.rawQuery(query, null);
        String TireBlowtittle = null;
        if (cursor.moveToNext()) {
            TireBlowtittle = cursor.getString(1);
        }

        cursor.close();
        myDataBase.close();
        return TireBlowtittle;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing"return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.
}
