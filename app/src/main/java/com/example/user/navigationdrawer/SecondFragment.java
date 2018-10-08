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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        arrayList = (ArrayList) MainActivity.appDB.myDAO().getOccupants();
        sortData();
        adapter = new MyAdapterOccupants(arrayList);
        recyclerView.setAdapter(adapter);
        return myView;
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        MyAdapterOccupants tempAdapter = (MyAdapterOccupants) adapter;
        int position = item.getGroupId();
        Occupants occupant = new Occupants();
        switch (item.getItemId()){
            case 121:
                //Snackbar.make(myView,"Position"+position,Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(),EditOccupantsActivity.class);
                int oid = arrayList.get(position).getOid();
                String name = arrayList.get(position).getName();
                String phone = arrayList.get(position).getPhone();
                String room = arrayList.get(position).getRoom();
                intent.putExtra("Oid",Integer.toString(oid));
                intent.putExtra("Name",name);
                intent.putExtra("Phone",phone);
                intent.putExtra("Room",room);
                getActivity().startActivityForResult(intent,2);
                return true;
            case 122:
                try {
                    occupant.setOid(arrayList.get(position).getOid());
                    String room_num = arrayList.get(position).getRoom();
                    int vacancy = MainActivity.appDB.myDAO().getVacancy(Integer.parseInt(room_num));
                    if(vacancy != 0)
                        vacancy++;
                    MainActivity.appDB.myDAO().updateVacancy(arrayList.get(position).getRoom(),Integer.toString(vacancy));
                    MainActivity.appDB.myDAO().deleteOccupant(occupant);
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
    private void sortData() {
        Collections.sort(arrayList, new Comparator<Occupants>() {
            @Override
            public int compare(Occupants lhs, Occupants rhs) {
                return lhs.getName().compareToIgnoreCase(rhs.getName());
            }
        });
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
        sortData();
        MainActivity mainActivity = (MainActivity)getActivity();
        adapter = new MyAdapterOccupants(arrayList);
        recyclerView.setAdapter(adapter);
        mainActivity.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),AddOccupantsActivity.class);
                startActivity(i);
            }
        });
    }
}
