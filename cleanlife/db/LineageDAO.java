package com.example.cleanlife.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import org.intellij.lang.annotations.Flow;

import java.util.List;

@Dao
public interface LineageDAO {
    @Insert
    void insert(Lineage lineage);

    //get line quantity
    @Query("Select lineQuantity FROM Lineage WHERE lineroomID LIKE:id")
    String getRoomAmount(String id);

    //return list of Lineage objects
    @Query("SELECT * FROM Lineage")
    List<Lineage> getAllLineage();

}
