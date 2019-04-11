package com.elbaz.eliran.mymood.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import com.elbaz.eliran.mymood.R;

public class ReallyBadMoodActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    public static final int NEXT_SCREEN_REQUEST_CODE=4; // just a random code for screen number 4...
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_really_bad_mood);

        Toast.makeText(this, "Sad Mood", Toast.LENGTH_LONG).show();

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
    }

    //Touch Event handles the touch
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }
}