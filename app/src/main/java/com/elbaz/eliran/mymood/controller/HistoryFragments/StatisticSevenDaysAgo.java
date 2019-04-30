package com.elbaz.eliran.mymood.controller.HistoryFragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.elbaz.eliran.mymood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticSevenDaysAgo extends Fragment {

    private ImageButton commentBtn;
    private TextView daysAgo;
    private View placeHolder;
    private LinearLayout mLinearLayout;
    private RelativeLayout mRelativeLayout;
    Context mContext;
    SharedPreferences mSharedPreferences;
    WindowManager mWindowManager;
    int mood7days,mood6days,mood5days,mood4days,mood3days,mood2days,mood1days,moodNToday; // mood integer per day
    String comment7, comment6,comment5,comment4,comment3,comment2,comment1, commentToday; //Comment Strings
    // HistoryScreen day fragment indicator
    int dayIndicator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_bar, container, false);

        ///////////////  TEST  /////////////////////////
        Context context = getActivity();

        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        // check display size to figure out what image resolution will be loaded
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Point size = new Point();
        display.getSize(size);
        float width = size.x/1.25f;

        RelativeLayout relativeLayout = (RelativeLayout) container.findViewById(R.id.relativeLayout_background_and_sum);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = (int)width;
        /////////////// TEST  ///////////////////////////

        daysAgo = (TextView) view.findViewById(R.id.dayTxt);
        commentBtn = (ImageButton) view.findViewById(R.id.commentButton);
        placeHolder = (View) view.findViewById(R.id.paddingView);

        SharedPreferences result = this.getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        dayIndicator = result.getInt("DayIndicator",-1);

        // Load mood reference numbers of each day for the last 7 days
        mood7days = result.getInt("7DaysAgo", -1);
        mood6days = result.getInt("6DaysAgo", -1);
        mood5days = result.getInt("5DaysAgo", -1);
        mood4days = result.getInt("4DaysAgo", -1);
        mood3days = result.getInt("3DaysAgo", -1);
        mood2days = result.getInt("2DaysAgo", -1);
        mood1days = result.getInt("1DaysAgo", -1);
        moodNToday = result.getInt("TodayMood", -1);
        // Load comments of the last 7 days
        comment7 = result.getString("comment7DaysAgo","default");
        comment6 = result.getString("comment6DaysAgo","default");
        comment5 = result.getString("comment5DaysAgo","default");
        comment4 = result.getString("comment4DaysAgo","default");
        comment3 = result.getString("comment3DaysAgo","default");
        comment2 = result.getString("comment2DaysAgo","default");
        comment1 = result.getString("comment1DaysAgo","default");
        commentToday = result.getString("DailyCommentData","default");

        switch (dayIndicator){
            case 0:
                daysAgo.setText("Today: ");
                break;
            case 1:
                daysAgo.setText("Yesterday: ");
                break;
            case 2:
                daysAgo.setText("2 Days ago: ");
                break;
            case 3:
                daysAgo.setText("3 Days ago: ");
                break;
            case 4:
                daysAgo.setText("4 Days ago: ");
                break;
            case 5:
                daysAgo.setText("5 Days ago: ");
                break;
            case 6:
                daysAgo.setText("6 Days ago: ");
                break;
            case 7:
                daysAgo.setText("7 Days ago: ");
                break;

        }
















//        SharedPreferences preferences = this.getActivity().getSharedPreferences("SaveData", Context.MODE_PRIVATE);
//        String value = preferences.getString("7DaysAgo", "default");
//        // Show the mood of specific day
//        moodText.setText("7 days ago: \n"+value);
//        // Check the mood and switch it to set the correct bar-color
//        switch (value) {
//            case "Super Happy Mood":
//                colorBar.setImageResource(R.drawable.super_happy_color);
//                FrameLayout.LayoutParams superHappy = new FrameLayout.LayoutParams(1000, 300);
//                superHappy.setMargins(0, 0, 0 ,0);
//                colorBar.setLayoutParams(superHappy);
//                break;
//            case "Happy Mood!":
//                colorBar.setImageResource(R.drawable.happy_color);
//                RelativeLayout.LayoutParams happy = new RelativeLayout.LayoutParams(800, 300);
//                happy.setMargins(0, 0, 0 ,0);
//                colorBar.setLayoutParams(happy);
//                layout7DaysAgo = (LinearLayout) getActivity().findViewById(R.id.frame_7_days_ago);
//                layout7DaysAgo.setWeightSum(30);
//                LinearLayout.LayoutParams happy = new LinearLayout.LayoutParams(800, 250);
//                happy.setMargins(0, 0, 0 ,0);
//                layout7DaysAgo.setLayoutParams(happy);
//                layout7DaysAgo.setBackgroundColor(Color.GREEN);


//                break;
//            case "Normal Mood":
//                colorBar.setImageResource(R.drawable.normal_color);
//                FrameLayout.LayoutParams normal = new FrameLayout.LayoutParams(800, 300);
//                normal.setMargins(0, 0, 0 ,0);
//                colorBar.setLayoutParams(normal);
//                break;
//            case "Disappointed":
//                colorBar.setImageResource(R.drawable.disappointed_color);
//                FrameLayout.LayoutParams disapp = new FrameLayout.LayoutParams(700, 300);
//                disapp.setMargins(0, 0, 0 ,0);
//                colorBar.setLayoutParams(disapp);
//                break;
//            case "Sad Mood":
//                colorBar.setImageResource(R.drawable.sad_color);
//                FrameLayout.LayoutParams sad = new FrameLayout.LayoutParams(600, 300);
//                sad.setMargins(0, 0, 0 ,0);
//                colorBar.setLayoutParams(sad);
//                break;
//            case "default":
//                colorBar.setImageResource(R.drawable.no_color);
//        }

        // Show/hide the comment button by checking if comment was made for that day
//        if (preferences.getString("comment7DaysAgo", "default").isEmpty() || preferences.getString("comment7DaysAgo", "default").equals("default")){
//            commentBtn.setVisibility(View.INVISIBLE);
//        }

        return view;
    }


}
