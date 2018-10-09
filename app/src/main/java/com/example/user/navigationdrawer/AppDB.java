package com.example.user.navigationdrawer;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
@Database(entities = {Occupants.class,Rooms.class,RentCalender.class},version = 1)
public abstract class AppDB extends RoomDatabase{
    public abstract MyDAO myDAO();
}
