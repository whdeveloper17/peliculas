package com.wilsonhernandez.peliculas.inicio.repositorio;

import com.wilsonhernandez.peliculas.inicio.model.DatosPeliculas;

import java.util.List;

import io.reactivex.Observable;

public interface InicioFragmentRepositorio {

    Observable<DatosPeliculas> getListaPeliculas(int pagina);

}
