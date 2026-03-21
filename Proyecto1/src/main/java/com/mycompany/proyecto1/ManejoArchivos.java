package com.mycompany.proyecto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ManejoArchivos {

    public static int cargarLibros(String nombreArchivo, Libro[] libros) {
        int contadorLibros = 0;

        try {
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();

                if (!linea.equals("")) {
                    String[] partes = linea.split(",");

                    if (partes.length >= 6) {
                        libros[contadorLibros] = new Libro(
                                partes[0],
                                partes[1],
                                partes[2],
                                Integer.parseInt(partes[3]),
                                Integer.parseInt(partes[4]),
                                Integer.parseInt(partes[5])
                        );
                        contadorLibros++;
                    }
                }
            }

            lector.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo de libros.");
        }

        return contadorLibros;
    }

    public static int cargarUsuarios(String nombreArchivo, Usuario[] usuarios) {
        int contadorUsuarios = 0;

        try {
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();

                if (!linea.equals("")) {
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
                linea = linea.trim();

                if (!linea.equals("")) {
                    String[] partes = linea.split(",");

                    if (partes.length >= 6) {
                        prestamos[contadorPrestamos] = new Prestamo(
                                partes[0],
                                partes[1],
                                partes[2],
                                partes[3],
                                partes[4],
                                Boolean.parseBoolean(partes[5])
                        );
                        contadorPrestamos++;
                    } else if (partes.length >= 4) {
                        prestamos[contadorPrestamos] = new Prestamo(
                                partes[0],
                                partes[1],
                                partes[2],
                                partes[3]
                        );
                        contadorPrestamos++;
                    }
                }
            }

            lector.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo de prestamos.");
        }

        return contadorPrestamos;
    }

    public static void guardarTodosLibros(String nombreArchivo, Libro[] libros, int contadorLibros) {
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo, false));

            for (int i = 0; i < contadorLibros; i++) {
                escritor.write(
                        libros[i].obtenerId() + ","
                        + libros[i].obtenerTitulo() + ","
                        + libros[i].obtenerAutor() + ","
                        + libros[i].obtenerCantidadDisponible() + ","
                        + libros[i].obtenerCantidadTotal() + ","
                        + libros[i].obtenerVecesPrestado()
                );
                escritor.newLine();
            }

            escritor.close();
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo de libros.");
        }
    }

    public static void guardarTodosUsuarios(String nombreArchivo, Usuario[] usuarios, int contadorUsuarios) {
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo, false));

            for (int i = 0; i < contadorUsuarios; i++) {
                escritor.write(
                        usuarios[i].obtenerId() + ","
                        + usuarios[i].obtenerNombre() + ","
                        + usuarios[i].obtenerUsuario() + ","
                        + usuarios[i].obtenerContrasena() + ","
                        + usuarios[i].obtenerRol()
                );
                escritor.newLine();
            }

            escritor.close();
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo de cuentas.");
        }
    }

    public static void guardarTodosPrestamos(String nombreArchivo, Prestamo[] prestamos, int contadorPrestamos) {
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo, false));

            for (int i = 0; i < contadorPrestamos; i++) {
                escritor.write(
                        prestamos[i].obtenerIdPrestamo() + ","
                        + prestamos[i].obtenerIdLibro() + ","
                        + prestamos[i].obtenerIdEstudiante() + ","
                        + prestamos[i].obtenerFechaPrestamo() + ","
                        + prestamos[i].obtenerFechaDevolucion() + ","
                        + prestamos[i].estaActivo()
                );
                escritor.newLine();
            }

            escritor.close();
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo de prestamos.");
        }
    }

    public static void guardarRegistro(String nombreArchivo, String mensaje) {
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo, true));
            escritor.write(mensaje);
            escritor.newLine();
            escritor.close();
        } catch (IOException e) {
            System.out.println("No se pudo escribir el registro.");
        }
    }
}