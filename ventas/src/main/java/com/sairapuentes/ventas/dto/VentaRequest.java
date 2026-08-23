package com.sairapuentes.ventas.dto;

import jakarta.validation.constraints.Positive;

public class VentaRequest {
    private int idVenta;
    private int idCliente;
    private int idSede;
    private int idProducto;
    @Positive
    private int cantidad;

    public VentaRequest() {
    }

    public VentaRequest(int idVenta, int idCliente, int idSede, int idProducto, int cantidad) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.idSede = idSede;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
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
}
