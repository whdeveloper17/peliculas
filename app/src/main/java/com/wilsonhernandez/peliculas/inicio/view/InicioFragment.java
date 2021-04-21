package com.wilsonhernandez.peliculas.inicio.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wilsonhernandez.peliculas.R;
import com.wilsonhernandez.peliculas.inicio.InicioFragmentMVP;
import com.wilsonhernandez.peliculas.inicio.present.InicioFragmentPresent;
import com.wilsonhernandez.peliculas.room.entidad.PeliculasEntidad;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragment extends Fragment implements InicioFragmentMVP.View {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private InicioFragmentMVP.Present present;
    @BindView(R.id.recycler_fragmentinicio_lista)
    RecyclerView recycler_fragmentinicio_lista;
    @BindView(R.id.progress_fragmentinicio_cargando)
    ProgressBar progress_fragmentinicio_cargando;
    private AdaptadorPeliculas adaptadorPeliculas;
    private int contador=0;
    private ProgressDialog progressDialog;
    private AlertDialog.Builder builder;
    private  View view;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public InicioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_inicio, container, false);
        ButterKnife.bind(this,view);
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Cargando...");
        builder=new AlertDialog.Builder(getActivity());
        present=new InicioFragmentPresent(this,getContext());
        present.solicitarPeliculas();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void mostrarDialog() {
        progressDialog.show();
    }

    @Override
    public void ocultarDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void mostrarProgress() {
        progress_fragmentinicio_cargando.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarProgress() {
        progress_fragmentinicio_cargando.setVisibility(View.INVISIBLE);
    }

    @Override
    public void reintentarLlamarPeliculas(String mensaje,String titulo,int index) {
        builder.setMessage(mensaje).setTitle(titulo)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (index){
                            case 1:
                                present.solicitarPeliculas();
                                break;
                            case 2:
                                present.solicitarActualizarMasPeliculas(contador);
                                break;
                        }
                    }
                }).setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    @Override
    public void mostrarRecyclerView(List<PeliculasEntidad> peliculasEntidads) {
        adaptadorPeliculas=new AdaptadorPeliculas(peliculasEntidads,getContext());
        recycler_fragmentinicio_lista.setLayoutManager(new GridLayoutManager(getContext(),3));
        recycler_fragmentinicio_lista.setAdapter(adaptadorPeliculas);
        contador= peliculasEntidads.get(peliculasEntidads.size()-1).pagina;
        adaptadorPeliculas.notifyDataSetChanged();

        adaptadorPeliculas.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("nombre",peliculasEntidads.get(recycler_fragmentinicio_lista.getChildAdapterPosition(v)).nombre);
                bundle.putString("fondo",peliculasEntidads.get(recycler_fragmentinicio_lista.getChildAdapterPosition(v)).imagen_fondo);
                bundle.putString("descripcion",peliculasEntidads.get(recycler_fragmentinicio_lista.getChildAdapterPosition(v)).description);
                Navigation.findNavController(view).navigate(R.id.detalleFragment,bundle);
            }
        });

        recycler_fragmentinicio_lista.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)){
                    contador++;
                    present.solicitarActualizarMasPeliculas(contador);

                }

            }
        });
    }

    @Override
    public void actualizarRecyclerView(List<PeliculasEntidad> peliculasEntidads) {
        adaptadorPeliculas.actualizarLista(peliculasEntidads);
    }

    @Override
    public void errorServidor() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Error servidor")
                .setMessage("A ocurrido un error intenet estamos trabajando. Intentar inciar mas tarde")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    @Override
    public void mostrarPeliculasCache(LiveData<List<PeliculasEntidad>> peliculas) {
        peliculas.observe(this, new Observer<List<PeliculasEntidad>>() {
            @Override
            public void onChanged(List<PeliculasEntidad> peliculasEntidads) {
                if (peliculasEntidads.size()!=0){
                    mostrarRecyclerView(peliculasEntidads);
                    Toast.makeText(getContext(),"Se mostraran peliculas que tienes guardada en cache",Toast.LENGTH_LONG).show();
                }else {
                   reintentarLlamarPeliculas("Desea reintentar del nuevo","No hay conexion a internet",1);
                }
            }
        });
    }
}