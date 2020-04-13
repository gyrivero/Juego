package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import Exportacion.Juego;
import Exportacion.evaluadores.EvaluadorFinal;

public class FinalActivity extends AppCompatActivity {
    MediaPlayer mediaPlayerFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        Juego.mediaPlayer.stop();
        Juego.mediaPlayer = null;
        if (EvaluadorFinal.evaluar(EvaluadorFinal.evaluarMuertos(Juego.getJugadores()),1)){
            mediaPlayerFinal = MediaPlayer.create(this,R.raw.lose);
            mediaPlayerFinal.start();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new MuertosFragment()).commit();
        }
        else {
            mediaPlayerFinal = MediaPlayer.create(this,R.raw.win);
            mediaPlayerFinal.start();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new VictoriaFragment()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Juego.mediaPlayer.stop();
        Juego.getJugadores().clear();
    }
}
