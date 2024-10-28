package com.example.canvaspre.controller;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.canvaspre.model.ent.PictureEntity;
import com.example.canvaspre.model.ent.RoomEntity;

public class EventViewModel extends ViewModel {
    private final MutableLiveData<Integer> selectedRoomId = new MutableLiveData<>();
    private final MutableLiveData<Integer> selectedPictureId = new MutableLiveData<>();
    private final MutableLiveData<Integer> closeFragmentRoomId = new MutableLiveData<>();

    private final MutableLiveData<Integer> pictureSelected = new MutableLiveData<>();


    // LiveData para observar la selección de la sala (Room)
    public LiveData<Integer> onRoomSelected() {
        return selectedRoomId;
    }

    public void selectRoom(int roomId) {
        selectedRoomId.setValue(roomId);
    }

    // LiveData para observar la selección de la imagen (Picture)
    public LiveData<Integer> onPictureSelected() {
        return selectedPictureId;
    }

    public void selectPicture(int pictureId) {
        selectedPictureId.setValue(pictureId);
    }

    // LiveData para observar el cierre de fragmento con ID de la sala (Room)
    public LiveData<Integer> onCloseFragment() {
        return closeFragmentRoomId;
    }

    public void closeFragment(int roomId) {
        closeFragmentRoomId.setValue(roomId);
    }

    public void setPictureSelected(int pictureId) {
        pictureSelected.setValue(pictureId);
    }
}
