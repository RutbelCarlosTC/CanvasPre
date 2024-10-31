package com.example.canvaspre.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.canvaspre.model.ent.RoomAndVertex;
import com.example.canvaspre.model.ent.RoomEntity;

import java.util.List;

@Dao
public interface RoomDao {
     @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<RoomEntity> roomEntityList);

    @Query("select * from rooms")
    List<RoomEntity> getAll();

    @Query("select * from rooms where roomId=:roomId")
    List<RoomEntity> getByRoomId(int roomId);

    @Query("select * from rooms")
    List<RoomAndVertex> getRoomWithVertex();

    @Query("select * from rooms where roomId=:roomId")
    RoomAndVertex getRoomWithVertexByRoomId(int roomId);

     @Query("DELETE FROM rooms")
    void deleteAll();
}
