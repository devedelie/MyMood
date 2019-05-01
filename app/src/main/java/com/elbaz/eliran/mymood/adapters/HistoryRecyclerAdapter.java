package com.elbaz.eliran.mymood.adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.model.History;

import java.util.ArrayList;

/**
 * Created by Eliran Elbaz on 28-Apr-19.
 *
 * Adapter is our bridge between our data (our arrayList) and the recyclerView
 *
 * The adaptors job is to take each individual object, and set the properties of each object
 * to the individual list items of the recyclerView
 */
public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder>{


    private ArrayList<History> mHistoryArrayList = new ArrayList<>();
    RecyclerView mRecyclerView;

    public HistoryRecyclerAdapter(ArrayList<History> historyArrayList) {
        mHistoryArrayList = historyArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_list_item,viewGroup,false);
        return new ViewHolder(view); // this will pass the view object to the constructor of the ViewHolder() class below
    }

    // onBindViewHolder is going to get called for every single entry in the list, X items = X calls
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // Show the day text
        viewHolder.dayText.setText(mHistoryArrayList.get(i).getDayText());
        // Condition to check if the commentData is loaded.  If empty, it won't show the image button.
        if(mHistoryArrayList.get(i).getCommentData()!="" &&  mHistoryArrayList.get(i).getCommentData() != null){
            viewHolder.commentBtn.setImageResource(mHistoryArrayList.get(i).getCommentBtnImage());
            // test colors --- Should be moved to other position
            viewHolder.itemView.setBackgroundColor(Color.CYAN);
        }


    }

    @Override
    public int getItemCount() {
        return mHistoryArrayList.size(); // this is going to return the size of items we have in our ArrayList (number of rows)
    }

    // ViewHolder() class is used to "hold" the current views that is shown at the moment on the screen
      // The rest of the items will be recycled once we scroll up/down
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView dayText;
        ImageView commentBtn;
        View paddingView;
        String commentData;
        RelativeLayout mRelativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayText = itemView.findViewById(R.id.dayTxt);
            commentBtn = itemView.findViewById(R.id.commentButton);
//            paddingView = itemView.findViewById(R.id.paddingView);

        }
    }


}
