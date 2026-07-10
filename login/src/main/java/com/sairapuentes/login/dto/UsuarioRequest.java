package com.sairapuentes.login.dto;

import jakarta.persistence.Column;

public class UsuarioRequest {
    private String nombreUsuario;
    private int cedula;
    private String correo;
    private String password;
    private int idRol;
    private int idSede;

    public UsuarioRequest() {
    }

    public UsuarioRequest(String nombreUsuario, int cedula, String correo, String password, int idRol, int idSede) {
        this.nombreUsuario = nombreUsuario;
        this.cedula = cedula;
        this.correo = correo;
        this.password = password;
        this.idRol = idRol;
        this.idSede = idSede;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }
}
