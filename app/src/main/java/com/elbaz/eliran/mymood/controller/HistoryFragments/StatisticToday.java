package com.elbaz.eliran.mymood.controller.HistoryFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.controller.MoodHistoryScreen;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticToday extends Fragment {
    private ImageView commentBtn;
    private TextView daysAgo;
    String comment;

    int mood;
    float widthDivider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_bar, container, false);

        SharedPreferences result = this.getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);

        // Load mood reference numbers of each day for the last 7 days
        mood = result.getInt("TodayMood", -1);
        comment = result.getString("DailyCommentData","");

        // Check the relevant width for the current mood
        widthSwitcher(mood);

        // Set the width of the fragment, regarding the current mood of specific day.
        float LayoutWidth = ((MoodHistoryScreen) getActivity()).setLayoutWidth() / widthDivider;
        RelativeLayout relativeLayout = (RelativeLayout) container.findViewById(R.id.relativeLayout_background_and_sum);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = (int) LayoutWidth;

        daysAgo = (TextView) view.findViewById(R.id.dayTxt);
        commentBtn = (ImageView) view.findViewById(R.id.commentButton);

        // Load the comment (if exist) and show/hide the button regarding the result
        if (result.getString("DailyCommentData", "").isEmpty() || result.getString("DailyCommentData", "").equals("")) {
            commentBtn.setVisibility(View.INVISIBLE);
        }else{
            commentBtn.setImageResource(R.drawable.ic_comment_black_48px);
            commentBtn.setOnClickListener(commentBtnClick);
        }

        // Set N/A message when no data was stored at the first week of the app run
        if (mood==-1){
            daysAgo.setText("N/A (No data has been recorded yet)");
        }else {
            daysAgo.setText("Today");
        }
        return view;
    }

    View.OnClickListener commentBtnClick = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getContext(), comment, Toast.LENGTH_LONG).show();
        }
    };

    public void widthSwitcher(int moodNum) {
        switch (moodNum) {
            case 0:
                widthDivider = 4.6f;
                break;
            case 1:
                widthDivider = 2.5f;
                break;
            case 2:
                widthDivider = 1.66f;
                break;
            case 3:
                widthDivider = 1.25f;
                break;
            case 4:
                widthDivider = 1.0f;
                break;
        }
    }
}

