package Exportacion.objetos;

import com.example.juego.R;

public enum Armaduras {
    ROPA("Ropa",0, R.drawable.ic_shirt,R.drawable.ic_shirt_land),
    ARMADURA_DE_CUERO("Armadura de Cuero",1,R.drawable.ic_leather_armor,R.drawable.ic_leather_armor_land),
    COTA_DE_MALLAS("Cota de Mallas",3,R.drawable.ic_cota,R.drawable.ic_cota_land),
    ARMADURA_DE_PLACAS_COMPLETAS("Armadura de Placas Completas",5,R.drawable.ic_placas,R.drawable.ic_placas_land),
    ARMADURA_DE_ESCAMAS_DE_DRAGON("Armadura de Escamas de Dragon",8,R.drawable.ic_dragon_scale,R.drawable.ic_dragon_scale_land);
   
    String nombre;
    int defensa;
    int imagenPort;
    int imagenLand;

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

    public int getDefensa() {
        return defensa;
    }

    public int getImagenPort() {return imagenPort;}

    Armaduras(String nombre, int defensa, int imagenPort, int imagenLand) {
        this.nombre = nombre;
        this.defensa = defensa;
        this.imagenPort = imagenPort;
        this.imagenLand = imagenLand;
    }
}
