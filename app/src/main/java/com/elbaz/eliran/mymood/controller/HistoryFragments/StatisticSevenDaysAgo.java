package com.elbaz.eliran.mymood.controller.HistoryFragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elbaz.eliran.mymood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticSevenDaysAgo extends Fragment {

    private ImageView colorBar, commentBtn;
    private TextView moodText;
    private LinearLayout layout7DaysAgo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic_seven_days_ago, container, false);

        commentBtn = (ImageView) view.findViewById(R.id.fragment_comment_btn);
        moodText = (TextView) view.findViewById(R.id.fragment_history_text);


        SharedPreferences preferences = this.getActivity().getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = preferences.getString("7DaysAgo", "default");
        // Show the mood of specific day
        moodText.setText("7 days ago: \n"+value);
        // Check the mood and switch it to set the correct bar-color
        ViewGroup.LayoutParams parameters = moodText.getLayoutParams();
        switch (value) {
            case "Super Happy Mood":
                parameters.width = 1050;
                moodText.setBackgroundColor(Color.YELLOW);
                break;
            case "Happy Mood!":
                parameters.width = 1150;
                moodText.setBackgroundColor(Color.GREEN);
                break;
            case "Normal Mood":
                parameters.width = 650;
                moodText.setBackgroundColor(Color.CYAN);
                break;
            case "Disappointed":
                parameters.width = 650;
                moodText.setBackgroundColor(Color.GRAY);
                break;
            case "Sad Mood":
                parameters.width = 650;
                moodText.setBackgroundColor(Color.RED);
                break;
            case "default":
                moodText.setVisibility(View.INVISIBLE);
        }
        moodText.setLayoutParams(parameters);

        // Show/hide the comment button by checking if comment was made for that day
//        if (preferences.getString("comment7DaysAgo", "default").isEmpty() || preferences.getString("comment7DaysAgo", "default").equals("default")){
//            commentBtn.setVisibility(View.INVISIBLE);
//        }

        return view;
    }


}
