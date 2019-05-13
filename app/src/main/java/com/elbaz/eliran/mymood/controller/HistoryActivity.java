package com.elbaz.eliran.mymood.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.model.Constants;

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

