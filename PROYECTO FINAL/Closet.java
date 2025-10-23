import java.util.ArrayList;

public class Closet {
    // Atributos
    private String propietario;
    // Usamos ArrayList para la relación "uno a muchos"
    private ArrayList<Prenda> prendas;
    private ArrayList<Accesorio> accesorios;

    // Constructor
    public Closet(String propietario) {
        this.propietario = propietario;
        this.prendas = new ArrayList<>();
        this.accesorios = new ArrayList<>();
    }

    // Métodos para agregar prendas y accesorios específicos (Sobrecarga)
    public void agregarItem(Prenda nuevaPrenda) {
        this.prendas.add(nuevaPrenda);
        System.out.println("\n\t>> Se agregó una prenda al clóset de " + this.propietario);
    }

    public void agregarItem(Accesorio nuevoAccesorio) {
        this.accesorios.add(nuevoAccesorio);
        System.out.println("\n\t>> Se agregó un accesorio al clóset de " + this.propietario);
    }

    // Método para mostrar todo el contenido del clóset
    public void mostrarContenido() {
        System.out.println("\n");
        System.out.println("***** CONTENIDO DEL CLÓSET DE " + propietario.toUpperCase() + " *****");

        int totalItems = prendas.size() + accesorios.size();
        System.out.println("\n\tTotal de ítems: " + totalItems);
        
        System.out.println("\n--- PRENDAS (" + prendas.size() + ") ---");
        if (prendas.isEmpty()) {
            System.out.println("\tNo hay prendas.");
        } else {
            for (Prenda prenda : prendas) {
                prenda.mostrarInfo(); // Polimorfismo
            }
        }

        System.out.println("\n--- ACCESORIOS (" + accesorios.size() + ") ---");
        if (accesorios.isEmpty()) {
            System.out.println("\tNo hay accesorios.");
        } else {
            for (Accesorio accesorio : accesorios) {
                accesorio.mostrarInfo(); // Polimorfismo
            }
        }
    }
}