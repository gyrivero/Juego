package com.example.juego;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import Exportacion.Juego;
import Exportacion.Jugador;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnfrentamientoFragment extends Fragment {
    TextView nombreDefensorTV;
    TextView nombreAtacanteTV;
    TextView vidaDefensorTV;
    TextView vidaAtacanteTV;
    ProgressBar vidaDefensorPB;
    ProgressBar vidaAtacantePB;
    Button defensorBtn;
    Button atacanteBtn;
    Bundle bundle;
    Jugador defensor;
    Jugador atacante;
    Button continuarBtn;
    int turno;
    int otroJugador;
    int tercerJugador;
    int cuartoJugador;
    Boolean atacarJugador;
    AlertDialog.Builder builder;
    FragmentManager fragmentManager;

    public EnfrentamientoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enfrentamiento, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nombreDefensorTV = getView().findViewById(R.id.nombreDefensorTV);
        nombreAtacanteTV = getView().findViewById(R.id.nombreAtacanteTV);
        vidaDefensorTV = getView().findViewById(R.id.vidaDefensorTV);
        vidaAtacanteTV = getView().findViewById(R.id.vidaAtacanteTV);
        vidaDefensorPB = getView().findViewById(R.id.vidaDefensorPB);
        vidaAtacantePB = getView().findViewById(R.id.vidaAtacantePB);
        defensorBtn = getView().findViewById(R.id.defensorBtn);
        atacanteBtn = getView().findViewById(R.id.atacanteBtn);
        continuarBtn = getView().findViewById(R.id.continuarEnfrenBtn);
        bundle = getArguments();
        continuarBtn.setEnabled(false);
        defensorBtn.setEnabled(false);
        fragmentManager = getFragmentManager();
        builder = new AlertDialog.Builder(getActivity());
        turno = Juego.getTurnoJugador();
        actualizarInfo();
        Juego.sonidos.release();
        Juego.sonidos = MediaPlayer.create(getActivity(),R.raw.choque_espadas);
        Juego.sonidos.start();
        tercerJugador = 5;
        cuartoJugador = 5;
        if (bundle.containsKey("tercerJugador")) {
            tercerJugador = bundle.getInt("tercerJugador");
        }
        if (bundle.containsKey("cuartoJugador")) {
            cuartoJugador = bundle.getInt("cuartoJugador");
        }

        continuarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Juego.sonidos.release();
                chequearPosicionesJugadores();
                if (atacarJugador) {
                    if (!bundle.containsKey("tercerJugador")) {
                        bundle.putInt("tercerJugador",bundle.getInt("otroJugador"));
                    }
                    else if (!bundle.containsKey("cuartoJugador")) {
                        bundle.putInt("cuartoJugador",otroJugador);
                    }
                    String nombreOtro = Juego.getJugadores().get(otroJugador).getNombre();
                    builder.setMessage("Estas en la misma casilla que el jugador " + nombreOtro + ". Queres atacarlo?").setCancelable(false).setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Fragment frag = new EnfrentamientoFragment();
                            bundle.putInt("otroJugador",otroJugador);
                            frag.setArguments(bundle);
                            fragmentManager.beginTransaction().replace(R.id.contenedor,frag).commit();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            atacante.avanzarEnTablero(Juego.getTablero(), getActivity(), fragmentManager);
                            actualizarInfo();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    atacante.avanzarEnTablero(Juego.getTablero(), getActivity(), fragmentManager);
                }
            }
        });

        defensorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defensor.atacar(atacante,getActivity(),5);
                Juego.sonidos = MediaPlayer.create(getActivity(),R.raw.swing);
                Juego.sonidos.start();
                actualizarInfo();
                defensorBtn.setEnabled(false);
                continuarBtn.setEnabled(true);
            }
        });

        atacanteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atacante.atacar(defensor,getActivity(),8);
                Juego.sonidos = MediaPlayer.create(getActivity(),R.raw.swing);
                Juego.sonidos.start();
                actualizarInfo();
                atacanteBtn.setEnabled(false);
                defensorBtn.setEnabled(true);
            }
        });
    }

    private void actualizarInfo() {
        if (bundle.containsKey("tercerJugador")) {
            tercerJugador = bundle.getInt("tercerJugador");
        }
        if (bundle.containsKey("cuartoJugador")) {
            cuartoJugador = bundle.getInt("cuartoJugador");
        }
        atacante = Juego.getJugadores().get(turno);
        otroJugador = bundle.getInt("otroJugador");
        defensor = Juego.getJugadores().get(otroJugador);
        nombreDefensorTV.setText(defensor.getNombre());
        nombreAtacanteTV.setText(atacante.getNombre());
        String vidaAtacante = atacante.getVida() + "/" + atacante.getVidaMaxima();
        String vidaDefensor = defensor.getVida() + "/" + defensor.getVidaMaxima();
        vidaAtacanteTV.setText(vidaAtacante);
        vidaDefensorTV.setText(vidaDefensor);
        vidaDefensorPB.setMax(defensor.getVidaMaxima());
        vidaDefensorPB.setProgress(defensor.getVida());
        vidaAtacantePB.setMax(atacante.getVidaMaxima());
        vidaAtacantePB.setProgress(atacante.getVida());
    }

    private void chequearPosicionesJugadores() {
        atacarJugador=false;
        otroJugador = turno;
        int otro = bundle.getInt("otroJugador");
        for (int i = 1; i < Juego.getJugadores().size(); i++) {
            otroJugador += 1;
            if (otroJugador>Juego.getJugadores().size()-1) {
                otroJugador = 0;
            }
            if (otroJugador != otro && otroJugador != tercerJugador && otroJugador != cuartoJugador) {
                if (Juego.getJugadores().get(otroJugador).getPosicion() == Juego.getJugadores().get(turno).getPosicion()) {
                    atacarJugador = true;
                    return;
                }
            }
        }
    }
}
