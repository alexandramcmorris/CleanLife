package com.example.cleanlife.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StorageDAO {
    @Insert
    void insert(Storage storage);

    @Update
    void update(Storage storage);

    @Query("select * from Storage")
    LiveData<List<Storage>> getAllRooms();

    @Query("SELECT sroomname FROM Storage WHERE sid=:sid")
    String returnName(int sid);

    @Query("SELECT * FROM Storage WHERE sid=:sids")
    Storage returnStorageItem(int sids);


    @Query("SELECT * FROM Storage")
    List<Storage> testinglistRooms();

    @Insert
    void insertList(ListStorage listStorage);

    @Update
    void updateList(ListStorage listStorage);

    @Query("select * from ListStorage WHERE storageID =:storid")
    List<ListStorage> getAllItemsRoom(int storid);
}
