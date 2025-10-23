public class PantalonVestir extends Pantalon {
    private String ocasion;

    public PantalonVestir(String color, String talla, String tipoCorte, String ocasion) {
        super(color, talla, tipoCorte);
        this.ocasion = ocasion;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("\t  (De Vestir) Ocasión: " + this.ocasion);
    }
}