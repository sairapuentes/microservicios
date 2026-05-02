package com.sairapuentes.productos.dto;

public class ProductoRequest {
    private int idCategoria;
    private String nombreProducto;
    private double precioCompra;
    private double ivaCompra;

    public ProductoRequest(ProductoRequest request) {
    }

    public ProductoRequest(int idCategoria, String nombreProducto, double precioCompra, double ivaCompra) {
        this.idCategoria = idCategoria;
        this.nombreProducto = nombreProducto;
        this.precioCompra = precioCompra;
        this.ivaCompra = ivaCompra;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
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

    public double getIvaCompra() {
        return ivaCompra;
    }

    public void setIvaCompra(double ivaCompra) {
        this.ivaCompra = ivaCompra;
    }
}