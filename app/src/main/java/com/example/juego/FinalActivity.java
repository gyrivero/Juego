package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import Exportacion.Juego;
import Exportacion.evaluadores.EvaluadorFinal;

public class FinalActivity extends AppCompatActivity {
    MediaPlayer mediaPlayerFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        if (EvaluadorFinal.evaluar(EvaluadorFinal.evaluarMuertos(Juego.getJugadores()),1)){
            playMusic(R.raw.lose);
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new MuertosFragment()).commit();
        }
        else {
            playMusic(R.raw.win);
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new VictoriaFragment()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Juego.musica.release();
        Juego.getJugadores().clear();
    }

    public void playMusic(int id) {
        Juego.musica.release();
        mediaPlayerFinal = MediaPlayer.create(this,id);
        mediaPlayerFinal.start();
        mediaPlayerFinal.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayerFinal.release();
            }
        });
    }
}
