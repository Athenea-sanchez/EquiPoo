import java.util.Random;

/**
 * Clase que representa a un jugador mii (como en wii (´▽`ʃ♡ƪ))en la carrera.
 * Cada participante es un hilo
 * Implementa Runnable para la ejecucion de varios hilos con el mismo run
 */
public class Mii implements Runnable {
 }
    private final int identificador;
    private final String nombreMii;
    private final String color;
    private int posicion;
    private boolean EsBloqueado;
    private int turnoBloqueado;
    private boolean menosDado;
    private int turnosDadoMenos;
    
    // PISTA 
    // Aqui va lo que jalamos de pista 
    private final Pista pista;
    
    // Control del hilo
    private boolean estaEnPista;
    private Random cualquiera;
    
    /**
     * Constructor jugador
     * @param identificador Identificador único del participante
     * @param nombreMii Nombre del participante
     * @param color Color asignado al vehículo
     * @param pista Referencia a la carrera compartida
     * @param EsBloqueado = false;
     * @param this.turnoBloqueado = 0;
     * @param this.menosDado = false;
     * @param this.turnosDadoMenos = 0;
     * @param this.estaEnPista= true;
     * @param this.cualquiera= new Random();
     * 
     */
    public Participante(int identificador, String nombreMii, String color, Pista pista,int posicion, boolean EsBloqueado, int turnosBloqueado,boolean menosDado,int turnosDadoMenos, boolean estaEnPista, Random cualquiera) {
        this.identificador = identificador;
        this.nombreMii = nombreMii;
        this.color = color;
        this.pista = pista;
        this.posicion = 0;
        this.EsBloqueado = false;
        this.turnoBloqueado = 0;
        this.menosDado = false;
        this.turnosDadoMenos = 0;
        this.estaEnPista= true;
        this.cualquiera= new Random();
    }

    /**
     * Realiza el movimiento del participante lanzando el dado
     */
    private void realizarMovimiento() {
        int avance;
        
        if (menosDado) {
            avance = cualquiera.nextInt(3) + 1; // Dado de 3 caras (1-3)
        } else {
            avance = cualquiera.nextInt(6) + 1; // Dado normal de 6 caras (1-6)
        }
        
        // Actualizar posición a través de pista metodo actualizar
        pista.actualizarPosicion(this, avance);
    }

    /**
     * Maneja el estado de bloqueo del participante
     */
    private void Bloquear() {
        try {
            Thread.sleep(300); //bloqueo por 3 turnos
            turnoBloqueado--;
            
            if (turnoBloqueado <= 0) {
                EsBloqueado = false;
               //aca lo de la pista pista.registrarEvento(nombre + " se ha liberado del bloqueo");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            estaEnPista = false;
        }
    }

    /**
     * dado menos 
     */
    private void Dado() {
        turnosDadoMenos--;
        
        if (turnosDadoMenos<= 0) {
            menosDado= false;
           //aca metodo de pista pista.registrarEvento(nombre + " ha recuperado su dado normal");
        }
    }

    /**
     * Aplica el efecto de poder 
     * @param tipoPoder 1: concha, 2:platano 
     */
    public void aplicarEfectoObjeto(int tipoObjeto) {
        switch (tipoObjeto) {
            case 1: // concha reduce dado a la mitad por 3 turnos
                menosDado = true;
                turnosDadoMenos= 3;
                //Metodo en pista pista.registrarEvento(nombre + " fue alcanzado por un caparazón! Dado reducido por 3 turnos");
                break;
                
            case 2: // platano bloquea por 3 turnos
                EsBloqueado= true;
                turnoBloqueado = 3;
               //pista metodo  carrera.registrarEvento(nombre + " pisó una banana! Bloqueado por 3 turnos");
                break;
        }
    }

    // Getters y Setters
    public int getIdentificador() { return identificador; }
    public String getMii() { return nombreMii; }
    public String getColor() { return color; }
    public int getPosicion() { return posicion; }
    public void setPosicion(int posicion) { this.posicion = posicion; }
    public boolean getestaEnPista() { return estaEnPista; }
    public void setestaEnPista(boolean estaEnPista) { this.estaEnPista= estaEnPista; }

    @Override
    public String toString() {
        return String.format("Participante{id=%d, nombre='%s', color='%s', posicion=%d}", 
                           identificador, nombreMii, color, posicion);
    }
}
