package com.elbaz.eliran.mymood.controller;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.controller.Fragments.StatisticFiveDaysAgo;
import com.elbaz.eliran.mymood.controller.Fragments.StatisticFourDaysAgo;
import com.elbaz.eliran.mymood.controller.Fragments.StatisticSevenDaysAgo;
import com.elbaz.eliran.mymood.controller.Fragments.StatisticSixDaysAgo;
import com.elbaz.eliran.mymood.controller.Fragments.StatisticThreeDaysAgo;
import com.elbaz.eliran.mymood.controller.Fragments.StatisticToday;
import com.elbaz.eliran.mymood.controller.Fragments.StatisticTwoDaysAgo;
import com.elbaz.eliran.mymood.controller.Fragments.StatisticYesterday;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoodHistoryScreen extends AppCompatActivity {
    SharedPreferences mSharedPreferences;
    String bar7DaysAgo,bar6DaysAgo,bar5DaysAgo,bar4DaysAgo,bar3DaysAgo,bar2DaysAgo,bar1DaysAgo,barToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mood_history_screen);
// Set all fragment layouts in their current frame
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_7_days_ago, new StatisticSevenDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_6_days_ago, new StatisticSixDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_5_days_ago, new StatisticFiveDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_4_days_ago, new StatisticFourDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_3_days_ago, new StatisticThreeDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_2_days_ago, new StatisticTwoDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_1_days_ago, new StatisticYesterday()).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_today, new StatisticToday()).commit();


//        /// To erase ?? ///
//        SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
//
//        String value7 = result.getString("7DaysAgo", "default");
//        String value6 = result.getString("6DaysAgo", "default");
//        String value5 = result.getString("5DaysAgo", "default");
//        String value4 = result.getString("4DaysAgo", "default");
//        String value3 = result.getString("3DaysAgo", "default");
//        String value2 = result.getString("2DaysAgo", "default");
//        String value1 = result.getString("1DaysAgo", "default");
//        String value = result.getString("TodayMood", "default");
//
//
//
//        if (value != "default") {
//            barToday = colorSwitcher.switcher(value);
////            barToday.setImageResource(R.drawable.super_happy_color);
//        }
//
//        /// End - To erase ?? ///


    }

    /**
     * The methods below will shows a toast messages when user click on the comment button
     */

    public void comment7DaysAgo(View view){
        SharedPreferences preferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = preferences.getString("comment7DaysAgo", "default");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
    public void comment6DaysAgo(View view){
        SharedPreferences preferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = preferences.getString("comment6DaysAgo", "default");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
    public void comment5DaysAgo(View view){
        SharedPreferences preferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = preferences.getString("comment5DaysAgo", "default");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
    public void comment4DaysAgo(View view){
        SharedPreferences preferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = preferences.getString("comment4DaysAgo", "default");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
    public void comment3DaysAgo(View view){
        SharedPreferences preferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = preferences.getString("comment3DaysAgo", "default");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
    public void comment2DaysAgo(View view){
        SharedPreferences preferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = preferences.getString("comment2DaysAgo", "default");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
    public void comment1DaysAgo(View view){
        SharedPreferences preferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = preferences.getString("comment1DaysAgo", "default");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
    public void commentToday(View view){
        SharedPreferences preferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = preferences.getString("DailyCommentData", "default");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
}
