public class Closet { // Super clase
	
    // Variables
        // Se declara como public
    public int totalItems = 0;
	
    // Atributos
    public String propietario;
	
    // Constructor
    public Closet(String propietario){
        this.propietario = propietario;
    }

    // Métodos
    public void agregarItem(int numItems){
        totalItems += numItems;
        /*System.out.println("\n");
        System.out.println("\t¡Listo!, agregaste " + numItems + " items al clóset");
        System.out.println("\tTienes " + totalItems + " items disponibles");*/
    }
	
    public void quitarItem(int numItems){
        if(numItems > totalItems){
            System.out.println("\n");
            System.out.println("\tNo puedes quitar más items de los que tienes");
        } 
        else {
            totalItems -= numItems;
            System.out.println("\n");
            System.out.println("\tEliminaste " + numItems + " items del clóset");
            System.out.println("\tTienes " + totalItems + " items disponibles");
        }
    }
	
    public void mostrarContenido(){
        System.out.println("\n");
        System.out.println("\tHola " + propietario );
        System.out.println("\tTienes " + totalItems + " items disponibles");
    }
}



