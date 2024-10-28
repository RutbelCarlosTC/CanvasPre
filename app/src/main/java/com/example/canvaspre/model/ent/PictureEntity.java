package com.example.canvaspre.model.ent;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "pictures",
        foreignKeys = @ForeignKey(entity = RoomEntity.class,
                                  parentColumns = "roomId",
                                  childColumns = "roomId",
                                  onDelete = ForeignKey.CASCADE)) // Comportamiento en caso de eliminación
public class PictureEntity {
    @PrimaryKey(autoGenerate = true)
    public int pictureId;

    public String title;
    public float x;
    public float y;
    public int roomId; // ID de la habitación a la que pertenece la imagen

    public PictureEntity(String title, float x, float y, int roomId) {
        this.title = title;
        this.x = x;
        this.y = y;
        this.roomId = roomId; // Asigna el roomId en el constructor
    }

}
