package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.xw.repo.XEditText;

import Exportacion.Juego;
import Exportacion.Jugador;

public class NombresJugadoresActivity extends AppCompatActivity {
    Button agregarBtn;
    RadioGroup sexoRG;
    XEditText nombreTV;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombres_jugadores);

        nombreTV = findViewById(R.id.nombreTV);
        agregarBtn = findViewById(R.id.agregarBtn);
        sexoRG = findViewById(R.id.sexoRG);
        sexoRG.check(R.id.hombreRG);
        nombreTV.setFilters(new InputFilter[]{EMOJI_FILTER});
    }


    public void agregar(View view) {
        Drawable drawable;
        if (nombreTV.getText().toString().length()>12) {
            nombreTV.setError("El maximo de caracteres es 12");
            return;
        }
        if (nombreTV.getText().toString().isEmpty()) {
            nombreTV.setError("Ingrese un nombre");
            return;
        }
        else {
            i += 1;
        }
        if (sexoRG.getCheckedRadioButtonId()==R.id.hombreRG) {
            drawable = getDrawable(R.drawable.ic_hombre);
        }
        else {
            drawable = getDrawable(R.drawable.ic_mujer);
        }
        if (Exportacion.Juego.getCantidadJugadores() >= i) {
            boolean cheat = false;
            if (nombreTV.getText().toString().equalsIgnoreCase("Gonzaloyr")) {
                cheat = true;
            }
            Exportacion.Juego.getJugadores().add(new Jugador(nombreTV.getText().toString(),(i-1),drawable,cheat));
            nombreTV.getText().clear();
            Toast.makeText(this, "Se ha agregado al jugador: " + Juego.getJugadores().get(i - 1).getNombre(), Toast.LENGTH_SHORT).show();
        }
        if (Exportacion.Juego.getCantidadJugadores() == i) {
            Intent intent = new Intent(this, JuegoActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Juego.getJugadores().clear();
    }

    public static InputFilter EMOJI_FILTER = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int index = start; index < end; index++) {
                int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE) {
                    return "";
                }
            }
            return null;
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        Juego.musica.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Juego.musica.start();
    }
}
