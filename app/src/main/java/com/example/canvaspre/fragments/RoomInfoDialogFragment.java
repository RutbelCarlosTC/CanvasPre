package com.example.canvaspre.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.canvaspre.R;

import org.jetbrains.annotations.Nullable;

public class RoomInfoDialogFragment extends DialogFragment {
    public static RoomInfoDialogFragment newInstance(String roomName) {
        RoomInfoDialogFragment fragment = new RoomInfoDialogFragment();
        Bundle args = new Bundle();
        args.putString("roomName", roomName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_info, container, false);
        TextView roomNameTextView = view.findViewById(R.id.roomName);
        String roomName = getArguments().getString("roomName");
        roomNameTextView.setText(roomName);
        return view;
    }
}