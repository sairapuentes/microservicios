package com.sairapuentes.ventas.communication;

public class InventarioResponse {
    private int idInventario;
    private int idProducto;
    private int idSede;
    private int cantidad;
    private String nombreSede;

    public InventarioResponse() {
    }
    public InventarioResponse(int idInventario,int idProducto ,int idSede,int cantidad,String nombreSede) {
        this.idInventario = idInventario;
        this.idProducto = idProducto;
        this.idSede = idSede;
        this.cantidad = cantidad;
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

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }
}
