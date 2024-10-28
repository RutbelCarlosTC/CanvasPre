package com.example.canvaspre.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.canvaspre.model.ent.VertexEntity;

import java.util.List;

@Dao
public interface VertexDao {
    @Insert
    void insert(List<VertexEntity> vertexEntityList);

    @Query("select * from vertex")
    List<VertexEntity> getAll();

}
