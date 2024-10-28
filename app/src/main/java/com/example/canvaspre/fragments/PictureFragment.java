package com.example.canvaspre.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.canvaspre.R;


public class PictureFragment extends Fragment {
    private static final String ARG_PICTURE_ID = "picture_id";
    private int pictureId;

    // Método de fábrica para crear una instancia de PictureFragment con el pictureId
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture, container, false);
    }
}