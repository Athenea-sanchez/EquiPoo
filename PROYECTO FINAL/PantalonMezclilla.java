public class PantalonMezclilla extends Pantalon {
    private int numBolsillos;
    private boolean decorado;

    public PantalonMezclilla(String color, String talla, String tipoCorte, int numBolsillos, boolean decorado) {
        super(color, talla, tipoCorte);
        this.numBolsillos = numBolsillos;
        this.decorado = decorado;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("\t  (Mezclilla) Bolsillos: " + this.numBolsillos + ", Decorado: " + (decorado ? "Sí" : "No"));
    }
}