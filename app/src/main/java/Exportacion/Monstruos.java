package Exportacion;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.juego.R;
import com.mycompany.juegotablero.evaluadores.EvaluadorPreguntas;

import Exportacion.objetos.Armaduras;
import Exportacion.objetos.Armas;


public enum Monstruos {
    GOBLIN(5, 5, 1, 4, "Goblin", R.drawable.ic_goblincolor, 1,R.raw.goblin),
    LOBO(10, 10, 2, 5, "Lobo", R.drawable.ic_lobo, 1,R.raw.lobo),
    ORCO(20, 20, 4, 7, "Orco", R.drawable.ic_orco, 1,R.raw.orco),
    OGRO(25, 25, 5, 10, "Ogro", R.drawable.ic_ogro, 1,R.raw.orco),
    DRAGON(40, 40, 12, 20, "Dragon", R.drawable.ic_dragon, 1,R.raw.orco);

    private int vidaOriginal;
    private int vida;
    private int dañoMin;
    private int dañoMax;
    private String nombre;
    private Dado dado = new Dado();
    private int imagen;
    private int nivel;
    private int raw;

    public int getRaw() {
        return raw;
    }

    public void setRaw(int raw) {
        this.raw = raw;
    }

    public int getVida() {
        return vida;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
        this.vidaOriginal *= this.nivel;
        if (nivel == 2) {
            this.dañoMin *= 2;
            this.dañoMax *= 1.5;
        }
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDañoMin() {
        return dañoMin;
    }

    public void setDañoMin(int dañoMin) {
        this.dañoMin = dañoMin;
    }

    public int getDañoMax() {
        return dañoMax;
    }

    public void setDañoMax(int dañoMax) {
        this.dañoMax = dañoMax;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    Monstruos(int vidaOriginal, int vida, int dañoMin, int dañoMax, String nombre, int imagen, int nivel,int raw) {
        this.vidaOriginal = vidaOriginal;
        this.vida = vida;
        this.dañoMin = dañoMin;
        this.dañoMax = dañoMax;
        this.nombre = nombre;
        this.imagen = imagen;
        this.nivel = nivel;
        this.raw = raw;
    }

    public void atacar(Jugador jugador, Activity activity) {
        int precision = dado.girar(1, 4);
        if (precision > 1) {
            int daño = dado.girar(dañoMin, dañoMax);
            daño -= jugador.getArmadura().getDefensa();
            if (daño < 0) {
                daño = 0;
            }
            jugador.setVida(jugador.getVida() - daño);
            if (jugador.getVida() < 0) {
                jugador.setVida(0);
            }
            if (!(Juego.toast ==null)) {
                Juego.toast.cancel();
            }
            Juego.toast = Toast.makeText(activity.getApplicationContext(), "Recibes " + daño + " puntos de daño", Toast.LENGTH_SHORT);
            Juego.toast.show();
        } else {
            if (!(Juego.toast ==null)) {
                Juego.toast.cancel();
            }
            Juego.toast = Toast.makeText(activity.getApplicationContext(), "Esquivas el ataque", Toast.LENGTH_SHORT);
            Juego.toast.show();
        }
    }

    public int getVidaOriginal() {
        return vidaOriginal;
    }

    public Bundle dejarItem() {
        Bundle bundle = new Bundle();
        int numero = dado.girar(1, 100);
        int opcion = dado.girar(1, 2);
        if (this.nombre.equals(Monstruos.GOBLIN.nombre)) {
            return itemGoblin(numero,opcion);
        }
        else if (this.nombre.equals(Monstruos.LOBO.nombre)) {
            return itemLobo(numero,opcion);
        }
        else if (this.nombre.equals(Monstruos.ORCO.nombre)) {
            return itemOrco(numero,opcion);
        }
        return bundle;
    }

    private Bundle itemOrco(int numero, int opcion) {
        Bundle extras = new Bundle();
        if (nivel == 1) {
            if (numero >= 1 && numero < 41) {
                extras.putInt("cantidad",0);
            } else if (numero >= 41 && numero < 82) {
                if (opcion == 1) {
                    extras.putString("item", Armas.MANDOBLE.name());
                    extras.putInt("cantidad",1);
                } else {
                    extras.putString("item", Armaduras.ARMADURA_DE_CUERO.name());
                    extras.putInt("cantidad",1);
                }
            } else if (numero >= 82 && numero < 93) {
                if (opcion == 1) {
                    extras.putString("item", Armaduras.COTA_DE_MALLAS.name());
                    extras.putInt("cantidad",1);
                } else {
                    extras.putString("item", "Pocion");
                    extras.putInt("cantidad", 2);
                }
            } else {
                if (opcion == 1) {
                    extras.putString("item", Armaduras.COTA_DE_MALLAS.name());
                    extras.putInt("cantidad",1);
                } else {
                    extras.putString("item", Armas.ESPADA_DE_PLATA.name());
                    extras.putInt("cantidad",1);
                }
            }
        }
        else {
            if (numero >= 1 && numero < 41) {
                //No encontrar nada
            } else if (numero >= 41 && numero < 82) {
                if (opcion == 1) {
                    //Encontrar una pocion
                } else {
                    //Encontrar una armadura de cuero
                }
            } else if (numero >= 82 && numero < 93) {
                if (opcion == 1) {
                    //Encontrar un Mandoble
                } else {
                    //Encontrar 2 pociones
                }
            } else if (numero >= 93 && numero < 97) {
                if (opcion == 1) {
                    //Encontrar una cota de mallas
                } else {
                    //Encontrar 2 pociones
                }
            } else {
                if (opcion == 1) {
                    //Encontrar una placas
                } else {
                    //Encontrar una espada de plata
                }
            }
        }
        return extras;
    }

    private Bundle itemLobo(int numero, int opcion) {
        Bundle extras = new Bundle();
        if (nivel == 1) {
            if (numero >= 1 && numero < 41) {
                extras.putInt("cantidad",0);
            } else if (numero >= 41 && numero < 82) {
                if (opcion == 1) {
                    extras.putString("item", Armas.ESPADA.name());
                    extras.putInt("cantidad",1);
                } else {
                    extras.putString("item", Armaduras.ARMADURA_DE_CUERO.name());
                    extras.putInt("cantidad",1);
                }
            } else if (numero >= 82 && numero < 93) {
                if (opcion == 1) {
                    extras.putString("item", Armas.ESPADA.name());
                    extras.putInt("cantidad",1);
                } else {
                    extras.putString("item", "Pocion");
                    extras.putInt("cantidad", 2);
                }
            } else {
                if (opcion == 1) {
                    extras.putString("item", Armas.MANDOBLE.name());
                    extras.putInt("cantidad", 1);
                } else {
                    extras.putString("item", "Pocion");
                    extras.putInt("cantidad", 2);
                }
            }
        }
        else {
            if (numero >= 1 && numero < 41) {
                //No encontrar nada
            } else if (numero >= 41 && numero < 82) {
                if (opcion == 1) {
                    //Encontrar una pocion
                } else {
                    //Encontrar una armadura de cuero
                }
            } else if (numero >= 82 && numero < 93) {
                if (opcion == 1) {
                    //Encontrar un Mandoble
                } else {
                    //Encontrar 2 pociones
                }
            } else if (numero >= 93 && numero < 97) {
                if (opcion == 1) {
                    //Encontrar una cota de mallas
                } else {
                    //Encontrar 2 pociones
                }
            } else {
                if (opcion == 1) {
                    //Encontrar una placas
                } else {
                    //Encontrar una espada de plata
                }
            }
        }
        return extras;
    }

    public Bundle itemGoblin(int numero, int opcion) {
        Bundle extras = new Bundle();
        if (nivel == 1) {
            if (numero >= 1 && numero < 41) {
                extras.putInt("cantidad",0);
            } else if (numero >= 41 && numero < 88) {
                if (opcion == 1) {
                    extras.putString("item",Armas.DAGA.name());
                    extras.putInt("cantidad",1);
                } else {
                    extras.putString("item","Pocion");
                    extras.putInt("cantidad",1);
                }
            } else if (numero >= 88 && numero < 98) {
                if (opcion == 1) {
                    extras.putString("item", Armas.ESPADA.name());
                    extras.putInt("cantidad",1);
                } else {
                    extras.putString("item", Armaduras.ARMADURA_DE_CUERO.name());
                    extras.putInt("cantidad",1);
                }
            } else {
                if (opcion == 1) {
                    extras.putString("item", "Pocion");
                    extras.putInt("cantidad", 2);
                } else {
                    extras.putString("item", Armas.MANDOBLE.name());
                    extras.putInt("cantidad", 1);
                }
            }
        }
        else {
            if (numero >= 1 && numero < 41) {
                //No encontrar nada
            } else if (numero >= 41 && numero < 72) {
                if (opcion == 1) {
                    //Encontrar una espada
                } else {
                    //Encontrar una pocion
                }
            } else if (numero >= 72 && numero < 88) {
                if (opcion == 1) {
                    //Encontrar 2 pociones
                } else {
                    //Encontrar una armadura de cuero
                }
            } else if (numero >= 88 && numero < 97) {
                if (opcion == 1) {
                    //Encontrar Cota de mallas
                } else {
                    //Encontrar un Mandoble
                }
            } else {
                if (opcion == 1) {
                    //Encontrar una armadura de placas completas
                } else {
                    //Encontrar una espada de plata
                }
            }
        }
        return extras;
    }
}
