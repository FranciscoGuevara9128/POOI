package run;

import models.Estudiante;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuántos estudiantes deseas registrar?: ");
        int cantidad = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        Estudiante[] estudiantes = new Estudiante[cantidad];

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\n=== Registro del estudiante " + (i + 1) + " ===");

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Edad: ");
            int edad = sc.nextInt();

            System.out.print("¿Cuántas notas va a ingresar?: ");
            int numNotas = sc.nextInt();
            double[] notas = new double[numNotas];

            for (int j = 0; j < numNotas; j++) {
                double nota;
                do {
                    System.out.print("Nota " + (j + 1) + " (0 - 10): ");
                    nota = sc.nextDouble();
                    if (nota < 0 || nota > 10) {
                        System.out.println("⚠️ Error: la nota debe estar entre 0 y 10. Intente de nuevo.");
                    }
                } while (nota < 0 || nota > 10);
                notas[j] = nota;
            }
            sc.nextLine(); // limpiar buffer

            estudiantes[i] = new Estudiante(nombre, edad, notas);
        }

        System.out.println("\n=== Lista de Estudiantes ===");
        for (Estudiante e : estudiantes) {
            e.mostrarDatos();
        }

        System.out.println("\n=== Estudiantes con promedio mayor a 8.0 ===");
        for (Estudiante e : estudiantes) {
            if (e.getPromedio() > 8.0) {
                e.mostrarDatos();
            }
        }

        sc.close();
    }
}



