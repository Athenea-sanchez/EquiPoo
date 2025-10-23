/*
Clase padre 
@author: athenea sanchez torres 
@version: 1.8
@since 21-10-25
*/

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

//Super clase 
public class Galaxia {
    // Atributos 
    protected String nombre; // Nuevo atributo para nombre de la galaxia
    protected double tamaño; 
    protected double edad; 
    protected double lejaniaRespectoTierra;  
    protected double fuerzaAtraccion; 
    protected boolean AgujeroNegro;
    protected Map<String, Astros> astrosMap; // Mapa para almacenar astros por nombre
    Scanner sc = new Scanner(System.in);  
   
    // Constructor 
    /* constructor de Galaxia que inicializa todos los atributos
     * @param nombre= nombre de la galaxia
     * @param tamaño= tamaño de la galaxia en años luz
     * @param edad= edad de la galaxia en millones de años
     * @param lejaniaRespectoTierra= distancia desde la Tierra en años luz
     * @param fuerzaAtraccion= fuerza gravitacional de la galaxia
     * @param AgujeroNegro= indica si la galaxia tiene agujero negro
     * SOBRECARGA: Este constructor tiene 6 parámetros
     */
    public Galaxia(String nombre, double tamaño, double edad, double lejaniaRespectoTierra, double fuerzaAtraccion, boolean AgujeroNegro) {
        this.nombre = nombre;
        this.tamaño = tamaño; 
        this.edad = edad; 
        this.lejaniaRespectoTierra = lejaniaRespectoTierra;
        this.fuerzaAtraccion = fuerzaAtraccion; 
        this.AgujeroNegro = AgujeroNegro;
        this.astrosMap = new HashMap<>(); // Inicializar el mapa
    }

    // Constructor vacío para herencia
    /* constructor por defecto para facilitar la herencia
     * inicializa los atributos con valores por defecto
     */
    public Galaxia() {
        this.nombre = "Sin nombre";
        this.tamaño = 0;
        this.edad = 0;
        this.lejaniaRespectoTierra = 0;
        this.fuerzaAtraccion = 0;
        this.AgujeroNegro = false;
        this.astrosMap = new HashMap<>(); // Inicializar el mapa
    }

    // Métodos 

    /* formarAstros: solo pide nombres
     * @param numeroAstrosACrear= recibe el numero de astros que contiene galaxia
     * SOBRECARGA: Este método solo crea nombres básicos
     */
    public void formarAstros(int numeroAstrosACrear) { 
        System.out.println("Formando astros básicos en la galaxia " + this.nombre + "...");
        String[] galaxy = new String[numeroAstrosACrear]; 

        for (int i = 0; i < numeroAstrosACrear; i++) {  
            System.out.println("Ingresa el nombre del astro " + (i + 1) + ": "); 
            galaxy[i] = sc.nextLine();
        }
        
        System.out.println("Astros creados en " + this.nombre + ":");
        for (int i = 0; i < numeroAstrosACrear; i++) {
            System.out.println("Astro " + (i + 1) + ": " + galaxy[i]);
        }
    }

