package com.sairapuentes.login.dto;

public class RolRequest {
    private String nombreRol;

    public RolRequest() {
    }

    public RolRequest(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
