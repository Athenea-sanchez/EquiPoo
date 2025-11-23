import java.util.Random;

public class Dado {
    
    private final Random dadoAleatorio;
    
    public Dado() {
        this.dadoAleatorio = new Random();
    }
    
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
