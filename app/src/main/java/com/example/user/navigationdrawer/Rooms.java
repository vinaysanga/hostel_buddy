package com.example.user.navigationdrawer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Room;

@Entity(tableName = "ROOMS")
public class Rooms{
    @PrimaryKey
    Integer room_no;
    Integer limit;
    Integer vacancy;
    public Rooms(){}
    public Rooms(Integer room_no, Integer limit) {
        this.room_no = room_no;
        this.limit = limit;
        this.vacancy = limit;
    }

    public Integer getRoom_no() {
        return room_no;
    }

    public void setRoom_no(Integer room_no) {
        this.room_no = room_no;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getVacancy() {
        return vacancy;
    }

    public void setVacancy(Integer vacancy) {
        this.vacancy = vacancy;
    }
}
