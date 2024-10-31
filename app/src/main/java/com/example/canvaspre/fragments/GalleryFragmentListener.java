package com.example.canvaspre.fragments;

import com.example.canvaspre.model.ent.RoomAndVertex;

import java.util.List;

public interface GalleryFragmentListener {
        void onRoomSelected(int roomId);  // Cuando se selecciona una sala específica
        void onResultRoomVertices(List<RoomAndVertex> data);
}
