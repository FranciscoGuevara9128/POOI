package models;
import java.util.Date;
import models.Enumeraciones.EstadoCursoGuardado;


public class CursoGuardado {
    private int id;
    private Usuario usuario;
    private Curso curso;
    public Date fechaGuardado;
    public EstadoCursoGuardado estado;

    public CursoGuardado(Usuario usuario, Curso curso, Date fechaGuardado, EstadoCursoGuardado estado) {
        this.usuario = usuario;
        this.curso = curso;
        this.fechaGuardado = fechaGuardado;
        this.estado = estado;
    }

    // Operaciones
    public void marcarComoEnProgreso() { estado = EstadoCursoGuardado.EN_PROGRESO; }
    public void marcarComoFinalizado() { estado = EstadoCursoGuardado.FINALIZADO; }
    public void mostrarInfoGuardado() {
        System.out.println("Curso: " + curso.getTitulo() + " - Estado: " + estado);
    }

    // Getters
    public Curso getCurso() { return curso; }
    public EstadoCursoGuardado getEstado() { return estado; }
}
