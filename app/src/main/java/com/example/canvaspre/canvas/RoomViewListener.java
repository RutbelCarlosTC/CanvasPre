package com.example.canvaspre.canvas;

import com.example.canvaspre.model.ent.PictureEntity;
import com.example.canvaspre.model.ent.DoorEntity;

public interface RoomViewListener {

    // Método que se llama cuando una puerta es seleccionada
    void onDoorSelected(DoorEntity door);

    // Método que se llama cuando una imagen es seleccionada
    void onPictureSelected(PictureEntity picture);

    // Puedes agregar más métodos según sea necesario
}