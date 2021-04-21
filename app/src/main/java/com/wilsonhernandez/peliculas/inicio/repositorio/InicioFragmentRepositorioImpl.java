package com.wilsonhernandez.peliculas.inicio.repositorio;

import com.wilsonhernandez.peliculas.http.ConfiguracionRetrofit;
import com.wilsonhernandez.peliculas.http.ServicioRetrofit;
import com.wilsonhernandez.peliculas.inicio.InicioFragmentMVP;
import com.wilsonhernandez.peliculas.inicio.model.DatosPeliculas;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InicioFragmentRepositorioImpl implements InicioFragmentRepositorio {
    private InicioFragmentMVP.Model model;
    private ServicioRetrofit servicioRetrofit;

    public InicioFragmentRepositorioImpl(InicioFragmentMVP.Model model) {
        this.model = model;
        servicioRetrofit= ConfiguracionRetrofit.getApiService();
    }

    @Override
    public Observable<DatosPeliculas> getListaPeliculas(int pagina) {
        return servicioRetrofit.getPeliculas(pagina).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
