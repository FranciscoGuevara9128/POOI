package models;

import java.util.Date;

public class Calificacion {
    private int id;
    private Usuario usuario;
    private Curso curso;
    public int valor;
    public String comentario;
    public Date fecha;

    public Calificacion(Usuario usuario, Curso curso, int valor, String comentario, Date fecha) {
        this.usuario = usuario;
        this.curso = curso;
        this.valor = valor;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    // Operaciones
    public void crearCalificacion() {}
    public void actualizarCalificacion() {}
    public void eliminarCalificacion() {}
    public void mostrarCalificacion() {
        System.out.println("Curso: " + curso.getTitulo() + " - Valor: " + valor + " - Comentario: " + comentario);
    }

    // Getters
    public int getValor() { return valor; }
}
