package models;

import models.Enumeraciones.EstadoCursoGuardado;
import models.Enumeraciones.Rol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario {
    private int id;
    public String nombre;
    public String email;
    private String password;
    public Rol rol;

    private List<CursoGuardado> listaGuardados;
    private List<Calificacion> listaCalificaciones;

    public Usuario(String nombre, String email, String password, Rol rol) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.listaGuardados = new ArrayList<>();
        this.listaCalificaciones = new ArrayList<>();
    }

    // Operaciones CRUD y funcionales
    public void crearUsuario() {
        // lógica para crear usuario
    }

    public void actualizarUsuario() {
        // lógica para actualizar usuario
    }

    public void eliminarUsuario() {
        // lógica para eliminar usuario
    }

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

    // Getters y Setters (opcional)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}