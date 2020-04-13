package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import Exportacion.Juego;
import Exportacion.Jugador;

public class JuegoActivity extends AppCompatActivity {
    Fragment frag;
    Button iniciarBtn;
    FragmentManager fragManag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        iniciarBtn = findViewById(R.id.iniciarBtn);
        fragManag = getSupportFragmentManager();
        if (null == savedInstanceState) {
            fragManag.beginTransaction().add(R.id.contenedor, new InicioFragment()).commit();
        }
    }

    public void iniciar(View view) {
        frag = new JugadorFragment();
        fragManag.beginTransaction().replace(R.id.contenedor, frag).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Juego.getJugadores().clear();
        Juego.mediaPlayer.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Juego.mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Juego.mediaPlayer.start();
    }

}