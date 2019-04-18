package com.elbaz.eliran.mymood.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elbaz.eliran.mymood.R;

public class CommentDialog extends AppCompatActivity {
    SharedPreferences mSharedPreferences;
    EditText mComment;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_dialog);

        /**
         * Declaration of the Dialog Builder along with the Text and Button
         */
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(CommentDialog.this);
        View mView = getLayoutInflater().inflate(R.layout.activity_comment_dialog, null);
        mComment = (EditText) mView.findViewById(R.id.activity_comment_dialog);
        Button mSave = (Button) mView.findViewById(R.id.comment_box_save_btn);
        Button mCancel = (Button) mView.findViewById(R.id.comment_box_cancel_btn);

        /**
         * Set the save button functionality
         */
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        // Save Button

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mComment.getText().toString().isEmpty()){
                    // Save mComment variable into SharedPreferences
                    mSharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString("DailyCommentData", mComment.getText().toString());
                    editor.apply();
                    // Toast message and termination
                    Toast.makeText(CommentDialog.this, "Comment saved", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(CommentDialog.this, "Your comment field is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Cancel button
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //// End of Save/Cancel button
    }

}
