package com.elbaz.eliran.mymood.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.elbaz.eliran.mymood.model.DataOrganizer;

import java.util.Calendar;

public class DateChecker  {

    public static final int MOOD_REQUEST_CODE=1;
    private static DateChecker instance;
    SharedPreferences prefs;
    private Context mContext;


    public static DateChecker getInstance() {
        return instance;
    }

    /**
     * Check if and how many days has passed since last time the data was organized, and update history data regardless
     */
    public DateChecker(Context context) {
        this.mContext = context;

        prefs = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        // Check if it is the first launch of the app since last time DataOrganizer was launched
        boolean isFirstLaunch = prefs.getBoolean("IsFirstLaunch" , true);
        if (isFirstLaunch) {
            Calendar firstLaunchDate = Calendar.getInstance();
            int yearOfLaunch = Calendar.getInstance().get(Calendar.YEAR);
            int monthOfLaunch = Calendar.getInstance().get(Calendar.MONTH);
            int dayOfLaunch = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            firstLaunchDate.set(yearOfLaunch,++monthOfLaunch,dayOfLaunch);
            SharedPreferences.Editor edit = prefs.edit();
            edit.putInt("FirstLaunchYear", yearOfLaunch);
            edit.putInt("FirstLaunchMonth", monthOfLaunch);
            edit.putInt("FirstLaunchDay", dayOfLaunch);
            edit.putBoolean("IsFirstLaunch" , false);
            edit.commit();
        }
        Calendar dateLoad = Calendar.getInstance();
        // Load the first launch date
        int year1 = prefs.getInt("FirstLaunchYear",0);
        int month1 = (prefs.getInt("FirstLaunchMonth",0)) ;
        int day1 = prefs.getInt("FirstLaunchDay",0);
        //Add first launch date into Calendar
        dateLoad.set(year1,month1,day1);
        long firstLaunchTime = dateLoad.getTimeInMillis();

        // Create today's calender instance
        Calendar cal2 = Calendar.getInstance();
        // Set the date for calendar instance
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        cal2.set(year, ++month, day); // ++months -->  because January starts with 0
        // Get the represented date in milliseconds
        long todaysTime = cal2.getTimeInMillis();

        // Calculate difference between last launch of DataOrganizer.java
        long diff = todaysTime - firstLaunchTime;

        // Calculate difference in days
        long diffDays = diff / (24 * 60 * 60 * 1000);

        // run DataOrganizer X times regarding the amount of days have been passed
        if (diffDays !=0){
            DataOrganizer dataOrganizer = new DataOrganizer(this.mContext);
            for (long x = diffDays; x > 0 ; x--){
                dataOrganizer.organize();
            }
        }
    }
}

