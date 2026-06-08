package com.sairapuentes.inventario.dto;

public class SedeResponse {
    private int idSede;
    private String nombreSede;
    private String direccion;
    private String ciudad;

    public SedeResponse() {
    }

    public SedeResponse(int idSede, String nombreSede, String direccion, String ciudad) {
        this.idSede = idSede;
        this.nombreSede = nombreSede;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
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
