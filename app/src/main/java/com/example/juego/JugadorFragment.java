package com.example.juego;

import android.annotation.SuppressLint;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import Exportacion.Juego;
import Exportacion.Jugador;


/**
 * A simple {@link Fragment} subclass.
 */
public class JugadorFragment extends Fragment {
    TextView nombreJugadorTV;
    ProgressBar vidaPB;
    ImageView personajeTV;
    TextView posicionTV;
    TextView pocionesTV;
    TextView armaTV;
    TextView armaduraTV;
    TextView vidaTV;
    ImageButton dadoBtn;
    Jugador jugador;
    Bundle extras;
    Button pocionBtn;
    int turno;
    MediaPlayer mediaPlayer;

    public JugadorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jugador, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String titulo = "Ronda N°: " + Juego.getRonda();
        getActivity().setTitle(titulo);
        Juego.bundle.clear();
        nombreJugadorTV = getView().findViewById(R.id.nombreJugadorTV);
        vidaPB = getView().findViewById(R.id.vidaPB);
        personajeTV = getView().findViewById(R.id.personajeIV);
        posicionTV = getView().findViewById(R.id.posicionTV);
        pocionesTV = getView().findViewById(R.id.pocionesTV);
        armaduraTV = getView().findViewById(R.id.armaduraTV);
        armaTV = getView().findViewById(R.id.armaTV);
        vidaTV = getView().findViewById(R.id.vidaTV);
        dadoBtn = getView().findViewById(R.id.dadoBtn);
        pocionBtn = getView().findViewById(R.id.pocionBtn);
        FragmentManager fragmentManager = getFragmentManager();

        turno = Juego.turnoJugador;
        jugador = Juego.getJugadores().get(turno);

        pocionBtn.setEnabled(false);
        if (jugador.getPociones() > 0 && jugador.getVida() < jugador.getVidaMaxima()) {
            pocionBtn.setEnabled(true);
        }
            pocionBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jugador.tomarPocion();
                    mediaPlayer = MediaPlayer.create(getActivity(),R.raw.pocion);
                    mediaPlayer.start();
                    actualizarInfo();
                    if (jugador.getPociones()<=0 || jugador.getVida()>=jugador.getVidaMaxima()){
                        pocionBtn.setEnabled(false);
                    }
                }
            });

        dadoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jugador = Juego.getJugadores().get(turno);
                jugador.moverse(Juego.getTablero(), getActivity(), fragmentManager);
                actualizarInfo();
            }
        });
        actualizarInfo();
    }

    public void actualizarInfo() {
        String titulo = "Ronda N°: " + Juego.getRonda();
        getActivity().setTitle(titulo);
        String posicion = "Posicion: " + jugador.getPosicion();
        String pociones = "Pociones: " + jugador.getPociones();
        String arma = "Arma: " + jugador.getArma().getNombre() + "(" + jugador.getArma().getMin() + "/" + jugador.getArma().getMax() + ")";
        String armadura = "Armadura: " + jugador.getArmadura().getNombre() + "(" + jugador.getArmadura().getDefensa() + ")";
        String vida = jugador.getVida() + "/" + jugador.getVidaMaxima();
        nombreJugadorTV.setText(jugador.getNombre());
        vidaPB.setMax(jugador.getVidaMaxima());
        vidaPB.setProgress(jugador.getVida());
        posicionTV.setText(posicion);
        personajeTV.setImageDrawable(jugador.getImage());
        pocionesTV.setText(pociones);
        armaduraTV.setText(armadura);
        armaTV.setText(arma);
        vidaTV.setText(vida);
    }
}
