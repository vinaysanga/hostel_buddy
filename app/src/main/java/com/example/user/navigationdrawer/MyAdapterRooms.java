package com.example.user.navigationdrawer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapterRooms extends RecyclerView.Adapter<MyAdapterRooms.ViewHolder> {
    ArrayList<Rooms> arrayList = new ArrayList<>();
    public MyAdapterRooms(ArrayList<Rooms> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_list_view_room,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rooms rooms = arrayList.get(position);
        holder.room_no.setText(rooms.getRoom_no().toString());
        holder.limit.setText(rooms.getLimit().toString());
        holder.vacancy.setText(rooms.getVacancy().toString());
    }

    @Override
    public int getItemCount() {
        return arrayList.size() ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView room_no,limit,vacancy;
        public ViewHolder(View itemView) {
            super(itemView);
            room_no = (TextView)itemView.findViewById(R.id.room_no);
            limit = (TextView)itemView.findViewById(R.id.max_occupants);
            vacancy = (TextView)itemView.findViewById(R.id.vacancy);
        }
    }
}
