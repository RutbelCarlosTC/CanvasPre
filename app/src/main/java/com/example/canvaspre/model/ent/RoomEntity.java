package com.example.canvaspre.model.ent;

import android.graphics.Point;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
@Entity(tableName = "rooms")
public class RoomEntity {
    @PrimaryKey
    public int roomId;

    private String name;
    public String label;
    private List<Point> vertices;

    public RoomEntity(String name, List<Point> vertices) {
        this.name = name;
        this.vertices = vertices;
    }

    public String getName() { return name; }
    public List<Point> getVertices() { return vertices; }
}
