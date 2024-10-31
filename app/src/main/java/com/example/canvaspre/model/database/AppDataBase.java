package com.example.canvaspre.model.database;


import android.content.Context;

import com.example.canvaspre.model.ent.DoorEntity;
import com.example.canvaspre.model.ent.PictureEntity;
import com.example.canvaspre.model.ent.RoomEntity;
import com.example.canvaspre.model.ent.VertexEntity;


import com.example.canvaspre.model.dao.DoorDao;
import com.example.canvaspre.model.dao.PictureDao;
import com.example.canvaspre.model.dao.RoomDao;
import com.example.canvaspre.model.dao.VertexDao;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 11,
        entities = {
                DoorEntity.class,
                PictureEntity.class,
                RoomEntity.class,
                VertexEntity.class
        }
        )

public abstract class AppDataBase extends RoomDatabase {
        public abstract DoorDao doorDao();

        public abstract PictureDao pictureDao();

        public abstract RoomDao roomVertexDao();

        public abstract VertexDao vertexDao();

        //public abstract RoomDao roomDao();

        private static AppDataBase INSTANCE = null;

        public static AppDataBase getInstance(Context context){
                synchronized (context){
                        AppDataBase instance = INSTANCE;
                        if(instance == null){
                                instance = Room.databaseBuilder(
                                        context,
                                        AppDataBase.class,
                                        "database-name"
                                ).fallbackToDestructiveMigrationOnDowngrade()
                                        .build();

                                INSTANCE = instance;
                        }
                        return instance;
                }
        }
}
