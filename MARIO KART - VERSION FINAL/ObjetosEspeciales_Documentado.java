/*
*SUBLCLASE #2 : OBJETOS ESPECIALES

*Clase encargada de almacenar los objetos especiales usados durante el juego.
*Define los métodos que aplican el castigo de dicho objeto.

@author: Ortiz Contreras Anakaren
         Sánchez Torres Athenea
@since: 20 de moviembre 2025
@version : 4.0
*/

public class ObjetosEspeciales {

    /*
    *Constante que representa el objeto que reduce el número de casillas por avanzar.
    */
    public static final int REDUCE_DADO = 1;
    /*
    *Constante que representa el objeto que bloquea a un jugador.
    */
    public static final int BLOQUEO = 2;
    /*
    *Constante que indica el número de turnos que permanece el efecto del caparazón.
    */
    public static final int DURACION_CAPARAZON_TURNOS = 3;
    /*
    *Constante que indica el número de turnos que permanece el efecto de la banana.
    */
    public static final int DURACION_BANANA_TURNOS = 3;

    /*
    *Método que regresa el valor de la duración del caparazón.
    @return : La duración del caparazón.
    */

    public static int aplicarCaparazon() {
        return DURACION_CAPARAZON_TURNOS;
    }

    /*
    *Método que regresa el valor de la duración de la banana.
    @return : La duración de la banana.
    */

    public static int aplicarBanana() {
        return DURACION_BANANA_TURNOS;
    }
}
