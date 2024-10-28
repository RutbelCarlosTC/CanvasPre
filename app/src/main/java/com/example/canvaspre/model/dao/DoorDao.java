package com.example.canvaspre.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;
import com.example.canvaspre.model.ent.DoorEntity;
import java.util.List;

@Dao
public interface DoorDao {
    @Insert
    void insert(List<DoorEntity> doorEntityList);

    @Query("select * from doors")
    List<DoorEntity> getAll();

}
