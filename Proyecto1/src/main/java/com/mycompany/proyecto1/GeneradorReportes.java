package com.mycompany.proyecto1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GeneradorReportes {

    public static void generarReporteHtml(Libro[] libros, int contadorLibros,
                                          Prestamo[] prestamos, int contadorPrestamos,
                                          Usuario[] usuarios, int contadorUsuarios) {

        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter("reporte_biblioteca.html"));

            int[] posiciones = new int[contadorLibros];

            for (int i = 0; i < contadorLibros; i++) {
                posiciones[i] = i;
            }

            for (int i = 0; i < contadorLibros - 1; i++) {
                for (int j = 0; j < contadorLibros - 1 - i; j++) {
                    if (libros[posiciones[j]].obtenerVecesPrestado() < libros[posiciones[j + 1]].obtenerVecesPrestado()) {
                        int aux = posiciones[j];
                        posiciones[j] = posiciones[j + 1];
                        posiciones[j + 1] = aux;
                    }
                }
            }

            escritor.write("<html>");
            escritor.newLine();
            escritor.write("<head>");
            escritor.newLine();
            escritor.write("<meta charset='UTF-8'>");
            escritor.newLine();
            escritor.write("<title>Reporte BiblioSystem</title>");
            escritor.newLine();
            escritor.write("</head>");
            escritor.newLine();
            escritor.write("<body>");
            escritor.newLine();

            escritor.write("<h1>REPORTE BIBLIOSYSTEM</h1>");
            escritor.newLine();

            escritor.write("<h2>LIBROS MAS PRESTADOS</h2>");
            escritor.newLine();
            escritor.write("<table border='1'>");
            escritor.newLine();
            escritor.write("<tr><th>ID</th><th>TITULO</th><th>AUTOR</th><th>VECES PRESTADO</th></tr>");
            escritor.newLine();

            for (int i = 0; i < contadorLibros; i++) {
                Libro libroActual = libros[posiciones[i]];

                escritor.write("<tr>");
                escritor.write("<td>" + libroActual.obtenerId() + "</td>");
                escritor.write("<td>" + libroActual.obtenerTitulo() + "</td>");
                escritor.write("<td>" + libroActual.obtenerAutor() + "</td>");
                escritor.write("<td>" + libroActual.obtenerVecesPrestado() + "</td>");
                escritor.write("</tr>");
                escritor.newLine();
            }

            escritor.write("</table>");
            escritor.newLine();

            escritor.write("<h2>PRESTAMOS ACTUALES</h2>");
            escritor.newLine();
            escritor.write("<table border='1'>");
            escritor.newLine();
            escritor.write("<tr><th>ID PRESTAMO</th><th>ID LIBRO</th><th>TITULO LIBRO</th><th>ID ESTUDIANTE</th><th>NOMBRE ESTUDIANTE</th><th>FECHA PRESTAMO</th></tr>");
            escritor.newLine();

            for (int i = 0; i < contadorPrestamos; i++) {
                if (prestamos[i].estaActivo()) {
                    Libro libroActual = buscarLibroPorId(libros, contadorLibros, prestamos[i].obtenerIdLibro());
                    Usuario estudianteActual = buscarUsuarioPorId(usuarios, contadorUsuarios, prestamos[i].obtenerIdEstudiante());

                    String tituloLibro = "";
                    String nombreEstudiante = "";

                    if (libroActual != null) {
                        tituloLibro = libroActual.obtenerTitulo();
                    }

                    if (estudianteActual != null) {
                        nombreEstudiante = estudianteActual.obtenerNombre();
                    }

                    escritor.write("<tr>");
                    escritor.write("<td>" + prestamos[i].obtenerIdPrestamo() + "</td>");
                    escritor.write("<td>" + prestamos[i].obtenerIdLibro() + "</td>");
                    escritor.write("<td>" + tituloLibro + "</td>");
                    escritor.write("<td>" + prestamos[i].obtenerIdEstudiante() + "</td>");
                    escritor.write("<td>" + nombreEstudiante + "</td>");
                    escritor.write("<td>" + prestamos[i].obtenerFechaPrestamo() + "</td>");
                    escritor.write("</tr>");
                    escritor.newLine();
                }
            }

            escritor.write("</table>");
            escritor.newLine();

            escritor.write("<h2>USUARIOS REGISTRADOS</h2>");
            escritor.newLine();
            escritor.write("<table border='1'>");
            escritor.newLine();
            escritor.write("<tr><th>ID</th><th>NOMBRE</th><th>USUARIO</th><th>ROL</th></tr>");
            escritor.newLine();

            for (int i = 0; i < contadorUsuarios; i++) {
                escritor.write("<tr>");
                escritor.write("<td>" + usuarios[i].obtenerId() + "</td>");
                escritor.write("<td>" + usuarios[i].obtenerNombre() + "</td>");
                escritor.write("<td>" + usuarios[i].obtenerUsuario() + "</td>");
                escritor.write("<td>" + usuarios[i].obtenerRol() + "</td>");
                escritor.write("</tr>");
                escritor.newLine();
            }

            escritor.write("</table>");
            escritor.newLine();

            escritor.write("</body>");
            escritor.newLine();
            escritor.write("</html>");

            escritor.close();

            System.out.println("Reporte HTML generado correctamente.");
        } catch (IOException e) {
            System.out.println("No se pudo generar el reporte HTML.");
        }
    }

    public static Libro buscarLibroPorId(Libro[] libros, int contadorLibros, String idBuscado) {
        for (int i = 0; i < contadorLibros; i++) {
            if (libros[i].obtenerId().equals(idBuscado)) {
                return libros[i];
            }
        }
        return null;
    }

    public static Usuario buscarUsuarioPorId(Usuario[] usuarios, int contadorUsuarios, String idBuscado) {
        for (int i = 0; i < contadorUsuarios; i++) {
            if (usuarios[i].obtenerId().equals(idBuscado)) {
                return usuarios[i];
            }
        }
        return null;
    }
}