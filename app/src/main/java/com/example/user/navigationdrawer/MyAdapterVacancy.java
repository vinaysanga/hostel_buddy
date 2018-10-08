package com.example.user.navigationdrawer;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapterVacancy extends RecyclerView.Adapter<MyAdapterVacancy.ViewHolder> {
    ArrayList<Rooms> arrayList;
    public MyAdapterVacancy(ArrayList<Rooms> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_list_view_vacancy,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rooms rooms = arrayList.get(position);
            holder.cv.setCardBackgroundColor(0xFFB9F6CA);
        holder.room_no.setText(rooms.getRoom_no().toString());
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
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView room_no,vacancy;
        CardView cv;
        public ViewHolder(View itemView) {
            super(itemView);
            room_no = (TextView)itemView.findViewById(R.id.vac_room);
            vacancy = (TextView)itemView.findViewById(R.id.vac_vacancy);
            cv = (CardView)itemView.findViewById(R.id.card_view_vacancy);
        }
    }
}
