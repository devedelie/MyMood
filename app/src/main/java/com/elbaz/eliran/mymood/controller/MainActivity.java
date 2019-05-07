package com.elbaz.eliran.mymood.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.model.DataOrganizer;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final int MOOD_REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Launch Moods.java activity
        Intent defaultSmileyIntent = new Intent(this, Moods.class);
        startActivityForResult(defaultSmileyIntent, MOOD_REQUEST_CODE);
        overridePendingTransition(R.anim.no_change,R.anim.slide_up_info);

        CheckDay();
     }

    /**
     * Check if day has changed, and update history data
     */
    public void CheckDay() {
        // Get today's date from calendar
         Calendar c = Calendar.getInstance();
         int thisDay = c.get(Calendar.DAY_OF_YEAR);
         long todayMillis = c.getTimeInMillis();
         // get the last date
         SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
         long last = prefs.getLong("DateComparator", 0);
         c.setTimeInMillis(last);
         int lastDay = c.get(Calendar.DAY_OF_YEAR);

         if( last==0 || lastDay != thisDay ){
             //New day, update TextView and preference:
             SharedPreferences.Editor edit = prefs.edit();
             edit.putLong("DateComparator", todayMillis);
             edit.commit();
             // Calculate how many days past since last app launch, and run intent
             for (int diff = thisDay - lastDay;  diff>0; diff--){
                 Intent defaultSmileyIntent = new Intent(this, DataOrganizer.class);
                 startActivityForResult(defaultSmileyIntent, MOOD_REQUEST_CODE);
             }
         }
     }
}
