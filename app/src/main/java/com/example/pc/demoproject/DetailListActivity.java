package com.example.pc.demoproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.pc.demoproject.adapter.DetailsListAdapter;
import com.example.pc.demoproject.database.DBHandler;
import com.example.pc.demoproject.database.modelClass.ClientDetailsModel;

import java.util.ArrayList;

public class DetailListActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    DetailsListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list);
        mRecyclerView = findViewById(R.id.recycler_view);
        setAdapter();
    }

    void setAdapter() {
        DBHandler db = new DBHandler(getApplicationContext());
        ArrayList<ClientDetailsModel> list = new ArrayList<>();
        list = db.getAllDataList();
        if (list.size()>0){
            mRecyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getApplicationContext());
            mRecyclerView.setLayoutManager(layoutManager);
            mAdapter = new DetailsListAdapter(this, list);
            mRecyclerView.setAdapter(mAdapter);
        }else {
            Toast.makeText(DetailListActivity.this, "Data list is empty!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
