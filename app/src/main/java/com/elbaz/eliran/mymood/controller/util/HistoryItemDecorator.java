package com.elbaz.eliran.mymood.controller.util;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Eliran Elbaz on 28-Apr-19.
 */
public class HistoryItemDecorator extends RecyclerView.ItemDecoration {

    private int verticalSpaceHeight;


    
    // Constructor to use when need decoration with a specific value from HistoryListActivity
    public HistoryItemDecorator(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

    }
}
