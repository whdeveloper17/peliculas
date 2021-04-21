package com.wilsonhernandez.peliculas.http;

import com.wilsonhernandez.peliculas.inicio.model.DatosPeliculas;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServicioRetrofit {
    @GET("movie/popular?api_key=72210fcdd9d184046b10e3ae4404ee2f&language=en-US")
    Observable<DatosPeliculas> getPeliculas(@Query("page") int pagina);
}
