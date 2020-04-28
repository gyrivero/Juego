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
import Exportacion.Monstruos;


/**
 * A simple {@link Fragment} subclass.
 */
public class CofreEnterradoFragment extends Fragment {
    Button abrirCofreBtn;
    Button cofreDejarBtn;
    TextView cofreDescTV;
    ImageView cofreIV;
    Boolean mimic;
    FragmentManager fragmentManager;
    Dado dado = new Dado();
    Bundle extras;

    public CofreEnterradoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cofre_enterrado, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        abrirCofreBtn = getView().findViewById(R.id.abrirCofreBtn);
        cofreDejarBtn = getView().findViewById(R.id.cofreDejarBtn);
        cofreDescTV = getView().findViewById(R.id.cofreDescTV);
        cofreIV = getView().findViewById(R.id.cofreIV);
        extras = new Bundle();
        mimic = false;
        fragmentManager = getFragmentManager();

        cofreDejarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mimic) {
                    Fragment frag = new MonstruoFragment();
                    Bundle bundle = new Bundle();
                    Monstruos.MIMIC.setVida(Monstruos.MIMIC.getVidaOriginal());
                    bundle.putString("monstruo", "MIMIC");
                    frag.setArguments(bundle);
                    fragmentManager.beginTransaction().replace(R.id.contenedor,frag).commit();
                    return;
                }
                Juego.cambiarTurno(getActivity(),fragmentManager);
            }
        });

        abrirCofreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int opcion = dado.girar(1,5);
                if (opcion == 1) {
                    cofreDescTV.setText("Cuando intentas abrir el cofre, revela su verdadera forma y muestra sus dientes!");
                    cofreIV.setImageDrawable(getResources().getDrawable(R.drawable.ic_mimic_chest,null));
                    cofreDejarBtn.setText("Pelear");
                    abrirCofreBtn.setEnabled(false);
                    mimic = true;
                    return;
                }
                sortearTesoros();
            }
        });
    }

    private void sortearTesoros() {
        int tesoro = dado.girar(1,10);
        if (tesoro >= 1 && tesoro < 5) {
            sortearArma();
        }
        else if (tesoro >= 5 && tesoro < 8) {
            sortearArmadura();
        }
        else {
            if (tesoro == 10) {
                extras.putString("item", "Pocion");
                extras.putInt("cantidad", 5);
            }
            else {
                extras.putString("item", "Pocion");
                extras.putInt("cantidad", 4);
            }
        }
        Fragment fragment = new ItemFragment();
        fragment.setArguments(extras);
        getFragmentManager().beginTransaction().replace(R.id.contenedor,fragment).commit();

    }

    private void sortearArmadura() {
        int armadura = dado.girar(1,10);
        if (armadura >= 1 && armadura < 7) {
            extras.putInt("cantidad",1);
            extras.putString("item","COTA_DE_MALLAS");
        }
        else if (armadura >= 7 && armadura < 10) {
            extras.putInt("cantidad",1);
            extras.putString("item","ARMADURA_DE_PLACAS_COMPLETAS");
        }
        else {
            extras.putInt("cantidad",1);
            extras.putString("item","ARMADURA_DE_ESCAMAS_DE_DRAGON");
        }
    }

    private void sortearArma() {
        int arma = dado.girar(1,10);
        if (arma >= 1 && arma < 7) {
            extras.putInt("cantidad",1);
            extras.putString("item","MANDOBLE");
        }
        else if (arma >= 7 && arma < 10) {
            extras.putInt("cantidad",1);
            extras.putString("item","ESPADA_DE_PLATA");
        }
        else {
            extras.putInt("cantidad",1);
            extras.putString("item","ESPADA_LEGENDARIA");
        }
    }
}
