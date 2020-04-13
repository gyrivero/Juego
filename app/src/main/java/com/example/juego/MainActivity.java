package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Exportacion.Juego;

public class MainActivity extends AppCompatActivity {
    Button nuevaPartidaBtn;
    Button cargarPartidaBtn;
    TextView tituloJuego;
    Fragment frag;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nuevaPartidaBtn = findViewById(R.id.nuevoBtn);
        cargarPartidaBtn = findViewById(R.id.cargarBtn);
        tituloJuego = findViewById(R.id.juegoTV);
        Juego.setRonda(1);
        Juego.mediaPlayer = MediaPlayer.create(this,R.raw.maker);
        Juego.mediaPlayer.start();
        Juego.mediaPlayer.setLooping(true);
    }

    public void nuevo(View view) {
        intent = new Intent(this, CantidadJugadoresActivity.class);
        startActivity(intent);
    }

    public void cargar(View view) {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Juego.mediaPlayer.stop();
    }
}
