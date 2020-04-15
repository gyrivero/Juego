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

import java.util.List;

import Exportacion.Juego;
import Exportacion.Jugador;
import Exportacion.evaluadores.EvaluadorFinal;
import Exportacion.objetos.Armaduras;
import Exportacion.objetos.Armas;


/**
 * A simple {@link Fragment} subclass.
 */
public class VictoriaFragment extends Fragment {
    TextView nombreGanadorTV;
    Button menuPBtn;
    Button salirBtn;
    Button volverJugarBtn;
    List<Jugador> jugadoresList;

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
        volverJugarBtn = getView().findViewById(R.id.volverAJugarVBtn);
        String nombreGanador = EvaluadorFinal.evaluarGanador(Juego.getJugadores()).get(0).getNombre() + " ha ganado!";

        nombreGanadorTV.setText(nombreGanador);

        volverJugarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Juego.volverAJugar=true;
                reiniciarJugadores();
                Juego.setRonda(1);
                ((FinalActivity)getActivity()).mediaPlayerFinal.release();
                Intent intent = new Intent(getActivity(), JuegoActivity.class);
                getFragmentManager().beginTransaction().replace(R.id.contenedor,new InicioFragment());
                startActivity(intent);
                getActivity().finish();
            }
        });

        salirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FinalActivity)getActivity()).mediaPlayerFinal.release();
                getActivity().onBackPressed();
            }
        });

        menuPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FinalActivity)getActivity()).mediaPlayerFinal.release();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private void reiniciarJugadores() {
        for (Jugador j:Juego.getJugadores()) {
            j.setPociones(0);
            j.setArmadura(Armaduras.ROPA);
            j.setArma(Armas.PUÑOS);
            j.setVida(j.getVidaMaxima());
            j.setPosicion(0);
        }
    }
}
