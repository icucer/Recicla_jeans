package cl.praxis.tienda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ArchivoServicio {

    public void cargarDatos(String fileName, List<Producto> listaProductos) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Producto producto = new Producto(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]);
                listaProductos.add(producto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
