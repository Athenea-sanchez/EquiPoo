import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Closet closet = new Closet("Anakaren");
        Prenda prenda = new Prenda("Anakaren", "Rojo", "M");
        Accesorio accesorio = new Accesorio("Anakaren", "Negro", "Collar");
        
        int op;
        do{
            System.out.println("\n¿Qué quieres hacer?");
            System.out.println("1. Agregar items al clóset");
            System.out.println("2. Quitar items al clóset");
            System.out.println("3. Ver contenido del clóset");
            System.out.println("4. Cerrar clóset");
            
            System.out.print("\nSeleccione una opción: ");
            op = sc.nextInt();
            
            switch (op){
                case 1:
                    // Para prendas
                    System.out.println("\n");
                    System.out.print("\t¿Cuántos prendas quieres agregar? ");
                    int agregarPrendas = sc.nextInt();
                    prenda.agregarItem(agregarPrendas);
                    closet.agregarItem(agregarPrendas);
                    
                    // Para accesorios
                    System.out.println("\n");
                    System.out.print("\t¿Cuántos accesorios quieres agregar? ");
                    int agregarAcc = sc.nextInt();
                    accesorio.agregarItem(agregarAcc);
                    closet.agregarItem(agregarAcc);
                    
                    break;
                    
                case 2:
                    System.out.println("\n");
                    System.out.print("\t¿Cuántos items quieres quitar? ");
                    int quitar = sc.nextInt();
                    closet.quitarItem(quitar);
                    break;
                    
                case 3:
                    closet.mostrarContenido();
                    break;
                    
                case 4:
                    System.out.println("\nCerrando clóset");
                    break;
                    
                default:
                    System.out.println("\nOpción no válida");
                continue;
            }
        } while (op != 4);
        
        sc.close();
    }
}
