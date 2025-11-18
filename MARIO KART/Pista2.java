/*	---CLASE PISTA---
	-Dentro de este código se busca implementar la clase pista, encargada de funcionar como el "tablero" dentro del cual avanzarán los coches.
	- */
import java.util.Random;

public class Pista {
	//Declaramos atributos
	int pista = 100;
	String[] cajaSorpresa; 
	String[] objetos = {"Caparazon", "Banana"};
	Random aleatorio = new Random();

	//Iniciali<ar usando el constructor
	public void Pista (String pista, String cajaSorpresa){
		this.pista = pista;
		this.cajaSorpresa = cajaSorpresa;

	//Metodo caja sorpresa
		public void posicionesObjeto(){
			for (int i=0; i = (pista-100); i++){
				int casillaObjeto = i;
				Syste.out.println("**CASILLA DEL OBJETO **");
				int objetoAleatorio = random.nextInt(objetos.lenght);
				String objetoSeleccionado = objeto[objetoAleatorio];
				System.out.println("El objeto obtenido es: " + objetoSeleccionado);
				System.out.println("\n");
				System.out.println("...\n");
				System.out.println("Lanzando objeto...");
				int jugadorAleatorio = random.nexInt(//seleccionar el hilo al que lo lanza)
			}
		}

	//Método para actualizar la posición del jugador conforme avanza
		public int actualizarPosicion (Participante p, int nuevaPosicion) {
			if(EsBloqueado){
				System.out.println("Participante bloqueado. Esquiva el caparazon a la proxima XD");
			} else{
				nuevaPosicion = p;
				if(posicionesObjeto == ){
					System.out.println("Felicidades!!! Has caido en la casilla del objeto...");
					posicionesObjeto(nuevaPosicion);
				}
			}
		}


	}


} 
