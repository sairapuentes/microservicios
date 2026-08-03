package com.sairapuentes.inventario.dto;

public class InventarioResponse {
    private int idInventario;
    private int idProducto;
    private int cantidad;
    private int idSede;
    private String nombreSede;

    public InventarioResponse(){

    }

    public InventarioResponse(int idInventario,int idProducto ,int cantidad,int idSede ,String nombreSede) {
        this.idInventario = idInventario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.idSede = idSede;
        this.nombreSede = nombreSede;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
}
