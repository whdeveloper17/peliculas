package com.wilsonhernandez.peliculas.room.entidad;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PeliculasEntidad {

    @PrimaryKey
    public int id;
    public int pagina;
    public String description;
    public String idioma;
    public String nombre;
    public String imagen;
    public String imagen_fondo;


}
