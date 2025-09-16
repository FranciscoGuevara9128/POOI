package run;
import models.Catalogo;
import models.Curso;
import models.Usuario;
import models.Enumeraciones.Modalidad;
import models.Enumeraciones.Nivel;
import models.Enumeraciones.Rol;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Catalogo catalogo = new Catalogo();
    private static List<Usuario> usuarios = new ArrayList<>();
    private static Usuario usuarioActivo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Crear el usuario admin por defecto
        Usuario admin = new Usuario("admin", "admin@gmail.com", "1234", Rol.ADMIN);
        usuarios.add(admin);

        System.out.println("=== Bienvenido al Sistema de Cursos ===");

        // --- Login antes del menú ---
        usuarioActivo = login(sc);
        if (usuarioActivo == null) {
            System.out.println("❌ Credenciales inválidas. Cerrando el sistema...");
            return;
        }

        // --- Menú principal ---
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            while (!sc.hasNextInt()) {
                System.out.println("Por favor ingrese un número válido.");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            // --- Opciones según el rol ---
            if (usuarioActivo.getRol() == Rol.ADMIN) {
                switch (opcion) {
                    case 1 -> crearCurso(sc);
                    case 2 -> catalogo.listarCursos();
                    case 3 -> guardarCurso(sc);
                    case 4 -> calificarCurso(sc);
                    case 5 -> usuarioActivo.verCursosGuardados();
                    case 6 -> crearUsuario(sc);
                    case 7 -> eliminarUsuario(sc);
                    case 8 -> listarUsuarios();
                    case 9 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("Opción inválida.");
                }
            } else { // Usuario regular
                switch (opcion) {
                    case 1 -> catalogo.listarCursos();
                    case 2 -> guardarCurso(sc);
                    case 3 -> calificarCurso(sc);
                    case 4 -> usuarioActivo.verCursosGuardados();
                    case 5 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("Opción inválida.");
                }
            }

        } while ((usuarioActivo.getRol() == Rol.ADMIN && opcion != 9) ||
                (usuarioActivo.getRol() == Rol.USUARIO && opcion != 5));

        sc.close();
    }

    // ------------------- LOGIN -------------------
    private static Usuario login(Scanner sc) {
        System.out.print("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = sc.nextLine();

        for (Usuario u : usuarios) {
            if (u.getNombre().equalsIgnoreCase(nombre) && u.getPassword().equals(password)) {
                System.out.println("✅ Bienvenido " + u.getNombre() + " (" + u.getRol() + ")");
                return u;
            }
        }
        return null;
    }

    // ------------------- MENÚ -------------------
    private static void mostrarMenu() {
        System.out.println("\n--- Menú Principal ---");
        if (usuarioActivo.getRol() == Rol.ADMIN) {
            System.out.println("1. Crear curso");
            System.out.println("2. Ver catálogo");
            System.out.println("3. Guardar curso");
            System.out.println("4. Calificar curso");
            System.out.println("5. Ver mis cursos guardados");
            System.out.println("6. Crear usuario");
            System.out.println("7. Eliminar usuario");
            System.out.println("8. Listar usuarios");
            System.out.println("9. Salir");
        } else {
            System.out.println("1. Ver catálogo");
            System.out.println("2. Guardar curso");
            System.out.println("3. Calificar curso");
            System.out.println("4. Ver mis cursos guardados");
            System.out.println("5. Salir");
        }
    }

    // ------------------- CURSOS -------------------
    private static void crearCurso(Scanner sc) {
        System.out.print("Título del curso: ");
        String titulo = sc.nextLine();

        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

        System.out.println("Nivel (1=BASICO, 2=INTERMEDIO, 3=AVANZADO): ");
        int nivelInput = sc.nextInt(); sc.nextLine();
        Nivel nivel = switch (nivelInput) {
            case 2 -> Nivel.INTERMEDIO;
            case 3 -> Nivel.AVANZADO;
            default -> Nivel.BASICO;
        };

        System.out.print("Duración (en horas): ");
        int duracion = sc.nextInt(); sc.nextLine();

        System.out.print("Precio: ");
        double precio = sc.nextDouble(); sc.nextLine();

        System.out.println("Modalidad (1=Presencial, 2=Virtual): ");
        int modalidadInput = sc.nextInt(); sc.nextLine();
        Modalidad modalidad = (modalidadInput == 1) ? Modalidad.PRESENCIAL : Modalidad.VIRTUAL;

        String ubicacion = "";
        String enlace = "";
        if (modalidad == Modalidad.PRESENCIAL) {
            System.out.print("Ingrese ubicación: ");
            ubicacion = sc.nextLine();
        } else {
            System.out.print("Ingrese enlace: ");
            enlace = sc.nextLine();
        }

        Curso curso = new Curso(titulo, descripcion, nivel, duracion, precio, modalidad, ubicacion, enlace);
        catalogo.agregarCurso(curso);
        System.out.println("✅ Curso agregado al catálogo correctamente.");
    }

    private static void guardarCurso(Scanner sc) {
        catalogo.listarCursos();
        if (catalogo.getCursos().isEmpty()) return;

        System.out.print("Ingrese el número del curso a guardar: ");
        int index = sc.nextInt(); sc.nextLine();

        if (index < 1 || index > catalogo.getCursos().size()) {
            System.out.println("Número inválido.");
            return;
        }

        Curso curso = catalogo.getCursos().get(index - 1);
        usuarioActivo.guardarCurso(curso);
        System.out.println("✅ Curso guardado.");
    }

    private static void calificarCurso(Scanner sc) {
        catalogo.listarCursos();
        if (catalogo.getCursos().isEmpty()) return;

        System.out.print("Ingrese el número del curso a calificar: ");
        int index = sc.nextInt(); sc.nextLine();

        if (index < 1 || index > catalogo.getCursos().size()) {
            System.out.println("Número inválido.");
            return;
        }

        Curso curso = catalogo.getCursos().get(index - 1);

        System.out.print("Ingrese calificación (1-5): ");
        int nota = sc.nextInt(); sc.nextLine();
        if (nota < 1 || nota > 5) {
            System.out.println("Calificación inválida.");
            return;
        }

        System.out.print("Comentario: ");
        String comentario = sc.nextLine();

        usuarioActivo.calificarCurso(curso, nota, comentario);
        System.out.println("✅ Calificación registrada.");
    }

    // ------------------- USUARIOS (solo admin) -------------------
    private static void crearUsuario(Scanner sc) {
        System.out.print("ID del usuario: ");
        String id = sc.nextLine();

        System.out.print("Nombre del usuario: ");
        String nombre = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        String password;
        do {
            System.out.print("Contraseña (5 dígitos): ");
            password = sc.nextLine();
        } while (!password.matches("\\d{5}"));

        System.out.print("Rol (1=ADMIN, 2=USUARIO): ");
        int rolInput = sc.nextInt(); sc.nextLine();
        Rol rol = (rolInput == 1) ? Rol.ADMIN : Rol.USUARIO;

        Usuario nuevo = new Usuario(id, nombre, email, rol);
        usuarios.add(nuevo);
        System.out.println("✅ Usuario creado correctamente.");
    }

    private static void eliminarUsuario(Scanner sc) {
        listarUsuarios();
        if (usuarios.isEmpty()) return;

        System.out.print("Ingrese el ID del usuario a eliminar: ");
        String id = sc.nextLine().trim();

        Usuario usuarioAEliminar = null;
        for (Usuario u : usuarios) {
            if (id.equals(String.valueOf(u.getId()))) {
                usuarioAEliminar = u;
                break;
            }
        }

        if (usuarioAEliminar != null && usuarioAEliminar != usuarioActivo) {
            usuarios.remove(usuarioAEliminar);
            System.out.println("✅ Usuario eliminado.");
        } else {
            System.out.println("❌ No se puede eliminar (no existe o es el admin actual).");
        }
    }

    private static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        System.out.println("\n--- Lista de Usuarios ---");
        for (Usuario u : usuarios) {
            System.out.println("ID: " + u.getId() + " | Nombre: " + u.getNombre() +
                    " | Email: " + u.getEmail() + " | Rol: " + u.getRol() +
                    " | Contraseña: " + u.getPassword());
        }
    }
}
