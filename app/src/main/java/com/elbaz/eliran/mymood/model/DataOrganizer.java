package com.elbaz.eliran.mymood.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.elbaz.eliran.mymood.controller.Moods;

/**
 * Created by Eliran Elbaz on 17-Apr-19.
 *
 * DataOrganizer.java will be launched every day at midnight to set and save the values for the next day.
 */
public class DataOrganizer extends AppCompatActivity {

    SharedPreferences mSharedPreferences;

    int day7, day6, day5, day4, day3, day2, yesterday, today; // Mood integers
    String comment7, comment6,comment5,comment4,comment3,comment2,comment1, commentToday; //Comment Strings

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Set all the statistics data in the right daily order while the periodic alarm is launched
         */
        // Load the daily mood from SharedPreference (last 7 days)
        SharedPreferences result = getSharedPreferences("Data", Context.MODE_PRIVATE);
        day7 = result.getInt("7DaysAgo", -1);
        day6 = result.getInt("6DaysAgo", -1);
        day5 = result.getInt("5DaysAgo", -1);
        day4 = result.getInt("4DaysAgo", -1);
        day3 = result.getInt("3DaysAgo", -1);
        day2 = result.getInt("2DaysAgo", -1);
        yesterday = result.getInt("1DaysAgo", -1);
        today = result.getInt("TodayMood", -1);

        // Load Daily comments for the last 7 days
        comment7 = result.getString("comment7DaysAgo","");
        comment6 = result.getString("comment6DaysAgo","");
        comment5 = result.getString("comment5DaysAgo","");
        comment4 = result.getString("comment4DaysAgo","");
        comment3 = result.getString("comment3DaysAgo","");
        comment2 = result.getString("comment2DaysAgo","");
        comment1 = result.getString("comment1DaysAgo","");
        commentToday = result.getString("DailyCommentData","");

        // Switch mood and comment data between days
        day7 = day6;         comment7 = comment6;
        day6 = day5;         comment6 = comment5;
        day5 = day4;         comment5 = comment4;
        day4 = day3;         comment4 = comment3;
        day3 = day2;         comment3 = comment2;
        day2 = yesterday;    comment2 = comment1;
        yesterday = today;   comment1 = commentToday;

        // Save updated variables with daily moods to SharedPreference
        mSharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt("7DaysAgo", day7);
        editor.putInt("6DaysAgo", day6);
        editor.putInt("5DaysAgo", day5);
        editor.putInt("4DaysAgo", day4);
        editor.putInt("3DaysAgo", day3);
        editor.putInt("2DaysAgo", day2);
        editor.putInt("1DaysAgo", yesterday);
        //Set back the daily mood to Happy by default after midnight
        editor.putInt("TodayMood", 3);

        // Save updated variables with daily comments to SharedPreference
        editor.putString("comment7DaysAgo", comment7);
        editor.putString("comment6DaysAgo", comment6);
        editor.putString("comment5DaysAgo", comment5);
        editor.putString("comment4DaysAgo", comment4);
        editor.putString("comment3DaysAgo", comment3);
        editor.putString("comment2DaysAgo", comment2);
        editor.putString("comment1DaysAgo", comment1);
        editor.commit();

        // set the comment back to empty & the default mood back to "Happy"
        SharedPreferences.Editor save = mSharedPreferences.edit();
        save.putString("DailyCommentData","");
        save.putInt("TodayMood", 3);
        save.commit();

        // if user is online, invoke Moods.java to refresh the screen to default mood
        boolean userIsOnline = mSharedPreferences.getBoolean("userIsOnline", false);
        if(userIsOnline){
            Intent reLaunchMoods = new Intent();
            reLaunchMoods.setClassName(this, Moods.class.getName());
            reLaunchMoods.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(reLaunchMoods);
            Toast.makeText(this, "It's a new day, it's a new dawn, it's a new mood...\n What is your mood today?", Toast.LENGTH_LONG).show();
        }
        finish();
    }
}


