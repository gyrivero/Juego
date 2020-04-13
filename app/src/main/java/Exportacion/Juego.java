package Exportacion;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.mycompany.juegotablero.evaluadores.EvaluadorPreguntas;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

public class Juego {

    static private List<Jugador> jugadores = new ArrayList<>();
    static private Tablero tablero = new Tablero();

    static private int cantidadJugadores;
    static private int ronda = 1;
    static private int momentoDeRonda = 0;
    static public Toast toast = null;
    static public Bundle bundle = new Bundle();
    static public MediaPlayer mediaPlayer;
    static public Bundle items = new Bundle();

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

    static public void bienvenida() {
        cantidadJugadores = EvaluadorPreguntas.preguntarCantidadJugadores();
        if (cantidadJugadores>1) {
            System.out.println("Bienvenidos al juego");
        }
        else
        {
            System.out.println("Bienvenido al juego");
        }
    }

    public void crearJugadores() {        
        for (int i = 0; i < cantidadJugadores; i++) {
            System.out.println("\nJugador Nª: " + (i+1));
        }
    }

    public void ronda() {      
        System.out.println("Comienza la ronda N°" + ronda);
        for (Jugador j : jugadores) {
            if (j.getIdentificacion() == momentoDeRonda) {
                if (j.getVida()>0) {
                System.out.println("\nTurno de: " + j.getNombre() + ".\n--");
                EvaluadorPreguntas.esperarTecla();
                System.out.println("---------------------------");
                faseEmboscada(j,jugadores);
                }
                momentoDeRonda += 1;                
            }            
        }
        momentoDeRonda = 1;
        ronda +=1;
    }

    private void faseEmboscada(Jugador j, List<Jugador> jugadores) {
        for (Jugador jugador:jugadores) {
            if (!jugador.equals(j) && j.getPosicion()==jugador.getPosicion() && jugador.getVida()>0 && j.getVida()>0){
                j.atacarJugador(jugador);
            }
        }
    }

    static void faseAtaque(Jugador j, Monstruos monstruo, Activity activity) {
        System.out.println();
        while(j.getVida()>0) {
            if (j.getVida()<j.vidaMaxima && j.getPociones()>0) {
                System.out.println("Queres tomar una pocion? Si lo haces, no podras atacar.");
                if (EvaluadorPreguntas.preguntarSiNo()) {
                    j.tomarPocion();
                }
                else {
                    j.atacar(monstruo,activity);
                }
            }
            else {
                j.atacar(monstruo,activity);
            }
            if (monstruo.getVida()<= 0)
            {
                System.out.println("Has derrotado al " + monstruo.getNombre() + ".");
                break;
            }
        }
    }

    public void fasePrimera(Jugador j, Activity context, FragmentManager fragmentManager){
        boolean dadoLanzado = false;
        int accion;
        while (!dadoLanzado) {
            accion = EvaluadorPreguntas.elegirAccion();
            switch (accion) {
                case 1:
                    j.revisarEstadisticas();
                    break;
                case 2:
                    j.tomarPocion();
                    break;
                case 4:
                    finalizar(true);                    
                case 5:
                    dadoLanzado=true;
                    j.moverse(tablero, context,fragmentManager);
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
                    break;
            }
        }
    }

    public void finalizar(boolean salir) {
        if (salir) {            
            System.out.println("\nSales del juego!");            
            System.exit(0);
        }
        else {
            System.out.println("El juego ha terminado!");
        }        
        System.out.println("Presione enter para salir.");
        EvaluadorPreguntas.esperarTecla();
        
    }

}
