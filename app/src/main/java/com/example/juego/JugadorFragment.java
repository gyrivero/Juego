package com.example.juego;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import Exportacion.Juego;
import Exportacion.Jugador;
import Exportacion.evaluadores.Evaluador;


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
    int otroJugador;
    boolean atacarJugador;
    AlertDialog.Builder builder;
    FragmentManager fragmentManager;

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
        fragmentManager = getFragmentManager();

        builder = new AlertDialog.Builder(getActivity());

        turno = Juego.getTurnoJugador();
        jugador = Juego.getJugadores().get(turno);

        pocionBtn.setEnabled(false);
        if (jugador.getPociones() > 0 && jugador.getVida() < jugador.getVidaMaxima()) {
            pocionBtn.setEnabled(true);
        }
            pocionBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jugador.tomarPocion();
                    Juego.iniciarSonido(getActivity(),R.raw.pocion);
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
                jugador.moverse(getActivity());
                Juego.iniciarSonido(getActivity(),R.raw.dice_1);
                Evaluador.crearEvaluadores(Juego.getJugadores());
                chequearAtaques();
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

    public void crearMensaje() {
        String nombreOtro = Juego.getJugadores().get(otroJugador).getNombre();
        builder.setMessage("Estas en la misma casilla que el jugador " + nombreOtro + ". Queres atacarlo?").setCancelable(false).setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Fragment frag = new EnfrentamientoFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("otroJugador",otroJugador);
                frag.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.contenedor,frag).commit();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                chequearAtaques();
            }
        });
    }

    public void chequearAtaques() {
        if (Evaluador.chequearPosicionesJugadores(turno)) {
            otroJugador = Evaluador.devolverJugadorPosicion();
            crearMensaje();
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else {
            jugador.avanzarEnTablero(Juego.getTablero(), getActivity(), fragmentManager);
            actualizarInfo();
        }
    }
}