    /* formarAstros: crea objetos Astros completos
     * @param numeroAstrosACrear= número de astros a crear
     * SOBRECARGA: Este método crea objetos Astros completos con todos los datos
     * y los almacena en el mapa por nombre
     */
    public void formarAstros(int numeroAstrosACrear, boolean crearCompletos) { 
        System.out.println("Formando astros completos en la galaxia " + this.nombre + "...");
        
        for (int i = 0; i < numeroAstrosACrear; i++) {  
            System.out.println("\n Creando Astro " + (i + 1) + " ");
            
            System.out.println("Ingresa el nombre del astro: ");
            String nombreAstro = sc.nextLine();
            
            System.out.println("Ingresa el período de rotación en horas: ");
            double periodoRotacion = sc.nextDouble();
            
            System.out.println("Ingresa el radio del astro en kilómetros: ");
            double radio = sc.nextDouble();
            
            System.out.println("Ingresa la masa del astro en kilogramos (ej: 5.972e24): ");
            double masa = sc.nextDouble();
            sc.nextLine();  // Limpiar buffer
            
            System.out.println("Ingresa el campo magnético en Teslas (ej: 0.00005): ");
            double campoMagnetico = sc.nextDouble();
            sc.nextLine();  // Limpiar buffer
            
            System.out.println("Ingresa el tipo de superficie (ej: sólida, gaseosa, rocosa): ");
            String superficie = sc.nextLine();
            
            System.out.println("Ingresa la forma del astro (ej: esférica, elíptica, irregular): ");
            String forma = sc.nextLine();
            
            // Crear el astro con los datos ingresados
            Astros nuevoAstro = new Astros(nombreAstro, periodoRotacion, radio, masa, 
                                         campoMagnetico, superficie, forma,
                                         this.tamaño, this.edad, 
                                         this.lejaniaRespectoTierra, 
                                         this.fuerzaAtraccion, 
                                         this.AgujeroNegro);
            
            // Almacenar en el mapa usando el nombre como clave
            astrosMap.put(nombreAstro, nuevoAstro);
            System.out.println("Astro '" + nombreAstro + "' creado y almacenado exitosamente!");
        }
        
        System.out.println("\nTotal de astros creados en " + this.nombre + ": " + astrosMap.size());
    }
    
    /* mostrarInfoGalaxia: muestra toda la información de la galaxia
     * incluye nombre y todos los atributos
     */
    public void mostrarInfoGalaxia() {
        System.out.println("\n INFORMACIÓN DE LA GALAXIA ");
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Tamaño: " + this.tamaño + " años luz");
        System.out.println("Edad: " + this.edad + " millones de años");
        System.out.println("Lejanía respecto a Tierra: " + this.lejaniaRespectoTierra + " años luz");
        System.out.println("Fuerza de atracción: " + this.fuerzaAtraccion);
        System.out.print("¿Tiene agujero negro?: ");
        if (this.AgujeroNegro) {
            System.out.println("Sí");
        } else {
            System.out.println("No");
        }
        System.out.println("Cantidad de astros: " + this.astrosMap.size());
    }
    
    /* mostrarAstros: muestra todos los astros almacenados en el mapa
     * SOBRESCRITURA: Este método podría ser sobrescrito en clases hijas
     * para mostrar información específica de diferentes tipos de galaxias
     */
    public void mostrarAstros() {
        System.out.println("\n ASTROS EN LA GALAXIA " + this.nombre.toUpperCase() + " ");
        if (astrosMap.isEmpty()) {
            System.out.println("No hay astros creados en esta galaxia.");
        } else {
            int contador = 1;
            for (String nombre : astrosMap.keySet()) {
                System.out.println(contador + ". " + nombre);
                contador++;
            }
        }
    }
    
    /* accederAstro: permite acceder a un astro específico por nombre
     * @param nombre= nombre del astro a buscar
     * @return el objeto Astros si existe, null si no existe
     */
    public Astros accederAstro(String nombre) {
        if (astrosMap.containsKey(nombre)) {
            return astrosMap.get(nombre);
        } else {
            System.out.println("No se encontró el astro '" + nombre + "'");
            return null;
        }
    }
    
    /* emiteLuzYEnergia: determina si emite luz y energia 
     * @param emite= valor booleano que indica si la galaxia emite luz y energia
     * SOBRESCRITURA: Este método podría ser sobrescrito en clases hijas
     * para comportamiento específico de diferentes tipos de galaxias
     */
    public void emiteLuzYEnergia(boolean emite) {
        if (!emite) {
            System.out.println("No emite luz ni energía"); 
        } else {
            System.out.println("Emite luz y energía"); 
        }
    }
    
