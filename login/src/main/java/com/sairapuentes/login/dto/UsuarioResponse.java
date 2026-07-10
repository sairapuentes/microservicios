package com.sairapuentes.login.dto;

import jakarta.persistence.Column;

public class UsuarioResponse {
    private int idUsuario;
    private String nombreUsuario;
    private String correo;
    private String nombreRol;
    private int idSede;

    public UsuarioResponse() {
    }

    public UsuarioResponse(int idUsuario,String nombreUsuario, String correo, String nombreRol, int idSede) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.nombreRol = nombreRol;
        this.idSede = idSede;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }
}
