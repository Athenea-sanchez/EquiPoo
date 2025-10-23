public abstract class Accesorio {
    // Atributos privados, se accede a ellos mediante getters si es necesario
    private String tipo;
    private String color;

    public Accesorio(String tipo, String color) {
        this.tipo = tipo;
        this.color = color;
    }
    
    // Getters para acceder a los atributos desde fuera
    public String getTipo() {
        return tipo;
    }
    
    public String getColor() {
        return color;
    }

    public abstract void mostrarInfo();

    public void limpiarAccesorio() {
        System.out.println("Limpiando el accesorio...");
    }
}
