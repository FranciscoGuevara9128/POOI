package models;

import models.Enumeraciones.Modalidad;
import models.Enumeraciones.Nivel;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private int id;
    private String titulo;
    private String descripcion;
    private Nivel nivel;
    private int duracion; // en horas
    private double precio;
    private Modalidad modalidad;
    private String ubicacion; // solo para PRESENCIAL
    private String enlace;    // solo para VIRTUAL

    private final List<Calificacion> listaCalificaciones;
    private final List<CursoGuardado> listaGuardados;

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

    public double calcularPromedioCalificaciones() {
        if (listaCalificaciones.isEmpty()) return 0.0;
        double suma = 0.0;
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

    // Getters y Setters necesarios

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Nivel getNivel() { return nivel; }
    public void setNivel(Nivel nivel) { this.nivel = nivel; }

    public int getDuracion() { return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public Modalidad getModalidad() { return modalidad; }
    public void setModalidad(Modalidad modalidad) { this.modalidad = modalidad; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getEnlace() { return enlace; }
    public void setEnlace(String enlace) { this.enlace = enlace; }

    public List<Calificacion> getListaCalificaciones() { return listaCalificaciones; }
    public List<CursoGuardado> getListaGuardados() { return listaGuardados; }
}
