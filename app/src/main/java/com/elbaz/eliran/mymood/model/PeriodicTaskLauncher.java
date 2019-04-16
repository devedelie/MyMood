package com.elbaz.eliran.mymood.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;

public class PeriodicTaskLauncher extends GcmTaskService {
   SharedPreferences mSharedPreferences;

    String day7, day6, day5, day4, day3, day2, yesterday, today;

    @Override
    public int onRunTask(TaskParams taskParams) {

        /**
         * Set all the statistics data in the right daily order while the periodic task is launched
         */

        //Load data from SharedPreference to variables
            SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
            day7 = result.getString("7DaysAgo", "default");
            day6 = result.getString("6DaysAgo", "default");
            day5 = result.getString("5DaysAgo", "default");
            day4 = result.getString("4DaysAgo", "default");
            day3 = result.getString("3DaysAgo", "default");
            day2 = result.getString("2DaysAgo", "default");
            yesterday = result.getString("1DaysAgo", "default");
            today = result.getString("TodayMood", "default");


            // Switch data between days
            day7 = day6;
            day6 = day5;
            day5 = day4;
            day4 = day3;
            day3 = day2;
            day2 = yesterday;
            yesterday = today;

            // Save updated variables to SharedPreference
            mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString("7DaysAgo", day7);
            editor.putString("6DaysAgo", day6);
            editor.putString("5DaysAgo", day5);
            editor.putString("4DaysAgo", day4);
            editor.putString("3DaysAgo", day3);
            editor.putString("2DaysAgo", day2);
            editor.putString("1DaysAgo", yesterday);
            editor.apply();


        return 0;
    }


}
