package com.example.juego;

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
import android.widget.TextView;
import android.widget.Toast;

import Exportacion.Dado;
import Exportacion.Juego;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrampaFragment extends Fragment {
    ImageView trampaIV;
    TextView trampaDescTV;
    Button trampaBtn;
    ImageButton dadoTrampaBtn;
    FragmentManager turnoFrag;

    public TrampaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trampa, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        trampaBtn = getView().findViewById(R.id.trampaContinuarBtn);
        trampaDescTV = getView().findViewById(R.id.trampaDescTV);
        trampaIV = getView().findViewById(R.id.trampaIV);
        dadoTrampaBtn = getView().findViewById(R.id.dadoTrampaBtn);
        turnoFrag = getFragmentManager();

        trampaBtn.setEnabled(false);

        trampaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Juego.cambiarTurno(getActivity(),turnoFrag);
            }
        });

        dadoTrampaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dado dado = new Dado();
                int resultado = dado.girar(1,10);

                if (resultado >= 1 && resultado < 5) {
                    trampaDescTV.setText("Lograste esquivar la trampa y no recibes ningun tipo de daño!");
                    if (!(Juego.toast ==null)) {
                        Juego.toast.cancel();
                    }
                    Juego.toast = Toast.makeText(getActivity(),"Has esquivado la trampa con exito!",Toast.LENGTH_LONG);
                    Juego.toast.show();
                }
                else {
                    trampaDescTV.setText("No lograste esquivar la trampa y recibes 7 de daño por la misma!");
                    if (!(Juego.toast ==null)) {
                        Juego.toast.cancel();
                    }
                    Juego.toast = Toast.makeText(getActivity(),"La trampa te ha herido!",Toast.LENGTH_LONG);
                    Juego.toast.show();
                    Juego.sonidos = MediaPlayer.create(getActivity(),R.raw.trap);
                    Juego.sonidos.start();
                    Juego.getJugadores().get(Juego.getTurnoJugador()).recibirDaño(7);
                }
                dadoTrampaBtn.setEnabled(false);
                trampaBtn.setEnabled(true);
            }
        });
    }
}
