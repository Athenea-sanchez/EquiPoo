import java.util.Scanner;
/*
*CLASE PRINCIPAL : MAIN
*Encargada de crear a los jugadores (hilos), darle inicio a la carrera y colocar en funcionamiento a los jugadores.

@author: Astudillo Benítez Francisco Armando
         Castillo Ramírez Constanza
         Ortiz Contreras Anakaren
         Sánchez Torres Athenea
@since: 14 de moviembre 2025
@version : 5.0
*/

public class Main {
    /*
    *Método principal que inicia la carrera.
    */

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        Jugadores[] listaDeJugadores = new Jugadores[8];

        Pista pistaDeCarreras = new Pista(listaDeJugadores);

        System.out.print("\n¿Como te llamas? ");
        String nombreJugador = sc.nextLine();
        System.out.print("¿De qué color será tu coche? ");
        String colorJugador = sc.nextLine();

        listaDeJugadores[0] = new Jugadores(1, nombreJugador, colorJugador, pistaDeCarreras);
        listaDeJugadores[1] = new Jugadores(2, "Ana", "Celeste", pistaDeCarreras);
        listaDeJugadores[2] = new Jugadores(3, "Cons", "Rosa", pistaDeCarreras);
        listaDeJugadores[3] = new Jugadores(4, "Pancho", "Azul", pistaDeCarreras);
        listaDeJugadores[4] = new Jugadores(5, "Athenea", "Morado", pistaDeCarreras);
        listaDeJugadores[5] = new Jugadores(6, "Mario", "Rojo", pistaDeCarreras);
        listaDeJugadores[6] = new Jugadores(7, "Luigi", "Verde", pistaDeCarreras);
        listaDeJugadores[7] = new Jugadores(8, "Toad", "Naranja", pistaDeCarreras);

        System.out.println("\n================================================");
        System.out.println("    CARRERA DE EQUI-POO - VERSION MARIO KART    ");
        System.out.println("================================================\n");

        for (Jugadores jugador : listaDeJugadores) {
            Thread hilo = new Thread(jugador);
            hilo.start();
        }   
    }
}
