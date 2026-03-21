package com.mycompany.proyecto1;

public class Libro {
    
    private String id;
    private String titulo;
    private String autor;
    private int cantidadDisponible;
    private int cantidadTotal;
    private int vecesPrestado;
    
    public Libro(String id, String titulo, String autor, int cantidadDisponible, int cantidadTotal) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadTotal = cantidadTotal;
        this.vecesPrestado = 0;
    }

    public Libro(String id, String titulo, String autor, int cantidadDisponible, int cantidadTotal, int vecesPrestado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadTotal = cantidadTotal;
        this.vecesPrestado = vecesPrestado;
    }

    public String obtenerId() {
        return id;
    }

    public String obtenerTitulo() {
        return titulo;
    }

    public String obtenerAutor() {
        return autor;
    }

    public int obtenerCantidadDisponible() {
        return cantidadDisponible;
    }

    public int obtenerCantidadTotal() {
        return cantidadTotal;
    }

    public int obtenerVecesPrestado() {
        return vecesPrestado;
    }

    public void cambiarTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void cambiarAutor(String autor) {
        this.autor = autor;
    }

    public void cambiarCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public void cambiarCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public void cambiarVecesPrestado(int vecesPrestado) {
        this.vecesPrestado = vecesPrestado;
    }

    public boolean estaDisponible() {
        return cantidadDisponible > 0;
    }

    public void prestarLibro() {
        if (cantidadDisponible > 0) {
            cantidadDisponible--;
            vecesPrestado++;
        }
    }

    public void devolverLibro() {
        if (cantidadDisponible < cantidadTotal) {
            cantidadDisponible++;
        }
    }
}