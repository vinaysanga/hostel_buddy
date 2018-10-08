package com.example.user.navigationdrawer;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by user on 12/31/15.
 */
public class SecondFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList <Occupants> arrayList = new ArrayList<>();
    View myView;
    public SecondFragment(){}

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.second_layout, container, false);
        recyclerView = (RecyclerView)myView.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        arrayList = (ArrayList)MainActivity.appDB.myDAO().getOccupants();
        adapter = new MyAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        return myView;
    }
    public void setUserVisibleHint(boolean visible)
    {
        super.setUserVisibleHint(visible);
        if (visible && isResumed())
        {
            onResume();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (!getUserVisibleHint())
        {
            return;
        }
        arrayList = (ArrayList)MainActivity.appDB.myDAO().getOccupants();
        MainActivity mainActivity = (MainActivity)getActivity();
        adapter = new MyAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        mainActivity.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),OccupantsActivity.class);
                startActivity(i);
            }
        });
    }
}