    // Clase hija = public class Nombre extends Superclase 
    /* Clase Astros que hereda de Galaxia
     * representa los astros individuales dentro de una galaxia
     * contiene atributos y metodos especificos de cuerpos celestes
     * SOBRESCRITURA: Esta clase sobrescribe algunos métodos de Galaxia
     */
    public static class Astros extends Galaxia {
        // ... (la clase Astros se mantiene igual que en tu código anterior)
        // Solo agregaré el constructor que falta
        private static final int SEGUNDOS_POR_HORA = 3600;
        protected double campoMagnetico;
        protected double periodoRotacionH; 
        protected double radio; 
        protected String superficieFisica; 
        protected String forma; 
        protected double masa; 
        protected String nombre;

  // Constructor completo
/* constructor completo que inicializa todos los atributos del astro
 * usa un nombre por defecto para la galaxia padre
 */
public Astros(String nombre, double periodoRotacionH, double radio, double masa,
              double campoMagnetico, String superficieFisica, String forma,
              double tamaño, double edad, double lejania, double fuerzaAtraccion, boolean agujeroNegro) {
    super("Galaxia de " + nombre, tamaño, edad, lejania, fuerzaAtraccion, agujeroNegro);
    this.nombre = nombre;
    this.periodoRotacionH = periodoRotacionH;
    this.radio = radio;
    this.masa = masa;
    this.campoMagnetico = campoMagnetico;
    this.superficieFisica = superficieFisica;
    this.forma = forma;
}

        // ... (todos los demás métodos de Astros se mantienen igual)
        public double getPeriodoSegundos() {
            return periodoRotacionH * SEGUNDOS_POR_HORA;
        }

        public double calcularVelocidadAngular() {
            double periodo = getPeriodoSegundos();
            if (periodo == 0) {
                throw new ArithmeticException("El período de rotación no puede ser cero");
            }
            return (2 * Math.PI) / periodo;
        }

        public double calcularVelocidadLinealKmH() {
            double circunferencia = 2 * Math.PI * radio;
            return circunferencia / periodoRotacionH;
        }

        public double calcularVelocidadLinealMs() {
            double radioMetros = radio * 1000;
            double velocidadAngular = calcularVelocidadAngular();
            return velocidadAngular * radioMetros;
        }

        public double calcularMomentoAngular() {
            double radioMetros = radio * 1000;
            double momentoInercia = (2.0/5.0) * masa * Math.pow(radioMetros, 2);
            return momentoInercia * calcularVelocidadAngular();
        }

        public void mostrarDatosRotacion() {
            System.out.println("\n=== DATOS DE ROTACIÓN: " + this.nombre + " ===");
            System.out.println("Período de rotación: " + periodoRotacionH + " horas");
            System.out.println("Radio: " + radio + " km");
            System.out.println("Masa: " + masa + " kg");
            System.out.println("Campo magnético: " + campoMagnetico + " T");
            System.out.println("Superficie: " + superficieFisica);
            System.out.println("Forma: " + forma);
            System.out.println("Velocidad angular: " + calcularVelocidadAngular()+ " rad/s");
            System.out.println("Velocidad lineal (ecuador): " +calcularVelocidadLinealKmH()+ " km/h");
            System.out.println("Velocidad lineal (ecuador): " + calcularVelocidadLinealMs()+ " m/s");
            System.out.println("Momento angular: " + calcularMomentoAngular()+ " kg·m²/s");
        }

        public void mostrarInfoBasica() {
            System.out.println("Astro: " + nombre + " | Radio: " + radio + " km | Masa: " + masa + " kg");
        }
    }
    
