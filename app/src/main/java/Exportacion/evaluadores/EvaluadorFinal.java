package Exportacion.evaluadores;

import Exportacion.Jugador;
import Exportacion.Tablero;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EvaluadorFinal {

    static private List<Jugador> listaAuxiliar;
    static private boolean muertos = false;
    static private boolean ganadores = false;

    public boolean isMuertos() {
        return muertos;
    }

    public boolean isGanadores() {
        return ganadores;
    }
   
    static public boolean evaluar(List<Jugador> evaluadores,int opcion) {
        if (opcion==1) {
            if (evaluadores.isEmpty()) {
                return true;
            }
            return false;
        }
        if (evaluadores.isEmpty()) {
            return false;
        }
        return true;
    }

    static public List<Jugador> evaluarGanador(List<Jugador> evaluadores) {
        listaAuxiliar = evaluadores.stream()
                .filter(z -> z.getPosicion() >= Tablero.getCasillaFinal())
                .filter(z -> z.getVida()>0)
                .collect(Collectors.toList());
        if (listaAuxiliar.size()>1) {
            Jugador jugador = listaAuxiliar.stream().max(Comparator.comparingInt(Jugador::getVida)).get();
            listaAuxiliar.clear();
            listaAuxiliar.add(jugador);
        }
        return listaAuxiliar;
    }

    static public List evaluarMuertos(List<Jugador> evaluadores) {
        List<Jugador> listaAuxiliar2;
        listaAuxiliar2 = evaluadores.stream()
                .filter(z -> z.getVida()>0)
                .collect(Collectors.toList());

        return listaAuxiliar2;
    }

    static public void devolverResultado(boolean opt1, boolean opt2) {
        if (opt1) {
            if (listaAuxiliar.size()<1) {
                System.out.println(listaAuxiliar.get(0).getNombre() + ".");
                return;
            }
            System.out.println("El ganador es: " + listaAuxiliar.stream().max(Comparator.comparingInt(Jugador::getVida)).get().getNombre() + ".");
            return;
        }
        if (opt2) {
            System.out.println("Estan todos muertos");
        }
    }

    static public void evaluarFinalGanador() {

    }
}
