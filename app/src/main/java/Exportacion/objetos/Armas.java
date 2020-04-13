package Exportacion.objetos;

import com.example.juego.R;

public enum Armas {
    PUÑOS("Puños",0,3, R.drawable.ic_fist,R.drawable.ic_fist_land),
    DAGA("Daga",1,4,R.drawable.ic_daga,R.drawable.ic_daga_land),
    ESPADA("Espada",3,6,R.drawable.ic_sword,R.drawable.ic_sword_land),
    MANDOBLE("Mandoble",5,8,R.drawable.ic_two_handed_sword,R.drawable.ic_two_handed_sword_land),
    ESPADA_DE_PLATA("Espada de Plata",8,10,R.drawable.ic_plata,R.drawable.ic_plata_land),
    ESPADA_LEGENDARIA("Espada Legendaria", 12,18,R.drawable.ic_relic_blade,R.drawable.ic_relic_blade_land);
   
    String nombre;
    int min;
    int max;
    int imagen;
    int imagenPort;
    int imagenLand;

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getImagenPort() {
        return imagenPort;
    }

    public void setImagenPort(int imagenPort) {
        this.imagenPort = imagenPort;
    }

    public int getImagenLand() {
        return imagenLand;
    }

    public void setImagenLand(int imagenLand) {
        this.imagenLand = imagenLand;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getImagen() {return imagen;}
        
    Armas(String nombre, int min, int max,int imagenPort, int imagenLand) {
        this.nombre = nombre;
        this.min = min;
        this.max = max;
        this.imagenPort = imagenPort;
        this.imagenLand = imagenLand;
    }

}
