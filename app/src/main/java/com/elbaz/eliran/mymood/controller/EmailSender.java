package com.elbaz.eliran.mymood.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.elbaz.eliran.mymood.R;

public class EmailSender extends AppCompatActivity {

    EditText mEmail, mComment;
    TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_sender);

    /**
     * Declaration of the Dialog Builder along with the Text and Button
     */
    AlertDialog.Builder mBuilder = new AlertDialog.Builder(EmailSender.this);
    View mView = getLayoutInflater().inflate(R.layout.activity_email_sender, null);
    mTitle = (TextView) mView.findViewById(R.id.email_dialog_activity_title);
    mEmail = (EditText) mView.findViewById(R.id.email_activity_email_dialog);
    mComment = (EditText) mView.findViewById(R.id.email_activity_comment_dialog);
    Button mSend = (Button) mView.findViewById(R.id.email_box_send_btn);
    Button mCancel = (Button) mView.findViewById(R.id.email_box_cancel_btn);
    // Get & set a pre-defined title for the email, from the current mood
    String data = getIntent().getExtras().getString("Email Subject","No Title");
    mTitle.setText(data);

        /**
         * KeyListener to detect the android "Back Press" button and dismiss the dialog box
         */
        mBuilder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_BACK){
                    dialog.dismiss(); // dismiss the dialog
                    EmailSender.this.finish(); // exits the activity
                }
                return true;
            }
        });

    /**
     * Set the save button functionality
     */
        mBuilder.setView(mView);
    final AlertDialog dialog = mBuilder.create();
        dialog.show();

        // Save Button
        mSend.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!mEmail.getText().toString().isEmpty()){
                // Send email Intent
                String recipientList = mEmail.getText().toString();
                String[] recipients = recipientList.split(",");

                String subject = "Hey, I would like to share my mood with you";
                String message = mComment.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);

                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose your email client"));

                // Toast message and termination
                Toast.makeText(EmailSender.this, "Sending Email", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(EmailSender.this, "Your comment field is empty", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
