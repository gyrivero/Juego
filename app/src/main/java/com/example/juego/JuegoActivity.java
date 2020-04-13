package com.example.juego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import Exportacion.Juego;
import Exportacion.Jugador;
import Exportacion.Monstruos;
import Exportacion.evaluadores.EvaluadorGanador;

public class JuegoActivity extends AppCompatActivity {
    Fragment frag;
    Button iniciarBtn;
    int turnoJugador = 0;
    Jugador jugador = Juego.getJugadores().get(turnoJugador);
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

    /*public void tirarDado(View view) {

    }*/

    public void actualizarInfo() {
        jugador = Juego.getJugadores().get(turnoJugador);
        String posicion = "Posicion: " + jugador.getPosicion();
        String pociones = "Pociones: " + jugador.getPociones();
        String arma = "Arma: " + jugador.getArma().getNombre();
        String armadura = "Armadura: " + jugador.getArmadura().getNombre();
        String vida = jugador.getVida() + "/" + jugador.getVidaMaxima();
        ((TextView) frag.getView().findViewById(R.id.nombreJugadorTV)).setText(jugador.getNombre());
        ((ProgressBar)frag.getView().findViewById(R.id.vidaPB)).setMax(jugador.getVidaMaxima());
        ((ProgressBar)frag.getView().findViewById(R.id.vidaPB)).setProgress(jugador.getVida());
        ((TextView) frag.getView().findViewById(R.id.posicionTV)).setText(posicion);
        ((ImageView)frag.getView().findViewById(R.id.personajeIV)).setImageDrawable(jugador.getImage());
        ((TextView) frag.getView().findViewById(R.id.pocionesTV)).setText(pociones);
        ((TextView) frag.getView().findViewById(R.id.armaduraTV)).setText(armadura);
        ((TextView) frag.getView().findViewById(R.id.armaTV)).setText(arma);
        ((TextView) frag.getView().findViewById(R.id.vidaTV)).setText(vida);
    }
}