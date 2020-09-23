package com.example.cleanlife.Rooms;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.cleanlife.db.AppDatabase;
import com.example.cleanlife.db.Storage;

import java.util.List;

public class allRoomsViewModel extends ViewModel {
    private LiveData<List<Storage>> roomList;

    public LiveData<List<Storage>> getRoomList(Context c)
    {
        if (roomList != null)
        {
            return roomList;
        }
        else
        {
            return roomList = AppDatabase.getInstance(c).StorageDAO().getAllRooms();
        }
    }
}
