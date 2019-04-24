package com.elbaz.eliran.mymood.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Eliran Elbaz on 17-Apr-19.
 *
 * DataOrganizer.java will be launched every day at midnight to set and save the values for the next day.
 */
public class DataOrganizer extends AppCompatActivity {

    SharedPreferences mSharedPreferences;

    String day7, day6, day5, day4, day3, day2, yesterday, today; // Mood Strings
    String comment7, comment6,comment5,comment4,comment3,comment2,comment1, commentToday; //Comment Strings

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Set all the statistics data in the right daily order while the periodic alarm is launched
         */
        // Load the daily mood from SharedPreference (last 7 days)
        SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        day7 = result.getString("7DaysAgo", "default");
        day6 = result.getString("6DaysAgo", "default");
        day5 = result.getString("5DaysAgo", "default");
        day4 = result.getString("4DaysAgo", "default");
        day3 = result.getString("3DaysAgo", "default");
        day2 = result.getString("2DaysAgo", "default");
        yesterday = result.getString("1DaysAgo", "default");
        today = result.getString("TodayMood", "default");

        // Load Daily comments for the last 7 days
        comment7 = result.getString("comment7DaysAgo","default");
        comment6 = result.getString("comment6DaysAgo","default");
        comment5 = result.getString("comment5DaysAgo","default");
        comment4 = result.getString("comment4DaysAgo","default");
        comment3 = result.getString("comment3DaysAgo","default");
        comment2 = result.getString("comment2DaysAgo","default");
        comment1 = result.getString("comment1DaysAgo","default");
        commentToday = result.getString("DailyCommentData","default");

        // Switch mood and comment data between days
        day7 = day6;         comment7 = comment6;
        day6 = day5;         comment6 = comment5;
        day5 = day4;         comment5 = comment4;
        day4 = day3;         comment4 = comment3;
        day3 = day2;         comment3 = comment2;
        day2 = yesterday;    comment2 = comment1;
        yesterday = today;   comment1 = commentToday;

        // Save updated variables with daily moods to SharedPreference
        mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("7DaysAgo", day7);
        editor.putString("6DaysAgo", day6);
        editor.putString("5DaysAgo", day5);
        editor.putString("4DaysAgo", day4);
        editor.putString("3DaysAgo", day3);
        editor.putString("2DaysAgo", day2);
        editor.putString("1DaysAgo", yesterday);
        //Set back the daily mood to Happy by default after midnight
        editor.putString("TodayMood", "Happy Mood! (-:");

        // Save updated variables with daily comments to SharedPreference
        editor.putString("comment7DaysAgo", comment7);
        editor.putString("comment6DaysAgo", comment6);
        editor.putString("comment5DaysAgo", comment5);
        editor.putString("comment4DaysAgo", comment4);
        editor.putString("comment3DaysAgo", comment3);
        editor.putString("comment2DaysAgo", comment2);
        editor.putString("comment1DaysAgo", comment1);
        editor.apply();
        finish();


        // Set the alarm flag back to zero &
        // set the comment back to empty &
        // set the default mood back to "Happy"
        mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        SharedPreferences.Editor save = mSharedPreferences.edit();
        // alarm flag to zero
//        save.putInt("AlarmSetFlag",0);
        // Daily comment to empty
        save.putString("DailyCommentData","");
        // Default mood back to Happy
        save.putString("TodayMood", "Happy Mood!");
        save.apply();
        //////////[ End of default values ]///////////////////////////
    }

}


