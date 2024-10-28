package com.example.canvaspre.fragments;

import com.example.canvaspre.model.ent.DoorEntity;
import com.example.canvaspre.model.ent.PictureEntity;
import com.example.canvaspre.model.ent.RoomAndVertex;

import java.util.List;

public interface RoomFragmentListener {
    void onResultRoomVertex (RoomAndVertex data);

    void onResultPicture (List<PictureEntity> data);

    void onResultDoors(List<DoorEntity> data);
}
