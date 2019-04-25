package com.elbaz.eliran.mymood.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.model.CommentDialog;
import com.elbaz.eliran.mymood.model.EmailSender;

public class HappyMood extends AppCompatActivity implements GestureDetector.OnGestureListener{

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    // MOOD_REQUEST_CODE=4   -  Code number 4 to determine the Happy mood location on tasks(1-5)
    public static final int MOOD_REQUEST_CODE=4;
    MediaPlayer mMediaPlayer;

    SharedPreferences mSharedPreferences;
    private GestureDetector mGestureDetector;
    private ImageView mSmiley, mNoteBtn, mHistoryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_mood);

        SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);

        // Play sound when activity is launched
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.zapsplat_warfare_sword_swipe_slash_head_chop_off_fall_to_ground_20831);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.start();

        // A Toast message to indicate the current mood for the user.
        Toast.makeText(this, "Happy Mood! (-:", Toast.LENGTH_SHORT).show();

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
        Intent statistics = new Intent(getApplicationContext(), MoodHistoryScreen.class);
        statistics.putExtra("history", MOOD_REQUEST_CODE);
        startActivity(statistics);
    }
    public void happyEmailBtn(View view){
        Intent email = new Intent(getApplicationContext(), EmailSender.class);
        email.putExtra("Email Subject", "Subject: Hey, I'm in Happy-mood and I wanted to share it with you.");
        startActivity(email);
    }

}