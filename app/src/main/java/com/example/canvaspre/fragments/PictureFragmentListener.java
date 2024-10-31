package com.example.canvaspre.fragments;

import com.example.canvaspre.model.ent.PictureEntity;

public interface PictureFragmentListener {
      void onResultPicture(PictureEntity picture);
      void onClosePicture();
}
