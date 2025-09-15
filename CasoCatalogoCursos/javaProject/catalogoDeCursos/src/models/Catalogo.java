package models;
import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private List<Curso> cursos;

    public Catalogo() { cursos = new ArrayList<>(); }

    public void agregarCurso(Curso curso) { cursos.add(curso); }
    public void eliminarCurso(Curso curso) { cursos.remove(curso); }
    public void actualizarCurso(Curso curso) { /* lógica de actualización */ }
    public Curso buscarCurso(String titulo) {
        for (Curso c : cursos) {
            if (c.getTitulo().equalsIgnoreCase(titulo)) return c;
        }
        return null;
    }
    public void listarCursos() {
        for (Curso c : cursos) c.mostrarInfo();
    }
}
