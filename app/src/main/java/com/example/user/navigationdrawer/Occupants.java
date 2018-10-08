package com.example.user.navigationdrawer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (tableName = "OCCUPANTS")
class Occupants {
    String name;
    @PrimaryKey@NonNull
    String phone;
    String room;
    public Occupants(){}

    public Occupants(String name, @NonNull String phone, String room) {
        this.name = name;
        this.phone = phone;
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
