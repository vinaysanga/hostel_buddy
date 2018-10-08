package com.example.user.navigationdrawer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MyDAO {
    int room_no=0;
    @Insert
    void insertOccupants(Occupants occupants);
    @Query("SELECT * FROM OCCUPANTS")
    List <Occupants> getOccupants();
    @Query("SELECT COUNT(name) FROM OCCUPANTS")
    int getOccupantsCount();
    @Update
    void updateOccupant(Occupants occupants);
    @Delete
    void deleteOccupant(Occupants occupants);
    @Insert
    void insertRooms(Rooms rooms);
    @Query("SELECT * FROM ROOMS")
    List<Rooms> getRooms();
    @Query("SELECT SUM(vacancy) FROM ROOMS")
    int getVacancyCount();
    @Query("SELECT vacancy FROM ROOMS WHERE room_no = :room_num")
    int getVacancy(int room_num);
    @Query("SELECT COUNT(room_no) FROM ROOMS WHERE room_no = :room_num")
    int existsRooms(int room_num);
    @Query("UPDATE ROOMS SET vacancy = :vacancy WHERE room_no = :room_num")
    void updateVacancy(String room_num,String vacancy);
    @Delete
    void deleteRoom(Rooms room);
    @Query("SELECT * FROM ROOMS WHERE NOT(vacancy = 0)")
    List <Rooms> getVacantRoomsList();
}
