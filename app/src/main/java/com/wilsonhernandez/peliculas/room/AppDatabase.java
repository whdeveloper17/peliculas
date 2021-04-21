package com.wilsonhernandez.peliculas.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wilsonhernandez.peliculas.room.dao.PeliculasDao;
import com.wilsonhernandez.peliculas.room.entidad.PeliculasEntidad;

@Database(entities = {PeliculasEntidad.class}, version = 1)
public abstract class AppDatabase  extends RoomDatabase {
    public abstract PeliculasDao peliculasDao();
    private static volatile AppDatabase INSTANCE;

    public  static AppDatabase  getDatabase(Context context){

        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,"prueba_peliculas")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
