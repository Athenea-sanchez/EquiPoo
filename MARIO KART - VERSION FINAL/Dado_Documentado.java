import java.util.Random;
/*
*SUBLCLASE #4 : DADO

*Clase encargada de coordinar la creación y el funcionamiento del dado.
*/

public class Dado {

    /*
    *Generador de números aleatorios para simular el lanzamiento del dado.
    */
    private final Random dadoAleatorio;
    
    /*
    *Constructor de la clase Dado:
    */
    public Dado() {
        this.dadoAleatorio = new Random();
    }
    
    /*
    *Método que permite lanzar el dado.
    *@param [dadoReducido] : Indica si se usa el dado reducido (True) o el normal (False).
    *@return : Número de casillas a avanzar (dependen del dado que se esta usando).
    */
    public int lanzar(boolean dadoReducido) {
        int avanceCasillas;
        
        if (dadoReducido) {
            avanceCasillas = dadoAleatorio.nextInt(3) + 1; 
        } else {
            avanceCasillas = dadoAleatorio.nextInt(6) + 1; 
        }
        
        return avanceCasillas;
    }
}
