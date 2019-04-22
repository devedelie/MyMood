package com.elbaz.eliran.mymood.controller.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elbaz.eliran.mymood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticToday extends Fragment {
    private ImageView colorBar, commentBtn;
    private TextView moodText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statistic_today, container, false);

        colorBar = (ImageView) view.findViewById(R.id.color_bar_image_today);
        commentBtn = (ImageView) view.findViewById(R.id.fragment_today_comment_btn);
        moodText = (TextView) view.findViewById(R.id.fragment_today_text);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = preferences.getString("TodayMood", "default");
        // Show the mood of specific day
        moodText.setText("Your mood today: \n"+value);
        // Check the mood and switch it to set the correct bar-color
        switch (value) {
                case "Super Happy Mood":
                    colorBar.setImageResource(R.drawable.super_happy_color);
                    break;
                case "Happy Mood!":
                    colorBar.setImageResource(R.drawable.happy_color);
                    break;
                case "Normal Mood":
                    colorBar.setImageResource(R.drawable.normal_color);
                    break;
                case "Disappointed":
                    colorBar.setImageResource(R.drawable.disappointed_color);
                    break;
                case "Sad Mood":
                    colorBar.setImageResource(R.drawable.sad_color);
                    break;
                case "default":
                    colorBar.setImageResource(R.drawable.no_color);
            }

        // Show/hide the comment button by checking if comment was made for that day
        if (preferences.getString("dailyCommentData", "default").isEmpty()){
            commentBtn.setVisibility(View.INVISIBLE);
        }

            return view;
        // End of switch

        }
}
