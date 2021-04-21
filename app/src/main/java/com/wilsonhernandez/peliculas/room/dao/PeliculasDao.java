package com.wilsonhernandez.peliculas.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.wilsonhernandez.peliculas.room.entidad.PeliculasEntidad;

import java.util.List;


@Dao
public interface PeliculasDao {
    @Query("Select * from peliculasentidad")
    LiveData<List<PeliculasEntidad>> getListaPelicula();

    @Insert
    void insertListaPeliculas(List<PeliculasEntidad> peliculasEntidadList);

    @Query("delete   from peliculasentidad")
    void delete();
}
