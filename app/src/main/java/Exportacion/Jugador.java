package Exportacion;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import Exportacion.objetos.Armaduras;
import Exportacion.objetos.Armas;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Jugador {
    final int vidaMaxima = 25;
    private String nombre;
    private int vida;
    private Dado dado = new Dado();
    private Armas arma;
    private Armaduras armadura;
    private int posicion;
    private int pociones;
    private int identificacion;
    private Drawable image;

    public Jugador(String nombre,int identificacion, Drawable image,boolean cheat) {
        if (cheat) {
            this.arma = Armas.ESPADA_LEGENDARIA;
            this.armadura = Armaduras.ARMADURA_DE_ESCAMAS_DE_DRAGON;
        }
        else {
            this.arma = Armas.PUÑOS;
            this.armadura = Armaduras.ROPA;
        }
        this.posicion = 0;
        this.nombre = nombre;
        this.vida = vidaMaxima;
        this.pociones = 0;
        this.identificacion = identificacion;
        this.image = image;
    }

    public Jugador(String nombre, int posicion) {
        this.nombre = nombre;
        this.posicion = posicion;
    }

    public void tomarPocion() {
        vida += 7;
        pociones -= 1;
        if (vida > vidaMaxima) {
            vida = vidaMaxima;
        }
    }

    public void recibirDaño(int daño) {
        vida -= daño;
        if (vida < 0) {
            vida = 0;
        }
    }

    public void curarVida(int curacion) {
        vida +=curacion;
        if (vida > vidaMaxima) {
            vida = vidaMaxima;
        }
    }

    public void moverse(Activity activity){
        int resultado = dado.girar(1,6);
        posicion += resultado;
        if (posicion > Tablero.getCasillaFinal()){
            posicion = Tablero.getCasillaFinal();
        }

        if (!(Juego.toast ==null)) {
            Juego.toast.cancel();
        }
        Juego.toast = Toast.makeText(activity.getApplicationContext(),"Sacaste: " + resultado,Toast.LENGTH_LONG);
        Juego.toast.show();
    }

    public void avanzarEnTablero(Tablero tablero, Activity activity, FragmentManager fragmentManager) {
        tablero.activarCasilla(posicion,this,activity, fragmentManager);
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

    public void atacar(Jugador jugador,Activity activity,int prob) {
        int precision = dado.girar(1,10);
        if (precision <= prob) {
            int daño = dado.girar(arma.getMin(),arma.getMax());
            daño -= jugador.getArmadura().getDefensa();
            if (daño <= 0)
            {
                daño = 1;
            }
            jugador.setVida(jugador.getVida()-daño);
            if (jugador.getVida() < 0) {
                jugador.setVida(0);
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
}
