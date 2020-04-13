package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import Exportacion.Juego;

public class CantidadJugadoresActivity extends AppCompatActivity {
    Spinner cantidadJugadoresSP;
    TextView bienvenidaTV;
    Button aceptarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cantidad_jugadores);

        cantidadJugadoresSP = findViewById(R.id.cantidadjugSP);
        bienvenidaTV = findViewById(R.id.bienvenidaTV);
        aceptarBtn = findViewById(R.id.aceptarCantidadBtn);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.cantidad_jugadores_array, android.R.layout.simple_spinner_item);
        cantidadJugadoresSP.setAdapter(spinnerAdapter);
        cantidadJugadoresSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Juego.setCantidadJugadores(Integer.parseInt(cantidadJugadoresSP.getSelectedItem().toString()));
                if (Juego.getCantidadJugadores() > 1) {
                    bienvenidaTV.setText("Bienvenidos");
                }
                else {
                    bienvenidaTV.setText("Bienvenido");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void aceptar(View view) {
        Intent intent = new Intent(this,NombresJugadoresActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
