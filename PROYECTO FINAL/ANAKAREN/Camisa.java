public class Camisa extends Prenda {
    private String tipoMangas;
    private String tipoCuello;

    public Camisa(String color, String talla, String tipoMangas, String tipoCuello) {
        super(color, talla);
        this.tipoMangas = tipoMangas;
        this.tipoCuello = tipoCuello;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("\t- Camisa | Color: " + color + ", Talla: " + talla + ", Mangas: " + tipoMangas + ", Cuello: " + tipoCuello);
    }

    public void planchar() {
        System.out.println("Planchando la camisa...");
    }
}
