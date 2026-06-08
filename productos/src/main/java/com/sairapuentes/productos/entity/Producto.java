package com.sairapuentes.productos.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_producto")
    private int idProducto;
    @ManyToOne
    @JoinColumn(name = "ID_categoria")
    private Categoria categoria;
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

    public Producto(int idProducto,Categoria categoria, String nombreProducto, double precioCompra, double precioVenta, double ivaCompra) {
        this.idProducto = idProducto;
        this.categoria = categoria;
        this.nombreProducto = nombreProducto;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.ivaCompra = ivaCompra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
