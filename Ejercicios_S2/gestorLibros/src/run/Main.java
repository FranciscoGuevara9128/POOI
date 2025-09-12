package run;

import models.Libro;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuántos libros deseas registrar?: ");
        int cantidad = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        Libro[] libros = new Libro[cantidad];

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\n=== Registro del libro " + (i + 1) + " ===");

            System.out.print("Título: ");
            String titulo = sc.nextLine();

            System.out.print("Autor: ");
            String autor = sc.nextLine();

            int anio;
            do {
                System.out.print("Año de publicación: ");
                anio = sc.nextInt();
                if (anio < 0 || anio > 2025) {
                    System.out.println("⚠️ Error: el año debe estar entre 0 y 2025. Intente de nuevo.");
                }
            } while (anio < 0 || anio > 2025);
            sc.nextLine(); // limpiar buffer

            libros[i] = new Libro(titulo, autor, anio);
        }

        // Mostrar todos los libros
        System.out.println("\n=== Lista de Libros Registrados ===");
        for (Libro l : libros) {
            l.mostrarDatos();
        }

        // Buscar por autor o palabra clave
        System.out.print("\n🔎 Buscar libros por autor o palabra clave en el título: ");
        String busqueda = sc.nextLine().toLowerCase();

        boolean encontrado = false;
        for (Libro l : libros) {
            if (l.getAutor().toLowerCase().contains(busqueda) ||
                    l.getTitulo().toLowerCase().contains(busqueda)) {
                l.mostrarDatos();
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron libros con ese criterio de búsqueda.");
        }

        // Mostrar libros publicados después de 2010
        System.out.println("\n=== Libros publicados después de 2010 ===");
        boolean hayRecientes = false;
        for (Libro l : libros) {
            if (l.getAnio() > 2010) {
                l.mostrarDatos();
                hayRecientes = true;
            }
        }
        if (!hayRecientes) {
            System.out.println("No hay libros publicados después de 2010.");
        }

        sc.close();
    }
}

