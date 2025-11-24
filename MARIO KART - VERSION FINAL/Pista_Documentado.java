/*
*SUBLCLASE #1 : PISTA

*Clase encargada de coordinar los recursos de los jugadores y su avance a lo largo de la pista.
*Clave para el desarrollo de funciones como: el avance de los jugadores, la aparición de cajas sorpresa cada 
determinado número de casillas, el lanzamiento aleatorio de dichos objetos y la elaboración del ranking final de posiciones.

@author: Astudillo Benítez Francisco Armando
         Castillo Ramírez Constanza
@since: 18 de moviembre 2025
@version : 2.0
*/

/*
*Importación de librerías que permiten la interacción con el archivo del ranking, el lanzamiento aleatorio de objetos, 
la asignación de llegada de los jugadores y el manejo de excepciones.
*/
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class Pista {
    
    // ** Atributos **
	private static final String NOMBRE_ARCHIVO_RANKING = "ranking_final.txt";
	long tiempoInicio;

    int longitudPista = 100; 
    int[] cajaSorpresa = {10, 20, 30, 40, 50, 60, 70, 80, 90}; 
    String[] objetos = {"Caparazon (Dado reducido)", "Banana (Bloqueo)"}; 
    Random aleatorio = new Random(); 
    Jugadores[] jugadores; 
    
    private int lugarLlegada = 1; 

    /*
    *Constructor de la clase Pista: 

    *@param [jugadores] : Número de jugadores.
    *@param [tiempoInicio] : Marca el momento de inicio de la carrera.
    */

    public Pista(Jugadores[] jugadores){
        this.jugadores = jugadores;
        this.tiempoInicio = System.currentTimeMillis(); 
    }

    /*
    *Método que permite asignar cajas sorpresa en la pista cada 10 casillas.

    *@param [posicion] : Posición en la pista a verificar.
    *@return : True si la casilla contiene una caja y False si no contiene.
    */
    private boolean tieneCajaSorpresa(int posicion){
        if (posicion > 0 && posicion < 100 && posicion % 10 == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /*
    *Método que permite lanzar un objeto obtenido de una caja sorpresa a otro jugador aleatoriamente.

    *@param [jugadorAtacante] : Jugador que lanza el objeto.
    */

    public synchronized void lanzarObjeto(Jugadores jugadorAtacante){
        int jugadoresActivos = jugadores.length; 
        if(jugadoresActivos < 2) return; 

        int idJugadorVictima;
        do{
            idJugadorVictima = aleatorio.nextInt(jugadoresActivos); 
        } while (jugadores[idJugadorVictima] == jugadorAtacante);

        Jugadores jugadorVictima = jugadores[idJugadorVictima];

        int objetoLanzado = aleatorio.nextInt(objetos.length); 
        jugadorVictima.aplicarEfectoObjeto(objetoLanzado + 1); 
        
        System.out.println(">>> " + jugadorAtacante.getNombre() + " lanza " + objetos[objetoLanzado] + " a " + jugadorVictima.getNombre() + " <<<\n");
    }

    /*
    *Método que permite actualizar la posición de un jugador en pista. 

    *@param [jugador] : Jugador que se mueve a lo largo de la pista.
    *@param [avanceCasillas] : Número de casillas que avanza el jugador.
    */

    public synchronized void actualizarPosicion(Jugadores jugador, int avanceCasillas){
        if(!jugador.getEstaEnPista()) return;

        int nuevaPosicion = jugador.getPosicion() + avanceCasillas;
        if (nuevaPosicion > longitudPista) nuevaPosicion = longitudPista;
            
        jugador.setPosicion(nuevaPosicion);

        System.out.println(jugador.getNombre() + " avanza a la casilla " + nuevaPosicion);

        if (tieneCajaSorpresa(nuevaPosicion)) {
            System.out.println("\n--- " + jugador.getNombre() + " encontró una caja sorpresa ---");
            lanzarObjeto(jugador);
        }

        if (nuevaPosicion == longitudPista) {
            jugador.setEstaEnPista(false); 
            System.out.println("\n\n\t*** " + jugador.getNombre() + " llegó a la meta en lugar #" + lugarLlegada + " ***\n\n");
            
            guardarRanking(jugador);
            lugarLlegada++;
        }
    }

     /*
    *Método que guarda la posición final del jugador en el archivo del ranking.

    *@param [jugador] : Jugador que cruza la meta (se actualiza cada que alguno termina la carrera).
    *@param [avanceCasillas] : Número de casillas que avanza.
    *@return : Impresión en el archivo que indica su posición final.
    */

    private void guardarRanking(Jugadores jugador) {
        
        long tiempoTotal = System.currentTimeMillis() - tiempoInicio; 
        double segundos = tiempoTotal / 1000.0;

        try {
        	File logFile = new File(NOMBRE_ARCHIVO_RANKING);
            boolean esPrimeraSesion = !logFile.exists() || logFile.length() == 0;

            try (FileWriter escritor = new FileWriter(NOMBRE_ARCHIVO_RANKING, true)) {

            	if (esPrimeraSesion) {
                    escritor.write("\n======================================================\n");
                    escritor.write("             RANKING FINAL - MARIO KART               \n");
                    escritor.write("======================================================\n");
                }
				
				escritor.write("\n--- LLEGADA DE JUGADOR ---\n");
                escritor.write("PUESTO: " + lugarLlegada + "\n");
                escritor.write("NOMBRE: " + jugador.getNombre() + "\n");
                escritor.write("COLOR DEL AUTO: " + jugador.getColor() + "\n");
                escritor.write("TIEMPO: " + segundos + " seg\n");


            	escritor.flush();
            	System.out.println("Resultado registrado exitosamente en " + NOMBRE_ARCHIVO_RANKING + "\n");
                
            } catch (IOException e) {
                System.err.println("Error de I/O al escribir el log: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Error al verificar el archivo: " + e.getMessage());
        }
    }
}
