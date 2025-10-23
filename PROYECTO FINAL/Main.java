import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Se inicializa el Scanner para leer la entrada del usuario
        Scanner sc = new Scanner(System.in);
        // El clóset se crea vacío, sin ningún ítem de ejemplo
        Closet miCloset = new Closet("Anakaren");

        int op;
        do {
            System.out.println("\n\n===== MENÚ DEL CLÓSET =====");
            System.out.println("1. Agregar Prenda");
            System.out.println("2. Agregar Accesorio");
            System.out.println("3. Ver contenido del clóset");
            System.out.println("4. Salir");
            System.out.print("\nSeleccione una opción: ");
            
            // Se valida que el usuario ingrese un número
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                op = 0; // Se asigna una opción inválida para que muestre el mensaje de error
            }

            switch (op) {
                case 1:
                    // --- SUBMENÚ PARA AGREGAR PRENDA ---
                    System.out.println("\n--- Agregar Prenda ---");
                    System.out.println("a) Camisa Casual");
                    System.out.println("b) Camisa Formal");
                    System.out.println("c) Pantalón de Mezclilla");
                    System.out.println("d) Pantalón de Vestir");
                    System.out.print("¿Qué prenda quieres agregar?: ");
                    String tipoPrenda = sc.nextLine().toLowerCase(); // Se convierte a minúsculas

                    // Variables comunes para todas las prendas
                    String color, talla, tipoCorte, tipoMangas, tipoCuello;
                    
                    switch(tipoPrenda) {
                        case "a": // Agregar Camisa Casual
                            System.out.print("-> Color: ");
                            color = sc.nextLine();
                            System.out.print("-> Talla (chica, mediana, grande): ");
                            talla = sc.nextLine();
                            System.out.print("-> Tipo de Mangas (corta, larga): ");
                            tipoMangas = sc.nextLine();
                            System.out.print("-> Tipo de Cuello (polo, redondo): ");
                            tipoCuello = sc.nextLine();
                            System.out.print("-> Estampado: ");
                            String estampado = sc.nextLine();
                            miCloset.agregarItem(new CamisaCasual(color, talla, tipoMangas, tipoCuello, estampado));
                            break;
                            
                        case "b": // Agregar Camisa Formal
                            System.out.print("-> Color: ");
                            color = sc.nextLine();
                            System.out.print("-> Talla (chica, mediana, grande): ");
                            talla = sc.nextLine();
                            System.out.print("-> Tipo de Mangas (larga): ");
                            tipoMangas = sc.nextLine();
                            System.out.print("-> Tipo de Cuello (italiano, inglés): ");
                            tipoCuello = sc.nextLine();
                            System.out.print("-> ¿Se usa con corbata? (s/n): ");
                            boolean llevaCorbata = sc.nextLine().equalsIgnoreCase("s");
                            System.out.print("-> ¿Admite mancuernillas? (s/n): ");
                            boolean llevaMancuernillas = sc.nextLine().equalsIgnoreCase("s");
                            miCloset.agregarItem(new CamisaFormal(color, talla, tipoMangas, tipoCuello, llevaCorbata, llevaMancuernillas));
                            break;

                        case "c": // Agregar Pantalón de Mezclilla
                             System.out.print("-> Color: ");
                            color = sc.nextLine();
                            System.out.print("-> Talla (chica, mediana, grande): ");
                            talla = sc.nextLine();
                            System.out.print("-> Tipo de Corte (recto, skinny, acampanado): ");
                            tipoCorte = sc.nextLine();
                            System.out.print("-> Número de bolsillos: ");
                            int numBolsillos = Integer.parseInt(sc.nextLine());
                            System.out.print("-> ¿Tiene decorado/rasgaduras? (s/n): ");
                            boolean decorado = sc.nextLine().equalsIgnoreCase("s");
                            miCloset.agregarItem(new PantalonMezclilla(color, talla, tipoCorte, numBolsillos, decorado));
                            break;

                        case "d": // Agregar Pantalón de Vestir
                            System.out.print("-> Color: ");
                            color = sc.nextLine();
                            System.out.print("-> Talla (chica, mediana, grande): ");
                            talla = sc.nextLine();
                            System.out.print("-> Tipo de Corte (recto, slim): ");
                            tipoCorte = sc.nextLine();
                            System.out.print("-> Ocasión de uso (oficina, formal, gala): ");
                            String ocasion = sc.nextLine();
                            miCloset.agregarItem(new PantalonVestir(color, talla, tipoCorte, ocasion));
                            break;

                        default:
                            System.out.println("Opción no válida :(");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("\n--- Agregar Accesorio ---");
                    // Aquí se podría crear un submenú similar para agregar diferentes accesorios
                    System.out.print("-> Tipo de bolsa (Tote, Crossbody): ");
                    String tipoBolsa = sc.nextLine();
                    System.out.print("-> Color: ");
                    String colorBolsa = sc.nextLine();
                    System.out.print("-> Tamaño (chico, mediano, grande): ");
                    String tamanio = sc.nextLine();
                    System.out.print("-> ¿Es bolsa de mano? (s/n): ");
                    boolean esDeMano = sc.nextLine().equalsIgnoreCase("s");
                    miCloset.agregarItem(new Bolsa(tipoBolsa, colorBolsa, tamanio, esDeMano));
                    break;

                case 3:
                    miCloset.mostrarContenido();
                    break;

                case 4:
                    System.out.println("\nCerrando clóset... ¡Adiós! :)");
                    break;

                default:
                    System.out.println("\nOpción no válida :(");
                    break;
            }
        } while (op != 4);

        sc.close();
    }
}