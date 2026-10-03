import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GestorInventario gestor = new GestorInventario();
    public static void main(String[] args) {
        precargarDatos();
        int opcion = 0;

        do {
            mostrarMenu();
            opcion = leerEnteroPositivo("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    registrarNuevoEquipo();
                    break;
                case 2:
                    cotizarAlquiler();
                    break;
                case 3:
                    confirmarAlquiler();
                    break;
                case 4:
                    devolverEquipo();
                    break;
                case 5:
                    gestor.generarReporte();
                    break;
                case 6:
                    System.out.println("¡Gracias por utilizar el sistema EnEscena!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 6);
    }

    private static void precargarDatos() {
        // Mínimo 2 equipos iniciales por categoría
        gestor.registrarEquipo(new Proyector(101, "Epson", "PowerLite E20", 150.0, true, 3400, true));
        gestor.registrarEquipo(new Proyector(102, "Sony", "VPL-CWZ10", 200.0, true, 5000, false));
        
        gestor.registrarEquipo(new CamaraVideo(201, "Canon", "XA40 4K", 250.0, true, 2160));
        gestor.registrarEquipo(new CamaraVideo(202, "Sony", "Handycam HDR", 120.0, true, 1080));

        gestor.registrarEquipo(new EquipoSonido(301, "JBL", "EON715", 180.0, true, 1.3));
        gestor.registrarEquipo(new EquipoSonido(302, "Bose", "F1 Model 812", 300.0, true, 1.0));
    }

    private static void mostrarMenu() {
        System.out.println("\n----- SISTEMA DE GESTIÓN ENESCENA -----");
        System.out.println("1. Registrar nuevo equipo");
        System.out.println("2. Cotizar alquiler");
        System.out.println("3. Confirmar alquiler");
        System.out.println("4. Devolver equipo");
        System.out.println("5. Ver reporte general");
        System.out.println("6. Salir");
    }

    private static void registrarNuevoEquipo() {
        System.out.println("\n--- Registrar Nuevo Equipo ---");
        System.out.println("1. Proyector | 2. Cámara de Video | 3. Equipo de Sonido");
        int tipo = leerEnteroPositivo("Seleccione el tipo de equipo: ");

        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo no válido.");
            return;
        }

        int codigo = leerEnteroPositivo("Ingrese el código de inventario: ");
        if (gestor.buscarPorCodigo(codigo) != null) {
            System.out.println("Error: El código ya existe en el inventario.");
            return;
        }

        System.out.print("Ingrese marca: ");
        String marca = scanner.nextLine().trim();
        System.out.print("Ingrese modelo: ");
        String modelo = scanner.nextLine().trim();
        double tarifa = leerDoublePositivo("Ingrese tarifa diaria (Q): ");

        switch (tipo) {
            case 1:
                int lumenes = leerEnteroPositivo("Ingrese lúmenes: ");
                System.out.print("¿Es inalámbrico? (s/n): ");
                boolean inalambrico = scanner.nextLine().trim().equalsIgnoreCase("s");
                gestor.registrarEquipo(new Proyector(codigo, marca, modelo, tarifa, true, lumenes, inalambrico));
                break;
            case 2:
                int res = leerEnteroPositivo("Ingrese resolución vertical (píxeles, ej: 1080, 2160): ");
                gestor.registrarEquipo(new CamaraVideo(codigo, marca, modelo, tarifa, true, res));
                break;
            case 3:
                double potencia = leerDoublePositivo("Ingrese potencia nominal en kW: ");
                gestor.registrarEquipo(new EquipoSonido(codigo, marca, modelo, tarifa, true, potencia));
                break;
        }
        System.out.println("¡Equipo registrado exitosamente!");
    }

    private static void cotizarAlquiler() {
        System.out.println("\n--- Cotizar Alquiler ---");
        int codigo = leerEnteroPositivo("Ingrese código del equipo: ");
        Equipo eq = gestor.buscarPorCodigo(codigo);

        if (eq == null) {
            System.out.println("Error: Equipo no encontrado.");
            return;
        }

        int dias = leerEnteroPositivo("Ingrese cantidad de días: ");
        double costo = gestor.cotizar(codigo, dias);
        System.out.printf("Cotización para %s %s (Código %d) por %d días: Q%.2f\n",
                eq.getMarca(), eq.getModelo(), eq.getCodigo(), dias, costo);
    }

    private static void confirmarAlquiler() {
        System.out.println("\n--- Confirmar Alquiler ---");
        int codigo = leerEnteroPositivo("Ingrese código del equipo: ");
        Equipo eq = gestor.buscarPorCodigo(codigo);

        if (eq == null) {
            System.out.println("Error: Equipo no encontrado.");
            return;
        }

        if (!eq.isDisponible()) {
            System.out.println("Error: El equipo se encuentra alquilado actualmente.");
            return;
        }

        int dias = leerEnteroPositivo("Ingrese cantidad de días: ");
        double costo = gestor.cotizar(codigo, dias);
        System.out.printf("Costo total a cobrar: Q%.2f\n", costo);
        System.out.print("¿Desea confirmar el alquiler? (s/n): ");
        String resp = scanner.nextLine().trim();

        if (resp.equalsIgnoreCase("s")) {
            if (gestor.confirmarAlquiler(codigo, dias)) {
                System.out.println("¡Alquiler confirmado con éxito!");
            } else {
                System.out.println("No se pudo procesar el alquiler.");
            }
        } else {
            System.out.println("Alquiler cancelado por el cliente. Ningún monto fue cobrado.");
        }
    }

    private static void devolverEquipo() {
        System.out.println("\n--- Devolución de Equipo ---");
        int codigo = leerEnteroPositivo("Ingrese código del equipo a devolver: ");
        
        if (gestor.devolverEquipo(codigo)) {
            System.out.println("¡Equipo devuelto con éxito! Ahora está disponible.");
        } else {
            System.out.println("Error: Código no existe o el equipo ya estaba disponible.");
        }
    }

    // Validación y captura segura contra entradas inválidas
    private static int leerEnteroPositivo(String mensaje) {
        int valor = -1;
        while (valor <= 0) {
            System.out.print(mensaje);
            try {
                valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor <= 0) System.out.println("Error: Debe ingresar un entero mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número entero válido.");
            }
        }
        return valor;
    }

    private static double leerDoublePositivo(String mensaje) {
        double valor = -1.0;
        while (valor <= 0) {
            System.out.print(mensaje);
            try {
                valor = Double.parseDouble(scanner.nextLine().trim());
                if (valor <= 0) System.out.println("Error: Debe ingresar un valor mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        }
        return valor;
    }
}
