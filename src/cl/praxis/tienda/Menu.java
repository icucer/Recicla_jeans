package cl.praxis.tienda;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private ProductoServicio productoServicio = new ProductoServicio();
    private ArchivoServicio arcivoServicio = new ArchivoServicio();
    private Scanner sc = new Scanner(System.in);
    private String nombreFicheroImportar = "ProductosImportados.csv";

    public void iniciarMenu() {

        int opcion;
        do {
            System.out.println(" \n +-----------------------------+");
            System.out.println(" |            MENU:            |");
            System.out.println(" +-----------------------------+");
            System.out.println(" |   1.    Listar producto     |");
            System.out.println(" |   2.      Editar datos      |");
            System.out.println(" |   3.     Importar datos     |");
            System.out.println(" |   4.        Salir           |");
            System.out.println(" +-----------------------------+");
            System.out.print("\nIngrese una opción: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());


                switch (opcion) {
                    case 1:
                        productoServicio.listarProducto();
                        break;
                    case 2:
                        editarProducto();
                        break;
                    case 3:
                        importarDatos();
                        break;
                    case 4:
                        terminarPrograma();
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese una opcion valida.");
                opcion = -1;
            }
        } while (opcion != 4);
    }

    private void editarProducto() {
        System.out.print("Ingrese el codigo del producto a editar: ");
        String codigoProducto = sc.nextLine();
        Producto producto = productoServicio.buscarProducto(codigoProducto);
        if (producto != null) {
            System.out.println("1 El nombre del articulo actual es: " + producto.getArticulo() + ".");
            System.out.println("2 El código del producto actual es: " + producto.getCodigo() + ".");
            System.out.println("3 El color del producto actual es: " + producto.getColor() + ".");
            System.out.println("4 La descripción del producto actual es:\n" + producto.getDescripcion() + ".");
            System.out.println("5 La marca del producto actual es: " + producto.getMarca() + ".");
            System.out.println("6 El precio del producto actual es: " + producto.getPrecio() + ".");
            System.out.println("7 La talla del producto actual es: " + producto.getTalla() + ".");
            System.out.println("Ingrese la opción a editar de los datos del producto:");
            int opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre del articulo:");
                    producto.setArticulo(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo código del producto:");
                    producto.setCodigo(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Ingrese el nuevo color del producto:");
                    producto.setColor(sc.nextLine());
                    break;
                case 4:
                    System.out.println("Ingrese la nueva descripción del producto:");
                    producto.setDescripcion(sc.nextLine());
                    break;
                case 5:
                    System.out.println("Ingrese la nueva marca del producto:");
                    producto.setMarca(sc.nextLine());
                    break;
                case 6:
                    System.out.println("Ingrese el nuevo precio del producto:");
                    producto.setPrecio(sc.nextLine());
                    break;
                case 7:
                    System.out.println("Ingrese la nueva talla del producto:");
                    producto.setTalla(sc.nextLine());
                    break;
            }
        } else
            System.out.println("Producto con siguiente codigo: " + codigoProducto + ", no existe.");
    }

    private void importarDatos() {
        int opcionOS = seleccionarOS();
        String rutaBase = solicitarRuta();
        String rutaCompleta;

        switch (opcionOS) {
            case 1:
                // Linux o Mac
                rutaCompleta = Paths.get(rutaBase, nombreFicheroImportar).toString();
                break;
            case 2:
                // Windows
                rutaCompleta = Paths.get(rutaBase, nombreFicheroImportar).toString();
                break;
            default:
                throw new IllegalStateException("Opción de sistema operativo no válida: " + opcionOS);
        }

        if (Files.exists(Paths.get(rutaCompleta))) {
            arcivoServicio.cargarDatos(rutaCompleta, productoServicio.obtenerListaProductos());
            System.out.println("Datos importados con éxito en la lista.");
        } else {
            System.out.println("El archivo no se encuentra en la ruta especificada. Por favor, verifique la ruta e intente nuevamente.");
        }
    }

    public int seleccionarOS() {
        int opcionOS = 0;
        boolean opcionValida = false;

        while (!opcionValida) {
            try {
                System.out.print("\n----------Selecciona el sistema operativo----------");
                System.out.println("\n1. Linux o Mac");
                System.out.println("2. Windows");
                System.out.print("Ingrese una opcion: ");
                opcionOS = Integer.parseInt(sc.nextLine());
                System.out.print("\n---------------------------------------------------\n");

                if (opcionOS == 1 || opcionOS == 2) {
                    opcionValida = true;
                } else {
                    System.out.println("Opción inválida. Inténtalo nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
            }
        }
        return opcionOS;
    }

    public String solicitarRuta() {
        String ruta;
        System.out.println("------------------Ruta del archivo------------------");
        System.out.print("Ingrese la ruta: ");
        ruta = sc.nextLine();
        System.out.println("----------------------------------------------------");
        return ruta;

    }

    private void terminarPrograma() {
        System.out.println("Saliendo del programa...");
        System.exit(0);
    }
}
