package com.sairapuentes.login.dto;

public class LoginResponse {
    //private String token;
    private int idUsuario;
    private String nombreUsuario;
    private String correo;
    private String nombreRol;
    private int idSede;
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(int idUsuario, String nombreUsuario, String correo, String nombreRol, int idSede, String token) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correo =correo;
        this.nombreRol = nombreRol;
        this.idSede = idSede;
        this.token =token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
