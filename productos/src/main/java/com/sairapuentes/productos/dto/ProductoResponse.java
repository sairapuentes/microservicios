package com.sairapuentes.productos.dto;

public class ProductoResponse {

    private int idProducto;
    private String nombreProducto;
    private double precioCompra;
    private double precioVenta;
    private double ivaCompra;

    public ProductoResponse() {
    }

    public ProductoResponse(int idProducto, String nombreProducto, double precioVenta, double precioCompra,double ivaCompra) {
        this.idProducto = idProducto;
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
    //Porque no es una lista de productos ya que el usuario puede comprar mas de un producto

    //respuesta de consulta del otro microservicios
//    private List<Producto> productoList;
//
//    public ProductoResponse() {
//    }
//
//    //Especifica que es una lista de respuesta
//    public ProductoResponse(List<Producto> productoList) {
//        this.productoList = productoList;
//    }
//
//    public List<Producto> getProductoList() {
//        return productoList;
//    }
//
//    public void setProductoList(List<Producto> productoList) {
//        this.productoList = productoList;
//    }
}
