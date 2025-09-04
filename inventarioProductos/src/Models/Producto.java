package Models;

public class Producto {
    private String nombre;
    private String codigo;
    private int cantidad;

    // Constructor
    public Producto(String nombre, String codigo, int cantidad) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    // Setter de cantidad
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Representación en texto
    @Override
    public String toString() {
        return "Código: " + codigo + " | Nombre: " + nombre + " | Cantidad: " + cantidad;
    }
}
