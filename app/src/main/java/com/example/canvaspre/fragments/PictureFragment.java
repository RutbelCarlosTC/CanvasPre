package com.example.canvaspre.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.canvaspre.R;
import com.example.canvaspre.controller.EventViewModel;
import com.example.canvaspre.model.database.AppDataBase;
import com.example.canvaspre.model.database.ExecuteTask;
import com.example.canvaspre.model.database.GalleryRepository;
import com.example.canvaspre.model.ent.PictureEntity;

import java.util.List;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;

import com.example.canvaspre.R;
import com.example.canvaspre.model.database.AppDataBase;
import com.example.canvaspre.model.database.ExecuteTask;
import com.example.canvaspre.model.database.GalleryRepository;
import com.example.canvaspre.model.ent.PictureEntity;

public class PictureFragment extends Fragment implements PictureFragmentListener {

    private static final String ARG_PICTURE_ID = "pictureId";
    private int pictureIdParam;
    private GalleryRepository repository;
    private final PictureFragmentListener pictureFragmentListener = this;

    public PictureFragment() {
        // Required empty public constructor
    }

    public static PictureFragment newInstance(int pictureId) {
        PictureFragment fragment = new PictureFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PICTURE_ID, pictureId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pictureIdParam = getArguments().getInt(ARG_PICTURE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_picture, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        repository = new GalleryRepository(AppDataBase.getInstance(requireContext()));

        // Cargar la imagen específica usando el pictureId
        loadPictureById(pictureIdParam);
    }

    private void loadPictureById(int pictureId) {
        ExecuteTask executeTask = new ExecuteTask();
        executeTask.asyncTask(() -> {
            PictureEntity picture = repository.getPictureById(pictureId);
            pictureFragmentListener.onResultPicture(picture);
        });
    }

    @Override
    public void onResultPicture(PictureEntity picture) {
        if (picture != null) {
            // Aquí se actualiza la UI con los datos de la imagen obtenida
            Log.d("PictureFragment", "Picture loaded: " + picture.title + ", Coordinates: (" + picture.x + ", " + picture.y + ")");
            // Actualiza cualquier elemento de la interfaz en fragment_picture.xml
        } else {
            Log.d("PictureFragment", "No picture found with ID: " + pictureIdParam);
        }
    }

    @Override
    public void onClosePicture() {

    }
}