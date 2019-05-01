package com.elbaz.eliran.mymood.controller.HistoryFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elbaz.eliran.mymood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentController extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_bar, container, false);
        SharedPreferences result = this.getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        checkMoodSetColor();
        return view;
    }

//     This method will receive the mood of each day, and will set the correct color
    public void checkMoodSetColor(){
        String colorResult;
        int day7, day6, day5, day4, day3, day2, yesterday, today; // Mood integers
        SharedPreferences result = this.getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);

        day7 = result.getInt("7DaysAgo", -1);
        colorResult = switchColor(day7);
        Fragment fragment7 = (Fragment) getFragmentManager().findFragmentById(R.id.frame_7_days_ago);
        fragment7.getView().setBackgroundColor(Color.parseColor(colorResult));

        day6 = result.getInt("6DaysAgo", -1);
        colorResult = switchColor(day6);
        Fragment fragment6 = (Fragment) getFragmentManager().findFragmentById(R.id.frame_6_days_ago);
        fragment6.getView().setBackgroundColor(Color.parseColor(colorResult));

        day5 = result.getInt("5DaysAgo", -1);
        colorResult = switchColor(day5);
        Fragment fragment5 = (Fragment) getFragmentManager().findFragmentById(R.id.frame_5_days_ago);
        fragment5.getView().setBackgroundColor(Color.parseColor(colorResult));

        day4 = result.getInt("4DaysAgo", -1);
        colorResult = switchColor(day4);
        Fragment fragment4 = (Fragment) getFragmentManager().findFragmentById(R.id.frame_4_days_ago);
        fragment4.getView().setBackgroundColor(Color.parseColor(colorResult));

        day3 = result.getInt("3DaysAgo", -1);
        colorResult = switchColor(day3);
        Fragment fragment3 = (Fragment) getFragmentManager().findFragmentById(R.id.frame_3_days_ago);
        fragment3.getView().setBackgroundColor(Color.parseColor(colorResult));

        day2 = result.getInt("2DaysAgo", -1);
        colorResult = switchColor(day2);
        Fragment fragment2 = (Fragment) getFragmentManager().findFragmentById(R.id.frame_2_days_ago);
        fragment2.getView().setBackgroundColor(Color.parseColor(colorResult));

        yesterday = result.getInt("1DaysAgo", -1);
        colorResult = switchColor(yesterday);
        Fragment fragment1 = (Fragment) getFragmentManager().findFragmentById(R.id.frame_1_days_ago);
        fragment1.getView().setBackgroundColor(Color.parseColor(colorResult));

        today = result.getInt("TodayMood", -1);
        colorResult = switchColor(today);
        Fragment fragmentT = (Fragment) getFragmentManager().findFragmentById(R.id.frame_today);
        fragmentT.getView().setBackgroundColor(Color.parseColor(colorResult));

    }
    // This method will receive the mood and will return the correct color
    public String switchColor(int day){
        String color=null;
        switch (day){
            case 0:
                color = "#FF5252";
                break;
            case 1:
                color = "#9E9E9E";
                break;
            case 2:
                color = "#00BCD4";
                break;
            case 3:
                color = "#8BC34A";
                break;
            case 4:
                color = "#FFEB3B";
                break;
                default:
                    color = "#D6D5D5";
        }
        return color;
    }
}

