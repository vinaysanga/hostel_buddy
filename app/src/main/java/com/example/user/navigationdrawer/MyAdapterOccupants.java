package com.example.user.navigationdrawer;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapterOccupants extends RecyclerView.Adapter<MyAdapterOccupants.ViewHolder> {
    ArrayList<Occupants> arrayList;
    public MyAdapterOccupants(){}
    public MyAdapterOccupants(ArrayList<Occupants> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_list_view_occupants,parent,false);
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

    public void removeItem(int position){
        arrayList.remove(position);
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView name,phone,room;
        CardView cv;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            phone = (TextView)itemView.findViewById(R.id.phone);
            room = (TextView)itemView.findViewById(R.id.room);
            cv = (CardView)itemView.findViewById(R.id.card_view_occupants);
            cv.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Select an Option");
            contextMenu.add(this.getAdapterPosition(),121,0,"Edit");
            contextMenu.add(this.getAdapterPosition(),122,1,"Delete");
        }
    }
}
