package com.example.canvaspre.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.canvaspre.R;
import com.example.canvaspre.model.database.AppDataBase;
import com.example.canvaspre.model.database.FileRepository;
import com.example.canvaspre.model.database.GalleryRepository;
import com.example.canvaspre.model.ent.DoorEntity;
import com.example.canvaspre.model.ent.PictureEntity;
import com.example.canvaspre.model.ent.RoomAndVertex;
import com.example.canvaspre.model.ent.RoomEntity;
import com.example.canvaspre.model.ent.VertexEntity;

import android.widget.Button;

import java.util.List;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {

    private Button fillDatabaseButton;
    private Button readDatabaseButton;
    private GalleryRepository galleryRepository;
    private FileRepository fileRepository;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = requireContext();
        AppDataBase database = AppDataBase.getInstance(context);

        galleryRepository = new GalleryRepository(database);
        fileRepository = new FileRepository(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fillDatabaseButton = view.findViewById(R.id.fillDatabaseButton);
        readDatabaseButton = view.findViewById(R.id.readDatabaseButton);

        // Configura el listener para el botón de llenar la base de datos
        fillDatabaseButton.setOnClickListener(v -> fillDatabase());

        // Configura el listener para el botón de leer la base de datos
        readDatabaseButton.setOnClickListener(v -> readDatabase());
    }

    private void fillDatabase() {
        Executors.newSingleThreadExecutor().execute(() -> {
            // Ejemplo de carga de datos desde FileRepository
            List<PictureEntity> pictures = fileRepository.getImages("Pictures.txt");
            List<DoorEntity> doors = fileRepository.getDoors("Doors.txt");
            List<RoomEntity> rooms = fileRepository.getRooms("Rooms.txt");
            String[] vertices = new String[]{"RoomVertex001.txt", "RoomVertex002.txt", "RoomVertex003.txt"};
            List<VertexEntity> vertexes = fileRepository.getVertexes(vertices);

            // Inserta las listas en la base de datos
            galleryRepository.addRooms(rooms);
            galleryRepository.addVertexes(vertexes);
            galleryRepository.addPictures(pictures);
            galleryRepository.addDoors(doors);
        });
    }

    private void readDatabase() {
        Executors.newSingleThreadExecutor().execute(() -> {
            // Leer y verificar los datos de VertexEntity
            List<VertexEntity> vertexes = galleryRepository.getVertexes();
            for (VertexEntity vertex : vertexes) {
                Log.d("DatabaseCheck", "Vertex ID: " + vertex.getId() + ", Room ID: " + vertex.getRoomId() +
                        ", X: " + vertex.getX() + ", Y: " + vertex.getY());
            }

            // Leer y verificar los datos de PictureEntity
            List<PictureEntity> pictures = galleryRepository.getPictures();
            for (PictureEntity picture : pictures) {
                Log.d("DatabaseCheck", "Picture ID: " + picture.pictureId + ", Title: " + picture.title +
                        ", Room ID: " + picture.roomId + ", X: " + picture.x + ", Y: " + picture.y);
            }

             // Leer y verificar los datos de PictureEntity
            List<RoomAndVertex> rooms = galleryRepository.getRoomWithVertexes();

            for (RoomAndVertex room : rooms) {
                Log.d("DatabaseCheck", "Room ID: " + room.roomEntity.getRoomId() + ", Vertices: " + room.vertexEntityList.toString());
            }

        });
    }
}