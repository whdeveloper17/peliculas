package com.wilsonhernandez.peliculas.inicio.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wilsonhernandez.peliculas.R;
import com.wilsonhernandez.peliculas.room.entidad.PeliculasEntidad;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdaptadorPeliculas extends RecyclerView.Adapter<AdaptadorPeliculas.ViewHolder> {
    private List<PeliculasEntidad> peliculasEntidads;
    private Context context;

    public AdaptadorPeliculas(List<PeliculasEntidad> peliculasEntidads, Context context) {
        this.peliculasEntidads = peliculasEntidads;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_peliculas,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text_cardview_titulo.setText(peliculasEntidads.get(position).nombre);
        Glide.with(context).load("https://image.tmdb.org/t/p/original/"+peliculasEntidads.get(position).imagen).placeholder(R.drawable.ic_foto).centerCrop().into(holder.img_cardview_imagen);

    }

    @Override
    public int getItemCount() {
        return peliculasEntidads.size();
    }

    public void actualizarLista(List<PeliculasEntidad> pelicula){
        peliculasEntidads.addAll(pelicula);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_cardview_imagen)
        ImageView img_cardview_imagen;
        @BindView(R.id.text_cardview_titulo)
        TextView text_cardview_titulo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
