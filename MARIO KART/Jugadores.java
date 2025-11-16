import java.util.Random;

public class Jugadores implements Runnable {
    
    // Atributos del Jugador
    private final int identificador;
    private final String nombreMii;
    private final String color;
    private int posicion;
    private boolean EsBloqueado;
    private int turnoBloqueado;
    private boolean menosDado;
    private int turnosDadoMenos;
    
    // Referencia a la pista para la carrera compartida
    private final Pista pista; 
    
    // Control del hilo
    private boolean estaEnPista;
    
    // Lógica Externa
    private final Dado dado; // Usamos la clase Dado
    
    /**
     * Constructor Jugador
     * @param identificador Identificador único del participante
     * @param nombre Nombre del participante
     * @param color Color asignado al vehículo
     * @param pista Referencia a la carrera compartida
     */
    public Jugadores(int identificador, String nombre, String color, Pista pista) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.color = color;
        this.pista = pista;
        
        // Inicialización de estado
        this.posicion = 0;
        this.EsBloqueado = false;
        this.turnoBloqueado = 0;
        this.menosDado = false;
        this.turnosDadoMenos = 0;
        this.estaEnPista = true;
        
        // Inicialización de la lógica del dado
        this.dado = new Dado();
    }

    /**
     * El método run() contiene el bucle principal de la carrera para el hilo.
     */
    @Override
    public void run() {
        // Este es el bucle principal de la carrera.
        while (estaEnPista) {
            
            // 1. Verificar estado de efectos
            if (EsBloqueado) {
                Bloquear();
                
                // NOTA IMPORTANTE: LLAMADA A PISTA PARA LOG DE EVENTOS
                // pista.registrarEvento(this.nombreMii + " está bloqueado. Turnos restantes: " + this.turnoBloqueado);
                // La clase Pista (compartida) debe tener un método (ej. registrarEvento) 
                // para que todos los jugadores puedan enviar información sobre lo que sucede,
                // manteniendo la Pista como la única entidad que maneja el estado global y la salida.
                
            } else {
                // Si no está bloqueado, se mueve.
                if (menosDado) {
                    Dado(); // Aplica la lógica para reducir los turnos de dado
                }
                
                realizarMovimiento();
            }
            
            // 2. Control de Velocidad/Turno
            try {
                // Pausa para simular un turno o retraso entre movimientos.
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                estaEnPista = false;
                // NOTA IMPORTANTE: LLAMADA A PISTA PARA FINALIZAR
                // pista.finalizarParticipante(this); 
                // La clase Pista debe ser informada cuando un participante termina o es interrumpido.
            }
        }
    }

    /**
     * Realiza el movimiento del participante lanzando el dado.
     * Utiliza la clase Dado para la lógica del lanzamiento.
     */
    private void realizarMovimiento() {
        // Usa la clase Dado para obtener el avance
        int avance = dado.lanzar(menosDado);
        
        // Actualizar posición a través de pista metodo actualizar
        // NOTA IMPORTANTE: LLAMADA A PISTA PARA ACTUALIZAR POSICIÓN
        pista.actualizarPosicion(this, avance);
        // La clase Pista DEBE ser la única que modifique la posición del jugador 
        // y verifique si ha cruzado la meta (validación de fin de carrera).
        // El método podría necesitar ser sincronizado en Pista (ej: public synchronized void actualizarPosicion(...)) 
        // para manejar el acceso concurrente de múltiples hilos (Jugadores).
        
        // NOTA IMPORTANTE: LLAMADA A PISTA PARA LOG DE EVENTOS
        // pista.registrarEvento(this.nombreMii + " avanza " + avance + " casillas. Nueva posición: " + this.posicion);
    }

    /**
     * Maneja el estado de bloqueo del participante.
     */
    private void Bloquear() {
        turnoBloqueado--;
        
        if (turnoBloqueado <= 0) {
            EsBloqueado = false;
           // NOTA IMPORTANTE: LLAMADA A PISTA PARA LOG DE EVENTOS
           // pista.registrarEvento(nombreMii + " se ha liberado del bloqueo");
        }
    }

    /**
     * Maneja la duración del efecto de dado reducido.
     */
    private void Dado() {
        turnosDadoMenos--;
        
        if (turnosDadoMenos <= 0) {
            menosDado = false;
           // NOTA IMPORTANTE: LLAMADA A PISTA PARA LOG DE EVENTOS
           // pista.registrarEvento(nombreMii + " ha recuperado su dado normal");
        }
    }

    /**
     * Aplica el efecto de poder utilizando la clase ObjetosEspeciales.
     * @param tipoObjeto 1: concha, 2:platano 
     */
    public void aplicarEfectoObjeto(int tipoObjeto) {
        switch (tipoObjeto) {
            case ObjetosEspeciales.REDUCE_DADO: // Concha
                menosDado = true;
                // Usa la lógica de ObjetosEspeciales para la duración
                turnosDadoMenos = ObjetosEspeciales.aplicarConcha(); 
                // NOTA IMPORTANTE: LLAMADA A PISTA PARA LOG DE EVENTOS
                // pista.registrarEvento(nombreMii + " fue alcanzado por un caparazón! Dado reducido por " + turnosDadoMenos + " turnos");
                break;
                
            case ObjetosEspeciales.BLOQUEO: // Plátano
                EsBloqueado = true;
                // Usa la lógica de ObjetosEspeciales para la duración
                turnoBloqueado = ObjetosEspeciales.aplicarPlatano(); 
               // NOTA IMPORTANTE: LLAMADA A PISTA PARA LOG DE EVENTOS
               // pista.registrarEvento(nombreMii + " pisó una banana! Bloqueado por " + turnoBloqueado + " turnos");
                break;
        }
    }

    // Getters y Setters
    public int getIdentificador() { return identificador; }
    public String getNombre() { return nombre; }
    public String getColor() { return color; }
    public int getPosicion() { return posicion; }
    public void setPosicion(int posicion) { this.posicion = posicion; }
    public boolean getEstaEnPista() { return estaEnPista; } 
    public void setEstaEnPista(boolean estaEnPista) { this.estaEnPista = estaEnPista; } 

    @Override
    public String toString() {
        return String.format("Jugadores{id=%d, nombre='%s', color='%s', posicion=%d}", 
                           identificador, nombreMii, color, posicion);
    }
}
