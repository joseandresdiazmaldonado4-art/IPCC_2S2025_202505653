package com.mycompany.proyecto1;

public class Prestamo {
    
    private String idPrestamo;
    private String idLibro;
    private String idEstudiante;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private boolean activo;
    
    public Prestamo(String idPrestamo, String idLibro, String idEstudiante, String fechaPrestamo) {
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.idEstudiante = idEstudiante;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = "";
        this.activo = true;
    }

    public Prestamo(String idPrestamo, String idLibro, String idEstudiante, String fechaPrestamo, String fechaDevolucion, boolean activo) {
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.idEstudiante = idEstudiante;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.activo = activo;
    }

    public String obtenerIdPrestamo() {
        return idPrestamo;
    }

    public String obtenerIdLibro() {
        return idLibro;
    }

    public String obtenerIdEstudiante() {
        return idEstudiante;
    }

    public String obtenerFechaPrestamo() {
        return fechaPrestamo;
    }

    public String obtenerFechaDevolucion() {
        return fechaDevolucion;
    }

    public boolean estaActivo() {
        return activo;
    }

    public void cambiarFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void cambiarActivo(boolean activo) {
        this.activo = activo;
    }

    public void marcarDevuelto(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
        this.activo = false;
    }
}