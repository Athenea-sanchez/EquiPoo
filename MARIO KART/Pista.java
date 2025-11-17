//esto arrojo gemini, pero esta super tecnico a madres, no creo que se pueda ocupar, o si?

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Carrera {
    
    // --- ATRIBUTOS (Recurso Compartido) ---
    private static final int LONGITUD_PISTA = 100;
    private final List<Participante> ranking;
    private final Random random;
    
    // Posiciones donde hay cajas de objetos (cada 10 espacios, excepto la meta 100)
    private final List<Integer> posicionesObjeto;
    
    // Constructor
    public Carrera(List<Participante> todosLosParticipantes) {
        this.ranking = todosLosParticipantes;
        this.random = new Random();
        this.posicionesObjeto = new ArrayList<>();
        
        // Inicializa las posiciones de objeto: 10, 20, 30... 90
        for (int i = 10; i < LONGITUD_PISTA; i += 10) {
            posicionesObjeto.add(i);
        }
    }
    
    // --- MÉTODOS SINCRONIZADOS (Requerimiento C.1: Sincronización Absoluta) ---
    
    /**
     * Sincronizado: Actualiza la posición del participante y verifica si obtiene un objeto.
     * @param p El participante que avanza.
     * @param pasos La cantidad de casillas a avanzar.
     */
    public synchronized void avanzar(Participante p, int pasos) {
        if (p.isBloqueado()) {
            // Lógica si el participante está bajo el efecto de un objeto
            p.restarTurnoBloqueo();
            generarLogTurno(p.getNombre() + " está bloqueado. Turnos restantes: " + p.getTurnosBloqueo());
            return; // No avanza este turno
        }
        
        int avanceReal = pasos;
        if (p.isDadoReducido()) {
            // Lógica para objeto de reducción de avance
            avanceReal = pasos / 2; // Reduce a la mitad
            p.restarTurnoReduccion();
            generarLogTurno(p.getNombre() + " tiene avance reducido. Avance: " + avanceReal + ". Turnos restantes: " + p.getTurnosReduccion());
        }

        p.setPosicion(p.getPosicion() + avanceReal);
        
        if (p.getPosicion() >= LONGITUD_PISTA) {
            p.setPosicion(LONGITUD_PISTA); // Cruzó la meta
            if (!p.isFinalizado()) {
                p.setFinalizado(true);
                registrarLlegada(p, System.currentTimeMillis());
            }
        } else {
            verificarObjeto(p);
        }
        
        actualizarRanking();
    }
    
    /**
     * Sincronizado: Verifica si el participante cae en una posición de objeto.
     * @param p El participante.
     */
    private synchronized void verificarObjeto(Participante p) {
        if (posicionesObjeto.contains(p.getPosicion())) {
            generarLogTurno(p.getNombre() + " ha tomado un objeto en la posición " + p.getPosicion());
            lanzarObjeto(p);
        }
    }
    
    /**
     * Sincronizado: Asigna un objeto aleatorio a un oponente aleatorio.
     * @param lanzador El participante que obtuvo el objeto.
     */
    private synchronized void lanzarObjeto(Participante lanzador) {
        // 1. Seleccionar un oponente aleatorio (diferente al lanzador)
        Participante oponente = null;
        List<Participante> disponibles = new ArrayList<>(ranking);
        disponibles.remove(lanzador); // No se lanza a sí mismo
        
        if (!disponibles.isEmpty()) {
            oponente = disponibles.get(random.nextInt(disponibles.size()));
        }
        
        if (oponente != null) {
            // 2. Elegir y aplicar el objeto (2 tipos de objetos)
            int tipoObjeto = random.nextInt(2); 
            
            if (tipoObjeto == 0) {
                // Objeto 1: Reducir avance por 3 turnos (Caparazón)
                oponente.setTurnosReduccion(3);
                generarLogTurno("  >>> ¡OBJETO LANZADO! Caparazón a " + oponente.getNombre());
            } else {
                // Objeto 2: Detener el coche por 3 turnos (Banana)
                oponente.setTurnosBloqueo(3);
                // Nota: El efecto de sleep se simula en el método avanzar() del participante (parte de la lógica de Ana/Athenea) 
                // o deteniendo el avance como se hizo en 'avanzar()'.
                generarLogTurno("  >>> ¡OBJETO LANZADO! Banana a " + oponente.getNombre());
            }
        }
    }

    /**
     * Sincronizado: Reordena el ranking de participantes.
     */
    private synchronized void actualizarRanking() {
        // Ordena la lista por posición (mayor posición primero)
        Collections.sort(ranking, Comparator.comparingInt(Participante::getPosicion).reversed());
    }

    /**
     * Sincronizado: Imprime el estado actual de la carrera en la consola. (Requerimiento D.1)
     * Este método podría llamarse después de que todos los hilos hayan completado su turno.
     */
    public synchronized void generarLogTurno(String evento) {
        System.out.println("--- LOG DE CARRERA ---");
        System.out.println("Evento: " + evento);
        
        for (int i = 0; i < ranking.size(); i++) {
            Participante p = ranking.get(i);
            // Muestra el nombre, la posición y el puesto
            System.out.printf("  %d. %s (Posición: %d/%d)\n", 
                (i + 1), p.getNombre(), p.getPosicion(), LONGITUD_PISTA);
        }
        System.out.println("----------------------\n");
    }

    /**
     * Sincronizado: Registra la llegada de un coche en el archivo de resultados. (Requerimiento D.2)
     * @param p El participante que cruzó la meta.
     * @param tiempo El tiempo de llegada (usando System.currentTimeMillis()).
     */
    private synchronized void registrarLlegada(Participante p, long tiempo) {
        actualizarRanking(); // Asegura que el puesto sea el correcto al momento de registrar
        int puesto = ranking.indexOf(p) + 1;
        
        String registro = String.format("Puesto: %d | Nombre: %s | Tiempo: %d ms\n", 
                                        puesto, p.getNombre(), tiempo);
        
        try (PrintWriter writer = new PrintWriter(new FileWriter("ranking_final.txt", true))) {
            // Usa FileWriter(..., true) para modo anexión
            writer.print(registro);
            generarLogTurno(p.getNombre() + " ha cruzado la meta en el Puesto " + puesto + "!");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de resultados: " + e.getMessage());
        }
    }
}

