package com.example.canvaspre.model.database;

import android.util.Log;

import com.example.canvaspre.model.ent.DoorEntity;
import com.example.canvaspre.model.ent.PictureEntity;
import com.example.canvaspre.model.ent.RoomAndVertex;
import com.example.canvaspre.model.ent.RoomEntity;
import com.example.canvaspre.model.ent.VertexEntity;

import java.util.List;

public class GalleryRepository {
    private final AppDataBase appDatabase;

    public GalleryRepository(AppDataBase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public List<PictureEntity> getPictures() {
        return appDatabase.pictureDao().getAll();
    }

    public List<VertexEntity> getVertexes() {
        return appDatabase.vertexDao().getAll();
    }

    public List<RoomEntity> getRoomById(int roomId) {
        return appDatabase.roomVertexDao().getByRoomId(roomId);
    }

    public List<RoomAndVertex> getRoomWithVertexes() {
        return appDatabase.roomVertexDao().getRoomWithVertex();
    }

    public RoomAndVertex getRoomWithVertexByRoomId(int roomId) {
        return appDatabase.roomVertexDao().getRoomWithVertexByRoomId(roomId);
    }

    public List<DoorEntity> getDoors() {

        return appDatabase.doorDao().getAll();
    }

    public List<PictureEntity> getPicturesByRoomId(int roomId) {
        return appDatabase.pictureDao().getPicturesByRoomId(roomId);
    }

    public PictureEntity getPictureById(int pictureId) {
        Log.d( "TAG", "GalleryRepository pictureld: "+pictureId);
        return appDatabase.pictureDao().getPictureById(pictureId);
    }
}
