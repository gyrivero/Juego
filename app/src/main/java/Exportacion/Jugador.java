package Exportacion;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.mycompany.juegotablero.evaluadores.EvaluadorPreguntas;

import Exportacion.objetos.Armaduras;
import Exportacion.objetos.Armas;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class Jugador {
    final int vidaMaxima = 20;
    private String nombre;
    private int vida;
    private Dado dado = new Dado();
    private Armas arma;
    private Armaduras armadura;
    private int posicion;
    private int pociones;
    private int identificacion;
    private Drawable image;

    public Jugador(String nombre,int identificacion, Drawable image) {
        this.posicion = 0;
        this.nombre = nombre;
        this.vida = vidaMaxima;
        this.arma = Armas.PUÑOS;
        this.armadura = Armaduras.ROPA;
        this.pociones = 0;
        this.identificacion = identificacion;
        this.image = image;
    }

    public void revisarEstadisticas() {
        System.out.println("Arma: " + arma.getNombre() + " (" + arma.getMin() + "-" + arma.getMax() + ")" + " - Armadura: " + armadura.getNombre() + " (" + armadura.getDefensa() + ")" + " - Pociones: " + pociones + ".");
        System.out.println("Vida: " + vida + ".");
        System.out.println("Posicion: " + posicion + ".");
        System.out.println();
    }

    public void tomarPocion() {
        vida += 5;
        pociones -= 1;
        if (vida > vidaMaxima) {
            vida = vidaMaxima;
        }
    }

    public void moverse(Tablero tablero, Activity activity, FragmentManager fragmentManager){
        int resultado = dado.girar(1,6);
        posicion += resultado;
        if (posicion > Tablero.getCasillaFinal()){
            posicion = Tablero.getCasillaFinal();
        }
        tablero.activarCasilla(posicion,this,activity, fragmentManager);
        if (!(Juego.toast ==null)) {
            Juego.toast.cancel();
        }
        if (!(Juego.toast ==null)) {
            Juego.toast.cancel();
        }
        Juego.toast = Toast.makeText(activity.getApplicationContext(),"Sacaste: " + resultado,Toast.LENGTH_LONG);
        Juego.toast.show();
    }

    public void atacar(Monstruos monstruo, Activity activity) {
        int precision = dado.girar(1,4);
        if (precision>1) {
            int daño = dado.girar(arma.getMin(),arma.getMax());
            monstruo.setVida(monstruo.getVida()-daño);
            if (monstruo.getVida() < 0) {
                monstruo.setVida(0);
            }
            String dañoString = "Golpeas por " + daño + " puntos.";
            if (!(Juego.toast ==null)) {
                Juego.toast.cancel();
            }
            Juego.toast = Toast.makeText(activity.getApplicationContext(),dañoString,Toast.LENGTH_SHORT);
            Juego.toast.show();
        }
        else {
            if (!(Juego.toast ==null)) {
                Juego.toast.cancel();
            }
            Juego.toast = Toast.makeText(activity.getApplicationContext(),"Erras el golpe",Toast.LENGTH_SHORT);
            Juego.toast.show();
        }
    }

    public void atacar(Jugador jugador,int probabilidad) {
        int precision = dado.girar(1,10);
        if (precision <= 6) {
            int daño = dado.girar(arma.getMin(),arma.getMax());
            daño -= jugador.getArmadura().getDefensa();
            if (daño < 0)
            {
                daño = 0;
            }
            jugador.setVida(jugador.getVida()-daño);
            if (jugador.getVida() < 0) {
                jugador.setVida(0);
            }
            System.out.println("Golpeas por " + daño + " puntos.");
        }
        else {
            System.out.println("Erras el golpe.");
        }
        System.out.println("La vida del " + jugador.getNombre() + " es: " + jugador.getVida() + ".");
        EvaluadorPreguntas.esperarTecla();
    }

    public void atacarJugador(Jugador jugador) {
        System.out.println("Estas en la misma casilla que: " + jugador.getNombre() + ".");
        System.out.println("\nQuieres intentar de darle un golpe?");
        System.out.println("Si lo intentas, el tambien podra intentar darte uno.");
        if (EvaluadorPreguntas.preguntarSiNo()) {
            atacar(jugador,6);
            if (jugador.getVida() > 0) {
                System.out.println("Turno de ataque de " + jugador.getNombre() + ".");
                jugador.atacar(this,3);                
                return;
            }
            System.out.println("El jugador: " + jugador.getNombre() + " ha muerto!");            ;
            return;
        }
        System.out.println("No atacas al jugador.");
        EvaluadorPreguntas.esperarTecla();
    }        
}
