package com.example.juego;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import Exportacion.Juego;
import Exportacion.Jugador;


/**
 * A simple {@link Fragment} subclass.
 */
public class CascadaFragment extends Fragment {
    Button cascadaContinuarBtn;
    FragmentManager turnoFrag;

    public CascadaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cascada, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cascadaContinuarBtn = getView().findViewById(R.id.cascadaContinuarBtn);
        turnoFrag = getFragmentManager();
        int vidaMaxima = Juego.getJugadores().get(0).getVidaMaxima();
        Juego.getJugadores().get(Juego.getTurnoJugador()).curarVida(vidaMaxima);

        cascadaContinuarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Juego.cambiarTurno(getActivity(),turnoFrag);
            }
        });
    }
}
