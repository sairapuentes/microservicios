package com.sairapuentes.ventas.dto;
public class VentaResponse {
    private int idVenta;
    private int idCliente;
    private int idProducto;
    private int cantidad;
    private double valorTotal;
    private int idSede;
    private String nombreCliente;
    private String nombreProducto;
    private String nombreSede;

    public VentaResponse() {
    }

    public VentaResponse(int idVenta, int idCliente, int idProducto, int cantidad, double valorTotal, int idSede, String nombreCliente, String nombreProducto, String nombreSede) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.valorTotal = valorTotal;
        this.idSede = idSede;
        this.nombreProducto = nombreProducto;
        this.nombreSede = nombreSede;
        this.nombreCliente = nombreCliente;
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

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }
}
