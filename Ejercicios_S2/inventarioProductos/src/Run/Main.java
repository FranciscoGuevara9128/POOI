package Run;

import Models.Producto;
import Models.Inventario;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Crear inventario con capacidad de 10 productos
        Inventario inventario = new Inventario(10);

        int opcion;
        do {
            System.out.println("\n===== MENÚ INVENTARIO =====");
            System.out.println("1. Agregar producto");
            System.out.println("2. Buscar producto");
            System.out.println("3. Mostrar productos con stock bajo (<5)");
            System.out.println("4. Mostrar todos los productos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre del producto: ");
                    String nombre = sc.nextLine();
                    System.out.print("Ingrese código del producto: ");
                    String codigo = sc.nextLine();
                    System.out.print("Ingrese cantidad: ");
                    int cantidad = sc.nextInt();

                    Producto p = new Producto(nombre, codigo, cantidad);
                    inventario.agregarProducto(p);
                    break;

                case 2:
                    System.out.print("Ingrese código a buscar: ");
                    String codBuscar = sc.nextLine();
                    Producto encontrado = inventario.buscarProducto(codBuscar);
                    if (encontrado != null) {
                        System.out.println("✅ Producto encontrado: " + encontrado);
                    } else {
                        System.out.println("❌ Producto no encontrado.");
                    }
                    break;

                case 3:
                    inventario.mostrarStockBajo();
                    break;

                case 4:
                    inventario.mostrarProductos();
                    break;

                case 0:
                    System.out.println("👋 Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
