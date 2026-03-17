package com.mycompany.proyecto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ManejoArchivos {

    public static int cargarUsuarios(String nombreArchivo, Usuario[] usuarios) {
        int contadorUsuarios = 0;

        try {
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length >= 5) {
                    usuarios[contadorUsuarios] = new Usuario(
                            partes[0],
                            partes[1],
                            partes[2],
                            partes[3],
                            partes[4]
                    );
                    contadorUsuarios++;
                }
            }

            lector.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo de cuentas.");
        }

        return contadorUsuarios;
    }

    public static int cargarPrestamos(String nombreArchivo, Prestamo[] prestamos) {
        int contadorPrestamos = 0;

        try {
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length >= 4) {
                    prestamos[contadorPrestamos] = new Prestamo(
                            partes[0],
                            partes[1],
                            partes[2],
                            partes[3]
                    );
                    contadorPrestamos++;
                }
            }

            lector.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo de prestamos.");
        }

        return contadorPrestamos;
    }

    public static void registrarBitacora(String nombreArchivo, String mensaje) {
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo, true));
            escritor.write(mensaje);
            escritor.newLine();
            escritor.close();
        } catch (IOException e) {
            System.out.println("No se pudo escribir en la bitacora.");
        }
    }
}