package com.elbaz.eliran.mymood.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.model.CommentDialog;
import com.elbaz.eliran.mymood.model.DataOrganizer;
import com.elbaz.eliran.mymood.model.EmailSender;

public class Moods extends AppCompatActivity implements GestureDetector.OnGestureListener{

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    // MOOD_REQUEST_CODE=4   -  Code number 4 to determine the Happy mood location on tasks(1-5)
    public static final int MOOD_REQUEST_CODE=4;

    SharedPreferences result;
    private GestureDetector mGestureDetector;
    private ImageView mSmiley, mNoteBtn, mHistoryBtn, mEmailBtn;
    /**
     * // mood numbers: //
     * 0 = Sad , 1 = Disappointed , 2 = Normal , 3 = Happy , 4 = Super-Happy
     */
    int moodNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moods);

        result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);

        // Check if alarm was executed
        int flagValue = result.getInt("AlarmSetFlag", 0);
        if (flagValue == 1) {
            result.edit().putInt("MOOD_NUMBER", 3).apply();
            // set alarm flag back to 0
            SharedPreferences.Editor alarmEditor = result.edit();
            alarmEditor.putInt("AlarmSetFlag", 0).apply();
            //Start the DataOrganizer.java class
            Intent data = new Intent(getApplicationContext(), DataOrganizer.class);
            startActivityForResult(data, MOOD_REQUEST_CODE);
            overridePendingTransition(R.anim.no_change,R.anim.slide_down_info);
        }else{
            // Get the last mood of the day. If its empty, set 3 (happy) by default and launch it
            moodNumber = result.getInt("MOOD_NUMBER",3);
        }
        // Link the elements of the layout to the activity
        mSmiley = (ImageView) findViewById(R.id.activity_main_default_smiley);
        mNoteBtn = (ImageView) findViewById(R.id.note_btn);
        mEmailBtn = (ImageView) findViewById(R.id.email_btn);
        mHistoryBtn = (ImageView) findViewById(R.id.history_btn);

        // Launch the last mood of the user, by switching the value of moodNumber
        SetMood(moodNumber);

        //Start of the Gesture Detector
        mGestureDetector = new GestureDetector(this);
    }
    // GestureDetector overriding methods
    @Override
    public boolean onDown(MotionEvent e) { // For future use
        return false;
    }
    @Override
    public void onShowPress(MotionEvent e) {}// For future use
    @Override
    public boolean onSingleTapUp(MotionEvent e) { // For future use
        return false;
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { // For future use
        return false; } // For future use
    @Override
    public void onLongPress(MotionEvent e) {}// For future use
    //Touch Event - handles the touch
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
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
    private void onSwipeLeft() {}// For future use
    private void onSwipeRight() {}// For future use

    private void onSwipeUp() {
        if (moodNumber < 4){
            moodNumber++;
            SetMood(moodNumber);
        }else{
            moodNumber=4;
            Toast.makeText(this, "We are glad you are happy, but no more moods above that one", Toast.LENGTH_LONG).show();
        }
    }
    private void onSwipeBottom() {
        if (moodNumber > 0) {
            moodNumber--;
            SetMood(moodNumber);
        } else {
            moodNumber=0;
            Toast.makeText(this, "We are sorry, but no more moods below that one", Toast.LENGTH_LONG).show();
        }
    }
    public void SetMood(int num){
        ConstraintLayout constraintLayout = findViewById(R.id.main_layout);
        result = getSharedPreferences("SaveData", MODE_PRIVATE);
        MediaPlayer mediaPlayer;

        switch (num){
            case 0: //Sad
                mSmiley.setImageResource(R.drawable.smiley_sad);
                constraintLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.SadSmiley));
                mediaPlayer = MediaPlayer.create(this, R.raw.zapsplat_warfare_sword_swipe_slash_head_chop_off_fall_to_ground_20831);
                mediaPlayer.start();
                break;
            case 1: //Disappointed
                mSmiley.setImageResource(R.drawable.smiley_disappointed);
                constraintLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.DisappointedSmiley));
                mediaPlayer = MediaPlayer.create(this, R.raw.zapsplat_warfare_sword_swipe_slash_head_chop_off_fall_to_ground_20831);
                mediaPlayer.start();
                break;
            case 2: //Normal
                mSmiley.setImageResource(R.drawable.smiley_normal);
                constraintLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.NormalSmiley));
                mediaPlayer = MediaPlayer.create(this, R.raw.zapsplat_warfare_sword_swipe_slash_head_chop_off_fall_to_ground_20831);
                mediaPlayer.start();
                break;
            case 3: // Happy
                mSmiley.setImageResource(R.drawable.smiley_happy);
                constraintLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.HappySmiley));
                mediaPlayer = MediaPlayer.create(this, R.raw.zapsplat_warfare_sword_swipe_slash_head_chop_off_fall_to_ground_20831);
                mediaPlayer.start();
                break;
            case 4: // Super Happy
                mSmiley.setImageResource(R.drawable.smiley_super_happy);
                constraintLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.SuperHappySmiley));
                mediaPlayer = MediaPlayer.create(this, R.raw.zapsplat_warfare_sword_swipe_slash_head_chop_off_fall_to_ground_20831);
                mediaPlayer.start();
                break;
        }
        //Storing the mood in SharedPreferences
        result.edit().putInt("MOOD_NUMBER", num).apply();
    }
    // Buttons methods
    public void NoteBtn(View view){
        Intent commentDialog = new Intent(this, CommentDialog.class);
        commentDialog.putExtra("MoodNumberForComment", MOOD_REQUEST_CODE);
        startActivity(commentDialog);
    }

    public void HistoryBtn(View view){
        Intent history = new Intent(getApplicationContext(), MoodHistoryScreen.class);
        history.putExtra("history", MOOD_REQUEST_CODE);
        startActivity(history);
    }
    public void EmailBtn(View view){
        Intent email = new Intent(getApplicationContext(), EmailSender.class);
        email.putExtra("Email Subject", "Subject: Hey, I'm in Happy-mood and I wanted to share it with you.");
        startActivity(email);
    }
}