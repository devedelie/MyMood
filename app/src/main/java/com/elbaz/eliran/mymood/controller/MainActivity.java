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

    // MOOD_REQUEST_CODE=4   -  Code number 4 to determine the Happy mood location on tasks(1-5)
    public static final int MOOD_REQUEST_CODE=4;
    SharedPreferences mSharedPreferences;


    // Set time variables for repeated execution of DataOrganizer.java (with AlarmManager)
    int hour=23,minutes=59,seconds=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the alarm flag to 0, for testing only // should be commented/erased on production
//        mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
//        SharedPreferences.Editor alarmEditor = mSharedPreferences.edit();
//        alarmEditor.putInt("AlarmSetFlag",0);
//        alarmEditor.apply();
        //////////[ End of alarm flag test ]///////////////////////////////////////////////////////
//
        /**
         * Periodic task:
         * Daily silent-alarm for the system, to initialize and organize
         * the moods and comments of the last 7 days. (with AlarmManager)
         */
        SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        int flagValue = result.getInt("AlarmSetFlag", 0);
        if(flagValue==0) {
            setCalendarForAlarm();
        }


        // Switch method to launch the last mood of the current day
        // After midnight, the mood will go back to "Happy" by default
        String todayMood = result.getString("TodayMood", "default");

            switch (todayMood){
                case "Sad Mood":
                    Intent sadSmileyIntent = new Intent(this, ReallyBadMoodActivity.class);
                    startActivityForResult(sadSmileyIntent, MOOD_REQUEST_CODE);
                    overridePendingTransition(R.anim.no_change,R.anim.slide_up_info);
                    break;
                case "Disappointed":
                    Intent todaySmileyIntent = new Intent(this, DisappointedMood.class);
                    startActivityForResult(todaySmileyIntent, MOOD_REQUEST_CODE);
                    overridePendingTransition(R.anim.no_change,R.anim.slide_up_info);
                    break;
                case "Normal Mood":
                    Intent normalSmileyIntent = new Intent(this, NormalMood.class);
                    startActivityForResult(normalSmileyIntent, MOOD_REQUEST_CODE);
                    overridePendingTransition(R.anim.no_change,R.anim.slide_up_info);
                    break;
                case "Happy Mood!":
                    Intent happySmileyIntent = new Intent(this, HappyMood.class);
                    startActivityForResult(happySmileyIntent, MOOD_REQUEST_CODE);
                    overridePendingTransition(R.anim.no_change,R.anim.slide_up_info);
                    break;
                case "Super Happy Mood":
                    Intent superHappySmileyIntent = new Intent(this, SuperHappyMood.class);
                    startActivityForResult(superHappySmileyIntent, MOOD_REQUEST_CODE);
                    overridePendingTransition(R.anim.no_change,R.anim.slide_up_info);
                    break;
                default:
                    Intent defaultSmileyIntent = new Intent(this, HappyMood.class);
                    startActivityForResult(defaultSmileyIntent, MOOD_REQUEST_CODE);
                    overridePendingTransition(R.anim.no_change,R.anim.slide_up_info);
                    break;
            }


//            organizeData();
     }

//     public void organizeData(){
//        Intent data = new Intent(getApplicationContext(), DataOrganizer.class);
//        startActivityForResult(data, MOOD_REQUEST_CODE);
//         overridePendingTransition(R.anim.no_change,R.anim.slide_down_info);
//    }


    /**
     * Set the calendar with date and time for system alarm to launch DataOrganizer.java
     */
    private void setCalendarForAlarm(){
        // Set the alarm flag to 1, to indicate the condition on restart, that alarm is already ON
        mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        SharedPreferences.Editor alarmEditor = mSharedPreferences.edit();
        alarmEditor.putInt("AlarmSetFlag",1);
        alarmEditor.apply();
        //////////[ End of alarm flag ]///////////////////////////////////////////////////////

        // Set the the time and date in calendar
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                hour,minutes,seconds);
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
