package com.example.juego;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import Exportacion.Juego;
import Exportacion.Jugador;
import Exportacion.objetos.Armaduras;
import Exportacion.objetos.Armas;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {
    TextView nombreJugadorTV;
    TextView vidaJugadorTV;
    TextView nombreItemTV;
    TextView armaTV;
    TextView armaduraTV;
    TextView pocionesTV;
    ProgressBar vidaPB;
    ImageView itemIV;
    Button tomarBtn;
    Button dejarBtn;
    Bundle extras;
    int item;
    int turno;
    Armas armaJugador;
    Armaduras armaduraJugador;
    FragmentManager turnoFragM;

    public ItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Juego.bundle.clear();
        nombreJugadorTV = getView().findViewById(R.id.nombreJugadorItemTV);
        vidaJugadorTV = getView().findViewById(R.id.vidaItemTV);
        nombreItemTV = getView().findViewById(R.id.nombreItemTV);
        armaTV = getView().findViewById(R.id.armaItemTV);
        armaduraTV = getView().findViewById(R.id.armaduraItemTV);
        pocionesTV = getView().findViewById(R.id.pocionesItemTV);
        vidaPB = getView().findViewById(R.id.vidaItemPB);
        itemIV = getView().findViewById(R.id.itemIV);
        tomarBtn = getView().findViewById(R.id.reemplazarBtn);
        dejarBtn = getView().findViewById(R.id.dejarBtn);
        extras = getArguments();
        turno = Juego.turnoJugador;
        turnoFragM = getFragmentManager();
        Jugador jugador = Juego.getJugadores().get(turno);

        String pociones = "Pociones: " + jugador.getPociones();
        String arma = "Arma: " + jugador.getArma().getNombre() + "(" + jugador.getArma().getMin() + "/" + jugador.getArma().getMax() + ")";
        String armadura = "Armadura: " + jugador.getArmadura().getNombre() + "(" + jugador.getArmadura().getDefensa() + ")";
        String vida = jugador.getVida() + "/" + jugador.getVidaMaxima();
        nombreJugadorTV.setText(jugador.getNombre());
        vidaPB.setMax(jugador.getVidaMaxima());
        vidaPB.setProgress(jugador.getVida());
        pocionesTV.setText(pociones);
        armaduraTV.setText(armadura);
        armaTV.setText(arma);
        vidaJugadorTV.setText(vida);

        tomarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item == 1) {
                    jugador.setArma(armaJugador);
                }
                else if (item == 2) {
                    jugador.setArmadura(armaduraJugador);
                }
                else {
                    jugador.setPociones(jugador.getPociones()+extras.getInt("cantidad"));
                }
                Juego.cambiarTurno(getActivity(),turnoFragM);
            }
        });

        dejarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Juego.cambiarTurno(getActivity(),turnoFragM);
            }
        });

        String nombreItem = extras.getString("item");
        actualizarImagen(nombreItem);
    }

    private void actualizarImagen(String nombreItem) {
        if (!nombreItem.equals("Pocion")) {
            try {
                armaJugador = Armas.valueOf(nombreItem);
                nombreItem = armaJugador.getNombre() + "(" + armaJugador.getMin() + "/" + armaJugador.getMax() + ")";
                item = 1;
            }
            catch (Exception e) {
                armaduraJugador = Armaduras.valueOf(nombreItem);
                nombreItem = armaduraJugador.getNombre() + "(" + armaduraJugador.getDefensa() + ")";
                item = 2;
            }
        }
        else {
            nombreItem = nombreItem + " x " + extras.getInt("cantidad");
            tomarBtn.setText("Agarrar");
            item = 3;
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (item == 1) {
                itemIV.setImageDrawable(getResources().getDrawable(armaJugador.getImagenLand(),null));
            }
            else if (item == 2) {
                itemIV.setImageDrawable(getResources().getDrawable(armaduraJugador.getImagenLand(),null));
            }
            else if (item == 3) {
                itemIV.setImageDrawable(getResources().getDrawable(R.drawable.ic_round_bottom_flask_land,null));
            }
        }
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (item == 1) {
                itemIV.setImageDrawable(getResources().getDrawable(armaJugador.getImagenPort(),null));
            }
            else if (item == 2) {
                itemIV.setImageDrawable(getResources().getDrawable(armaduraJugador.getImagenPort(),null));
            }
            else if (item == 3) {
                itemIV.setImageDrawable(getResources().getDrawable(R.drawable.ic_round_bottom_flask,null));
            }
        }
        nombreItemTV.setText(nombreItem);
    }
}
