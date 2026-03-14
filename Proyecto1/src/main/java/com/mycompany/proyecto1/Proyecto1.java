package com.mycompany.proyecto1;

public class Proyecto1 {

    public static void main(String[] args) {

        Libro[] libros = new Libro[50];
        Usuario[] usuarios = new Usuario[50];
        Prestamo[] prestamos = new Prestamo[100];

        int contadorLibros = 0;
        int contadorUsuarios = 0;
        int contadorPrestamos = 0;

        libros[contadorLibros++] = new Libro("001", "Harry Potter y la Piedra Filosofal", "J.K. Rowling", 8, 8);
        libros[contadorLibros++] = new Libro("002", "Don Quijote de la Mancha", "Miguel de Cervantes", 6, 6);
        libros[contadorLibros++] = new Libro("003", "Five Nights at Freddy's: Los ojos de plata", "Scott Cawthon", 7, 7);
        libros[contadorLibros++] = new Libro("004", "Introduccion al Calculo", "James Stewart", 10, 10);
        libros[contadorLibros++] = new Libro("005", "Fisica Universitaria", "Sears y Zemansky", 10, 10);
        libros[contadorLibros++] = new Libro("006", "El Principito", "Antoine de Saint-Exupery", 7, 7);
        libros[contadorLibros++] = new Libro("007", "La Biblia", "Varios", 9, 9);
        libros[contadorLibros++] = new Libro("008", "Diario de Greg: Un pringao total", "Jeff Kinney", 8, 8);
        libros[contadorLibros++] = new Libro("009", "La Odisea", "Homero", 6, 6);

        usuarios[contadorUsuarios++] = new Usuario("U001", "Administrador General", "admin", "admin123", "ADMIN");
        usuarios[contadorUsuarios++] = new Usuario("U002", "Operador Uno", "operador1", "oper123", "OPERADOR");
        usuarios[contadorUsuarios++] = new Usuario("U003", "Estudiante Uno", "estudiante1", "estu123", "ESTUDIANTE");

        System.out.println("BIBLIOSYSTEM");
        System.out.println("LIBROS CARGADOS: " + contadorLibros);
        System.out.println("USUARIOS CARGADOS: " + contadorUsuarios);
        System.out.println("PRESTAMOS CARGADOS: " + contadorPrestamos);
        System.out.println();

        for (int i = 0; i < contadorLibros; i++) {
            System.out.println(
                libros[i].obtenerId() + " | "
                + libros[i].obtenerTitulo() + " | "
                + libros[i].obtenerAutor() + " | DISPONIBLES: "
                + libros[i].obtenerCantidadDisponible() + " | TOTALES: "
                + libros[i].obtenerCantidadTotal()
            );
        }
    }
}