    // Método main para probar la clase
    /* metodo principal que ejecuta el programa
     * crea una galaxia, solicita datos al usuario y muestra la informacion
     * permite crear astros adicionales si el usuario lo desea
     * @param args= argumentos de linea de comandos
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  
        
        System.out.println(" Juguemos a ser dios ");
        
        // Crear la galaxia pidiendo datos al usuario
        System.out.println("\n      CREACION DE TU GALAXIA               ");
        System.out.println("Ingresa el nombre de tu galaxia: ");
        String nombreGalaxia = sc.nextLine();
        
        System.out.println("Ingresa el tamaño de tu galaxia en años luz: ");
        double tamaño = sc.nextDouble(); 

        System.out.println("Ingresa la edad de tu galaxia en millones de años: ");
        double edad = sc.nextDouble(); 

        System.out.println("Ingresa la lejanía respecto a la Tierra en años luz: ");
        double lejania = sc.nextDouble();

        System.out.println("Ingresa la fuerza de atracción: ");
        double fuerzaAtraccion = sc.nextDouble(); 

        System.out.println("¿Tu galaxia tiene un agujero negro? (Si=1/No=2): ");
        int valor = sc.nextInt(); 
        sc.nextLine();  // Limpiar buffer

        boolean tieneAgujeroNegro = false;
        if(valor == 1) {
            tieneAgujeroNegro = true;
        } else if (valor == 2){
            tieneAgujeroNegro = false;
        } else{
            System.out.println("Valor no válido, se establecerá como 'No'"); 
        }

        // Crear la galaxia con los datos ingresados
        Galaxia miGalaxia = new Galaxia(nombreGalaxia, tamaño, edad, lejania, fuerzaAtraccion, tieneAgujeroNegro);
        
        // Mostrar información de la galaxia creada
        System.out.println("\n ¡GALAXIA CREADA EXITOSAMENTE! ");
        miGalaxia.mostrarInfoGalaxia();
        
        // Preguntar sobre emisión de luz y energía
        System.out.println("\n    EMISION DE LUZ Y ENERGÍA    ");
        miGalaxia.emiteLuzYEnergia(tieneAgujeroNegro);  

        // Crear astros
        System.out.println("\n    --- CREACION DE ASTROS ---");
        System.out.println("¿Quieres crear astros básicos (1) o astros completos (2)? ");
        int tipoAstros = sc.nextInt();
        sc.nextLine();  // Limpiar buffer

        if (tipoAstros == 1) {
            System.out.println("¿Cuántos astros básicos quieres crear?");
            int cantidad = sc.nextInt();
            sc.nextLine();  // Limpiar buffer
            miGalaxia.formarAstros(cantidad);
        } else if (tipoAstros == 2) {
            System.out.println("¿Cuántos astros completos quieres crear?");
            int cantidad = sc.nextInt();
            sc.nextLine();  // Limpiar buffer
            miGalaxia.formarAstros(cantidad, true);
        } else {
            System.out.println("Opción no válida, no se crearán astros.");
        }

        // Mostrar astros creados
        miGalaxia.mostrarAstros();

             // Acceder a astros específicos
        System.out.println("\n--- CONSULTA DE ASTROS ---");
        
        // Verificar si hay astros creados
        if (miGalaxia.astrosMap.isEmpty()) {
            System.out.println("No hay astros creados para consultar.");
        } else {
            System.out.println("¿Quieres ver los datos de algún astro específico? (Si/No): ");
            String verAstro = sc.nextLine();
            
            while (verAstro.equalsIgnoreCase("Si") || verAstro.equalsIgnoreCase("Sí")) {
                // Mostrar siempre la lista primero
                System.out.println("\nAstros disponibles:");
                miGalaxia.mostrarAstros();
                
                System.out.println("\nIngresa el nombre del astro que quieres consultar: ");
                String nombreAstro = sc.nextLine();
                
                Astros astro = miGalaxia.accederAstro(nombreAstro);
                if (astro != null) {
                    astro.mostrarDatosRotacion();
                } else {
                    System.out.println(" El astro '" + nombreAstro + "' no existe. Verifica el nombre en la lista anterior.");
                }
                
                System.out.println("\n¿Quieres consultar otro astro? (Si/No): ");
                verAstro = sc.nextLine();
            }
        }

        System.out.println("\n byyeeee ");
        System.out.println("Juguemos  a ser dios mas tarde");
        sc.close();
    }
}
