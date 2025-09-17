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

    // Getters
    public int getValor() { return valor; }
}
