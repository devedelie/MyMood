package com.elbaz.eliran.mymood.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.model.CommentDialog;
import com.elbaz.eliran.mymood.model.PeriodicTaskLauncher;
import com.elbaz.eliran.mymood.model.Statistics;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;

public class ReallyBadMoodActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    public static final int NEXT_SCREEN_REQUEST_CODE=1; //  code for screen number 1...
    private GestureDetector mGestureDetector;

    SharedPreferences mSharedPreferences;
    private GcmNetworkManager mGcmNetworkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_really_bad_mood);

        Toast.makeText(this, "Sad Mood", Toast.LENGTH_LONG).show();

        /**
         * Periodic task - Daily time counter to initialize the mood into 7 days statistics
         */
        mGcmNetworkManager = GcmNetworkManager.getInstance(this);
        PeriodicTask task = new PeriodicTask.Builder()
                .setService(PeriodicTaskLauncher.class)
                .setPeriod(86400L) // Period in seconds
                .setFlex(86400L) // Initialize the time to first launch the task after running the GcmNetworkManager
                .setTag("PeriodicTaskLauncher")
                .build();

        mGcmNetworkManager.schedule(task);
        // [End of Periodic Task Launcher]

        /**
         * The below is used to save the user's mood state on SharedPreferences
         */
        mSharedPreferences = getSharedPreferences("SaveCommentData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("Mood", "Sad Mood");
        editor.apply();
        //////////End of data saving///////////////////////////////////////////////////////



        //Gesture Detector
        mGestureDetector = new GestureDetector(this);


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
        Toast.makeText(this, "Note: This is the saddest smiley. We hope your day will get better soon. (-;", Toast.LENGTH_LONG).show();
    }
    private void onSwipeUp() {
        SwipeUpForNextActivity();

    }



    private void SwipeUpForNextActivity() {
        Intent nextSmileyIntent = new Intent(this, DisappointedMood.class);
        startActivityForResult(nextSmileyIntent, NEXT_SCREEN_REQUEST_CODE);
        overridePendingTransition(R.anim.no_change,R.anim.slide_up_info);
    }

    //Touch Event handles the touch
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    public void sadNoteBtn(View view){
        Intent commentDialog = new Intent(this, CommentDialog.class);
        commentDialog.putExtra("MoodNumberForComment", NEXT_SCREEN_REQUEST_CODE);
        startActivity(commentDialog);
    }

    public void sadHistoryBtn(View view){
        Intent statistics = new Intent(getApplicationContext(), Statistics.class);
        startActivityForResult(statistics, NEXT_SCREEN_REQUEST_CODE);
    }
}