package com.sairapuentes.ventas.communication;

public class InventarioRestarRequest {
    private int idProducto;
    private int idSede;
    private int cantidad;

    public InventarioRestarRequest() {
    }

    public InventarioRestarRequest(int idProducto,int idSede,int cantidad) {
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
