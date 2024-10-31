package com.example.canvaspre.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.canvaspre.R;
import com.example.canvaspre.canvas.GalleryView;
import com.example.canvaspre.controller.EventViewModel;
import com.example.canvaspre.model.database.AppDataBase;
import com.example.canvaspre.model.database.ExecuteTask;
import com.example.canvaspre.model.database.GalleryRepository;
import com.example.canvaspre.model.ent.RoomAndVertex;

import java.util.List;


public class GalleryFragment extends Fragment implements  GalleryFragmentListener{

    private GalleryRepository repository;
    private EventViewModel eventViewModel;
    private GalleryView galleryView;
    private final GalleryFragmentListener galleryFragmentListener = this;

    public GalleryFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el layout del fragmento
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializa el ViewModel y el repositorio
        eventViewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);
        repository = new GalleryRepository(AppDataBase.getInstance(requireContext()));

        // Referencia a la vista personalizada de la galería
        galleryView = view.findViewById(R.id.galleryview);
        galleryView.setEventViewModel(eventViewModel);

        // Cargar datos de las habitaciones
        loadRoomsWithVertices();
    }

    private void loadRoomsWithVertices() {
        ExecuteTask executeTask = new ExecuteTask();
        executeTask.asyncTask(() -> galleryFragmentListener.onResultRoomVertices(repository.getRoomWithVertexes()));
    }

    @Override
    public void onRoomSelected(int roomId) {

    }

    @Override
    public void onResultRoomVertices(List<RoomAndVertex> data) {
        if (galleryView != null) {
            galleryView.setRooms(data); // Asigna la lista de habitaciones para dibujar
        }
    }
}