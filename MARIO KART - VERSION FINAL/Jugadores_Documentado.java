/*
*SUBLCLASE #3 : JUGADORES

*Clase encargada de coordinar todas las funciones de los jugadores a lo largo del juego.
*Implementa el método run() para asignar funciones a los hilos que representan a los jugadores.
*Se encarga de obtener los datos de los jugadores.
*Lanza los objetos especiales y administra los métodos que permiten su funcionamiento. 
*/
import java.util.Random;

public class Jugadores implements Runnable {
    
    private final int id;
    private final String nombre;
    private final String color;

    private int posicion;
    private boolean esBloqueado;
    private int turnosBloqueado;
    private boolean dadoReducido;
    private int turnosDadoReducido;
    
    private final Pista pista; 
    private boolean estaEnPista;
    private final Dado dado; 

    /*
    *Constructor de la clase Jugadores:
    @param [nombre] : Nombre del jugador.
    @param [color] : Color del coche.
    @param [pista] : Referencia a la pista.
    @param [id] : ID del jugador.
    */
    
    public Jugadores(int id, String nombre, String color, Pista pista) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.pista = pista;
        
        this.posicion = 0;
        this.esBloqueado = false;
        this.turnosBloqueado = 0;
        this.dadoReducido = false;
        this.turnosDadoReducido = 0;

        this.estaEnPista = true;
        this.dado = new Dado();
    }

    /*
    *Método que contiene los métodos a ejecutar por el hilo cuando inicie su funcionamiento.
    *throws [InteruptedException] : Si el funcionamiento del hilo es interrumpido.
    */

    @Override
    public void run() {
        while (estaEnPista) {
            
            if (esBloqueado) {
                Bloquear();
                
            } else {
                if (dadoReducido) {
                    DadoReducido(); 
                }
                
                realizarMovimiento();
            }
            
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                estaEnPista = false;
            }
        }
    }

    /*
    *Método que permite avanzar con el dado reducido.
    */

    private void realizarMovimiento() {
        int avanceCasillas = dado.lanzar(dadoReducido);
        pista.actualizarPosicion(this, avanceCasillas);
    }

    /*
    *Método que permite bloquear a un jugador.
    */

    private void Bloquear() {
        System.out.println(" -> " + nombre + " está bloqueado -> Turnos restantes: " + turnosBloqueado);

        turnosBloqueado--;
        
        if (turnosBloqueado <= 0) {
            esBloqueado = false;
            System.out.println(" -> " + nombre + " se ha liberado del bloqueo");
        }
    }

    /*
    *Método que permite que un jugador use el dado reducido.
    */

    private void DadoReducido() {
System.out.println(" -> " + nombre + " tira con dado reducido -> Turnos restantes: " + turnosDadoReducido);

        turnosDadoReducido--;
        
        if (turnosDadoReducido <= 0) {
            dadoReducido = false;
            System.out.println(" -> " + nombre + " recuperó su dado normal");
        }
    }

    /*
    *Método que permite aplicar los objetos especiales.
    *@param [tipoObjeto] : Tipo de objeto a aplicar (REDUCE_DADO o BLOQUEO).
    */

    public void aplicarEfectoObjeto(int tipoObjeto) {
        switch (tipoObjeto) {
            case ObjetosEspeciales.REDUCE_DADO: 
                dadoReducido = true;
                turnosDadoReducido = ObjetosEspeciales.aplicarCaparazon(); 
                break;
                
            case ObjetosEspeciales.BLOQUEO: 
                esBloqueado = true;
                turnosBloqueado = ObjetosEspeciales.aplicarBanana(); 
                break;
        }
    }

    /*
    *Método que permite obtener el ID de un jugador.
    *@return :El ID:
    */
    public int getId() { 
        return id;
    }

    /*
    *Método que permite obtener el nombre de un jugador.
    *@return : El nombre del jugador. 
    */
    public String getNombre() {
        return nombre; 
    }

    /*
    *Método que permite obtener el color del coche.
    *@return : El color del coche.
    */
    public String getColor() { 
        return color; 
    }

    /*
    *Método que permite obtener la posición de un jugador.
    *@return : La posición del jugador.
    */
    public int getPosicion() { 
        return posicion; 
    }

    /*
    *Método que permite mostrar la posición de un jugador.
    *@param [posicion] : Posición en pista del jugador.
    */
    public void setPosicion(int posicion) { 
        this.posicion = posicion; 
    }

    /*
    *Método que permite saber si un jugador se encuentra en la pista.
    *@return : True si se encuentra en pista, False en caso contrario.
    */
    public boolean getEstaEnPista() { 
        return estaEnPista; 
    } 

    /*
    *Método que permite mostrar si el jugador se encuentra en pista.
    *@param [estaEnPista] : Indica si el jugador se encuentra en pista.
    */
    public void setEstaEnPista(boolean estaEnPista) { 
        this.estaEnPista = estaEnPista; 
    } 
}
