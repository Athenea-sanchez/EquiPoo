/**
 * Clase de utilidad para gestionar los efectos de los objetos especiales (power-ups)
 * en el juego, como la Concha y la Banana.
 */
public class ObjetosEspeciales {
    
    // Constantes para los tipos de objeto (usadas en Jugadores.java)
    public static final int REDUCE_DADO = 1;
    public static final int BLOQUEO = 2;
    
    // Duraciones predefinidas de los efectos (se pueden modificar fácilmente aquí)
    public static final int DURACION_CONCHA_TURNOS = 3;
    public static final int DURACION_PLATANO_TURNOS = 3;

    /**
     * Aplica el efecto de la Concha (Caparazón) que reduce el dado del jugador.
     * * @return El número de turnos que durará el efecto de dado reducido.
     */
    public static int aplicarConcha() {
        return DURACION_CONCHA_TURNOS;
    }

    /**
     * Aplica el efecto del Plátano (Banana) que bloquea el movimiento del jugador.
     * * @return El número de turnos que durará el efecto de bloqueo.
     */
    public static int aplicarPlatano() {
        return DURACION_PLATANO_TURNOS;
    }
}