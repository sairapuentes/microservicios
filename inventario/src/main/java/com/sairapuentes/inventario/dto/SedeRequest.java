package com.sairapuentes.inventario.dto;

import jakarta.validation.constraints.NotBlank;

public class SedeRequest {
    @NotBlank(message = "El nombre de la sede es obligatorio")
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
