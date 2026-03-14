package com.mycompany.proyecto1;

public class Usuario {
    
    private String id;
    private String nombre;
    private String usuario;
    private String contrasena;
    private String rol;
    private boolean activo;
    
    public Usuario(String id, String nombre, String usuario, String contrasena, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.activo = true;
    }

    public String obtenerId() {
        return id;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public String obtenerUsuario() {
        return usuario;
    }

    public String obtenerContrasena() {
        return contrasena;
    }

    public String obtenerRol() {
        return rol;
    }

    public boolean estaActivo() {
        return activo;
    }

    public void cambiarNombre(String nombre) {
        this.nombre = nombre;
    }

    public void cambiarUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void cambiarContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void cambiarRol(String rol) {
        this.rol = rol;
    }

    public void cambiarActivo(boolean activo) {
        this.activo = activo;
    }
}