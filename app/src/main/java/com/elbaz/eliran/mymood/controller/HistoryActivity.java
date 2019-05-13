package com.elbaz.eliran.mymood.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.elbaz.eliran.mymood.R;

public class HistoryActivity extends AppCompatActivity {
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        
        createHistoryList();
    }

    public void createHistoryList(){
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        LayoutInflater inflater = LayoutInflater.from(this);
        float[] paddingWeight = {0.4f, 0.8f, 1.66f, 3.9f, 1000f};


    }
}

