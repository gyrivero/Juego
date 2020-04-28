package com.example.juego;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Exportacion.Dado;
import Exportacion.Juego;


/**
 * A simple {@link Fragment} subclass.
 */
public class CadaverFragment extends Fragment {
    Button tomarCadaverBtn;
    Button cadaverDejarBtn;
    TextView cadaverDescTV;
    ImageView cadaverIV;
    FragmentManager fragmentManager;
    Dado dado = new Dado();
    Bundle extras;

    public CadaverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadaver, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tomarCadaverBtn = getView().findViewById(R.id.tomarCadaverBtn);
        cadaverDejarBtn = getView().findViewById(R.id.cadaverDejarBtn);
        cadaverDescTV = getView().findViewById(R.id.cadaverDescTV);
        cadaverIV = getView().findViewById(R.id.cadaverIV);
        fragmentManager = getFragmentManager();
        extras = new Bundle();

        cadaverDejarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Juego.cambiarTurno(getActivity(),fragmentManager);
            }
        });

        tomarCadaverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortearTesoros();
            }
        });
    }

    private void sortearTesoros() {
        int tesoro = dado.girar(1,10);
        if (tesoro >= 1 && tesoro < 3) {
            sortearArma();
        }
        else if (tesoro >= 3 && tesoro < 5) {
            sortearArmadura();
        }
        else {
            if (tesoro >= 9) {
                extras.putString("item", "Pocion");
                extras.putInt("cantidad", 2);
            }
            else {
                extras.putString("item", "Pocion");
                extras.putInt("cantidad", 1);
            }
        }
        Fragment fragment = new ItemFragment();
        fragment.setArguments(extras);
        getFragmentManager().beginTransaction().replace(R.id.contenedor,fragment).commit();

    }

    private void sortearArmadura() {
        int armadura = dado.girar(1,10);
        if (armadura >= 1 && armadura < 9) {
            extras.putInt("cantidad",1);
            extras.putString("item","ARMADURA_DE_CUERO");
        }
        else {
            extras.putInt("cantidad",1);
            extras.putString("item","COTA_DE_MALLAS");
        }
    }

    private void sortearArma() {
        int arma = dado.girar(1,10);
        if (arma >= 1 && arma < 9) {
            extras.putInt("cantidad",1);
            extras.putString("item","ESPADA");
        }
        else {
            extras.putInt("cantidad",1);
            extras.putString("item","MANDOBLE");
        }
    }
}
