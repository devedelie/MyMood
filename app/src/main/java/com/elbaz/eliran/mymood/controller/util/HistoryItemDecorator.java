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
    private float mainDivider, complementForFloat, paddingSpaceOnRight;


    // Constructor to use when no need any value from HistoryListActivity
    public HistoryItemDecorator(float floatValueDevider,float complement ){
        this.mainDivider = floatValueDevider;
        this.complementForFloat = complement;

    }
    // Constructor to use when need decoration with a specific value from HistoryListActivity
    public HistoryItemDecorator(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // Divide the width of the item by certain values for shrinking its size
        paddingSpaceOnRight = parent.getMeasuredWidth() / mainDivider;
        view.getLayoutParams().width = (int)(paddingSpaceOnRight + complementForFloat);
    }
}
