package model;
//Sistema de Calificaciones
//Objetivo: Clase Curso que tiene un nombre y una matriz de notas (estudiantes x evaluaciones).
//•    Método para promedio por estudiante.
//•    Método para promedio por evaluación.
//•    Estudiante con mejor rendimiento.

public class Curso {
    private String nombre;
    private double[][] notas; // [estudiantes][evaluaciones]

    public Curso(String nombre, int numEstudiantes, int numEvaluaciones) {
        this.nombre = nombre;
        this.notas = new double[numEstudiantes][numEvaluaciones];
    }

    public void setNota(int estudiante, int evaluacion, double nota) {
        notas[estudiante][evaluacion] = nota;
    }

    public double promedioEstudiante(int estudiante) {
        double suma = 0;
        for (double nota : notas[estudiante]) {
            suma += nota;
        }
        return suma / notas[estudiante].length;
    }

    public double promedioEvaluacion(int evaluacion) {
        double suma = 0;
        for (int i = 0; i < notas.length; i++) {
            suma += notas[i][evaluacion];
        }
        return suma / notas.length;
    }

    public int mejorEstudiante() {
        int mejor = 0;
        double mejorPromedio = promedioEstudiante(0);
        for (int i = 1; i < notas.length; i++) {
            double promedio = promedioEstudiante(i);
            if (promedio > mejorPromedio) {
                mejorPromedio = promedio;
                mejor = i;
            }
        }
        return mejor;
    }

    public int getNumEstudiantes() {
        return notas.length;
    }

    public int getNumEvaluaciones() {
        return notas[0].length;
    }

    public String getNombre() {
        return nombre;
    }
}
