package com.example.user.navigationdrawer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyDAO {
    @Insert
    long insertOccupants(Occupants occupants);
    @Query("SELECT * FROM OCCUPANTS")
    List<Occupants> getOccupants();
    @Query("SELECT COUNT(name) FROM OCCUPANTS")
    int getOccupantsCount();
    @Insert
    void insertRooms(Rooms rooms);
    @Query("SELECT * FROM ROOMS")
    List<Rooms> getRooms();
}
