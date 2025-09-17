package models;

import models.Enumeraciones.EstadoCursoGuardado;
import models.Enumeraciones.Rol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario {
    private static int contadorId = 0; // contador estático para asignar ids únicos

    private int id;
    private String nombre;
    private String email;
    private String password;
    private Rol rol;

    private List<CursoGuardado> listaGuardados;
    private List<Calificacion> listaCalificaciones;

    // Constructor
    public Usuario(String nombre, String email, String password, Rol rol) {
        this.id = ++contadorId; // asigna id automáticamente
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.listaGuardados = new ArrayList<>();
        this.listaCalificaciones = new ArrayList<>();
    }

    // Operaciones CRUD y funcionales
    public void verPerfil() {
        System.out.println("Usuario: " + nombre + ", Email: " + email + ", Rol: " + rol);
    }

    public void guardarCurso(Curso curso) {
        CursoGuardado guardado = new CursoGuardado(this, curso, new Date(), EstadoCursoGuardado.GUARDADO);
        listaGuardados.add(guardado);
        curso.agregarCursoGuardado(guardado);
    }

    public void calificarCurso(Curso curso, int valor, String comentario) {
        Calificacion cal = new Calificacion(this, curso, valor, comentario, new Date());
        listaCalificaciones.add(cal);
        curso.agregarCalificacion(cal);
    }

    public void verCursosGuardados() {
        for (CursoGuardado cg : listaGuardados) {
            System.out.println(cg.getCurso().getTitulo() + " - Estado: " + cg.getEstado());
        }
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Rol getRol() { return rol; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
}
