package com.example.user.navigationdrawer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    ArrayList<Occupants> arrayList = new ArrayList<>();
    public MyAdapter(ArrayList<Occupants> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_list_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Occupants occupants = arrayList.get(position);
        holder.name.setText(occupants.getName());
        holder.phone.setText(occupants.getPhone());
        holder.room.setText(occupants.getRoom());
    }

    @Override
    public int getItemCount() {
        return arrayList.size() ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,phone,room;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            phone = (TextView)itemView.findViewById(R.id.phone);
            room = (TextView)itemView.findViewById(R.id.room);
        }
    }
}
