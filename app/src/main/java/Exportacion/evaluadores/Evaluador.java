package Exportacion.evaluadores;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Exportacion.Juego;
import Exportacion.Jugador;

public class Evaluador {
    static private boolean atacarJugador;
    static private int otroJugador;
    static private int jugador;

    public static List<Jugador> getEvaluadores() {
        return evaluadores;
    }

    static private List<Jugador> evaluadores = new ArrayList<>();

    static public void crearEvaluadores(List<Jugador> jugadores) {
        for (int i = 0; i < Juego.getJugadores().size(); i++) {
            Jugador jugador = Juego.getJugadores().get(i);
            try {
                evaluadores.set(i,new Jugador(jugador.getNombre(),jugador.getPosicion()));
            }
            catch (Exception ex) {
                evaluadores.add(new Jugador(jugador.getNombre(),jugador.getPosicion()));
            }
        }
    }

    static public void borrarEvaluadores() {
        evaluadores.clear();
    }

    static public int devolverJugadorPosicion() {
        return otroJugador;
    }

    static public boolean chequearPosicionesJugadores(int turno) {
        otroJugador = turno;
        Log.i("Jugadores", "tamaño: " + evaluadores.size());
        for (int i = 1; i < evaluadores.size(); i++) {
            otroJugador += 1;
            Log.i("Jugadores", "chequearPosicionesJugadores: " + otroJugador);
            if (otroJugador>evaluadores.size()-1) {
                otroJugador = 0;
            }
            Log.i("Jugadores", "index: " + otroJugador + " Nombre: " + evaluadores.get(otroJugador).getNombre());
            if (evaluadores.get(otroJugador).getPosicion() == evaluadores.get(turno).getPosicion()) {
                evaluadores.get(otroJugador).setPosicion(0);
                return true;
            }
        }
        return false;
    }
}
