package com.wilsonhernandez.peliculas.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.wilsonhernandez.peliculas.room.dao.PeliculasDao;
import com.wilsonhernandez.peliculas.room.entidad.PeliculasEntidad;

import java.util.List;

public class Consultas {
    private Context context;
    private PeliculasDao peliculasDao;

    public Consultas(Context context) {
        this.context = context;
        AppDatabase appDatabase=AppDatabase.getDatabase(context);
        peliculasDao=appDatabase.peliculasDao();

    }

    public void insertarPeliculasRoom(List<PeliculasEntidad> peliculasEntidads){
        new insertAsyncTask(peliculasDao).execute(peliculasEntidads);
    }

    private static class  insertAsyncTask extends AsyncTask<List<PeliculasEntidad>,Void,Void> {
        private PeliculasDao peliculasDao;
        insertAsyncTask(PeliculasDao dao){
            peliculasDao=dao;
        }
        @Override
        protected Void doInBackground(List<PeliculasEntidad>... peliculasEntidads) {
            peliculasDao.insertListaPeliculas(peliculasEntidads[0]);
            return null;
        }
    }

    public void deletePeliculas(){
        new deleteProductoAsynTask(peliculasDao).execute();
    }
    private static class deleteProductoAsynTask extends AsyncTask<Boolean, Void, Boolean> {
        private PeliculasDao  peliculasDao;

        deleteProductoAsynTask(PeliculasDao dao){
            peliculasDao=dao;
        }
        @Override
        protected Boolean doInBackground(Boolean... booleans) {
            peliculasDao.delete();
            return true;
        }
    }

    public LiveData<List<PeliculasEntidad>> solicitarPeliculas(){
        return peliculasDao.getListaPelicula();
    }

}
