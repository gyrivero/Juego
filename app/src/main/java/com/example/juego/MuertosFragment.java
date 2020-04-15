package com.example.juego;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Comparator;
import java.util.List;

import Exportacion.Juego;
import Exportacion.Jugador;
import Exportacion.objetos.Armaduras;
import Exportacion.objetos.Armas;


/**
 * A simple {@link Fragment} subclass.
 */
public class MuertosFragment extends Fragment {
    TextView muertosTV;
    Button menuPBtn;
    Button salirBtn;
    Button volverJugarBtn;
    TextView casillaAlcanzadaTV;
    TextView casillaFinalTV;
    String casillaFinal;
    String casillaAlcanzada;

    public MuertosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_muertos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        muertosTV = getView().findViewById(R.id.muertosTV);
        menuPBtn = getView().findViewById(R.id.menuPrincipalBtn);
        salirBtn = getView().findViewById(R.id.salirBtn);
        volverJugarBtn = getView().findViewById(R.id.volverAJugarMBtn);
        casillaAlcanzadaTV = getView().findViewById(R.id.casillaAlcanzadaTV);
        casillaFinalTV = getView().findViewById(R.id.casillaFinalTV);

        if (Juego.getCantidadJugadores()<2) {
            int casilla = Juego.getJugadores().get(0).getPosicion();
            casillaFinal = "Terminaste en la posicion: " + casilla;
            Juego.casillaAlcanzada = Math.max(Juego.casillaAlcanzada,casilla);
            casillaAlcanzada = "Lo mas lejos que llegaste es: " + Juego.casillaAlcanzada;
        }
        else {
            int casilla = Juego.getJugadores().stream().max(Comparator.comparingInt(Jugador::getPosicion)).get().getPosicion();
            String nombreRecord = Juego.getJugadores().stream().max(Comparator.comparingInt(Jugador::getPosicion)).get().getNombre();
            casillaFinal = nombreRecord + " fue quien llego mas lejos. Su posicion fue: " + casilla;
            if (Juego.casillaAlcanzada < casilla) {
                Juego.nombreRecord = nombreRecord;
            }
            Juego.casillaAlcanzada = Math.max(Juego.casillaAlcanzada,casilla);
            casillaAlcanzada = "El jugador que mas lejos llego hasta ahora es: " + Juego.nombreRecord + ". Su posicion fue: " + Juego.casillaAlcanzada;
        }

        casillaFinalTV.setText(casillaFinal);
        casillaAlcanzadaTV.setText(casillaAlcanzada);

        if (Juego.getCantidadJugadores()>1) {
            muertosTV.setText("Todos los jugadores han muerto!");
        }
        else {
            muertosTV.setText("Has muerto!");
        }

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
