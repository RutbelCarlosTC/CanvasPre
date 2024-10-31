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

    public String author;

    public String imageFile;

    public int roomId;

    public float x;

    public float y;

    public String description;

    // Constructor
    public PictureEntity(String title, String author, String imageFile, int roomId, float x, float y, String description) {
        this.title = title;
        this.author = author;
        this.imageFile = imageFile;
        this.roomId = roomId;
        this.x = x;
        this.y = y;
        this.description = description;
    }

}
