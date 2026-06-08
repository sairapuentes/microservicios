package com.sairapuentes.productos.dto;

public class CategoriaRequest {
    private String nombreCategoria;

    public CategoriaRequest() {
    }

    public CategoriaRequest(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
