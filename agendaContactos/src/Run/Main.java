package Run;

import Models.Contacto;
import Models.Agenda;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Agenda agenda = new Agenda(10); // capacidad para 10 contactos
        int opcion;

        do {
            System.out.println("\n===== MENÚ AGENDA =====");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Buscar contacto por nombre");
            System.out.println("3. Mostrar todos los contactos");
            System.out.println("4. Buscar contactos por dominio de correo");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Ingrese teléfono: ");
                    String telefono = sc.nextLine();
                    System.out.print("Ingrese correo: ");
                    String correo = sc.nextLine();

                    Contacto c = new Contacto(nombre, telefono, correo);
                    agenda.agregarContacto(c);
                    break;

                case 2:
                    System.out.print("Ingrese nombre a buscar: ");
                    String nombreBuscar = sc.nextLine();
                    Contacto encontrado = agenda.buscarPorNombre(nombreBuscar);
                    if (encontrado != null) {
                        System.out.println(" Contacto encontrado: " + encontrado);
                    } else {
                        System.out.println(" Contacto no encontrado.");
                    }
                    break;

                case 3:
                    agenda.mostrarContactos();
                    break;

                case 4:
                    System.out.print("Ingrese dominio (ej: @gmail.com): ");
                    String dominio = sc.nextLine();
                    agenda.buscarPorDominio(dominio);
                    break;

                case 0:
                    System.out.println(" Saliendo de la agenda...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
