package com.example.user.navigationdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class VacancyActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList <Rooms> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancy);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_vacancy);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = (ArrayList)MainActivity.appDB.myDAO().getVacantRoomsList();
        adapter = new MyAdapterVacancy(arrayList);
        recyclerView.setAdapter(adapter);
    }
}
