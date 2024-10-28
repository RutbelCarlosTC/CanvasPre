package com.example.canvaspre.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class GalleryView extends View {
    private List<ImageView> images; // Lista de imágenes para la galería

    public GalleryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Código para dibujar o posicionar las imágenes
        for (ImageView image : images) {
            // Lógica para dibujar cada imagen en la galería
            image.draw(canvas);
        }
    }

    // Método para cargar las imágenes
    public void setImages(List<ImageView> images) {
        this.images = images;
        invalidate(); // Redibuja la vista
    }
}
