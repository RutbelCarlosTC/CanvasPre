package com.example.canvaspre.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.canvaspre.model.ent.PictureEntity;

import java.util.List;

@Dao
public interface PictureDao {
     @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<PictureEntity> pictureEntityList);

    @Query("SELECT * FROM pictures")
    List<PictureEntity> getAll();

    @Query("SELECT * FROM pictures where roomId=:roomId")
    List<PictureEntity> getPicturesByRoomId(int roomId);

    @Query("select * from pictures where pictureId=:pictureId")
    PictureEntity getPictureById(int pictureId);

     @Query("DELETE FROM pictures")
    void deleteAll();
}
