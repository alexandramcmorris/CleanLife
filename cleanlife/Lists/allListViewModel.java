package com.example.cleanlife.Lists;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.cleanlife.db.AppDatabase;
import com.example.cleanlife.db.ListStorage;

import java.util.List;

public class allListViewModel extends ViewModel {
    private LiveData<List<ListStorage>> roomLists;
    private int holdStorage;

    allListViewModel(int stid)
    {
        this.holdStorage = stid;
    }

    /*public LiveData<List<ListStorage>> getLists(Context c)
    {
        if (roomLists != null)
        {
            return roomLists;
        }
        else
        {
            return roomLists = AppDatabase.getInstance(c).StorageDAO().getAllItemsRoom(holdStorage);
        }
    }*/


}
