package models;

import java.util.Arrays;

public class Estudiante {
    private String nombre;
    private int edad;
    private double[] notas;

    public Estudiante(String nombre, int edad, double[] notas) {
        this.nombre = nombre;
        this.edad = edad;
        this.notas = notas;
    }

    // Método para calcular el promedio
    public double calcularPromedio() {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return (notas.length > 0) ? suma / notas.length : 0;
    }

    // Método para mostrar datos
    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Notas: " + Arrays.toString(notas));
        System.out.println("Promedio: " + calcularPromedio());
        System.out.println("-----------------------------");
    }

    // Getter del promedio (para filtrar en Main)
    public double getPromedio() {
        return calcularPromedio();
    }
}

