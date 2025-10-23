public class CamisaFormal extends Camisa {
    private boolean llevaCorbata;
    private boolean llevaManuernillas;

    public CamisaFormal(String color, String talla, String tipoMangas, String tipoCuello, boolean llevaCorbata, boolean llevaManuernillas) {
        super(color, talla, tipoMangas, tipoCuello);
        this.llevaCorbata = llevaCorbata;
        this.llevaManuernillas = llevaManuernillas;
    }

    @Override
    public void mostrarInfo() {
        // Llama al método de la clase padre y añade su propia información
        super.mostrarInfo();
        System.out.println("\t  (Formal) Admite corbata: " + (llevaCorbata ? "Sí" : "No") + ", Admite mancuernillas: " + (llevaManuernillas ? "Sí" : "No"));
    }
}