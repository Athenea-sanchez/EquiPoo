import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Principal {

    // Paso 1.1: Recurso de Premios (static final)
    private static final String[] PREMIOS = {"Concha", "Estrella", "Champiñón", "Flor de Fuego", "Super Hoja"};

    public static void main(String[] args) {
        
        // Paso 3.1: Generador Compartido
        // Usamos una semilla fija (42) para resultados predecibles
        Random generadorCompartido = new Random(42); 

        // Paso 3.2: Lanzamiento de Hilos
        String[] nombresJugadores = {"Mario", "Luigi", "Toad", "Peach", "Yoshi"};
        
        // Lista para guardar las referencias a los hilos para el control final
        List<Thread> hilosJugadores = new ArrayList<>(); 

        System.out.println("--- 🏁 Inicio de la Apertura Concurrente de Cofres ---");

        // Iterar sobre el arreglo de nombres
        for (String nombre : nombresJugadores) {
            // Crear instancia de AbridorCofre (la tarea)
            Runnable abridor = new AbridorCofre(nombre, generadorCompartido, PREMIOS);
            
            // Crear nuevo objeto Thread con esa tarea
            Thread jugador = new Thread(abridor);
            
            // Iniciar el hilo con start()
            jugador.start();
            
            // Guardar la referencia del hilo
            hilosJugadores.add(jugador); 
        }

        // Paso 3.3: Control Final con join()
        System.out.println("\n--- Hilo principal esperando que todos los jugadores terminen... ---\n");
        
        try {
            for (Thread hilo : hilosJugadores) {
                // Esperar a que cada hilo termine su ejecución
                hilo.join(); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("El proceso de espera fue interrumpido.");
        }

        System.out.println("\n--- 🛑 Todos los cofres se han abierto. Programa Finalizado Exitosamente. ---");
    }
}
