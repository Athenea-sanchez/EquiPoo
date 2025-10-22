// Clase hija Accesorio
public class Accesorio extends Closet{
    // Atributos
    public String color;
    public String tipo;
    public int numAccesorios = 0;
    
    
    // Constructor
    public Accesorio(String propietario, String color, String tipo){
        super(propietario);
        this.color = color;
        this.tipo = tipo;
    }
    
    // Métodos
    int idItemAcc;
    public void limpiarAccesorio(int idItemAcc){
        System.out.println("El accesorio " + idItemAcc + " se está limpiando...");
        System.out.println("El accesorio " + idItemAcc + " ya está limpio :)");
    }
    
    // Polimorfismo
    @Override
    public void agregarItem(int numItems){
        super.agregarItem(numItems); 
        numAccesorios += numItems;
        //totalItems += numAccesorios;
        System.out.println("\t¡Listo! Tienes " + numAccesorios + " accesorios en total");
    }
    
    @Override
    public void mostrarContenido(){
        System.out.println("\n");
        System.out.println("\tTienes " + idItemAcc + " accesorios disponibles");
    }
}
