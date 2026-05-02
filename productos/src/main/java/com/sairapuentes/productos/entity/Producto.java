package com.sairapuentes.productos.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_producto")
    private int idProducto;
    @Column(name="ID_categoria", nullable = false)
    private int idCategoria;
    @Column(name="nombre_producto", nullable = false, length=100)
    private String nombreProducto;
    @Column(name="precio_compra")
    private double precioCompra;
    @Column(name="precio_venta")
    private double precioVenta;
    @Column(name="iva_compra")
    private double ivaCompra;

    public Producto() {

        super();
    }

    public Producto(int idProducto,int idCategoria, String nombreProducto, double precioCompra, double precioVenta, double ivaCompra) {
        this.idProducto = idProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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
