package com.example.cleanlife.db;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Lineage {

    @PrimaryKey(autoGenerate = true)
    int l_id;

    String lineDifficulty, lineroomID, lineQuantity;

    public Lineage(String lineDifficulty, String lineroomID, String lineQuantity)
    {
        this.lineDifficulty = lineDifficulty;
        this.lineroomID = lineroomID;
        this.lineQuantity = lineQuantity;
    }

    public int getL_id() {
        return l_id;
    }

    public void setL_id(int id)
    {
        this.l_id = id;
    }

    public String getLineDifficulty() {
        return lineDifficulty;
    }

    public void setCourseName(String lineDifficulty) {
        this.lineDifficulty = lineDifficulty;
    }

    public String getLineQuantity() {
        return lineQuantity;
    }

    public void setLineDifficulty(String lineDifficulty) {
        this.lineDifficulty = lineDifficulty;
    }

    public String getLineroomID() {
        return lineroomID;
    }

    public void setLineroomID(String lineroomID) {
        this.lineroomID = lineroomID;
    }

}
