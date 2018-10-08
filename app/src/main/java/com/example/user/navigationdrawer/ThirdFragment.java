package com.example.user.navigationdrawer;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by user on 12/31/15.
 */
public class ThirdFragment extends Fragment{
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Rooms> arrayList = new ArrayList<>();
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.third_layout, container, false);
        recyclerView = (RecyclerView)myView.findViewById(R.id.recycler_rooms);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        arrayList = (ArrayList)MainActivity.appDB.myDAO().getRooms();
        adapter = new MyAdapterRooms(arrayList);
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
        arrayList = (ArrayList)MainActivity.appDB.myDAO().getRooms();
        MainActivity mainActivity = (MainActivity)getActivity();
        adapter = new MyAdapterRooms(arrayList);
        recyclerView.setAdapter(adapter);
        mainActivity.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),AddRoomsActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        MyAdapterRooms tempAdapter = (MyAdapterRooms) adapter;
        int position = item.getGroupId();
        Rooms room = new Rooms();
        switch (item.getItemId()){
            case 221:
                try {
                    room.setRoom_no(arrayList.get(position).getRoom_no());
                    MainActivity.appDB.myDAO().deleteRoom(room);
                    tempAdapter.removeItem(position);
                    Snackbar snackbar = Snackbar.make(myView,"Removed Successfully !!",Snackbar.LENGTH_LONG);
                    View snackbarview = snackbar.getView();
                    snackbarview.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.materialGreenDark,null));
                    snackbar.show();
                }catch (Exception e) {
                    String error = e.getClass().toString();
                    Snackbar snackbar = Snackbar.make(myView, error, Snackbar.LENGTH_LONG);
                    View snackbarview = snackbar.getView();
                    snackbarview.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.materialRedDark,null));
                    snackbar.show();
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
