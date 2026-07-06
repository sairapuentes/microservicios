package com.sairapuentes.ventas.dto;

import jakarta.validation.constraints.Positive;

public class VentaRequest {
    private int idVenta;
    private int idCliente;
    private int idCiudad;
    private int idProducto;
    @Positive
    private int cantidad;
    private int idSede;

    public VentaRequest() {
    }

    public VentaRequest(int idVenta, int idCliente, int idCiudad, int idProducto, int cantidad,int idSede) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.idCiudad = idCiudad;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.idSede = idSede;
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

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
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
}
