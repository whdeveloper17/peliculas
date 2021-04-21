package com.wilsonhernandez.peliculas.inicio.present;

import android.content.Context;

import com.wilsonhernandez.peliculas.clases.Internet;
import com.wilsonhernandez.peliculas.inicio.InicioFragmentMVP;
import com.wilsonhernandez.peliculas.inicio.model.InicioFragmentModel;
import com.wilsonhernandez.peliculas.room.entidad.PeliculasEntidad;

import java.util.List;

public class InicioFragmentPresent implements InicioFragmentMVP.Present {
    private InicioFragmentMVP.View view;
    private InicioFragmentMVP.Model model;
    private Context context;
    private Internet internet;

    public InicioFragmentPresent(InicioFragmentMVP.View view, Context context) {
        this.view = view;
        this.context = context;
        model=new InicioFragmentModel(this,context);

        internet=new Internet();
    }

    @Override
    public void solicitarPeliculas() {
            if (view!=null){
                if (internet.validarInternet()){
                    view.mostrarDialog();
                    model.solicitarPeliculasModel(1);
                }else{
                    view.ocultarDialog();
                    view.mostrarPeliculasCache(model.obtenenerPeliculasRoom());
                }
            }
    }

    @Override
    public void listaPeliculas(List<PeliculasEntidad> peliculasEntidads) {
        view.ocultarDialog();
        view.mostrarRecyclerView(peliculasEntidads);
    }

    @Override
    public void solicitarActualizarMasPeliculas(int pagina) {
      if (view!=null){
          if (internet.validarInternet()){
              view.mostrarProgress();
              model.solicitarMasPeliculasModel(pagina);

          }else {
              view.ocultarProgress();;
              view.reintentarLlamarPeliculas("Desea reintentar del nuevo","No hay conexion a internet",2);
          }
      }
    }

    @Override
    public void respuestaMasPeliculas(List<PeliculasEntidad> peliculasEntidads) {
        view.ocultarProgress();
        view.actualizarRecyclerView(peliculasEntidads);
    }

    @Override
    public void errorServidor() {
        view.errorServidor();
    }
}
