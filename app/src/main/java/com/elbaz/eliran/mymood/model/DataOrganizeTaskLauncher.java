package com.elbaz.eliran.mymood.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by Eliran Elbaz on 17-Apr-19.
 */
public class DataOrganizeTaskLauncher extends BroadcastReceiver {

    // This method will be invoked once the AlarmManager reaches the time we set for data saving
    @Override
    public void onReceive(Context context, Intent intent) {
        // The task to be launched by Android AlarmManager when time is right.
        Toast.makeText(context, "It is midnight. Your data from yesterday is being saved now.", Toast.LENGTH_LONG).show();

        // Set the alarm flag to 1, to indicate the condition that alarm was executed
        SharedPreferences mSharedPreferences;
        mSharedPreferences = context.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        SharedPreferences.Editor alarmEditor = mSharedPreferences.edit();
        alarmEditor.putInt("AlarmSetFlag",1);
        alarmEditor.apply();
        //////////[ End of alarm flag ]///////////////////////////////////////////////////////

        //Start the Mood.java to refresh the mood to Happy only if the app is ON
        /**
         * *****************boolean onStop onStart of Moods***************
         * to check if the user is live or the app is off
         * if it is off, it will be updated anyway on next launch
         * if it is on "onStart/onCreate" it will relaunch Moods.java
         */
//        Intent reload = new Intent();
//        dataOrganizer.setClassName(context.getPackageName(), DataOrganizer.class.getName());
//        dataOrganizer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(dataOrganizer);
    }
}
