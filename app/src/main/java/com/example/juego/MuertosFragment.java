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

import Exportacion.Juego;


/**
 * A simple {@link Fragment} subclass.
 */
public class MuertosFragment extends Fragment {
    TextView muertosTV;
    Button menuPBtn;
    Button salirBtn;

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

        if (Juego.getCantidadJugadores()>1) {
            muertosTV.setText("Todos los jugadores han muerto!");
        }
        else {
            muertosTV.setText("Has muerto!");
        }

        salirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        menuPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Juego.mediaPlayer.stop();
                Juego.mediaPlayer.release();
                Juego.getJugadores().clear();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}
