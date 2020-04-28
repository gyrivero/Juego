package Exportacion;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.example.juego.CadaverFragment;
import com.example.juego.CascadaFragment;
import com.example.juego.CofreEnterradoFragment;
import com.example.juego.MonstruoFragment;
import com.example.juego.R;

import com.example.juego.TrampaFragment;

public class Tablero {
    private static int casillaFinal = 60;
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
                encontrarEvento(jugador, fragManag, 1);
                break;
            case 4:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 5:
                encontrarEvento(jugador, fragManag, 1);
                break;
            case 6:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 7:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 8:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 9:
                encontrarEvento(jugador, fragManag, 1);
                break;
            case 10:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 11:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 12:
                encontrarEvento(jugador, fragManag, 1);
                break;
            case 13:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 14:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 15:
                encontrarEvento(jugador, fragManag, 1);
                break;
            case 16:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 17:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 18:
                encontrarEvento(jugador, fragManag, 1);
                break;
            case 19:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 20:
                encontrarMonstruo(jugador, Monstruos.GOBLIN, fragManag);
                break;
            case 21:
                encontrarEvento(jugador, fragManag, 2);
                break;
            case 22:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 23:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 24:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 25:
                encontrarEvento(jugador, fragManag, 2);
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
                encontrarEvento(jugador, fragManag, 2);
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
                encontrarEvento(jugador, fragManag, 2);
                break;
            case 36:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 37:
                encontrarMonstruo(jugador, Monstruos.OGRO, fragManag);
                break;
            case 38:
                encontrarMonstruo(jugador, Monstruos.OGRO, fragManag);
                break;
            case 39:
                encontrarEvento(jugador, fragManag, 2);
                break;
            case 40:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 41:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 42:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 43:
                encontrarEvento(jugador, fragManag, 2);
                break;
            case 44:
                encontrarMonstruo(jugador, Monstruos.OGRO, fragManag);
                break;
            case 45:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 46:
                encontrarMonstruo(jugador, Monstruos.LOBO, fragManag);
                break;
            case 47:
                encontrarMonstruo(jugador, Monstruos.OGRO, fragManag);
                break;
            case 48:
                encontrarEvento(jugador, fragManag, 2);
                break;
            case 49:
                encontrarMonstruo(jugador, Monstruos.OGRO, fragManag);
                break;
            case 50:
                encontrarMonstruo(jugador, Monstruos.OGRO, fragManag);
                break;
            case 51:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 52:
                encontrarMonstruo(jugador, Monstruos.OGRO, fragManag);
                break;
            case 53:
                encontrarEvento(jugador, fragManag, 2);
                break;
            case 54:
                encontrarMonstruo(jugador, Monstruos.OGRO, fragManag);
                break;
            case 55:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 56:
                encontrarMonstruo(jugador, Monstruos.ORCO, fragManag);
                break;
            case 57:
                encontrarEvento(jugador, fragManag, 2);
                break;
            case 58:
                encontrarMonstruo(jugador, Monstruos.OGRO, fragManag);
                break;
            case 59:
                encontrarEvento(jugador, fragManag, 2);
                break;
            case 60:
                encontrarMonstruo(jugador, Monstruos.DRAGON, fragManag);
                break;
        }
    }

    public void encontrarEvento(Jugador jugador, FragmentManager fragManag, int nivel) {
        int opcion = dado.girar(1,100);
        if (nivel == 1) {
            if (opcion >= 1 && opcion < 25) {
                fragManag.beginTransaction().replace(R.id.contenedor, new CascadaFragment()).commit();
            }
            else if (opcion >= 25 && opcion < 57) {
                fragManag.beginTransaction().replace(R.id.contenedor, new CadaverFragment()).commit();
            }
            else if (opcion >= 57 && opcion < 72) {
                fragManag.beginTransaction().replace(R.id.contenedor, new CofreEnterradoFragment()).commit();
            }
            else {
                fragManag.beginTransaction().replace(R.id.contenedor, new TrampaFragment()).commit();
            }
        }
        else
        {
            if (opcion >= 1 && opcion < 25) {
                fragManag.beginTransaction().replace(R.id.contenedor, new CascadaFragment()).commit();
            }
            else if (opcion >= 25 && opcion < 57) {
                fragManag.beginTransaction().replace(R.id.contenedor, new CofreEnterradoFragment()).commit();
            }
            else if (opcion >= 57 && opcion < 72) {
                fragManag.beginTransaction().replace(R.id.contenedor, new CadaverFragment()).commit();
            }
            else {
                fragManag.beginTransaction().replace(R.id.contenedor, new TrampaFragment()).commit();
            }
        }

    }

    public void encontrarMonstruo(Jugador jugador, Monstruos monstruo, FragmentManager fragManag) {
        /*if (chequearMuerto(jugador)) {
            return;
        }*/
        this.hayMonstruo = true;
        this.monstruo = monstruo;
        this.monstruo.setVida(monstruo.getVidaOriginal());
        Fragment frag = new MonstruoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("monstruo", monstruo.name());
        frag.setArguments(bundle);
        fragManag.beginTransaction().replace(R.id.contenedor, frag).commit();
    }

   /* public boolean chequearMuerto(Jugador jugador) {
        if (jugador.getVida() <= 0) {
            return true;
        }
        return false;
    }*/
}
