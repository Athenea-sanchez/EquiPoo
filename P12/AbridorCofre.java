import java.util.Random;

public class AbridorCofre implements Runnable {

    private final String nombreJugador;
    // La instancia de Random compartida
    private final Random generador;
    private final String[] premios;

    // Constructor: Recibe las dependencias (nombre, Random y premios)
    public AbridorCofre(String nombre, Random generador, String[] premios) {
        this.nombreJugador = nombre;
        this.generador = generador;
        this.premios = premios;
    }

    // Paso 2: Implementación de Concurrencia (El Método run())
    @Override
    public void run() {
        // Obtenemos el nombre del hilo actual para el mensaje de resultado
        String nombreHilo = Thread.currentThread().getName(); 
        System.out.println(nombreJugador + " (Hilo: " + nombreHilo + ") comienza a abrir el cofre...");
        
        // 1. Simular Espera (Thread.sleep)
        try {
            // Valor aleatorio entre 50ms y 150ms
            int tiempoEspera = generador.nextInt(101) + 50; 
            Thread.sleep(tiempoEspera);
        } catch (InterruptedException e) {
            // Manejo de interrupción
            Thread.currentThread().interrupt();
            System.err.println(nombreJugador + " fue interrumpido antes de terminar.");
            return;
        }
        
        // 2. Selección Aleatoria
        int indiceAleatorio = generador.nextInt(premios.length);
        String premioObtenido = premios[indiceAleatorio];

        // 3. Resultado
        System.out.printf("✅ ¡%s (Hilo: %s) obtuvo el premio: **%s**!\n", 
                          nombreJugador, nombreHilo, premioObtenido);
    }
}
