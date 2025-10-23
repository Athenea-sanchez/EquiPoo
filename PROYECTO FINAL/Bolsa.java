public class Bolsa extends Accesorio {
    private String tamanio;
    private boolean bolsaDeMano;

    public Bolsa(String tipo, String color, String tamanio, boolean bolsaDeMano) {
        super(tipo, color);
        this.tamanio = tamanio;
        this.bolsaDeMano = bolsaDeMano;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("\t- Bolsa | Tipo: " + getTipo() + ", Color: " + getColor() + ", Tamaño: " + tamanio + ", ¿De mano?: " + (bolsaDeMano ? "Sí" : "No"));
    }

    public void colgarBolsa() {
        System.out.println("Colgando la bolsa...");
    }
}