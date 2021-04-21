package com.wilsonhernandez.peliculas.inicio;

import androidx.lifecycle.LiveData;

import com.wilsonhernandez.peliculas.inicio.model.DatosPeliculas;
import com.wilsonhernandez.peliculas.room.entidad.PeliculasEntidad;

import java.util.List;

import io.reactivex.Observable;

public interface InicioFragmentMVP {

    interface View{
        void mostrarDialog();
        void ocultarDialog();

        void mostrarProgress();
        void ocultarProgress();

        void reintentarLlamarPeliculas(String mensaje,String titulo,int index);

        void mostrarRecyclerView(List<PeliculasEntidad> peliculasEntidads);

        void actualizarRecyclerView(List<PeliculasEntidad> peliculasEntidads);

        void errorServidor();

        void mostrarPeliculasCache(LiveData<List<PeliculasEntidad>> peliculas);
    }

    interface Present{

        void solicitarPeliculas();

        void listaPeliculas(List<PeliculasEntidad> peliculasEntidads);

        void solicitarActualizarMasPeliculas(int pagina);

        void respuestaMasPeliculas(List<PeliculasEntidad> peliculasEntidads);

        void errorServidor();


    }
    interface Model{

        void solicitarPeliculasModel(int pagina);

        void solicitarMasPeliculasModel(int pagina);

        LiveData<List<PeliculasEntidad>> obtenenerPeliculasRoom();

    }
}
