package com.sairapuentes.inventario.dto;

public class SedeRequest {
    private String nombreSede;
    private String direccion;
    private String ciudad;

    public SedeRequest() {
    }

    public SedeRequest(String nombreSede, String direccion, String ciudad) {
        this.nombreSede = nombreSede;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
