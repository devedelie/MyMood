package com.elbaz.eliran.mymood.controller;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.controller.HistoryFragments.FragmentController;
import com.elbaz.eliran.mymood.controller.HistoryFragments.StatisticFiveDaysAgo;
import com.elbaz.eliran.mymood.controller.HistoryFragments.StatisticFourDaysAgo;
import com.elbaz.eliran.mymood.controller.HistoryFragments.StatisticSevenDaysAgo;
import com.elbaz.eliran.mymood.controller.HistoryFragments.StatisticSixDaysAgo;
import com.elbaz.eliran.mymood.controller.HistoryFragments.StatisticThreeDaysAgo;
import com.elbaz.eliran.mymood.controller.HistoryFragments.StatisticToday;
import com.elbaz.eliran.mymood.controller.HistoryFragments.StatisticTwoDaysAgo;
import com.elbaz.eliran.mymood.controller.HistoryFragments.StatisticYesterday;

/**
 * History screen frame-layout with 8 fragments
 */
public class MoodHistoryScreen extends AppCompatActivity {
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mood_history_screen);

        mSharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        // Set all possible fragment widths dividers for each mood
        editor.putFloat("sHappyWidth", 1.0f);
        editor.putFloat("happyWidth", 1.25f);
        editor.putFloat("normalWidth", 1.66f);
        editor.putFloat("disappWidth", 2.5f);
        editor.putFloat("sadWidth", 4.6f);
        // Set the layout width in SharedPreferences
        float x = setLayoutWidth();
        editor.putFloat("screenWidth",x);

        // Set all fragment layouts in their current frame
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_7_days_ago, new StatisticSevenDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_6_days_ago, new StatisticSixDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_5_days_ago, new StatisticFiveDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_4_days_ago, new StatisticFourDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_3_days_ago, new StatisticThreeDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_2_days_ago, new StatisticTwoDaysAgo()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_1_days_ago, new StatisticYesterday()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_today, new StatisticToday()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_controller, new FragmentController()).commit();
    }

    // A method to to ask the width of the screen (to be divided later)
    public float setLayoutWidth(){
        Display display = ((WindowManager) this.getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        // check display size to figure out what image resolution will be loaded
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Point size = new Point();
        display.getSize(size);
        float width = size.x;

        return width;
    }
}
