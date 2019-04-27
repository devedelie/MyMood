package com.elbaz.eliran.mymood.controller;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.controller.HistoryFragments.StatisticSevenDaysAgo;

/**
 * History screen frame-layout with 8 fragments
 */
public class MoodHistoryScreen extends AppCompatActivity {
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mood_history_screen);
// Set all fragment layouts in their current frame
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_7_days_ago, new StatisticSevenDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_6_days_ago, new StatisticSevenDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_5_days_ago, new StatisticSevenDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_4_days_ago, new StatisticSevenDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_3_days_ago, new StatisticSevenDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_2_days_ago, new StatisticSevenDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_1_days_ago, new StatisticSevenDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_today, new StatisticSevenDaysAgo()).commit();

    }

    /**
     * The methods below will shows a toast messages when user click on the comment button
     */

    public void comment7DaysAgo(View view){
        mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = mSharedPreferences.getString("comment7DaysAgo", "default");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
    public void comment6DaysAgo(View view){
        mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = mSharedPreferences.getString("comment6DaysAgo", "default");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
    public void comment5DaysAgo(View view){
        mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = mSharedPreferences.getString("comment5DaysAgo", "default");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
    public void comment4DaysAgo(View view){
        mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = mSharedPreferences.getString("comment4DaysAgo", "default");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
    public void comment3DaysAgo(View view){
        mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = mSharedPreferences.getString("comment3DaysAgo", "default");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
    public void comment2DaysAgo(View view){
        mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = mSharedPreferences.getString("comment2DaysAgo", "default");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
    public void comment1DaysAgo(View view){
        mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = mSharedPreferences.getString("comment1DaysAgo", "default");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
    public void commentToday(View view){
        mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = mSharedPreferences.getString("DailyCommentData", "default");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
}
