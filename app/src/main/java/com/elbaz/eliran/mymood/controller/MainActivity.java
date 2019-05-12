package com.elbaz.eliran.mymood.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.model.Constants;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
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
    public void onResume(){
        super.onResume();
        // Check if date has changed, if yes, organize the data
        DateChecker dateChecker = new DateChecker(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moods);
        //Get today's mood from SharedPreferences (mood 3 by default = happy)
        result = getSharedPreferences("Data", Context.MODE_PRIVATE);
        moodNumber = result.getInt("TodayMood",3);

        // Set indicator to check if user is using the app now (used for alarm and dataOrganizer.java)
        SharedPreferences.Editor editor = result.edit();
        editor.putBoolean("userIsOnline", true).apply();

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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Set the boolean to false, to indicate the system that user is not using the app now
        result = getSharedPreferences("Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = result.edit();
        editor.putBoolean("userIsOnline", false).commit();
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

    /**
     * SetMood() will set the correct smiley + Background color + launch a sound gesture for each mood
     */
    public void SetMood(int num){
        ConstraintLayout constraintLayout = findViewById(R.id.main_layout);
        result = getSharedPreferences("Data", MODE_PRIVATE);
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
        result.edit().putInt("TodayMood", num).commit();
    }
    /**
     * Add comment button
     */
    public void NoteBtn(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        view = inflater.inflate(R.layout.activity_comment_dialog, null);
        final EditText editText = view.findViewById(R.id.commentEditTxt);
        builder.setView(view)
                .setTitle(R.string.alert_dialog_title)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String comment = editText.getText().toString();
                        if (comment.isEmpty()){
                            Toast.makeText(MainActivity.this, R.string.toast_comment_dialog_empty, Toast.LENGTH_SHORT).show();
                        }else {
                            SharedPreferences.Editor editor = result.edit();
                            editor.putString("DailyCommentData", comment);
                            editor.apply();
                            Toast.makeText(MainActivity.this, R.string.toast_comment_dialog_save, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .create()
                .show();
    }
    /**
     * History screen button
     */
    public void HistoryBtn(View view){
        Intent history = new Intent(getApplicationContext(), MoodHistoryScreen.class);
        history.putExtra("history", MOOD_REQUEST_CODE);
        startActivity(history);
    }
    /**
     *  Share button
     */
    public void EmailBtn(View view){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, Constants.emailSubjects[moodNumber]);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, R.string.email_body_message);
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_dialog_text)));
    }
}