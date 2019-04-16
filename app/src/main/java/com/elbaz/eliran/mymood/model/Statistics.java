package com.elbaz.eliran.mymood.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.elbaz.eliran.mymood.R;

public class Statistics extends AppCompatActivity {//implements NoteDialog.CommentListener {


    private TextView userTodayMood, userComment, userMood7, userMood6, userMood5, userMood4, userMood3, userMood2, userMood1;
    SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        // Link the layout text lines
        userMood7 = (TextView) findViewById(R.id.user_mood_statistics_7_days_ago);
        userMood6 = (TextView) findViewById(R.id.user_mood_statistics_6_days_ago);
        userMood5 = (TextView) findViewById(R.id.user_mood_statistics_5_days_ago);
        userMood4 = (TextView) findViewById(R.id.user_mood_statistics_4_days_ago);
        userMood3 = (TextView) findViewById(R.id.user_mood_statistics_3_days_ago);
        userMood2 = (TextView) findViewById(R.id.user_mood_statistics_2_days_ago);
        userMood1 = (TextView) findViewById(R.id.user_mood_statistics_1_days_ago);

        userTodayMood = (TextView) findViewById(R.id.user_mood_statistics_test);
        userComment = (TextView) findViewById(R.id.user_mood_note);


        // Load the values from SharedPreferences into the layout
        SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);

        String value7 = result.getString("7DaysAgo", "default");
        String value6 = result.getString("6DaysAgo", "default");
        String value5 = result.getString("5DaysAgo", "default");
        String value4 = result.getString("4DaysAgo", "default");
        String value3 = result.getString("3DaysAgo", "default");
        String value2 = result.getString("2DaysAgo", "default");
        String value1 = result.getString("1DaysAgo", "default");

        String value = result.getString("TodayMood", "default");

        String value22 = result.getString("CommentData", "default");

        userMood7.setText(value7);
        userMood6.setText(value6);
        userMood5.setText(value5);
        userMood4.setText(value4);
        userMood3.setText(value3);
        userMood2.setText(value2);
        userMood1.setText(value1);

        userTodayMood.setText(value);

        userComment.setText(value22);

    }


}
