package com.elbaz.eliran.mymood.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Eliran Elbaz on 17-Apr-19.
 */
public class DataOrganizeTaskLauncher extends BroadcastReceiver {

    // This method will be invoked once the AlarmManager reaches the midnight time (00:00:00)
    @Override
    public void onReceive(Context context, Intent intent) {
        // The task to be launched by Android AlarmManager when time is right.
        Toast.makeText(context, "BroadCast was received. Your data from yesterday is being saved now.", Toast.LENGTH_LONG).show();

        //Start the DataOrganizer.java class
        Intent dataOrganizer = new Intent();
        dataOrganizer.setClassName(context.getPackageName(), DataOrganizer.class.getName());
        dataOrganizer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(dataOrganizer);
    }
}
