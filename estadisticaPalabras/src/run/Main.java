package run;

import model.EstTexto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("¿Cuántas palabras deseas ingresar? ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        String[] palabras = new String[cantidad];

        for (int i = 0; i < cantidad; i++) {
            System.out.print("Palabra " + (i + 1) + ": ");
            palabras[i] = scanner.nextLine().trim();
        }

        EstTexto estadistica = new EstTexto(palabras);

        System.out.println("\nCantidad total de palabras: " + estadistica.contarPalabras());
        System.out.println("Palabras únicas: " + estadistica.palabrasUnicas());
        System.out.println("Palabra más larga: " + estadistica.palabraMasLarga());
        System.out.println("Palabra más corta: " + estadistica.palabraMasCorta());

        scanner.close();
    }
}
