package Exportacion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.juego.FinalActivity;
import com.example.juego.JuegoActivity;
import com.example.juego.JugadorFragment;
import com.example.juego.R;

import java.util.ArrayList;
import java.util.List;

import Exportacion.evaluadores.EvaluadorFinal;

public class Juego {

    static private List<Jugador> jugadores = new ArrayList<>();
    static private Tablero tablero = new Tablero();

    static private int cantidadJugadores;
    static private int ronda = 1;
    static private int momentoDeRonda = 0;
    static public Toast toast = null;
    static public Bundle bundle = new Bundle();
    static public MediaPlayer musica = null;
    static public MediaPlayer sonidos = null;
    static public Bundle items = new Bundle();
    static private int turnoJugador;
    static public boolean volverAJugar = false;
    static public int casillaAlcanzada = 0;
    static public String nombreRecord = "";

    public static int getTurnoJugador() {
        return turnoJugador;
    }

    public static void setTurnoJugador(int turnoJugador) {
        Juego.turnoJugador = turnoJugador;
    }

    public static List<Jugador> getJugadores() {
        return jugadores;
    }

    public static void setJugadores(List<Jugador> jugadores) {
        Juego.jugadores = jugadores;
    }

    public static Tablero getTablero() {
        return tablero;
    }

    public static void setTablero(Tablero tablero) {
        Juego.tablero = tablero;
    }

    public static int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public static void setCantidadJugadores(int cantidadJugadores) {
        Juego.cantidadJugadores = cantidadJugadores;
    }

    public static int getRonda() {
        return ronda;
    }

    public static void setRonda(int ronda) {
        Juego.ronda = ronda;
    }

    public static int getMomentoDeRonda() {
        return momentoDeRonda;
    }

    public static void setMomentoDeRonda(int momentoDeRonda) {
        Juego.momentoDeRonda = momentoDeRonda;
    }

    public void playMedia(int media,Activity activity){

        try {

            musica = MediaPlayer.create(activity, media);
            musica.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Log.w("Musica", "inicia reproduccion!  ");
                    musica.start();

                }
            });

        }
        catch (IllegalArgumentException e) {
            Log.e("Musica", "IllegalArgumentException Unable to play audio : " + e.getMessage());
        }
        catch (IllegalStateException e) {
            Log.e("Musica", "IllegalStateException Unable to play audio : " + e.getMessage());
        }

    }


    static public void cambiarTurno(Activity activity, FragmentManager fragmentManager) {
        int vida;
        if (EvaluadorFinal.evaluar(EvaluadorFinal.evaluarMuertos(jugadores),1)) {
            Intent intent = new Intent(activity, FinalActivity.class);
            activity.startActivity(intent);
            activity.finish();
            return;
        }
        do {
            if (cambioRonda(turnoJugador)) {
                if (EvaluadorFinal.evaluar(EvaluadorFinal.evaluarGanador(jugadores),2)) {
                    Jugador ganador = EvaluadorFinal.evaluarGanador(jugadores).get(0);
                    if (!(toast ==null)) {
                        toast.cancel();
                    }
                    toast = Toast.makeText(activity.getApplicationContext(),"Gano el jugador: " + ganador.getNombre(),Toast.LENGTH_LONG);
                    toast.show();
                    Intent intent = new Intent(activity, FinalActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                    return;
                }
                turnoJugador = 0;
            }
            else {
                turnoJugador+=1;
            }
            vida = jugadores.get(turnoJugador).getVida();
        }
        while (vida<= 0);
        fragmentManager.beginTransaction().replace(R.id.contenedor,new JugadorFragment()).commit();
    }

    static public boolean cambioRonda(int turno) {
        if (turno+1>Juego.getJugadores().size()-1) {
            Juego.setRonda(Juego.getRonda()+1);
            return true;
        }
        return false;
    }

}
