package com.sairapuentes.ventas.communication;

public class ProductoResponse {
    private int idProducto;
    private String nombreCategoria;
    private String nombreProducto;
    private double precioCompra;
    private double precioVenta;
    private double ivaCompra;

    public ProductoResponse() {
    }

    public ProductoResponse(int idProducto,String nombreCategoria, String nombreProducto, double precioVenta, double precioCompra,double ivaCompra) {
        this.idProducto = idProducto;
        this.nombreCategoria = nombreCategoria;
        this.nombreProducto = nombreProducto;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.ivaCompra = ivaCompra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getIvaCompra() {
        return ivaCompra;
    }

    public void setIvaCompra(double ivaCompra) {
        this.ivaCompra = ivaCompra;
    }
}
