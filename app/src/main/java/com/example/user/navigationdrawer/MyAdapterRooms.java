package com.example.user.navigationdrawer;

import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapterRooms extends RecyclerView.Adapter<MyAdapterRooms.ViewHolder> {
    ArrayList<Rooms> arrayList;
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
        if(rooms.getVacancy() == 0)
            holder.cv.setCardBackgroundColor(0xFFFF8A80);
        else if(rooms.getVacancy() == Math.round((0.25)*rooms.getLimit()))
            holder.cv.setCardBackgroundColor(0xFFFFFF8D);
        else
            holder.cv.setCardBackgroundColor(0xFFB9F6CA);
        holder.room_no.setText(rooms.getRoom_no().toString());
        holder.limit.setText(rooms.getLimit().toString());
        holder.vacancy.setText(rooms.getVacancy().toString());
    }

    @Override
    public int getItemCount() {
        return arrayList.size() ;
    }

    public void removeItem(int position){
        arrayList.remove(position);
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView room_no,limit,vacancy;
        CardView cv;
        public ViewHolder(View itemView) {
            super(itemView);
            room_no = (TextView)itemView.findViewById(R.id.room_no);
            limit = (TextView)itemView.findViewById(R.id.max_occupants);
            vacancy = (TextView)itemView.findViewById(R.id.vacancy);
            cv = (CardView)itemView.findViewById(R.id.card_view_rooms);
            cv.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Select an Option");
            contextMenu.add(this.getAdapterPosition(),221,1,"Delete");
        }
    }
}
