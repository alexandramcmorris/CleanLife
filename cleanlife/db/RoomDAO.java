package com.example.cleanlife.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoomDAO {

    @Insert
    void insert(RoomTable roomTable);

    @Query("select * from RoomTable")
    LiveData<List<RoomTable>> getAll();

    //get the correct room list
    @Query("SELECT room_lists FROM RoomTable WHERE room_id LIKE:id " +
        "AND room_difficulty LIKE:diff")
    String getRoom(String id, String diff);

    //get room name for lineage
    @Query("SELECT room_name FROM RoomTable WHERE room_id LIKE:rid")
    String getRoomName(String rid);
}
