package com.example.canvaspre.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;
import com.example.canvaspre.model.ent.DoorEntity;
import java.util.List;

@Dao
public interface DoorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<DoorEntity> doorEntityList);

    @Query("select * from doors")
    List<DoorEntity> getAll();

    @Query("DELETE FROM doors")
    void deleteAll();

}
