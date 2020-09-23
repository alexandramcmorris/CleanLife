package com.example.cleanlife.db;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cleanlife.R;

@Entity
public class RoomTable {

    //columns needed for room table
    @PrimaryKey(autoGenerate = true)
    int r_id;

    String room_id;
    String room_name;
    String room_difficulty;
    String room_lists;

    public RoomTable(String room_id, String room_name, String room_difficulty, String room_lists)
    {
        this.room_id = room_id;
        this.room_name = room_name;
        this.room_difficulty = room_difficulty;
        this.room_lists = room_lists;
    }

    public int getR_id()
    {
        return r_id;
    }

    public void setR_id(int id) {
        this.r_id = id;
    }

    public String getRoom_id()
    {
        return room_id;
    }

    public void setRoom_id(String room_id)
    {
        this.room_id = room_id;
    }

    public String getRoom_name(){
        return room_name;
    }

    public void setRoom_name(String room_name)
    {
        this.room_name = room_name;
    }

    public String getRoom_difficulty(){
        return room_difficulty;
    }

    public void setRoom_difficulty()
    {
        this.room_difficulty = room_difficulty;
    }

    public String getRoom_lists()
    {
        return room_lists;
    }

    public void setRoom_lists(String room_lists)
    {
        this.room_lists = room_lists;
    }

    @NonNull
    @Override
    public String toString() {
        return "Room: " + room_name + " " + room_difficulty;
    }


}
