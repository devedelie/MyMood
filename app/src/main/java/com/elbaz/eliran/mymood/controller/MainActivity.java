package com.elbaz.eliran.mymood.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.model.CommentDialog;
import com.elbaz.eliran.mymood.model.DataOrganizeTaskLauncher;
import com.elbaz.eliran.mymood.model.Statistics;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    // MOOD_REQUEST_CODE=4   -  Code number 4 to determine the Happy mood location on tasks(1-5)
    public static final int MOOD_REQUEST_CODE=4;

    SharedPreferences mSharedPreferences;
    private GestureDetector mGestureDetector;
    private ImageView mSmiley, mNoteBtn, mHistoryBtn;
    boolean dateHasChanged;
    // Set the variables to time of system alarm execute
    int hours=0,minutes=0,seconds=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // A Toast message to indicate the current mood for the user.
        Toast.makeText(this, "Happy Mood! (-:", Toast.LENGTH_SHORT).show();


        /**
         * Periodic task - Daily alarm(silent) for the system, to initialize and organize
         * the mood and comments into 7 days reordered statistics
         */
        SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        int flagValue = result.getInt("AlarmSetFlag", 0);
        if(flagValue==0) {
            setCalendarForAlarm();
        }


        /**
         * The below is used to save the user's mood state on SharedPreferences
         */
        mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("TodayMood", "Happy Mood!");
        editor.apply();
        //////////[ End of data saving ]///////////////////////////////////////////////////////


        mSmiley = (ImageView) findViewById(R.id.activity_main_default_smiley);
        mNoteBtn = (ImageView) findViewById(R.id.happy_note_btn);
        mHistoryBtn = (ImageView) findViewById(R.id.happy_history_btn);

        //Start of the Gesture Detector operation
        mGestureDetector = new GestureDetector(this);
    }


    /**
     * Set the calendar with date and time for the alarm
     */
    private void setCalendarForAlarm(){
        // Set the alarm flag back to 1, to indicate the system alarm is on
        mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        SharedPreferences.Editor alarmEditor = mSharedPreferences.edit();
        alarmEditor.putInt("AlarmSetFlag",1);
        alarmEditor.apply();
        //////////[ End of alarm flag ]///////////////////////////////////////////////////////

        Calendar calendar = Calendar.getInstance();
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                    hours, minutes, seconds);
        } else {
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                    hours, minutes, seconds);
        }
        setAlarm(calendar.getTimeInMillis());
    }
    /**
     * setAlarm method belongs to the daily task operation every midnight
     * The initialization of the alarm is above
     * @param
     */
    private void setAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, DataOrganizeTaskLauncher.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent,0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Alarm was set", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }
    @Override
    public void onShowPress(MotionEvent e) {
    }
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }
    @Override
    public void onLongPress(MotionEvent e) {
    }

    /**
     * onFling detects the direction and the velocity of the swipe on the screen
     * @param downEvent the value of movement on the screen
     * @param moveEvent the value of movement on the screen
     * @param velocityX finger move velocity on X axis
     * @param velocityY finger move velocity on Y axis
     * @return
     */
    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
        boolean result=false;
        //How far user finger traversed throw the Y axis
        float diffY = moveEvent.getY() - downEvent.getY();
        //How far user finger traversed throw the X axis
        float diffX = moveEvent.getX() - downEvent.getX();
        //Which was greater? movement across Y or X?
        if (Math.abs(diffX) > Math.abs(diffY)){
            //Right or left swipe
            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX)> SWIPE_VELOCITY_THRESHOLD){
                if (diffX >0 ){
                    onSwipeRight();
                }else{
                    onSwipeLeft();
                }
                result = true;
            }
        }else{
            //Up or down swipe
            if (diffY > 0){
                onSwipeBottom();
            }else{
                onSwipeUp();
            }
            result = true;
        }

        return result;
    }
    private void onSwipeLeft() {
    }
    private void onSwipeRight() {
    }
    private void onSwipeBottom() {
        SwipeDownForNextActivity();
    }
    private void onSwipeUp() {
        SwipeUpForNextActivity();

    }
    private void SwipeDownForNextActivity() {
        Intent precedentSmileyIntent = new Intent(this, NormalMood.class);
        startActivityForResult(precedentSmileyIntent, MOOD_REQUEST_CODE);
        overridePendingTransition(R.anim.no_change,R.anim.slide_down_info);
    }

    private void SwipeUpForNextActivity() {
        Intent nextSmileyIntent = new Intent(this, SuperHappyMood.class);
        startActivityForResult(nextSmileyIntent, MOOD_REQUEST_CODE);
        overridePendingTransition(R.anim.no_change,R.anim.slide_up_info);
    }

    //Touch Event - handles the touch
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    public void happyNoteBtn(View view){
        Intent commentDialog = new Intent(this, CommentDialog.class);
        commentDialog.putExtra("MoodNumberForComment", MOOD_REQUEST_CODE);
        startActivity(commentDialog);
    }

    public void happyHistoryBtn(View view){
        Intent statistics = new Intent(getApplicationContext(), Statistics.class);
        startActivityForResult(statistics, MOOD_REQUEST_CODE);
    }
    

}
