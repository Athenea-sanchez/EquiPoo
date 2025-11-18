/*	---CLASE PISTA---
	-Implementación de la pista que permite el avance de los jugadores al funcionar como un tablero 
	-Realiza la asignacion de las cajas sorpresa cada 10 casillas
	-Asigna un objeto a un jugador
	-Lanza dicho objeto a otro jugador (asignandolo aleatoriamente con random)
	-Controla el avance de posiciones de los jugadores */
import java.util.Random;

public class Pista {
	//Declaramos atributos
	int longitudPista = 100; //Declaramos las 100 casillas que debe medir la pista
	int[] cajaSorpresa; //Guardamos las casillas donde se localiza cada caja 
	String[] objetos = {"Caparazon", "Banana"}; //Arreglo que almacena los objetos disponibles
	Random aleatorio = new Random(); //Aleatorio sirve para asignar un objeto 
	Jugadores[] jugadores; //Sirve para contener las referencias de cada jugador (hilo)

	//Inicializar usando el constructor
	public Pista (Jugadores[] jugadores){
		this.jugadores = juadores;
		this.cajaSorpresa = new int[longitudPista / 10 -1] //Asigna unicamente 9 cajas sorpresa
		for (int i=0; i<cajaSorpresa; i++){
			cajaSorpresa[i] = 10*(i+1); //Se encarga que las cajas se asignen cada 10 casillas, exceptuando la meta
		}
	}

	//Metodo para verificar si en una casilla hay caja sorpresa, usando la posición para hacer el chequeo
	private boolean tieneCajaSorpresa(int posicion){
		/*La siguiente línea verifica si la posicion del jugador tiene una caja, considerando que debe ser una donde
			-Sea mayor a cero (no puede ser el inicio de la pista)
			-Menor a 100 (no puede ser la meta)
			-Debe ser una casilla múltiplo de 10*/ 
		return posicion > 0 && posicion < 100 && posicion % 10 == 0;
	}
		
	//Metodo para lanzar un objeto a otro jugador aleatorio 
		public void lanzarObjeto(Jugadores disparador){
			//Elige un oponente al azar, distinto del jugador
			int total = jugadores.lenght;
			if(total < 2) return; //No hay a quien lanzar

			//Elegimos un juagador mediante su posicion en el arreglo de jugadores
			int pos;
			do{
				pos = aleatorio.nextInt(total); //Del total de jugadores escoge uno al cual lanzarle
			} while (jugadores[pos] == disparador);

			Jugadores oponente = jugadores[pos];

			//Seleccionar el objeto a lanzar
			int objetoLanzado = aleatorio.nextInt(objetos.lenght); //Escoge un indice entre 0 y 1 (para asignar el objeto)
			oponente.aplicarEfectoObjeto(objetoLanzado + 1); //Debemos sumar 1 porque en ObjetosEspeciales Caparazon=1, Banana=2;
			System.out.println("---Lanzando objeto [" + objeto[objetoLanzado - 1] + "]  a " + oponente.getNombre());
		}

	//Método para actualizar la posición del jugador conforme avanza
		public void actualizarPosicion (Jugadores jugador, int avance){
			//Nueva posicion
			int nuevaPosicion = jugador.getPosicion() + avance;
			if (nuevaPosicion > longitudPista) nuevaPosicion = longitudPista;
				
			jugador.setPosicion(nuevaPosicion);

			//Verificamos si cae en una caja sorpresa
			if (tieneCajaSorpresa(nuevaPosicion)) {
				System.out.println(" --- CASILLA DEL OBJETO ---");
				System.out.println(jugador.getNombre() + " cayó en una caja sorpresa en la casilla " + nuevaPosicion);
				lanzarObjeto(jugador);
			}

			//Verificamos si llegó a la meta
			if (nuevaPosicion == longitudPista) {
				jugador.setEstaEnPista(false);
				System.out.println("Jugador - " + jugador.getNombre() + "ha cruzado la meta ! :D");
				System.out.println("\n\n");
				System.out.println("¡¡¡ FELICIDADES JUGADOR " + jugador.getNombre() + " !!! ");
				System.out.println(">>>>>>> HAS GANADO <<<<<<");
			}
		}
	}
