package com.wilsonhernandez.peliculas.inicio.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.wilsonhernandez.peliculas.inicio.InicioFragmentMVP;
import com.wilsonhernandez.peliculas.inicio.repositorio.InicioFragmentRepositorio;
import com.wilsonhernandez.peliculas.inicio.repositorio.InicioFragmentRepositorioImpl;
import com.wilsonhernandez.peliculas.inicio.view.InicioFragment;
import com.wilsonhernandez.peliculas.room.AppDatabase;
import com.wilsonhernandez.peliculas.room.Consultas;
import com.wilsonhernandez.peliculas.room.dao.PeliculasDao;
import com.wilsonhernandez.peliculas.room.entidad.PeliculasEntidad;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class InicioFragmentModel implements InicioFragmentMVP.Model {
    private InicioFragmentMVP.Present present;
    private InicioFragmentRepositorio repositorio;
    private Context context;
    private Consultas consultas;

    public InicioFragmentModel(InicioFragmentMVP.Present present,Context context) {
        this.present = present;
        this.context=context;
        repositorio=new InicioFragmentRepositorioImpl(this);
        consultas=new Consultas(context);
    }

    @Override
    public void solicitarPeliculasModel(int pagina) {
         repositorio.getListaPeliculas(pagina).subscribe(new Observer<DatosPeliculas>() {
             @Override
             public void onSubscribe(@NonNull Disposable d) {

             }

             @Override
             public void onNext(@NonNull DatosPeliculas datosPeliculas) {
                 List<PeliculasEntidad> peliculasEntidadList= organizarPeliculas(datosPeliculas);
                 consultas.deletePeliculas();
                consultas.insertarPeliculasRoom(peliculasEntidadList);
                present.listaPeliculas(peliculasEntidadList);

             }

             @Override
             public void onError(@NonNull Throwable e) {
                    present.errorServidor();
             }

             @Override
             public void onComplete() {

             }
         });
    }

    @Override
    public void solicitarMasPeliculasModel(int pagina) {
        repositorio.getListaPeliculas(pagina).subscribe(new Observer<DatosPeliculas>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull DatosPeliculas datosPeliculas) {
                List<PeliculasEntidad> peliculasEntidadList= organizarPeliculas(datosPeliculas);
                consultas.insertarPeliculasRoom(peliculasEntidadList);
                present.respuestaMasPeliculas(peliculasEntidadList);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                present.errorServidor();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public LiveData<List<PeliculasEntidad>> obtenenerPeliculasRoom() {

        return consultas.solicitarPeliculas();
    }

    private List<PeliculasEntidad> organizarPeliculas(DatosPeliculas datosPeliculas){
        List<PeliculasEntidad> peliculasEntidadList=new ArrayList<>();
        for (int i=0;i<datosPeliculas.getResult().size();i++){
            PeliculasEntidad peliculasEntidad=new PeliculasEntidad();
            peliculasEntidad.id=datosPeliculas.getResult().get(i).getId();
            peliculasEntidad.description=datosPeliculas.getResult().get(i).getOverview();
            peliculasEntidad.idioma=datosPeliculas.getResult().get(i).getOriginal_language();
            peliculasEntidad.imagen=datosPeliculas.getResult().get(i).getPoster_path();
            peliculasEntidad.imagen_fondo=datosPeliculas.getResult().get(i).getBackdrop_path();
            peliculasEntidad.nombre=datosPeliculas.getResult().get(i).getTitle();
            peliculasEntidad.pagina=datosPeliculas.getPage();
            peliculasEntidadList.add(peliculasEntidad);
        }
        return peliculasEntidadList;
    }
}
