package cl.praxis.tienda;

import java.util.InputMismatchException;

public class Utilidad {
    // Método para limpiar la pantalla alternativo
    public static void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    // Método para realizar una pausa en la ejecución
    public static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
