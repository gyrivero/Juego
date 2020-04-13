package com.example.juego;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Exportacion.Juego;
import Exportacion.Jugador;
import Exportacion.Monstruos;


/**
 * A simple {@link Fragment} subclass.
 */
public class MonstruoFragment extends Fragment {
    Bundle extras;
    ImageView monstruoIV;
    TextView nombreMonstruoTV;
    TextView vidaMonstruoTV;
    Button atacarBtn;
    TextView nombreJugadorTV;
    TextView vidaJugadorTV;
    Button defenderBtn;
    ImageButton itemMonstruoBtn;
    Fragment fragment;
    Bundle envio;
    Button continuarBtn;
    ImageButton pocionBtn;
    TextView pocionesMonstruoTV;
    AlertDialog.Builder builder;
    MediaPlayer mediaPlayer;
    String pociones;

    public MonstruoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onPause() {
        super.onPause();
        Juego.bundle.putBoolean("defender",defenderBtn.isEnabled());
        Juego.bundle.putBoolean("itemMonstruo",itemMonstruoBtn.isEnabled());
        Juego.bundle.putBoolean("continuar",continuarBtn.isEnabled());
        Juego.bundle.putBoolean("atacar",atacarBtn.isEnabled());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monstruo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        monstruoIV = getView().findViewById(R.id.monstruoIV);
        nombreMonstruoTV = getView().findViewById(R.id.nombreMonstruoTV);
        vidaMonstruoTV = getView().findViewById(R.id.vidaMonstruoTV);
        atacarBtn = getView().findViewById(R.id.atacarBtn);
        vidaJugadorTV = getView().findViewById(R.id.vidaJugadorMTV);
        nombreJugadorTV = getView().findViewById(R.id.nombreJugadorMTV);
        defenderBtn = getView().findViewById(R.id.defenderBtn);
        itemMonstruoBtn = getView().findViewById(R.id.itemMonstruoBtn);
        continuarBtn = getView().findViewById(R.id.continuarBtn);
        pocionBtn = getView().findViewById(R.id.pocionMonstruoBtn);
        pocionesMonstruoTV = getView().findViewById(R.id.pocionesMonstruoTV);

        if (Juego.bundle.containsKey("defender")) {
            defenderBtn.setEnabled(Juego.bundle.getBoolean("defender"));
            itemMonstruoBtn.setEnabled(Juego.bundle.getBoolean("itemMonstruo"));
            continuarBtn.setEnabled(Juego.bundle.getBoolean("continuar"));
            atacarBtn.setEnabled(Juego.bundle.getBoolean("atacar"));
        }
        else {
            defenderBtn.setEnabled(false);
            itemMonstruoBtn.setEnabled(false);
            continuarBtn.setEnabled(false);
        }
        if (itemMonstruoBtn.isEnabled()) {
            itemMonstruoBtn.setColorFilter(new PorterDuffColorFilter(getResources().getColor(R.color.colorPrimary, null), PorterDuff.Mode.SRC_IN));
        }

        extras = getArguments();
        Monstruos monstruo = Monstruos.valueOf(extras.getString("monstruo"));
        Jugador jugador = Juego.getJugadores().get(extras.getInt("jugador"));
        pociones ="Pociones: " + jugador.getPociones();
        mediaPlayer = MediaPlayer.create(getActivity(),monstruo.getRaw());
        mediaPlayer.start();
        nombreMonstruoTV.setText(monstruo.getNombre());
        monstruoIV.setImageDrawable(getResources().getDrawable(monstruo.getImagen(),null));
        vidaMonstruoTV.setText(String.valueOf(monstruo.getVida()));
        vidaJugadorTV.setText(String.valueOf(jugador.getVida()));
        nombreJugadorTV.setText(jugador.getNombre());
        pocionesMonstruoTV.setText(pociones);
        pocionBtn.setEnabled(false);

        if (jugador.getPociones() > 0 && jugador.getVida() < jugador.getVidaMaxima()) {
            pocionBtn.setEnabled(true);
            pocionBtn.setColorFilter(new PorterDuffColorFilter(getResources().getColor(R.color.colorPrimary, null), PorterDuff.Mode.SRC_IN));
        }

        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("El " + monstruo.getNombre() + " te ha dejado un item!")
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        pocionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jugador.tomarPocion();
                vidaJugadorTV.setText(String.valueOf(jugador.getVida()));
                pociones ="Pociones: " + jugador.getPociones();
                pocionesMonstruoTV.setText(pociones);
                defenderBtn.setEnabled(true);
                atacarBtn.setEnabled(false);
                if (jugador.getPociones() > 0 && jugador.getVida() < jugador.getVidaMaxima()) {
                    pocionBtn.setEnabled(true);
                    pocionBtn.setColorFilter(new PorterDuffColorFilter(getResources().getColor(R.color.colorPrimary, null), PorterDuff.Mode.SRC_IN));
                }
                else {
                    pocionBtn.clearColorFilter();
                    pocionBtn.setEnabled(false);
                }
            }
        });

        continuarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Juego.bundle.clear();
                int turno = ((JuegoActivity)getActivity()).turnoJugador;
                int vida = 0;
                int i = 0;
                while (vida<= 0)
                {
                    i+=1;
                    if (i>Juego.getCantidadJugadores()){
                        break;
                    }
                    turno+=1;
                    if (turno>Juego.getJugadores().size()-1) {
                        Juego.setRonda(Juego.getRonda()+1);
                        turno = 0;
                    }
                    vida = Juego.getJugadores().get(turno).getVida();
                }
                if (i>Juego.getCantidadJugadores()){
                    Toast.makeText(getActivity().getApplicationContext(),"Se termino el juego",Toast.LENGTH_LONG).show();
                    return;
                }
                ((JuegoActivity)getActivity()).turnoJugador = turno;
                getFragmentManager().beginTransaction().replace(R.id.contenedor,new JugadorFragment()).commit();
            }
        });

        itemMonstruoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Juego.bundle.clear();
                extras.putInt("cantidad",Juego.items.getInt("cantidad"));
                extras.putString("item",Juego.items.getString("item"));

                fragment = new ItemFragment();
                fragment.setArguments(Juego.items);
                getFragmentManager().beginTransaction().replace(R.id.contenedor,fragment).commit();
            }
        });

        defenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monstruo.atacar(jugador,getActivity());
                vidaJugadorTV.setText(String.valueOf(jugador.getVida()));
                if (jugador.getVida()<=0) {
                    if (!(Juego.toast ==null)) {
                        Juego.toast.cancel();
                    }
                    Juego.toast.makeText(getActivity().getApplicationContext(),"Has muerto!",Toast.LENGTH_LONG);
                    Juego.toast.show();
                    continuarBtn.setEnabled(true);
                    defenderBtn.setEnabled(false);
                }
                else {
                    defenderBtn.setEnabled(false);
                    atacarBtn.setEnabled(true);
                    if (jugador.getPociones() > 0 && jugador.getVida() < jugador.getVidaMaxima()) {
                        pocionBtn.setEnabled(true);
                        pocionBtn.setColorFilter(new PorterDuffColorFilter(getResources().getColor(R.color.colorPrimary, null), PorterDuff.Mode.SRC_IN));
                    }
                }
            }
        });

        atacarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jugador.atacar(monstruo,getActivity());
                vidaMonstruoTV.setText(String.valueOf(monstruo.getVida()));
                mediaPlayer = MediaPlayer.create(getActivity(),R.raw.swing);
                mediaPlayer.start();
                if (monstruo.getVida()>0){
                    defenderBtn.setEnabled(true);
                }
                else {
                    Juego.items = monstruo.dejarItem();
                    if (Juego.items.getInt("cantidad")>0) {
                        itemMonstruoBtn.setEnabled(true);
                        itemMonstruoBtn.setColorFilter(new PorterDuffColorFilter(getResources().getColor(R.color.colorPrimary, null), PorterDuff.Mode.SRC_IN));
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                    else {
                        if (!(Juego.toast ==null)) {
                            Juego.toast.cancel();
                        }
                        Juego.toast.makeText(getActivity().getApplicationContext(),"El monstruo no ha dejado ningun item!",Toast.LENGTH_LONG);
                        Juego.toast.show();
                        continuarBtn.setEnabled(true);
                    }
                }
                atacarBtn.setEnabled(false);
                pocionBtn.setEnabled(false);
            }
        });
    }
}
