public class CamisaCasual extends Camisa {
    private String estampado;

    public CamisaCasual(String color, String talla, String tipoMangas, String tipoCuello, String estampado) {
        super(color, talla, tipoMangas, tipoCuello);
        this.estampado = estampado;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("\t  (Casual) Estampado: " + this.estampado);
    }
}