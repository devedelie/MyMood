package com.elbaz.eliran.mymood.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.model.DataOrganizeTaskLauncher;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final int MOOD_REQUEST_CODE=1;
    SharedPreferences mSharedPreferences;
    private AlarmManager mAlarmManager;
    private PendingIntent mPendingIntent;
    // Set time variables for repeated execution of DataOrganizer.java (with AlarmManager)
    int hour=0,minutes=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Launch Moods.java activity
        Intent defaultSmileyIntent = new Intent(this, Moods.class);
        startActivityForResult(defaultSmileyIntent, MOOD_REQUEST_CODE);
        overridePendingTransition(R.anim.no_change,R.anim.slide_up_info);

        // Check if alarm is running, if not, prepare the periodic alarm
        SharedPreferences result = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String alarmFlag = result.getString("AlarmSetFlag", "");
        if (alarmFlag.equals("Standby")){
            finish();
        }else {
            setAlarm();
            finish();
        }
     }
    /**
     * setAlarm() method, will receive the time in milliseconds from the calendar,
     * and will initialize a repeating alarm to call dataOrganizer.java every 24H
     */
    public void setAlarm() {
        // set alarm flag
        mSharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("AlarmSetFlag", "Standby").commit();
        // Initialize AlarmManager
        mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, DataOrganizeTaskLauncher.class);
        mPendingIntent = PendingIntent.getBroadcast(this, 0, intent,0);
        // Set the time in calendar for the first launch
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        // setRepeating() will specify a precise custom interval of repeats every 24H
        mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 60 * 24 , mPendingIntent); // set the time in milliseconds for the next alarm
        Toast.makeText(this, "Alarm set", Toast.LENGTH_LONG).show();
    }
}
