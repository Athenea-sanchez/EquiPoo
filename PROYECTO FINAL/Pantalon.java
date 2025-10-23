public class Pantalon extends Prenda {
    private String tipoCorte;

    public Pantalon(String color, String talla, String tipoCorte) {
        super(color, talla);
        this.tipoCorte = tipoCorte;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("\t- Pantalón | Color: " + color + ", Talla: " + talla + ", Corte: " + tipoCorte);
    }
}