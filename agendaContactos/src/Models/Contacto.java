package Models;

public class Contacto {
    private String nombre;
    private String telefono;
    private String correo;

    // Constructor
    public Contacto(String nombre, String telefono, String correo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    // toString para mostrar el contacto
    @Override
    public String toString() {
        return "Nombre: " + nombre + " | Teléfono: " + telefono + " | Correo: " + correo;
    }
}
