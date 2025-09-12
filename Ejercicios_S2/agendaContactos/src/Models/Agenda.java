package Models;

public class Agenda {
    private Contacto[] contactos;
    private int contador; // cantidad actual de contactos

    // Constructor
    public Agenda(int capacidad) {
        contactos = new Contacto[capacidad];
        contador = 0;
    }

    // Metodo para añadir contacto
    public void agregarContacto(Contacto c) {
        if (contador < contactos.length) {
            contactos[contador] = c;
            contador++;
        } else {
            System.out.println("Agenda llena, no se puede agregar más contactos.");
        }
    }

    // Buscar por nombre
    public Contacto buscarPorNombre(String nombre) {
        for (int i = 0; i < contador; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre)) {
                return contactos[i];
            }
        }
        return null;
    }

    // Mostrar todos los contactos
    public void mostrarContactos() {
        System.out.println("\n📋 Lista de contactos:");
        for (int i = 0; i < contador; i++) {
            System.out.println(contactos[i]);
        }
        if (contador == 0) {
            System.out.println("Agenda vacía.");
        }
    }

    // Buscar por dominio de correo (@gmail.com, etc)
    public void buscarPorDominio(String dominio) {
        System.out.println("\n Contactos con correo en " + dominio + ":");
        boolean encontrado = false;
        for (int i = 0; i < contador; i++) {
            if (contactos[i].getCorreo().toLowerCase().endsWith(dominio.toLowerCase())) {
                System.out.println(contactos[i]);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron contactos con ese dominio.");
        }
    }
}
