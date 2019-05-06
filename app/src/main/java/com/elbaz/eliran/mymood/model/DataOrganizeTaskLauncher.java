package com.elbaz.eliran.mymood.model;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Eliran Elbaz on 17-Apr-19.
 */
public class DataOrganizeTaskLauncher extends BroadcastReceiver {
    SharedPreferences mSharedPreferences;
    private AlarmManager mAlarmManager;
    private PendingIntent mPendingIntent;
    int hour=0, minutes=0;

    /**
     * This method will be invoked once the AlarmManager reaches the alarm-time
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        // The task to be launched by Android AlarmManager when time is right.
        Toast.makeText(context, "It is midnight. Your your mood data is being saved now.", Toast.LENGTH_LONG).show();

        // invoke the DataOrganizer activity, to set the order after midnight
        Intent dataOrganizer = new Intent();
        dataOrganizer.setClassName(context.getPackageName(), DataOrganizer.class.getName());
        dataOrganizer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(dataOrganizer);

        // If the device has been rebooted, this condition will set the alarm back to cycle.
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            // set alarm flag
            mSharedPreferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString("AlarmSetFlag", "Standby").commit();
            // Initialize AlarmManager
            mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent alarm = new Intent(context, DataOrganizeTaskLauncher.class);
            mPendingIntent = PendingIntent.getBroadcast(context, 0, alarm,0);
            // Set the time in calendar for the first launch
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minutes);
            // setRepeating() will specify a precise custom interval of repeats every 24H
            mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    1000 * 60 * 60 * 24, mPendingIntent);
            Toast.makeText(context, "Alarm set", Toast.LENGTH_LONG).show();
        }


        // Set the alarm flag to OFF, for testing only
//        SharedPreferences mSharedPreferences;
//        mSharedPreferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
//        SharedPreferences.Editor alarmEditor = mSharedPreferences.edit();
//        alarmEditor.putString("AlarmSetFlag","OFF");
//        alarmEditor.apply();
        //////////[ End of alarm flag ]///////////////////////////////////////////////////////
    }
}
