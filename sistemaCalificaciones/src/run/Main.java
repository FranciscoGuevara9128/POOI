package run;

import model.Curso;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nombre del curso: ");
        String nombreCurso = scanner.nextLine();

        int numEstudiantes;
        while (true) {
            System.out.print("Número de estudiantes: ");
            if (scanner.hasNextInt()) {
                numEstudiantes = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer
                break;
            } else {
                System.out.println("Por favor ingresa un número entero válido.");
                scanner.nextLine(); // limpiar entrada incorrecta
            }
        }

        int numEvaluaciones;
        while (true) {
            System.out.print("Número de evaluaciones: ");
            if (scanner.hasNextInt()) {
                numEvaluaciones = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer
                break;
            } else {
                System.out.println("Por favor ingresa un número entero válido.");
                scanner.nextLine(); // limpiar entrada incorrecta
            }
        }

        Curso curso = new Curso(nombreCurso, numEstudiantes, numEvaluaciones);

        // Ingreso de notas
        for (int i = 0; i < numEstudiantes; i++) {
            System.out.println("Notas para el estudiante " + (i + 1) + ":");
            for (int j = 0; j < numEvaluaciones; j++) {
                double nota;
                while (true) {
                    System.out.print("  Evaluación " + (j + 1) + ": ");
                    if (scanner.hasNextDouble()) {
                        nota = scanner.nextDouble();
                        scanner.nextLine(); // limpiar buffer
                        break;
                    } else {
                        System.out.println("Por favor ingresa una nota válida (número decimal).");
                        scanner.nextLine(); // limpiar entrada incorrecta
                    }
                }
                curso.setNota(i, j, nota);
            }
        }

        // Mostrar estadísticas
        System.out.println("\n--- Estadísticas del Curso: " + curso.getNombre() + " ---");

        System.out.println("\nPromedio por estudiante:");
        for (int i = 0; i < curso.getNumEstudiantes(); i++) {
            System.out.printf("Estudiante %d: %.2f%n", i + 1, curso.promedioEstudiante(i));
        }

        System.out.println("\nPromedio por evaluación:");
        for (int j = 0; j < curso.getNumEvaluaciones(); j++) {
            System.out.printf("Evaluación %d: %.2f%n", j + 1, curso.promedioEvaluacion(j));
        }

        int mejor = curso.mejorEstudiante();
        System.out.printf("%nEl estudiante con mejor rendimiento es el Estudiante %d con promedio %.2f%n",
                mejor + 1, curso.promedioEstudiante(mejor));

        scanner.close();
    }
}
