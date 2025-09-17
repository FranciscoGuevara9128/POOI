package models;

import models.Enumeraciones.Nivel;
import models.Enumeraciones.Modalidad;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private final List<Curso> cursos = new ArrayList<>();

    // --- Acceso básico ---
    public List<Curso> getCursos() {
        return cursos;
    }

    // --- Operaciones sobre el catálogo ---
    public void agregarCurso(Curso curso) {
        if (curso == null) return;
        cursos.add(curso);
    }

    // --- Listado con todos los campos ---
    public void listarCursos() {
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos disponibles.");
            return;
        }

        for (int i = 0; i < cursos.size(); i++) {
            Curso c = cursos.get(i);
            System.out.println((i + 1) + ". " + safe(c.getTitulo()));
            System.out.println("   Descripción: " + safe(c.getDescripcion()));
            System.out.println("   Nivel: " + prettyNivel(c.getNivel()));
            System.out.println("   Duración: " + c.getDuracion() + " horas");
            System.out.printf ("   Precio: $%.2f%n", c.getPrecio());
            System.out.println("   Modalidad: " + prettyModalidad(c.getModalidad()));
            if (c.getModalidad() == Modalidad.PRESENCIAL) {
                System.out.println("   Ubicación: " + safe(c.getUbicacion()));
            } else {
                System.out.println("   Enlace: " + safe(c.getEnlace()));
            }
            // Opcional: promedio de calificaciones
            double promedio = c.calcularPromedioCalificaciones();
            if (promedio > 0) {
                System.out.printf("   Promedio calificaciones: %.2f%n", promedio);
            }
            System.out.println();
        }
    }

    // --- Helpers de presentación ---
    private String prettyNivel(Nivel n) {
        if (n == null) return "(sin nivel)";
        return switch (n) {
            case BASICO -> "BÁSICO";
            case INTERMEDIO -> "INTERMEDIO";
            case AVANZADO -> "AVANZADO";
        };
    }

    private String prettyModalidad(Modalidad m) {
        if (m == null) return "(sin modalidad)";
        return switch (m) {
            case PRESENCIAL -> "Presencial";
            case VIRTUAL -> "Virtual";
        };
    }

    private String safe(String s) {
        return (s == null || s.isBlank()) ? "(no especificado)" : s;
    }
}
