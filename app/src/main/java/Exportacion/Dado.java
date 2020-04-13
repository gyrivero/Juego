package Exportacion;

import java.util.Random;

public class Dado {

    public int girar(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max+1)-min)+min;
    }
}
