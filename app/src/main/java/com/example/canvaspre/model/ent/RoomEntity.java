package com.example.canvaspre.model.ent;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "rooms")
public class RoomEntity {
    @PrimaryKey(autoGenerate = true)
    private int roomId;    // Identificador único de la sala

    public String label;   // Nombre de la sala

    // Constructor
    public RoomEntity(int roomId, String label) {
        this.roomId = roomId;
        this.label = label;
    }

    // Getters y setters
    public int getRoomId() {
        return roomId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}