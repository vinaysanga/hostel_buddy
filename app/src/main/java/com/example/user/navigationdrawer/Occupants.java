package com.example.user.navigationdrawer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.content.Intent;
import android.support.annotation.NonNull;

@Entity (indices = {@Index(value = "phone",unique = true)},tableName = "OCCUPANTS",
        foreignKeys = @ForeignKey(entity = Rooms.class,parentColumns = "room_no",childColumns = "room",onDelete = 5,onUpdate = 5))
public class Occupants {
    @PrimaryKey(autoGenerate = true)@NonNull
    Integer oid;
    @NonNull
    String name;
    @NonNull
    String phone;
    @NonNull
    String room;
    public Occupants(){}

    public Occupants(@NonNull Integer oid, @NonNull String name, @NonNull String phone, @NonNull String room) {
        this.oid = oid;
        this.name = name;
        this.phone = phone;
        this.room = room;
    }

    @NonNull
    public Integer getOid() {
        return oid;
    }

    public void setOid(@NonNull Integer oid) {
        this.oid = oid;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    @NonNull
    public String getRoom() {
        return room;
    }

    public void setRoom(@NonNull String room) {
        this.room = room;
    }
}
