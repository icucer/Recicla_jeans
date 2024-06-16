package cl.praxis.tienda;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class ProductoServicio {
    private List<Producto> listaProductos;
    private Scanner sc = new Scanner(System.in);

    public ProductoServicio() {
        this.listaProductos = new ArrayList<>();
    }

    public void listarProducto() {
        if (!listaProductos.isEmpty()) {
            for (Producto producto : listaProductos) {
                System.out.println(producto);
            }
        } else
            System.out.println("La lista de productos está vacía.");
    }

    public void añadirProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public List<Producto> obtenerListaProductos() {
        return listaProductos;
    }

    public Producto buscarProducto(String codigoProducto) {
        for (Producto producto : listaProductos) {
            if (producto.getCodigo().equals(codigoProducto)) {
                return producto;
            }
        }
        return null;
    }
}
