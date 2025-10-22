// Clase hija Prenda
public class Prenda extends Closet{
    // Atributos
    public String color;
    public String talla;
    public int numPrendas = 0;
    
    
    // Constructor
    public Prenda(String propietario, String color, String talla){
        super(propietario);
        this.color = color;
        this.talla = talla;
    }
    
    // Métodos
    int idItemPrenda;
    public void lavarPrenda(int idItemPrenda){
        System.out.println("La prenda " + idItemPrenda + " se está lavando...");
        System.out.println("La prenda " + idItemPrenda + " ya está limpia :)");
    }
    
    public void doblarPrenda(int idItem){
        System.out.println("La prenda " + idItemPrenda + " se está doblando...");
        System.out.println("La prenda " + idItemPrenda + " ya está dobalada :)");
    }
    
    // Polimorfismo
    @Override
    public void agregarItem(int numItems){
        super.agregarItem(numItems);
        numPrendas += numItems;
        //totalItems += numPrendas;
        System.out.println("\t¡Listo! Tienes " + numPrendas + " prendas en total");
    }
    
    @Override
    public void mostrarContenido(){
        System.out.println("\n");
        System.out.println("\nHola " + propietario);
        System.out.println("\tTienes " + numPrendas + " prendas en el clóset");
    }
    
}