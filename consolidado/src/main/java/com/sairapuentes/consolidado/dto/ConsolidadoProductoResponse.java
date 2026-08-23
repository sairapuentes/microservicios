package com.sairapuentes.consolidado.dto;

public class ConsolidadoProductoResponse {
    private int idProducto;
    private String nombreProducto;
    private int totalUnidades;
    private double totalVendido;

    public ConsolidadoProductoResponse() {
    }

    public ConsolidadoProductoResponse(int idProducto, String nombreProducto, int totalUnidades, double totalVendido) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.totalUnidades = totalUnidades;
        this.totalVendido = totalVendido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getTotalUnidades() {
        return totalUnidades;
    }

    public void setTotalUnidades(int totalUnidades) {
        this.totalUnidades = totalUnidades;
    }

    public double getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(double totalVendido) {
        this.totalVendido = totalVendido;
    }
}
