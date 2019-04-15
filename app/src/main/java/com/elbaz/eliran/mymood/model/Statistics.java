package com.elbaz.eliran.mymood.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.elbaz.eliran.mymood.R;

public class Statistics extends AppCompatActivity {//implements NoteDialog.CommentListener {



    private TextView textForTEST, userComment;
    SharedPreferences mSharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        textForTEST = (TextView) findViewById(R.id.user_mood_statistics_test);
        userComment = (TextView) findViewById(R.id.user_mood_note) ;


        SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);

        String value = result.getString("Mood", "default");
        String value2 = result.getString("CommentData", "default");

        textForTEST.setText(value);
        userComment.setText(value2);

    }

}
