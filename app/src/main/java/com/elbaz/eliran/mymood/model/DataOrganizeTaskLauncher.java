package com.elbaz.eliran.mymood.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Eliran Elbaz on 17-Apr-19.
 */
public class DataOrganizeTaskLauncher extends BroadcastReceiver {

    // This method will be invoked once the AlarmManager reaches the time we set for data saving
    @Override
    public void onReceive(Context context, Intent intent) {
        // The task to be launched by Android AlarmManager when time is right.
        Toast.makeText(context, "It is midnight. Your your mood data is being saved now.", Toast.LENGTH_LONG).show();

        // invoke the DataOrganizer activity, to set the order after midnight
        Intent dataOrganizer = new Intent();
        dataOrganizer.setClassName(context.getPackageName(), DataOrganizer.class.getName());
        dataOrganizer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(dataOrganizer);

        // Set the alarm flag to OFF, for testing only
//        SharedPreferences mSharedPreferences;
//        mSharedPreferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
//        SharedPreferences.Editor alarmEditor = mSharedPreferences.edit();
//        alarmEditor.putString("AlarmSetFlag","OFF");
//        alarmEditor.apply();
        //////////[ End of alarm flag ]///////////////////////////////////////////////////////
    }
}
