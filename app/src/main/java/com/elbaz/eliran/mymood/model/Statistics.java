package com.elbaz.eliran.mymood.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.elbaz.eliran.mymood.R;

public class Statistics extends AppCompatActivity {

//    private static final String SHARED_PREFS = "sharedPref" ;
////    private static final String MOOD_STATE = "moodState" ;



    private String userMoodSatetString;
    private String moodLoader;
    private TextView textForTEST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        textForTEST = (TextView) findViewById(R.id.user_mood_statistics_test);

        SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);

        String value = result.getString("Mood", "default");

        textForTEST.setText(value);

    }
}
