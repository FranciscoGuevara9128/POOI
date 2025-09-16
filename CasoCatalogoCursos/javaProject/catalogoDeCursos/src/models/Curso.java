package models;

import models.Enumeraciones.Modalidad;
import models.Enumeraciones.Nivel;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private int id;
    public String titulo;
    public String descripcion;
    public Nivel nivel;
    public int duracion; // en horas
    public double precio;
    public Modalidad modalidad;
    public String ubicacion;
    public String enlace;

    private List<Calificacion> listaCalificaciones;
    private List<CursoGuardado> listaGuardados;

    public Curso(String titulo, String descripcion, Nivel nivel, int duracion, double precio,
                 Modalidad modalidad, String ubicacion, String enlace) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.nivel = nivel;
        this.duracion = duracion;
        this.precio = precio;
        this.modalidad = modalidad;
        this.ubicacion = ubicacion;
        this.enlace = enlace;
        this.listaCalificaciones = new ArrayList<>();
        this.listaGuardados = new ArrayList<>();
    }

    // Operaciones CRUD y funcionales
    public void crearCurso() {}
    public void actualizarCurso() {}
    public void eliminarCurso() {}
    public void mostrarInfo() {
        System.out.println("Curso: " + titulo + " - Nivel: " + nivel + " - Modalidad: " + modalidad);
    }

    public float calcularPromedioCalificaciones() {
        if (listaCalificaciones.isEmpty()) return 0;
        float suma = 0;
        for (Calificacion c : listaCalificaciones) {
            suma += c.getValor();
        }
        return suma / listaCalificaciones.size();
    }

    public void agregarCalificacion(Calificacion calificacion) {
        listaCalificaciones.add(calificacion);
    }

    public void agregarCursoGuardado(CursoGuardado cg) {
        listaGuardados.add(cg);
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }

    public int getNivel() {
        return Nivel.values().length;
    }

    public int getPrecio() {
        return (int) precio;
    }
}
