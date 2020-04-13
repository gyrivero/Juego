package Exportacion;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.example.juego.MonstruoFragment;
import com.example.juego.R;

import com.mycompany.juegotablero.evaluadores.EvaluadorPreguntas;

import Exportacion.objetos.Armaduras;
import Exportacion.objetos.Armas;

public class Tablero {
    private static int casillaFinal = 10;
    private boolean hayMonstruo = false;
    private Monstruos monstruo;
    private Dado dado = new Dado();

    public Monstruos getMonstruo() {
        return monstruo;
    }

    public boolean isHayMonstruo() {
        return hayMonstruo;
    }

    public void setHayMonstruo(boolean hayMonstruo) {
        this.hayMonstruo = hayMonstruo;
    }

    public static int getCasillaFinal() {
        return casillaFinal;
    }

    public void activarCasilla(int casilla, Jugador jugador, Activity activity, FragmentManager fragManag) {
        switch (casilla) {
            case 1:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 2:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 3:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                //encontrarEvento(jugador, fragManag);
                break;
            case 4:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 5:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 6:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 7:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 8:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 9:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 10:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 11:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 12:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 13:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 14:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 15:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 16:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 17:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 18:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 19:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 20:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 21:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 22:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 23:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 24:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 25:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 26:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 27:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 28:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 29:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 30:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 31:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 32:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 33:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 34:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 35:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 36:
                break;
            case 37:
                break;
            case 38:
                break;
            case 39:
                break;
            case 40:
                break;
            case 41:
                break;
            case 42:
                break;
            case 43:
                break;
            case 44:
                break;
            case 45:
                break;
            case 46:
                break;
            case 47:
                break;
            case 48:
                break;
            case 49:
                break;
            case 50:
                break;
            case 51:
                break;
            case 52:
                break;
            case 53:
                break;
            case 54:
                break;
            case 55:
                break;
            case 56:
                break;
            case 57:
                break;
            case 58:
                break;
            case 59:
                break;
            case 60:
                break;
        }
    }

    public void encontrarMonstruo(Jugador jugador, Monstruos monstruo, FragmentManager fragManag) {
        if (chequearMuerto(jugador)) {
            return;
        }
        this.hayMonstruo = true;
        this.monstruo = monstruo;
        this.monstruo.setVida(monstruo.getVidaOriginal());
        Fragment frag = new MonstruoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("monstruo", monstruo.name());
        frag.setArguments(bundle);
        fragManag.beginTransaction().replace(R.id.contenedor, frag).commit();
    }

    public void encontrarArma(Jugador jugador, Armas arma, FragmentManager fragManag) {
        if (chequearMuerto(jugador)) {
            return;
        }
        System.out.println("Encuentras 1 " + arma.getNombre() + " (" + arma.getMin() + "-" + arma.getMax() + ").");
        System.out.println("Tu arma actual es: " + jugador.getArma().getNombre() + " (" + jugador.getArma().getMin() + "-" + jugador.getArma().getMax() + ").");
        System.out.println("\nQueres agarrar el arma? " + arma.getNombre() + ".");
        if (EvaluadorPreguntas.preguntarSiNo()) {
            jugador.setArma(arma);
            System.out.println("Cambiaste de arma.");
            return;
        }
        System.out.println("Mantenes el mismo arma.");
    }

    public void encontrarArmadura(Jugador jugador, Armaduras armadura, FragmentManager fragManag) {
        if (chequearMuerto(jugador)) {
            return;
        }
        System.out.println("Encuentras 1 " + armadura.getNombre() + " (" + armadura.getDefensa() + ").");
        System.out.println("Tu armadura actual es: " + jugador.getArmadura().getNombre() + "(" + jugador.getArmadura().getDefensa() + ").");
        System.out.println("\nQueres agarrar la armadura? " + armadura.getNombre() + ".");
        if (EvaluadorPreguntas.preguntarSiNo()) {
            jugador.setArmadura(armadura);
            System.out.println("Cambiaste de armadura.");
            return;
        }
        System.out.println("Mantenes la misma armadura.");
    }

    public void encontrarPocion(Jugador jugador, int cantidadDePociones, FragmentManager fragManag) {
        if (chequearMuerto(jugador)) {
            return;
        }
        jugador.setPociones(jugador.getPociones() + cantidadDePociones);
        if (cantidadDePociones > 1) {
            System.out.println("Encuentras " + cantidadDePociones + " pociones.");
            return;
        }
        System.out.println("Encuentras " + cantidadDePociones + " pocion.");
        EvaluadorPreguntas.esperarTecla();
    }

    public void encontrarCofre(Jugador jugador, FragmentManager fragManag) {
        if (chequearMuerto(jugador)) {
            return;
        }
        System.out.println("Encuentras un cofre abandonado y revisas que hay dentro.");
        EvaluadorPreguntas.esperarTecla();
        int opcion = dado.girar(1, 100);
        if (opcion >= 1 && opcion <= 60) {
            encontrarPocion(jugador, 1, fragManag);
        } else if (opcion >= 61 && opcion <= 90) {
            encontrarPocion(jugador, 2, fragManag);
        } else if (opcion >= 91 && opcion <= 97) {
            encontrarPocion(jugador, 3, fragManag);
        } else {
            encontrarPocion(jugador, 4, fragManag);
        }
        opcion = dado.girar(1, 100);
        if (opcion >= 1 && opcion <= 50) {
            encontrarArma(jugador, Armas.DAGA, fragManag);
        } else if (opcion >= 51 && opcion <= 83) {
            encontrarArma(jugador, Armas.ESPADA, fragManag);
        } else if (opcion >= 84 && opcion <= 94) {
            encontrarArma(jugador, Armas.MANDOBLE, fragManag);
        } else if (opcion >= 95 && opcion <= 99) {
            encontrarArma(jugador, Armas.ESPADA_DE_PLATA, fragManag);
        } else {
            encontrarArma(jugador, Armas.ESPADA_LEGENDARIA, fragManag);
        }
        opcion = dado.girar(1, 100);
        if (opcion >= 1 && opcion <= 70) {
            encontrarArmadura(jugador, Armaduras.ARMADURA_DE_CUERO, fragManag);
        } else if (opcion >= 71 && opcion <= 92) {
            encontrarArmadura(jugador, Armaduras.COTA_DE_MALLAS, fragManag);
        } else if (opcion >= 93 && opcion <= 99) {
            encontrarArmadura(jugador, Armaduras.ARMADURA_DE_PLACAS_COMPLETAS, fragManag);
        } else {
            encontrarArmadura(jugador, Armaduras.ARMADURA_DE_ESCAMAS_DE_DRAGON, fragManag);
        }
    }

    public void encontrarCueva(Jugador jugador, FragmentManager fragManag) {
        if (chequearMuerto(jugador)) {
            return;
        }
        System.out.println("Te adentras en una cueva oscura.");
        for (int i = 0; i < 3; i++) {
            int opcion = dado.girar(1, 100);
            if (opcion >= 1 && opcion <= 30) {
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
            } else if (opcion >= 31 && opcion <= 63) {
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
            } else if (opcion >= 64 && opcion <= 84) {
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
            } else if (opcion >= 85 && opcion <= 97) {
                encontrarMonstruo(jugador, Monstruos.OGRO, fragManag);
            } else {
                encontrarMonstruo(jugador, Monstruos.DRAGON, fragManag);
            }
        }
        encontrarCofre(jugador, fragManag);
    }

    public boolean chequearMuerto(Jugador jugador) {
        if (jugador.getVida() <= 0) {
            return true;
        }
        return false;
    }
}
