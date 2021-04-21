package com.wilsonhernandez.peliculas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.txt_fragmentdetalle_titulo)
    TextView txt_fragmentdetalle_titulo;

    @BindView(R.id.txt_fragmentdetalle_descripcion)
    TextView txt_fragmentdetalle_descripcion;

    @BindView(R.id.img_fragmentdetalle_fondo)
    ImageView img_fragmentdetalle_fondo;

    @BindView(R.id.txt_fragmentdetalle_toobar)
    TextView txt_fragmentdetalle_toobar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleFragment newInstance(String param1, String param2) {
        DetalleFragment fragment = new DetalleFragment();
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
        View view=inflater.inflate(R.layout.fragment_detalle, container, false);
        ButterKnife.bind(this,view);
        // Inflate the layout for this fragment

        txt_fragmentdetalle_descripcion.setText(getArguments().getString("descripcion"));
        txt_fragmentdetalle_titulo.setText(getArguments().getString("nombre"));
        txt_fragmentdetalle_toobar.setText(getArguments().getString("nombre"));
        Glide.with(getContext()).load("https://image.tmdb.org/t/p/original/"+getArguments().getString("fondo")).placeholder(R.drawable.ic_foto).centerCrop().into(img_fragmentdetalle_fondo);

        return view;
    }
}