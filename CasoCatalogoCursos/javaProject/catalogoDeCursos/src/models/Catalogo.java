package models;
import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private List<Curso> cursos = new ArrayList<>();

    public List<Curso> getCursos() {
        return cursos;
    }

    public void agregarCurso(Curso curso) {
        cursos.add(curso);
    }
    public void eliminarCurso(Curso curso) {
        cursos.remove(curso);
    }
    public void actualizarCurso(Curso curso) {
        /* lógica de actualización */
    }
    public Curso buscarCurso(String titulo) {
        for (Curso c : cursos) {
            if (c.getTitulo().equalsIgnoreCase(titulo)) return c;
        }
        return null;
    }
    public void listarCursos() {
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos disponibles.");
            return;
        }
        int i = 1;
        for (Curso curso : cursos) {
            System.out.println(i + ". " + curso.getTitulo() + " - " +
                    "Nivel: " + curso.getNivel() +
                    " | Precio: $" + curso.getPrecio());
            i++;
        }
    }

}
