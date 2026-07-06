package com.sairapuentes.login.dto;

import jakarta.persistence.Column;

public class UsuarioRequest {
    private String nombreUsuario;
    private int cedula;
    private String correo;
    private String contraseña;

    public UsuarioRequest() {
    }

    public UsuarioRequest(String nombreUsuario, int cedula, String correo, String contraseña) {
        this.nombreUsuario = nombreUsuario;
        this.cedula = cedula;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
