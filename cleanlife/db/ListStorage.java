package com.example.cleanlife.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity
public class ListStorage {
    @PrimaryKey (autoGenerate = true)
    int lsid;

    @ForeignKey(entity = Storage.class,
                parentColumns = "sid",
                childColumns = "storageID",
                onDelete = ForeignKey.CASCADE)
    int storageID;
    int listNum;
    String listItem;
    boolean listItemDone;

    public ListStorage(int storageID, int listNum, String listItem, boolean listItemDone)
    {
        this.storageID = storageID;
        this.listNum = listNum;
        this.listItem = listItem;
        this.listItemDone = listItemDone;
    }

    public void setLsid(int id)
    {
        this.lsid = id;
    }

    public int getLsid()
    {
        return lsid;
    }

    public void setStorageID(int id)
    {
        this.storageID = id;
    }

    public int getStorageID()
    {
        return storageID;
    }

    public void setListNum(int listnum)
    {
        this.listNum = listnum;
    }

    public int getListNum()
    {
        return listNum;
    }

    public void setListItem(String listItem)
    {
        this.listItem = listItem;
    }

    public String getListItem()
    {
        return listItem;
    }

    public void setListItemDone(boolean itemDone)
    {
        this.listItemDone = itemDone;
    }

    public boolean getListItemDone()
    {
        return listItemDone;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
