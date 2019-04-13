package com.elbaz.eliran.mymood.controller.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.elbaz.eliran.mymood.R;

/**
 * Created by Eliran Elbaz on 13-Apr-19.
 */
public class NoteDialog extends AppCompatDialogFragment {

    private EditText editComment;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_note_dialog, null);

        builder.setView(view)
                .setTitle("Add a comment:")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        editComment = view.findViewById(R.id.activity_note_dialog);

        return builder.create();
    }
}
