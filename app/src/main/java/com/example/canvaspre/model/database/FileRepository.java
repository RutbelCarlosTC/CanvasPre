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

    // Constructor que recibe un Context
    public FileRepository(Context context) {
        this.context = context;
    }

    // Método para leer Rooms.txt y obtener una lista de RoomEntity
    public List<RoomEntity> getRooms(String filename) {
        BufferedReader reader = null;
        List<RoomEntity> rooms = new ArrayList<>();
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                RoomEntity roomEntity = new RoomEntity(
                        Integer.parseInt(data[0].trim()), // Room ID
                        data[1].trim()                    // Nombre de la sala
                );
                rooms.add(roomEntity);
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
        return rooms;
    }

    public List<PictureEntity> getImages(String filename) {
    BufferedReader reader = null;
    List<PictureEntity> images = new ArrayList<>();
    try {
        // Abre el archivo desde los recursos de assets
        reader = new BufferedReader(
                new InputStreamReader(context.getAssets().open(filename)));
        String line;

        // Lee cada línea del archivo
        while ((line = reader.readLine()) != null) {
            // Divide la línea por el carácter "|"
            String[] data = line.split("\\|");

            // Crea un objeto PictureEntity con los datos correspondientes
            PictureEntity pictureEntity = new PictureEntity(
                    data[2],                        // Título de la imagen
                    data[1],                        // Autor
                    data[3],                        // Nombre del archivo de imagen
                    Integer.parseInt(data[4]),      // roomId
                    Float.parseFloat(data[5]),      // Coordenada X
                    Float.parseFloat(data[6]),      // Coordenada Y
                    data[7]                         // Descripción
            );

            // Agrega el objeto a la lista
            images.add(pictureEntity);
        }

    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        // Cierra el reader
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
                            Integer.parseInt(vertex[0]), // Vertex ID
                            Integer.parseInt(vertex[1]), // Room ID
                            Float.parseFloat(vertex[2]), // Coordenada X
                            Float.parseFloat(vertex[3])  // Coordenada Y
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




