package com.sairapuentes.login.dto;

import jakarta.persistence.Column;

public class UsuarioResponse {
    private int idUsuario;
    private String nombreUsuario;
    private String correo;

    public UsuarioResponse() {
    }

    public UsuarioResponse(int idUsuario,String nombreUsuario, String correo) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
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
}
