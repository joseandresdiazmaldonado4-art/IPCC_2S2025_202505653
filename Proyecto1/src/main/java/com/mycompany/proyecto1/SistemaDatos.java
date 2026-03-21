package com.mycompany.proyecto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SistemaDatos {

    private Libro[] libros;
    private Usuario[] usuarios;
    private Prestamo[] prestamos;
    private String[] registrosBitacora;

    private int contadorLibros;
    private int contadorUsuarios;
    private int contadorPrestamos;
    private int contadorBitacora;

    public SistemaDatos() {
        libros = new Libro[50];
        usuarios = new Usuario[50];
        prestamos = new Prestamo[100];
        registrosBitacora = new String[300];

        contadorLibros = 0;
        contadorUsuarios = 0;
        contadorPrestamos = 0;
        contadorBitacora = 0;

        cargarTodo();
    }

    public void cargarTodo() {
        contadorLibros = cargarLibrosDesdeArchivo("libros.txt");
        contadorUsuarios = cargarUsuariosDesdeArchivo("cuentas.txt");
        contadorPrestamos = cargarPrestamosDesdeArchivo("prestamos.txt");
        contadorBitacora = cargarBitacoraDesdeArchivo("bitacora.txt");
        actualizarDisponibilidadInicial();
    }

    public int cargarLibrosDesdeArchivo(String nombreArchivo) {
        int contador = 0;

        try {
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();

                if (!linea.equals("")) {
                    String[] partes = linea.split(",");

                    if (partes.length >= 6) {
                        libros[contador] = new Libro(
                                partes[0],
                                partes[1],
                                partes[2],
                                Integer.parseInt(partes[3]),
                                Integer.parseInt(partes[4]),
                                Integer.parseInt(partes[5])
                        );
                        contador++;
                    }
                }
            }

            lector.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer libros.txt");
        }

        return contador;
    }

    public int cargarUsuariosDesdeArchivo(String nombreArchivo) {
        int contador = 0;

        try {
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();

                if (!linea.equals("")) {
                    String[] partes = linea.split(",");

                    if (partes.length >= 5) {
                        usuarios[contador] = new Usuario(
                                partes[0],
                                partes[1],
                                partes[2],
                                partes[3],
                                partes[4]
                        );
                        contador++;
                    }
                }
            }

            lector.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer cuentas.txt");
        }

        return contador;
    }

    public int cargarPrestamosDesdeArchivo(String nombreArchivo) {
        int contador = 0;

        try {
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();

                if (!linea.equals("")) {
                    String[] partes = linea.split(",");

                    if (partes.length >= 6) {
                        prestamos[contador] = new Prestamo(
                                partes[0],
                                partes[1],
                                partes[2],
                                partes[3],
                                partes[4],
                                Boolean.parseBoolean(partes[5])
                        );
                        contador++;
                    }
                }
            }

            lector.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer prestamos.txt");
        }

        return contador;
    }

    public int cargarBitacoraDesdeArchivo(String nombreArchivo) {
        int contador = 0;

        try {
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();

                if (!linea.equals("")) {
                    registrosBitacora[contador] = linea;
                    contador++;
                }
            }

            lector.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer bitacora.txt");
        }

        return contador;
    }

    public void guardarTodosLibros() {
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter("libros.txt", false));

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
            System.out.println("No se pudo guardar libros.txt");
        }
    }

    public void guardarTodosPrestamos() {
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter("prestamos.txt", false));

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
            System.out.println("No se pudo guardar prestamos.txt");
        }
    }

    public void guardarRegistro(String mensaje) {
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter("bitacora.txt", true));
            escritor.write(mensaje);
            escritor.newLine();
            escritor.close();

            registrosBitacora[contadorBitacora] = mensaje;
            contadorBitacora++;
        } catch (IOException e) {
            System.out.println("No se pudo escribir bitacora.txt");
        }
    }

    public Usuario validarLogin(String nombreUsuario, String contrasena) {
        for (int i = 0; i < contadorUsuarios; i++) {
            if (usuarios[i].obtenerUsuario().equals(nombreUsuario)
                    && usuarios[i].obtenerContrasena().equals(contrasena)) {

                guardarRegistro("LOGIN EXITOSO - " + nombreUsuario + " - " + usuarios[i].obtenerRol());
                return usuarios[i];
            }
        }

        guardarRegistro("LOGIN FALLIDO - " + nombreUsuario);
        return null;
    }

    public void actualizarTablaLibros(JTable tabla) {
        String[] columnas = {"ID", "TITULO", "AUTOR", "DISPONIBLES", "TOTALES", "PRESTAMOS"};

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        for (int i = 0; i < contadorLibros; i++) {
            Object[] fila = new Object[6];
            fila[0] = libros[i].obtenerId();
            fila[1] = libros[i].obtenerTitulo();
            fila[2] = libros[i].obtenerAutor();
            fila[3] = libros[i].obtenerCantidadDisponible();
            fila[4] = libros[i].obtenerCantidadTotal();
            fila[5] = libros[i].obtenerVecesPrestado();

            modelo.addRow(fila);
        }

        tabla.setModel(modelo);
    }

    public void actualizarTablaPrestamos(JTable tabla) {
        String[] columnas = {"ID PRESTAMO", "ID LIBRO", "ID ESTUDIANTE", "FECHA PRESTAMO", "FECHA DEVOLUCION", "ACTIVO"};

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        for (int i = 0; i < contadorPrestamos; i++) {
            Object[] fila = new Object[6];
            fila[0] = prestamos[i].obtenerIdPrestamo();
            fila[1] = prestamos[i].obtenerIdLibro();
            fila[2] = prestamos[i].obtenerIdEstudiante();
            fila[3] = prestamos[i].obtenerFechaPrestamo();
            fila[4] = prestamos[i].obtenerFechaDevolucion();
            fila[5] = prestamos[i].estaActivo();

            modelo.addRow(fila);
        }

        tabla.setModel(modelo);
    }

    public void actualizarTablaBitacora(JTable tabla) {
        String[] columnas = {"NUMERO", "REGISTRO"};

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        for (int i = 0; i < contadorBitacora; i++) {
            Object[] fila = new Object[2];
            fila[0] = i + 1;
            fila[1] = registrosBitacora[i];
            modelo.addRow(fila);
        }

        tabla.setModel(modelo);
    }

    public void buscarLibrosPorTitulo(JTable tabla, String textoBusqueda) {
        String[] columnas = {"ID", "TITULO", "AUTOR", "DISPONIBLES", "TOTALES", "PRESTAMOS"};

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        for (int i = 0; i < contadorLibros; i++) {
            if (libros[i].obtenerTitulo().toLowerCase().contains(textoBusqueda.toLowerCase())) {
                Object[] fila = new Object[6];
                fila[0] = libros[i].obtenerId();
                fila[1] = libros[i].obtenerTitulo();
                fila[2] = libros[i].obtenerAutor();
                fila[3] = libros[i].obtenerCantidadDisponible();
                fila[4] = libros[i].obtenerCantidadTotal();
                fila[5] = libros[i].obtenerVecesPrestado();

                modelo.addRow(fila);
            }
        }

        tabla.setModel(modelo);
    }

    public void cargarComboLibrosDisponibles(JComboBox<String> combo) {
        combo.removeAllItems();

        for (int i = 0; i < contadorLibros; i++) {
            if (libros[i].estaDisponible()) {
                combo.addItem(libros[i].obtenerId() + " - " + libros[i].obtenerTitulo());
            }
        }
    }

    public void cargarComboEstudiantes(JComboBox<String> combo) {
        combo.removeAllItems();

        for (int i = 0; i < contadorUsuarios; i++) {
            if (usuarios[i].obtenerRol().equals("ESTUDIANTE")) {
                combo.addItem(usuarios[i].obtenerId() + " - " + usuarios[i].obtenerNombre());
            }
        }
    }

    public String registrarPrestamoDesdeGui(String idLibro, String idEstudiante, String usuarioQueOpera) {
        Libro libroEncontrado = buscarLibroPorId(idLibro);
        Usuario estudianteEncontrado = buscarUsuarioPorId(idEstudiante);

        if (libroEncontrado == null) {
            return "No se encontro el libro.";
        }

        if (estudianteEncontrado == null) {
            return "No se encontro el estudiante.";
        }

        if (!estudianteEncontrado.obtenerRol().equals("ESTUDIANTE")) {
            return "El usuario seleccionado no es estudiante.";
        }

        if (!libroEncontrado.estaDisponible()) {
            return "No hay stock disponible.";
        }

        if (contarPrestamosActivosDeEstudiante(idEstudiante) >= 3) {
            return "El estudiante ya tiene 3 prestamos activos.";
        }

        String idPrestamo = generarIdPrestamo(contadorPrestamos + 1);
        String fechaActual = obtenerFechaActual();

        prestamos[contadorPrestamos] = new Prestamo(
                idPrestamo,
                idLibro,
                idEstudiante,
                fechaActual,
                "",
                true
        );

        contadorPrestamos++;
        libroEncontrado.prestarLibro();

        guardarTodosPrestamos();
        guardarTodosLibros();
        guardarRegistro("PRESTAMO - " + usuarioQueOpera + " - LIBRO " + idLibro + " - ESTUDIANTE " + idEstudiante);

        return "Prestamo registrado correctamente.";
    }

    public Libro buscarLibroPorId(String idBuscado) {
        for (int i = 0; i < contadorLibros; i++) {
            if (libros[i].obtenerId().equals(idBuscado)) {
                return libros[i];
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorId(String idBuscado) {
        for (int i = 0; i < contadorUsuarios; i++) {
            if (usuarios[i].obtenerId().equals(idBuscado)) {
                return usuarios[i];
            }
        }
        return null;
    }

    public int contarPrestamosActivosDeEstudiante(String idEstudiante) {
        int contador = 0;

        for (int i = 0; i < contadorPrestamos; i++) {
            if (prestamos[i].obtenerIdEstudiante().equals(idEstudiante) && prestamos[i].estaActivo()) {
                contador++;
            }
        }

        return contador;
    }

    public void actualizarDisponibilidadInicial() {
        for (int i = 0; i < contadorLibros; i++) {
            libros[i].cambiarCantidadDisponible(libros[i].obtenerCantidadTotal());
        }

        for (int i = 0; i < contadorPrestamos; i++) {
            if (prestamos[i].estaActivo()) {
                Libro libroEncontrado = buscarLibroPorId(prestamos[i].obtenerIdLibro());

                if (libroEncontrado != null && libroEncontrado.obtenerCantidadDisponible() > 0) {
                    libroEncontrado.cambiarCantidadDisponible(libroEncontrado.obtenerCantidadDisponible() - 1);
                }
            }
        }
    }

    public String generarIdPrestamo(int numero) {
        if (numero < 10) {
            return "P00" + numero;
        } else if (numero < 100) {
            return "P0" + numero;
        } else {
            return "P" + numero;
        }
    }

    public String obtenerFechaActual() {
        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formato);
    }
}