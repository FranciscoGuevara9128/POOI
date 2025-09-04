package Models;

public class Inventario {
    private Producto[] productos;
    private int contador;  // Para llevar control de cuántos productos hay

    // Constructor
    public Inventario(int capacidad) {
        productos = new Producto[capacidad];
        contador = 0;
    }

    // Metodo para añadir producto
    public void agregarProducto(Producto p) {
        if (contador < productos.length) {
            productos[contador] = p;
            contador++;
        } else {
            System.out.println("Inventario lleno, no se puede agregar más productos.");
        }
    }

    // Metodo para buscar producto por codigo
    public Producto buscarProducto(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (productos[i].getCodigo().equalsIgnoreCase(codigo)) {
                return productos[i];
            }
        }
        return null;
    }

    // Metodo para mostrar productos con stock menor a 5
    public void mostrarStockBajo() {
        System.out.println("\n📦 Productos con stock menor a 5:");
        boolean encontrado = false;
        for (int i = 0; i < contador; i++) {
            if (productos[i].getCantidad() < 5) {
                System.out.println(productos[i]);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No hay productos con stock bajo.");
        }
    }

    // Metodo para mostrar todos los productos
    public void mostrarProductos() {
        System.out.println("\n📋 Inventario completo:");
        for (int i = 0; i < contador; i++) {
            System.out.println(productos[i]);
        }
    }
}
