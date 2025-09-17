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
    private static final String PASSWORD_REGEX = "\\d{5}";
    private static final String PASSWORD_MSG = "Contraseña (5 dígitos): ";

    private static Catalogo catalogo = new Catalogo();
    private static List<Usuario> usuarios = new ArrayList<>();
    private static Usuario usuarioActivo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Usuario admin por defecto (cumple la regla de 5 dígitos)
        Usuario admin = new Usuario("admin", "admin@gmail.com", "12345", Rol.ADMIN);
        usuarios.add(admin);

        System.out.println("=== Bienvenido al Sistema de Cursos ===");

        // Bucle principal: permite cerrar sesión y volver a entrar sin terminar el programa
        while (true) {
            usuarioActivo = flujoInicio(sc);
            if (usuarioActivo == null) {
                System.out.println("👋 Saliendo del sistema...");
                break; // Opción "Salir" desde el inicio
            }

            boolean salirPrograma = mostrarMenuPrincipal(sc); // devuelve true si eligen "Salir"
            if (salirPrograma) break; // cierra el programa
            // si no, fue "Cerrar sesión": vuelve al while para relogin
        }

        sc.close();
    }

    // ------------------- FLUJO INICIAL -------------------
    private static Usuario flujoInicio(Scanner sc) {
        while (true) {
            System.out.println("\n--- Inicio ---");
            System.out.println("1. Ingresar");
            System.out.println("2. Crear cuenta");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");
            String op = sc.nextLine().trim();

            switch (op) {
                case "1" -> {
                    Usuario u = login(sc);
                    if (u != null) return u;
                    System.out.println("❌ Credenciales inválidas.");
                }
                case "2" -> {
                    Usuario nuevo = crearCuentaUsuario(sc);
                    usuarios.add(nuevo);
                    System.out.println("✅ Cuenta creada. Iniciando sesión como " + nuevo.getNombre());
                    return nuevo;
                }
                case "3" -> { // salir del programa
                    return null;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
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

    // Crear cuenta de tipo USUARIO (usa la validación unificada)
    private static Usuario crearCuentaUsuario(Scanner sc) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        String password = obtenerPasswordValida(sc);

        return new Usuario(nombre, email, password, Rol.USUARIO);
    }

    // ------------------- MENÚ PRINCIPAL (según rol) -------------------
    // Devuelve true si el usuario elige "Salir" (cierra programa). Devuelve false si elige "Cerrar sesión".
    private static boolean mostrarMenuPrincipal(Scanner sc) {
        while (true) {
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
                System.out.println("9. Cerrar sesión");
                System.out.println("10. Salir");
            } else {
                System.out.println("1. Ver catálogo");
                System.out.println("2. Guardar curso");
                System.out.println("3. Calificar curso");
                System.out.println("4. Ver mis cursos guardados");
                System.out.println("5. Cerrar sesión");
                System.out.println("6. Salir");
            }

            System.out.print("Seleccione una opción: ");
            String entrada = sc.nextLine().trim();
            int opcion;
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
                continue;
            }

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
                    case 9 -> { // Cerrar sesión
                        usuarioActivo = null;
                        clearScreen();
                        return false; // volver al login
                    }
                    case 10 -> { // Salir del programa
                        return true;
                    }
                    default -> System.out.println("Opción inválida.");
                }
            } else { // Usuario regular
                switch (opcion) {
                    case 1 -> catalogo.listarCursos();
                    case 2 -> guardarCurso(sc);
                    case 3 -> calificarCurso(sc);
                    case 4 -> usuarioActivo.verCursosGuardados();
                    case 5 -> { // Cerrar sesión
                        usuarioActivo = null;
                        clearScreen();
                        return false; // volver al login
                    }
                    case 6 -> { // Salir del programa
                        return true;
                    }
                    default -> System.out.println("Opción inválida.");
                }
            }
        }
    }

    // ------------------- CURSOS -------------------
    private static void crearCurso(Scanner sc) {
        System.out.print("Título del curso: ");
        String titulo = sc.nextLine();

        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

        System.out.println("Nivel (1=BASICO, 2=INTERMEDIO, 3=AVANZADO): ");
        int nivelInput = leerEntero(sc);
        Nivel nivel = switch (nivelInput) {
            case 2 -> Nivel.INTERMEDIO;
            case 3 -> Nivel.AVANZADO;
            default -> Nivel.BASICO;
        };

        System.out.print("Duración (en horas): ");
        int duracion = leerEntero(sc);

        System.out.print("Precio: ");
        double precio = leerDouble(sc);

        System.out.println("Modalidad (1=Presencial, 2=Virtual): ");
        int modalidadInput = leerEntero(sc);
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
        int index = leerEntero(sc);

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
        int index = leerEntero(sc);

        if (index < 1 || index > catalogo.getCursos().size()) {
            System.out.println("Número inválido.");
            return;
        }

        Curso curso = catalogo.getCursos().get(index - 1);

        System.out.print("Ingrese calificación (1-5): ");
        int nota = leerEntero(sc);
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
        System.out.print("Nombre del usuario: ");
        String nombre = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        String password = obtenerPasswordValida(sc); // <- validación unificada

        System.out.print("Rol (1=ADMIN, 2=USUARIO): ");
        int rolInput = leerEntero(sc);
        Rol rol = (rolInput == 1) ? Rol.ADMIN : Rol.USUARIO;

        // Ajusta al constructor que tengas. Si tu Usuario necesita password en este constructor:
        Usuario nuevo = new Usuario(nombre, email, password, rol);
        usuarios.add(nuevo);
        System.out.println("✅ Usuario creado correctamente con ID: " + nuevo.getId());
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
            System.out.println("❌ No se puede eliminar (no existe o es el usuario actual).");
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

    // ------------------- HELPERS -------------------
    // Validación unificada de contraseña
    private static String obtenerPasswordValida(Scanner sc) {
        String password;
        do {
            System.out.print(PASSWORD_MSG);
            password = sc.nextLine().trim();
        } while (!password.matches(PASSWORD_REGEX));
        return password;
    }

    // Limpia pantalla (ANSI) con fallback
    private static void clearScreen() {
        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {
            // Fallback: imprimir varias líneas
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    private static int leerEntero(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }

    private static double leerDouble(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }
}
