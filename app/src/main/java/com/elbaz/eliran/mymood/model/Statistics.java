package com.elbaz.eliran.mymood.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.elbaz.eliran.mymood.R;

public class Statistics extends AppCompatActivity {//implements NoteDialog.CommentListener {

    private TextView userTodayMood, userComment, userMood7, userMood6, userMood5, userMood4, userMood3, userMood2, userMood1;
    private ImageView commentToday, commentYesterday, comment2Days, comment3Days, comment4Days, comment5Days, comment6Days, comment7Days;
    String commentData7DaysAgo, commentData6DaysAgo, commentData5DaysAgo, commentData4DaysAgo,commentData3DaysAgo, commentData2DaysAgo, commentDataYesterday, commentDataToday;
    String bar7,bar6,bar5,bar4,bar3,bar2,bar1;
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        // Link the layout text lines
        userMood7 = (TextView) findViewById(R.id.user_mood_statistics_7_days_ago);
        userMood6 = (TextView) findViewById(R.id.user_mood_statistics_6_days_ago);
        userMood5 = (TextView) findViewById(R.id.user_mood_statistics_5_days_ago);
        userMood4 = (TextView) findViewById(R.id.user_mood_statistics_4_days_ago);
        userMood3 = (TextView) findViewById(R.id.user_mood_statistics_3_days_ago);
        userMood2 = (TextView) findViewById(R.id.user_mood_statistics_2_days_ago);
        userMood1 = (TextView) findViewById(R.id.user_mood_statistics_1_days_ago);
        userTodayMood = (TextView) findViewById(R.id.user_mood_statistics_test);


        // Link comment btn images to each mood line on the layout
        comment7Days = (ImageView) findViewById(R.id.btn_comment_7_days_ago);
        comment6Days = (ImageView) findViewById(R.id.btn_comment_6_days_ago);
        comment5Days = (ImageView) findViewById(R.id.btn_comment_5_days_ago);
        comment4Days = (ImageView) findViewById(R.id.btn_comment_4_days_ago);
        comment3Days = (ImageView) findViewById(R.id.btn_comment_3_days_ago);
        comment2Days = (ImageView) findViewById(R.id.btn_comment_2_days_ago);
        commentYesterday = (ImageView) findViewById(R.id.btn_comment_yesterday);

        userComment = (TextView) findViewById(R.id.user_mood_note); // to be erased


//        // Initializer for testing only - to be erased
//        mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = mSharedPreferences.edit();
//        editor.putString("comment2DaysAgo", "");
//        editor.apply();
//        //End of initializer

        /**
         * Check if comment button should be visible/Invisible
         */
        SharedPreferences showOrHide = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        if (showOrHide.getString("comment7DaysAgo", "default").isEmpty())
            comment7Days.setVisibility(View.INVISIBLE);
        if (showOrHide.getString("comment6DaysAgo", "default").isEmpty())
            comment6Days.setVisibility(View.INVISIBLE);
        if (showOrHide.getString("comment5DaysAgo", "default").isEmpty())
            comment5Days.setVisibility(View.INVISIBLE);
        if (showOrHide.getString("comment4DaysAgo", "default").isEmpty())
            comment4Days.setVisibility(View.INVISIBLE);
        if (showOrHide.getString("comment3DaysAgo", "default").isEmpty())
            comment3Days.setVisibility(View.INVISIBLE);
        if (showOrHide.getString("comment2DaysAgo", "default").isEmpty())
            comment2Days.setVisibility(View.INVISIBLE);
        if (showOrHide.getString("comment1DaysAgo", "default").isEmpty())
            commentYesterday.setVisibility(View.INVISIBLE);
        if (showOrHide.getString("dailyCommentData", "default").isEmpty()) // to be erased
            commentToday.setVisibility(View.INVISIBLE); // to be erased


        // Load the values of daily moods from SharedPreferences into the layout
        SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);

        String value7 = result.getString("7DaysAgo", "default");
        String value6 = result.getString("6DaysAgo", "default");
        String value5 = result.getString("5DaysAgo", "default");
        String value4 = result.getString("4DaysAgo", "default");
        String value3 = result.getString("3DaysAgo", "default");
        String value2 = result.getString("2DaysAgo", "default");
        String value1 = result.getString("1DaysAgo", "default");
        String value = result.getString("TodayMood", "default");

        userMood7.setText("Your mood 7 days ago: "+ value7);
        userMood6.setText("Your mood 6 days ago: "+ value6);
        userMood5.setText("Your mood 5 days ago: "+ value5);
        userMood4.setText("Your mood 4 days ago: "+ value4);
        userMood3.setText("Your mood 3 days ago: "+ value3);
        userMood2.setText("Your mood 2 days ago: "+ value2);
        userMood1.setText("Your mood yesterday: "+ value1);

        //Load all daily comment 7 days old
        commentData7DaysAgo = result.getString("comment7DaysAgo", "default");
        commentData6DaysAgo = result.getString("comment6DaysAgo", "default");
        commentData5DaysAgo =  result.getString("comment5DaysAgo", "default");
        commentData4DaysAgo = result.getString("comment4DaysAgo", "default");
        commentData3DaysAgo = result.getString("comment3DaysAgo", "default");
        commentData2DaysAgo = result.getString("comment2DaysAgo", "default");
        commentDataYesterday = result.getString("comment1DaysAgo", "default");
        commentDataToday = result.getString("DailyCommentData", "default");

        userTodayMood.setText("Your mood today: "+ value); // to be erased
        userComment.setText(commentDataToday);  // to be erased



//        /**
//         * Statistics - mood color bar selection
//         */
//        //Reusing the object "result" from above
//        if (value6 != "default"){
//            bar6 = colorSwitch(value6);
//        }
//
//    }
//
//    public String colorSwitch(String moodToColor) {
//        switch (moodToColor) {
//            case "Super Happy Mood":
//                return "#FAF911";
//            case "Happy Mood!":
//                return "#9086";
//            case "Normal Mood":
//                return "#11D8D6";
//            case "Disappointed":
//                return "#A4A4A4";
//            case "Sad Mood":
//                return "#cf1a08";
//            case "default":
//                return "";
//
//        }return null;

    }

    /**
     * Methods which are used to show Toast messages for each comment button on the layout
     * @param view
     */
    public void Comment7daysAgo(View view){
        Toast.makeText(this, commentData7DaysAgo, Toast.LENGTH_LONG).show();
    }
    public void Comment6daysAgo(View view){
        Toast.makeText(this, commentData6DaysAgo, Toast.LENGTH_LONG).show();
    }
    public void Comment5daysAgo(View view){
        Toast.makeText(this, commentData5DaysAgo, Toast.LENGTH_LONG).show();
    }
    public void Comment4daysAgoComment(View view){
        Toast.makeText(this, commentData4DaysAgo, Toast.LENGTH_LONG).show();
    }
    public void Comment3daysAgo(View view){
        Toast.makeText(this, commentData3DaysAgo, Toast.LENGTH_LONG).show();
    }
    public void Comment2daysAgo(View view){
        Toast.makeText(this, commentData2DaysAgo, Toast.LENGTH_LONG).show();
    }
    public void yesterdayComment(View view){
        Toast.makeText(this, commentDataYesterday, Toast.LENGTH_LONG).show();
    }
    public void todayComment(View view){
        Toast.makeText(this, commentDataToday, Toast.LENGTH_LONG).show();
    }
}
