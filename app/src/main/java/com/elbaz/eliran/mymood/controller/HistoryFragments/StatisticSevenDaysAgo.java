package com.elbaz.eliran.mymood.controller.HistoryFragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.elbaz.eliran.mymood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticSevenDaysAgo extends Fragment {

    private ImageView colorBar, commentBtn;
    private TextView moodText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic_seven_days_ago, container, false);

        colorBar = (ImageView) view.findViewById(R.id.color_bar_image_7);
        commentBtn = (ImageView) view.findViewById(R.id.fragment_7_days_ago_comment_btn);
        moodText = (TextView) view.findViewById(R.id.fragment_7_days_ago_text);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = preferences.getString("7DaysAgo", "default");
        // Show the mood of specific day
        moodText.setText("Your mood 7 days ago: \n"+value);
        // Check the mood and switch it to set the correct bar-color
        switch (value) {
            case "Super Happy Mood":
                colorBar.setImageResource(R.drawable.super_happy_color);
                FrameLayout.LayoutParams superHappy = new FrameLayout.LayoutParams(1000, 300);
                superHappy.setMargins(0, 0, 0 ,0);
                colorBar.setLayoutParams(superHappy);
                break;
            case "Happy Mood!":
                colorBar.setImageResource(R.drawable.happy_color);
                FrameLayout.LayoutParams happy = new FrameLayout.LayoutParams(900, 300);
                happy.setMargins(0, 0, 0 ,0);
                colorBar.setLayoutParams(happy);
                break;
            case "Normal Mood":
                colorBar.setImageResource(R.drawable.normal_color);
                FrameLayout.LayoutParams normal = new FrameLayout.LayoutParams(800, 300);
                normal.setMargins(0, 0, 0 ,0);
                colorBar.setLayoutParams(normal);
                break;
            case "Disappointed":
                colorBar.setImageResource(R.drawable.disappointed_color);
                FrameLayout.LayoutParams disapp = new FrameLayout.LayoutParams(700, 300);
                disapp.setMargins(0, 0, 0 ,0);
                colorBar.setLayoutParams(disapp);
                break;
            case "Sad Mood":
                colorBar.setImageResource(R.drawable.sad_color);
                FrameLayout.LayoutParams sad = new FrameLayout.LayoutParams(600, 300);
                sad.setMargins(0, 0, 0 ,0);
                colorBar.setLayoutParams(sad);
                break;
            case "default":
                colorBar.setImageResource(R.drawable.no_color);
        }

        // Show/hide the comment button by checking if comment was made for that day
        if (preferences.getString("comment7DaysAgo", "default").isEmpty() || preferences.getString("comment7DaysAgo", "default").equals("default")){
            commentBtn.setVisibility(View.INVISIBLE);
        }

        return view;
    }


}
