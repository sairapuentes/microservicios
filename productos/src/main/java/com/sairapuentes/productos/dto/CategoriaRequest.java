package com.sairapuentes.productos.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoriaRequest {
    @NotBlank(message="El nombre es obligatorio")
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
