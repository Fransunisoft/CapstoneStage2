package com.fransunisoft.drivingguide;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by francis on 26/06/2015.
 */

public class SharedPreference {
    //shared preferences
    SharedPreferences myPref;
    //Editor for the shared preferences
    SharedPreferences.Editor editor;
    //Context
    Context _context;
    //shared pref mode
    int PRIVATE_MODE = 0;

    int score;
    //shared pref file name
    private static final String PREF_NAME = "ManualPref";
    //title key
    public static final String KEY_TITLE = "title";
    //Driving key
    public static final String KEY_MANUAL = "manual";

    public SharedPreference (Context context){
        this._context = context;
        myPref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = myPref.edit();
    }




    //creating login session
    public void saveManual(String manual_title, String manual){
        //put prayer title and prayer
        editor.putString(KEY_TITLE, manual_title);
        editor.putString(KEY_MANUAL, manual);
        //commit the changes
        editor.commit();
    }



    //get stored session data
    public HashMap<String, String> getManualDetails(){
        HashMap<String, String> manual = new HashMap<String, String>();
       manual.put(KEY_TITLE, myPref.getString(KEY_TITLE, null));
        manual.put(KEY_MANUAL, myPref.getString(KEY_MANUAL, null));
        return manual;
    }




}
