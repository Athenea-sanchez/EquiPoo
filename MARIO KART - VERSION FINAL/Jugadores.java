import java.util.Random;

public class Jugadores implements Runnable {
    
    private final int id;
    private final String nombre;
    private final String color;

    private int posicion;
    private boolean esBloqueado;
    private int turnosBloqueado;
    private boolean dadoReducido;
    private int turnosDadoReducido;
    
    private final Pista pista; 
    private boolean estaEnPista;
    private final Dado dado; 
    
    public Jugadores(int id, String nombre, String color, Pista pista) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.pista = pista;
        
        this.posicion = 0;
        this.esBloqueado = false;
        this.turnosBloqueado = 0;
        this.dadoReducido = false;
        this.turnosDadoReducido = 0;

        this.estaEnPista = true;
        this.dado = new Dado();
    }

    @Override
    public void run() {
        while (estaEnPista) {
            
            if (esBloqueado) {
                Bloquear();
                
            } else {
                if (dadoReducido) {
                    DadoReducido(); 
                }
                
                realizarMovimiento();
            }
            
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                estaEnPista = false;
            }
        }
    }

    private void realizarMovimiento() {
        int avanceCasillas = dado.lanzar(dadoReducido);
        pista.actualizarPosicion(this, avanceCasillas);
    }

    private void Bloquear() {
        System.out.println(" -> " + nombre + " está bloqueado -> Turnos restantes: " + turnosBloqueado);

        turnosBloqueado--;
        
        if (turnosBloqueado <= 0) {
            esBloqueado = false;
            System.out.println(" -> " + nombre + " se ha liberado del bloqueo");
        }
    }

    private void DadoReducido() {
System.out.println(" -> " + nombre + " tira con dado reducido -> Turnos restantes: " + turnosDadoReducido);

        turnosDadoReducido--;
        
        if (turnosDadoReducido <= 0) {
            dadoReducido = false;
            System.out.println(" -> " + nombre + " recuperó su dado normal");
        }
    }

    public void aplicarEfectoObjeto(int tipoObjeto) {
        switch (tipoObjeto) {
            case ObjetosEspeciales.REDUCE_DADO: 
                dadoReducido = true;
                turnosDadoReducido = ObjetosEspeciales.aplicarCaparazon(); 
                break;
                
            case ObjetosEspeciales.BLOQUEO: 
                esBloqueado = true;
                turnosBloqueado = ObjetosEspeciales.aplicarBanana(); 
                break;
        }
    }

    public int getId() { 
        return id;
    }

    public String getNombre() {
        return nombre; 
    }

    public String getColor() { 
        return color; 
    }

    public int getPosicion() { 
        return posicion; 
    }

    public void setPosicion(int posicion) { 
        this.posicion = posicion; 
    }

    public boolean getEstaEnPista() { 
        return estaEnPista; 
    } 

    public void setEstaEnPista(boolean estaEnPista) { 
        this.estaEnPista = estaEnPista; 
    } 
}