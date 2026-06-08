package com.sairapuentes.inventario.dto;

import jakarta.persistence.Column;

public class InventarioRequest {
    private int idProducto;
    private int idSede;
    private int cantidad;

    public InventarioRequest(){

    }

    public InventarioRequest(int idProducto,int idSede,int cantidad) {
        this.idProducto = idProducto;
        this.idSede = idSede;
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
