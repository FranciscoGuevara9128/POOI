package run;
import models.Catalogo;
import models.Curso;
import models.Usuario;
import models.Calificacion;
import models.Enumeraciones.Modalidad;
import models.Enumeraciones.Nivel;
import models.Enumeraciones.Rol;
import models.Enumeraciones.EstadoCursoGuardado;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        // Crear catálogo
        Catalogo catalogo = new Catalogo();

        // Crear cursos
        Curso curso1 = new Curso(
                "Introducción a IA",
                "Fundamentos de Inteligencia Artificial",
                Nivel.BASICO,
                40,
                200.0,
                Modalidad.VIRTUAL,
                "",
                "https://curso-ia.com"
        );

        Curso curso2 = new Curso(
                "Análisis de Datos con Excel",
                "Aprende a manejar Excel y análisis de datos",
                Nivel.INTERMEDIO,
                30,
                150.0,
                Modalidad.PRESENCIAL,
                "Managua, Nicaragua",
                ""
        );

        // Agregar cursos al catálogo
        catalogo.agregarCurso(curso1);
        catalogo.agregarCurso(curso2);

        // Crear usuarios
        Usuario admin = new Usuario("Ana", "ana@mail.com", "1234", Rol.ADMIN);
        Usuario user1 = new Usuario("Juan", "juan@mail.com", "abcd", Rol.USUARIO);

        // Usuario guarda cursos
        user1.guardarCurso(curso1);
        user1.guardarCurso(curso2);

        // Usuario califica cursos
        user1.calificarCurso(curso1, 5, "Excelente curso de IA!");
        user1.calificarCurso(curso2, 4, "Muy bueno, pero podría ser más práctico");

        // Mostrar cursos guardados del usuario
        System.out.println("\nCursos guardados por " + user1.nombre + ":");
        user1.verCursosGuardados();

        // Mostrar promedio de calificaciones de los cursos
        System.out.println("\nPromedio de calificaciones:");
        System.out.println(curso1.getTitulo() + ": " + curso1.calcularPromedioCalificaciones());
        System.out.println(curso2.getTitulo() + ": " + curso2.calcularPromedioCalificaciones());

        // Listar todos los cursos en el catálogo
        System.out.println("\nCatálogo completo:");
        catalogo.listarCursos();
    }
}
