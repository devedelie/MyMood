package com.elbaz.eliran.mymood.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.elbaz.eliran.mymood.R;
import com.elbaz.eliran.mymood.adapters.HistoryRecyclerAdapter;
import com.elbaz.eliran.mymood.controller.util.HistoryItemDecorator;
import com.elbaz.eliran.mymood.model.History;

import java.util.ArrayList;

public class HistoryListActivity extends AppCompatActivity {

    // UI Components
    private RecyclerView mRecyclerView;

    // vars
    private ArrayList<History> mHistory = new ArrayList<>();
    private HistoryRecyclerAdapter mHistoryRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        mRecyclerView = findViewById(R.id.recyclerView);

        initRecyclerView();
        insertHistoryData();
    }

    private void insertHistoryData(){

        for (int x=7; x>=0 ; x-- ){
            switch (x){
                case 7:
                    History history = new History();
                    mHistory.add(new History("7 Days ago","comment bla bla",(R.drawable.ic_comment_black_48px)));
                    break;
                case 6:
                    mHistory.add(new History("6 Days ago","comment bla bla",(R.drawable.ic_comment_black_48px)));
                    break;
                case 5:
                    mHistory.add(new History("5 Days ago","comment bla bla",(R.drawable.ic_comment_black_48px)));
                    break;
                case 4:
                    mHistory.add(new History("4 Days ago","comment bla bla",(R.drawable.ic_comment_black_48px)));
                    break;
                case 3:
                    mHistory.add(new History("3 Days ago","comment bla bla",(R.drawable.ic_comment_black_48px)));
                    break;
                case 2:
                    mHistory.add(new History("2 Days ago","",(R.drawable.ic_comment_black_48px)));
                    break;
                case 1:
                    mHistory.add(new History("1 Day ago","comment bla bla",(R.drawable.ic_comment_black_48px)));
                    break;
                case 0:
                    mHistory.add(new History("Today","comment bla bla",(R.drawable.ic_comment_black_48px)));
                    break;

            }

        }

        // FOR loop to set 7 days titles on history screen
//        for (int i = 7; i>=0; i--){
//            if (i == 1){
//                History history = new History();
////                history.setDayText("Yesterday"); // add text for yesterday item
//                mHistory.add(new History("Yesterday","comment bla bla",(R.drawable.ic_comment_black_48px)));
//            }else if (i == 0){
//                History history = new History();
////                history.setDayText("Today"); // add text for today item
//                mHistory.add(new History("Today","",(R.drawable.ic_comment_black_48px)));
//            }else{
//                 History history = new History();
//                 history.setDayText(i + " Days ago"); // add text title for 7-2 days ago
//                 mHistory.add(history);
//            }
//        }


        // Important: Need to call notifyDataSetChanged(), otherwise no data will be shown on the list
        mHistoryRecyclerAdapter.notifyDataSetChanged();
    }




    private void initRecyclerView(){
        // Setting a LinearLayout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // set the layoutManager to the recyclerView
        mRecyclerView.setLayoutManager(linearLayoutManager);


        //**************************************TEST set item width
        HistoryItemDecorator itemDecorator = new HistoryItemDecorator(1.25f, 0.25f);
        mRecyclerView.addItemDecoration(itemDecorator);
        //*************************************************


        // Instantiate the adapter
        mHistoryRecyclerAdapter = new HistoryRecyclerAdapter(mHistory);
        // set the adapter to the recyclerView
        mRecyclerView.setAdapter(mHistoryRecyclerAdapter);
    }

}
