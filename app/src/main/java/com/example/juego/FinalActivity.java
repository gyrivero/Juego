package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import Exportacion.Juego;
import Exportacion.evaluadores.EvaluadorFinal;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        if (EvaluadorFinal.evaluar(EvaluadorFinal.evaluarMuertos(Juego.getJugadores()),1)){
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new MuertosFragment()).commit();
        }
        else {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new InicioFragment()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Juego.mediaPlayer.stop();
        Juego.getJugadores().clear();
    }
}
