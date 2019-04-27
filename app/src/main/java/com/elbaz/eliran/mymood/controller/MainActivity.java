package com.elbaz.eliran.mymood.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.model.DataOrganizeTaskLauncher;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // MOOD_REQUEST_CODE=4   -  Code number 4 to determine the Happy mood location on tasks(1-5)
    public static final int MOOD_REQUEST_CODE=1;

    // Set time variables for repeated execution of DataOrganizer.java (with AlarmManager)
    int hour=23,minutes=59,seconds=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Launch moods activity
        Intent defaultSmileyIntent = new Intent(this, Moods.class);
        startActivityForResult(defaultSmileyIntent, MOOD_REQUEST_CODE);
        overridePendingTransition(R.anim.no_change,R.anim.slide_up_info);

        // Set the calender to prepare the periodic alarm
        setCalendarForAlarm();
        finish();
     }

    /**
     * Set the calendar with date and time for system alarm to launch DataOrganizer.java
     */
    private void setCalendarForAlarm(){
        // Set the the time and date in calendar
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, seconds);
        // call setAlarm with the time in milli-seconds
        setAlarm(calendar.getTimeInMillis());
    }
    /**
     * setAlarm() method, will receive the time in milliseconds from setCalendarForAlarm()
     * and will initialize a repeating system alarm to call dataOrganizer.java
     * @param
     */
    public void setAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, DataOrganizeTaskLauncher.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent,0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Alarm set", Toast.LENGTH_LONG).show();
    }

}
