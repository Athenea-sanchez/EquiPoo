public abstract class Prenda {
    // Atributos protegidos para que las clases hijas puedan acceder
    protected String color;
    protected String talla;

    public Prenda(String color, String talla) {
        this.color = color;
        this.talla = talla;
    }

    // Método abstracto que todas las clases hijas deben implementar
    public abstract void mostrarInfo();

    public void lavarPrenda() {
        System.out.println("Lavando la prenda...");
    }

    public void doblarPrenda() {
        System.out.println("Doblando la prenda...");
    }
}
