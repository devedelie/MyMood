package com.elbaz.eliran.mymood.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.model.History;

import java.util.ArrayList;

/**
 * Created by Eliran Elbaz on 28-Apr-19.
 *
 * Adapter will adapt the list of objects to the recyclerView
 * The adaptors job is to take each individual object, and set the properties of each object
 * to the individual list items of the recyclerView
 */
public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder>{


    private ArrayList<History> mHistoryArrayList = new ArrayList<>();

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
        viewHolder.dayText.setText(mHistoryArrayList.get(i).getDayText());

    }

    @Override
    public int getItemCount() {
        return mHistoryArrayList.size(); // this is going to return the size of items we have in our ArrayList (number of rows)
    }

    // ViewHolder() class is used to "hold" the current views that is shown at the moment on the screen
      // The rest of the items will be recycled once we scroll up/down
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView dayText;
        ImageButton commentBtn;
        View paddingView;
        String commentData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayText = itemView.findViewById(R.id.dayTxt);
            commentBtn = itemView.findViewById(R.id.commentButton);
            paddingView = itemView.findViewById(R.id.paddingView);

        }
    }
}
