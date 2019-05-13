package com.elbaz.eliran.mymood.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.model.Constants;

public class HistoryActivity extends AppCompatActivity {
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        createHistoryList();
    }

    public void createHistoryList(){
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        LayoutInflater inflater = LayoutInflater.from(this);
        float[] paddingWeight = {0.4f, 0.8f, 1.66f, 3.9f, 1000f};

        for (int i = 0; i <= 6; i++) {
            // Inflate the bar
            @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.history_item, null);
            // Link the layout elements with the activity
            View paddingView = view.findViewById(R.id.paddingView);
            ImageButton commentBtn = view.findViewById(R.id.commentButton);
            TextView textView = view.findViewById(R.id.dayTxt);

            //Get the mood of the current day
            mSharedPreferences  = getSharedPreferences("Data", Context.MODE_PRIVATE);
            int mood = mSharedPreferences.getInt(Constants.moodDaysAgo[i], -1);
            // check if NO content available on a specific day, and set it invisible
            if(mood == -1){
                textView.setText(Constants.dataNotAvailable);
                commentBtn.setVisibility(View.INVISIBLE);
            }else{
                // Set relevant text
                textView.setText(Constants.historyDaysText[i]);

                // Set background color
                view.setBackgroundColor(getResources().getColor(Constants.historyColors[mood]));
                // Set bar width
                paddingView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, paddingWeight[mood]));

                // Get the comment of specific day and show button, or hide if not exist
                final String comment = mSharedPreferences.getString(Constants.commentDaysAgo[i],"");
                if (comment.equals("") || comment.isEmpty()) {
                    commentBtn.setVisibility(View.INVISIBLE);
                } else {
                    commentBtn.setOnClickListener(new View.OnClickListener() {

                        // Toast message to show the comment
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(HistoryActivity.this, comment, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            linearLayout.addView(view, params);
        }
    }
}

