package com.example.juego;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import Exportacion.Juego;
import Exportacion.evaluadores.EvaluadorFinal;


/**
 * A simple {@link Fragment} subclass.
 */
public class VictoriaFragment extends Fragment {
    TextView nombreGanadorTV;
    Button menuPBtn;
    Button salirBtn;

    public VictoriaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_victoria, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nombreGanadorTV = getView().findViewById(R.id.nombreGanadorTV);
        menuPBtn = getView().findViewById(R.id.menuPrincipalGBtn);
        salirBtn = getView().findViewById(R.id.salirGBtn);
        String nombreGanador = EvaluadorFinal.evaluarGanador(Juego.getJugadores()).get(0).getNombre() + " ha ganado!";

        nombreGanadorTV.setText(nombreGanador);

        Juego.getJugadores().clear();

        salirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        menuPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}
