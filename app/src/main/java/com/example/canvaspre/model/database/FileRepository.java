package com.example.canvaspre.model.database;

import android.content.Context;
import android.graphics.Point;

import com.example.canvaspre.model.ent.DoorEntity;
import com.example.canvaspre.model.ent.PictureEntity;
import com.example.canvaspre.model.ent.RoomEntity;
import com.example.canvaspre.model.ent.VertexEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileRepository {
    private Context context;

    public List<PictureEntity> getImages(String filename) {
        BufferedReader reader = null;
        List<PictureEntity> images = new ArrayList<>();
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(filename)));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(",");
                PictureEntity pictureEntity = new PictureEntity();
                images.add(pictureEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return images;
    }

    public List<VertexEntity> getVertexes(String[] filenames) {
        BufferedReader reader = null;
        List<VertexEntity> vertexEntityList = new ArrayList<>();
        try {
            for (String filename : filenames) {

                reader = new BufferedReader(
                        new InputStreamReader(context.getAssets().open(filename)));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] vertex = line.split(",");
                    VertexEntity vertexEntity = new VertexEntity(
                            Integer.parseInt(vertex[8]),
                            Integer.parseInt(vertex[1]),
                            Float.parseFloat(vertex[2]),
                            Float.parseFloat(vertex[3])
                    );
                    vertexEntityList.add(vertexEntity);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return vertexEntityList;
    }

    public List<DoorEntity> getDoors(String filename) {
        BufferedReader reader = null;
        List<DoorEntity> doors = new ArrayList<>();
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(filename)));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(",");
                DoorEntity doorEntity = new DoorEntity(
                        Integer.parseInt(cols[0]),
                        Float.parseFloat(cols[1]),
                        Float.parseFloat(cols[2]),
                        Float.parseFloat(cols[3]),
                        Float.parseFloat(cols[4])
                );
                doors.add(doorEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return doors;
    }
}




