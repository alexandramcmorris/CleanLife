package com.example.cleanlife.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Storage {

    @PrimaryKey (autoGenerate = true)
    int sid;

    int lineageID;
    String sroomname;
    boolean isDone;

    public Storage(int lineageID, String sroomname, boolean isDone)
    {
        this.lineageID = lineageID;
        this.sroomname = sroomname;
        this.isDone = isDone;
    }

    public int getSid()
    {
        return sid;
    }

    public void setSid(int id)
    {
        this.sid = id;
    }

    public int getLineageID()
    {
        return lineageID;
    }

    public void setLineageID(int lineageID)
    {
        this.lineageID = lineageID;
    }

    public String getSroomname()
    {
        return sroomname;
    }

    public void setSroomname(String roomname)
    {
        this.sroomname = roomname;
    }

    public boolean getisDone()
    {
        return isDone;
    }

    public void setisDone(boolean done)
    {
        this.isDone = done;
    }




}
