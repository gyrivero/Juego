package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import Exportacion.Juego;

public class JuegoActivity extends AppCompatActivity {
    Fragment frag;
    Button iniciarBtn;
    FragmentManager fragManag;
    Boolean pause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        iniciarBtn = findViewById(R.id.iniciarBtn);
        fragManag = getSupportFragmentManager();
        if (null == savedInstanceState) {
            fragManag.beginTransaction().add(R.id.contenedor, new InicioFragment()).commit();
        }

        if (Juego.volverAJugar) {
            Log.i("Juego", "onCreate: ");
            Juego.musica = MediaPlayer.create(this,R.raw.maker);
            Juego.musica.start();
            Juego.musica.setLooping(true);
            Juego.volverAJugar=false;
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
        Juego.musica.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Juego.musica.pause();
        pause = true;

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pause||!Juego.volverAJugar) {
            Juego.musica.start();
            pause=false;
        }
    }

}