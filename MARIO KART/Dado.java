import java.util.Random;

/**
 * Clase que gestiona la lógica del lanzamiento del dado para los jugadores.
 * Encapsula el generador de números aleatorios.
 */
public class Dado {
    
    private final Random generadorAleatorio;
    
    /**
     * Constructor para inicializar el generador de números aleatorios.
     */
    public Dado() {
        this.generadorAleatorio = new Random();
    }
    
    /**
     * Simula el lanzamiento del dado para determinar el avance del jugador.
     * * @param dadoReducido Indica si el jugador debe usar el dado de 3 caras (true) o el normal de 6 (false).
     * @return El número de casillas que avanza el jugador.
     */
    public int lanzar(boolean dadoReducido) {
        int avance;
        
        if (dadoReducido) {
            // Dado de 3 caras (1-3)
            avance = generadorAleatorio.nextInt(3) + 1; 
        } else {
            // Dado normal de 6 caras (1-6)
            avance = generadorAleatorio.nextInt(6) + 1; 
        }
        
        return avance;
    }
}